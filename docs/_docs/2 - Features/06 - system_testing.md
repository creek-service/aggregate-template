---
title: System Testing
permalink: /features/system-testing
description: Learn about the black-box functional testing the Creek aggregate template sets up for you
layout: single
---

Creek system tests provide black box testing of your completed service, running in a Docker container.

The repository contains a `system-tests` subproject configured with the [Creek system-test Gradle plugin][sysTestGradle]
already applied, and preconfigured to re-run the system tests, and capture code coverage metrics, on any code changes. 

Learn more about [Creek system tests][systemTest].

## Executing system tests

System tests can be run with the following Gradle command:

```
./gradlew systemTest
```

Results of the system tests are written out to `system-tests/build/test-results/system-test` directory in an XML file
format similar to those output by unit test frameworks and compatible with most build servers.

## Debugging system tests

As system tests execute, services are started in Docker containers. The services, running in their Docker containers,
can be debugged by attaching the IntelliJ debugger. See the [Basic Kafka Streams Tutorial][tutorialDebug] for more info.

[sysTestGradle]: https://github.com/creek-service/creek-system-test-gradle-plugin
[systemTest]: https://github.com/creek-service/creek-system-test
[tutorialDebug]: https://www.creekservice.org/basic-kafka-streams-demo/debugging
[todo]: update above links to doc site once docs migrated.