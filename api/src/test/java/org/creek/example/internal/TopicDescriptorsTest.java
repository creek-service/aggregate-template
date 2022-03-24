/*
 * Copyright 2021-2022 Creek Contributors (https://github.com/creek-service)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.creek.example.internal;

import static org.creek.example.internal.TopicConfigBuilder.withPartitions;
import static org.creek.example.internal.TopicDescriptors.KAFKA_FORMAT;
import static org.creek.example.internal.TopicDescriptors.creatableInternalTopic;
import static org.creek.example.internal.TopicDescriptors.inputTopic;
import static org.creek.example.internal.TopicDescriptors.internalTopic;
import static org.creek.example.internal.TopicDescriptors.outputTopic;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Mockito.when;

import org.creek.api.kafka.metadata.CreatableKafkaTopicInternal;
import org.creek.api.kafka.metadata.KafkaTopicConfig;
import org.creek.api.kafka.metadata.KafkaTopicInternal;
import org.creek.api.kafka.metadata.OwnedKafkaTopicInput;
import org.creek.api.kafka.metadata.OwnedKafkaTopicOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TopicDescriptorsTest {

    private static final KafkaTopicConfig CONFIG = withPartitions(1).build();

    @Mock private TopicConfigBuilder config;

    @BeforeEach
    void setUp() {
        when(config.build()).thenReturn(CONFIG);
    }

    @Test
    void shouldCreateInputTopic() {
        // When:
        final OwnedKafkaTopicInput<Long, String> topic =
                inputTopic("name", Long.class, String.class, config);

        // Then:
        assertThat(topic.name(), is("name"));
        assertThat(topic.key().format(), is(KAFKA_FORMAT));
        assertThat(topic.key().type(), is(Long.class));
        assertThat(topic.value().format(), is(KAFKA_FORMAT));
        assertThat(topic.value().type(), is(String.class));
        assertThat(topic.config(), is(sameInstance(CONFIG)));
    }

    @Test
    void shouldCreateInternalTopic() {
        // When:
        final KafkaTopicInternal<Long, String> topic =
                internalTopic("name", Long.class, String.class);

        // Then:
        assertThat(topic.name(), is("name"));
        assertThat(topic.key().format(), is(KAFKA_FORMAT));
        assertThat(topic.key().type(), is(Long.class));
        assertThat(topic.value().format(), is(KAFKA_FORMAT));
        assertThat(topic.value().type(), is(String.class));
    }

    @Test
    void shouldCreateCreatableInternalTopic() {
        // When:
        final CreatableKafkaTopicInternal<Long, String> topic =
                creatableInternalTopic("name", Long.class, String.class, config);

        // Then:
        assertThat(topic.name(), is("name"));
        assertThat(topic.key().format(), is(KAFKA_FORMAT));
        assertThat(topic.key().type(), is(Long.class));
        assertThat(topic.value().format(), is(KAFKA_FORMAT));
        assertThat(topic.value().type(), is(String.class));
        assertThat(topic.config(), is(sameInstance(CONFIG)));
    }

    @Test
    void shouldOutputTopic() {
        // When:
        final OwnedKafkaTopicOutput<Long, String> topic =
                outputTopic("name", Long.class, String.class, config);

        // Then:
        assertThat(topic.name(), is("name"));
        assertThat(topic.key().format(), is(KAFKA_FORMAT));
        assertThat(topic.key().type(), is(Long.class));
        assertThat(topic.value().format(), is(KAFKA_FORMAT));
        assertThat(topic.value().type(), is(String.class));
        assertThat(topic.config(), is(sameInstance(CONFIG)));
    }
}
