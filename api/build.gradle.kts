/*
 * Copyright 2023-2026 Creek Contributors (https://github.com/creek-service)
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

plugins {
    `java-library`
    // Generates JSON schemas from @GeneratesSchema-annotated types. Remove if not using JSON.
    id("org.creekservice.schema.json")
}

dependencies {
    api("org.creekservice:creek-kafka-metadata:${property("creekVersion")}")
    // Remove both if not using JSON payloads:
    implementation("org.creekservice:creek-base-annotation:${property("creekVersion")}")
    api("com.fasterxml.jackson.core:jackson-annotations:${property("jacksonVersion")}")

    // To avoid dependency hell downstream, avoid adding any more dependencies except Creek metadata jars and test dependencies.

    testImplementation("org.apache.kafka:kafka-clients:${property("kafkaVersion")}")

    // Remove if not using JSON:
    jsonSchemaGenerator("org.creekservice:creek-json-schema-generator:${property("creekVersion")}")
}

// Remove if not using JSON:
creek.schema.json {
    typeScanning.moduleWhiteList(moduleName)
    subTypeScanning.moduleWhiteList(moduleName)
}