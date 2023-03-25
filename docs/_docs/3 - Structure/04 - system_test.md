---
title: System Tests Module
permalink: /structure/system-tests
description: Learn about aggregate wide system testing that Creek provides
layout: single
---

The `system-test` module is the place to write aggregate-wide system tests, i.e. system tests that test the 
functionality of the aggregate as a whole. 

The module comes preconfigured with the [org.creekservice.system.test][sysTestPlugin] Gradle plugin,
which adds tasks for running the tests:

```
./gradlew systemTest
```

...and configured with the [Creek Kafka System Test Extension][creekKafkaTestExt], which extends the system tests with 
Kafka topics support. System tests can seed and input records to topics and expect records output to other topics.
See the [Creek Kafka System Test Extension][creekKafkaTestExt] docs for more info, or the [System tests][tutorialSysTest]
section of the [Basic Kafka Streams Tutorial][tutorial] for a quick example. 

[system-test]: https://github.com/creek-service/creek-system-test
[sysTestPlugin]: https://github.com/creek-service/creek-system-test-gradle-plugin
[creekKafkaTestExt]: https://www.creekservice.org/creek-kafka/#system-test-extension
[tutorial]: https://www.creekservice.org/basic-kafka-streams-demo/
[tutorialSysTest]: https://www.creekservice.org/basic-kafka-streams-demo/system-testing