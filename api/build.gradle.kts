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
    // Generates JSON schemas from @GeneratesSchema-annotated types. Remove this plugin, and the
    // creek.schema.json block below, if this aggregate doesn't use JSON payloads:
    id("org.creekservice.schema.json")
}

val kafkaVersion: String by extra
val creekVersion : String by extra
val jacksonVersion : String by extra

dependencies {
    api("org.creekservice:creek-kafka-metadata:$creekVersion")
    // Provides @GeneratesSchema, used to annotate topic value types so Creek can generate their
    // JSON schema. Remove if not using JSON payloads:
    implementation("org.creekservice:creek-base-annotation:$creekVersion")
    // Used to annotate topic value types so Creek can generate their JSON schema. Remove if not
    // using JSON payloads:
    api("com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion")

    // To avoid dependency hell downstream, avoid adding any more dependencies except Creek metadata jars and test dependencies.

    testImplementation("org.apache.kafka:kafka-clients:$kafkaVersion")

    // Remove if not using JSON payloads:
    jsonSchemaGenerator("org.creekservice:creek-json-schema-generator:$creekVersion")
}

// Remove if not using JSON payloads:
creek.schema.json {
    typeScanning.moduleWhiteList(moduleName)
    subTypeScanning.moduleWhiteList(moduleName)
}