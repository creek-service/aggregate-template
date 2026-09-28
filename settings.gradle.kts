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

// Temporary, until Creek 0.5.0 is released - remove once the plugins block in build.gradle.kts
// no longer pins a -SNAPSHOT version:
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
        // Public, unauthenticated repo Creek publishes SNAPSHOTs of every library & plugin to on
        // every push to main:
        maven {
            url = uri("https://central.sonatype.com/repository/maven-snapshots/")
        }
    }
}

rootProject.name = "aggregate-template"

include(
    "api",
    "example-service",  // init:remove
    "services",
    "system-tests"
)
