---
title: Unit Testing
permalink: /features/unit-testing
description: Learn about the Kafka Streams topology unit testing the Creek aggregate template sets up for you
layout: single
---

The repository comes preconfigured with the following test dependencies to allow the quick authoring of unit tests:

 * [JUnit5 framework][junit] for writing unit tests.
 * [Mockito][mockito] for quickly mocking types and verifying invocations.
 * [Hamcrest][hamcrest] for more descriptive assertions.
 * [Guava TestLib][guavaTest] which contains many useful testing utilities.
 * [Log4J 2.x][log4j] with SLF4J bindings, to allow test code to log.

In addition, each microservice [added][addService] to the repository comes with a bare-bones `TopologyBuilderTest` class,
which can be enhanced to test the service's Kafka Streams topology, making use of:

 * [Kafka Streams test utils][ksTest]
 * [Creek Kafka Streams test util][creekKsTest]

[junit]: https://junit.org/junit5/docs/current/user-guide/
[mockito]: https://site.mockito.org/
[hamcrest]: http://hamcrest.org/JavaHamcrest/index
[guavaTest]: https://github.com/google/guava/tree/master/guava-testlib
[log4j]: https://logging.apache.org/log4j/2.x/
[addService]: {{ "/add-service" | relative_url }}
[ksTest]: https://kafka.apache.org/documentation/streams/developer-guide/testing.html
[creekKsTest]: https://github.com/creek-service/creek-kafka/tree/main/streams-test