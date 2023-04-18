/*
 * Copyright 2023 Creek Contributors (https://github.com/creek-service)
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
    java
    jacoco
    `common-convention` apply false
    `module-convention` apply false
    `coverage-convention`
    `publishing-convention` apply false
    id("pl.allegro.tech.build.axion-release") version "1.15.0" // https://plugins.gradle.org/plugin/pl.allegro.tech.build.axion-release
    id("com.bmuschko.docker-remote-api") version "9.3.1" apply false
}

project.version = scmVersion.version

allprojects {
    tasks.jar {
        onlyIf { sourceSets.main.get().allSource.files.isNotEmpty() }
    }
}

subprojects {
    project.version = project.parent?.version!!

    apply(plugin = "common-convention")
    apply(plugin = "module-convention")

    if (!name.startsWith("test-")) {
        apply(plugin = "jacoco")
    }

    // Only publish the API module, as this is the only module other repos should need.
    if (name == "api") {
        apply(plugin = "publishing-convention")
    }

    extra.apply {
        set("creekVersion", "0.4.0")            // https://mvnrepository.com/artifact/org.creekservice
        set("kafkaVersion", "3.4.0")            // https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients
        set("spotBugsVersion", "4.4.2")         // https://mvnrepository.com/artifact/com.github.spotbugs/spotbugs-annotations
        set("guavaVersion", "31.1-jre")         // https://mvnrepository.com/artifact/com.google.guava/guava
        set("log4jVersion", "2.20.0")           // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core

        set("junitVersion", "5.9.2")            // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
        set("junitPioneerVersion", "2.0.1")     // https://mvnrepository.com/artifact/org.junit-pioneer/junit-pioneer
        set("mockitoVersion", "5.3.0")          // https://mvnrepository.com/artifact/org.mockito/mockito-junit-jupiter
        set("hamcrestVersion", "2.2")           // https://mvnrepository.com/artifact/org.hamcrest/hamcrest-core
    }

    val creekVersion : String by extra
    val guavaVersion : String by extra
    val log4jVersion : String by extra
    val kafkaVersion : String by extra
    val junitVersion: String by extra
    val junitPioneerVersion: String by extra
    val mockitoVersion: String by extra
    val hamcrestVersion : String by extra

    dependencies {
        testImplementation("org.creekservice:creek-test-hamcrest:$creekVersion")
        testImplementation("org.creekservice:creek-test-util:$creekVersion")
        testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
        testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
        testImplementation("org.junit-pioneer:junit-pioneer:$junitPioneerVersion")
        testImplementation("org.mockito:mockito-junit-jupiter:$mockitoVersion")
        testImplementation("org.hamcrest:hamcrest-core:$hamcrestVersion")
        testImplementation("com.google.guava:guava-testlib:$guavaVersion")
        testImplementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
        testImplementation("org.apache.logging.log4j:log4j-slf4j2-impl:$log4jVersion")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    }

    configurations.all {
        resolutionStrategy.eachDependency {
            if (requested.group == "org.apache.kafka") {
                // Need a known Kafka version for module patching to work:
                useVersion(kafkaVersion)
            }
        }
    }
}

defaultTasks("format", "static", "check")
