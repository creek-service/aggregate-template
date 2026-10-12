/*
 * Copyright 2021-2026 Creek Contributors (https://github.com/creek-service)
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

package org.acme.example.services;

import static org.acme.example.internal.TopicDescriptors.KAFKA_FORMAT;
import static org.acme.example.internal.TopicDescriptors.inputTopic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.acme.example.api.ExampleAggregateDescriptor;
import org.acme.example.internal.TopicConfigBuilder;
import org.creekservice.api.kafka.metadata.topic.OwnedKafkaTopicInput;
import org.creekservice.api.kafka.metadata.topic.OwnedKafkaTopicOutput;
import org.creekservice.api.platform.metadata.ComponentInput;
import org.creekservice.api.platform.metadata.ComponentInternal;
import org.creekservice.api.platform.metadata.ComponentOutput;
import org.creekservice.api.platform.metadata.ServiceDescriptor;

public final class ExampleServiceDescriptor implements ServiceDescriptor {

    // formatting:off init:remove
    public static final OwnedKafkaTopicInput<String, Long> InputTopic =     // init:remove
            inputTopic(                                                     // init:remove
                    "input",                                                // init:remove
                    String.class,                                           // init:remove
                    KAFKA_FORMAT,                                           // init:remove
                    Long.class,                                             // init:remove
                    KAFKA_FORMAT,                                           // init:remove
                    TopicConfigBuilder.withPartitions(3));                  // init:remove
                                                                            // init:remove
    public static final OwnedKafkaTopicOutput<Long, String> OutputTopic =   // init:remove
            ExampleAggregateDescriptor.OutputTopic;                         // init:remove
    // formatting:on  init:remove

    public ExampleServiceDescriptor() {}

    @Override
    public String dockerImage() {
        return "ghcr.io/creek-service/aggregate-template-example-service";
    }
                                                    // init:remove
    @Override                                       // init:remove
    public Collection<ComponentInput> inputs() {    // init:remove
        return List.of(InputTopic);                 // init:remove
    }                                               // init:remove
                                                    // init:remove
    @Override                                       // init:remove
    public Collection<ComponentOutput> outputs() {  // init:remove
        return List.of(OutputTopic);                // init:remove
    }                                               // init:remove
}
