---
title: Service Modules
permalink: /structure/service
layout: single
toc: true
snippet_comment_prefix: "//"
---

Each service module added via the [add service module GitHub workflow][addService] is created in its own
directory a.k.a. module, with its descriptor created in the [services module][services].

The workflow creates a bare [Kafka Streams][kafkaStreams] based microservice that utilises the [Creek Kafka][creekKafka]
service and test extensions to simplify development and testing.

Each service module has the following structure:

## Entry Point

The entry point to the service is defined in the `ServiceMain` class. 
The entry point initialised Creek, [builds a Kafka Streams topology](#topology-builder) and executes it.

{% highlight java %}
{% include_snippet entry-point from ../example-service/src/main/java/org/acme/example/service/ServiceMain.java %}
{% endhighlight %}

**ProTip:** The application will terminate if the Kafka Streams application fails.
{: .notice--info}

## Kafka Streams topology

### Topology Builder

The Kafka Streams topology is built by the `TopologyBuilder` class. A bare implementation is provided.

See the [Basic Kafka Streams Tutorial][tutorial] to learn how to create a simple topology, 
and the [Kafka Streams][kafkaStreams] documentation for more info.

**ProTip:** Break more complex topologies up into sub topology builders, utilising the `Name` instance 
to reduce the change of node-name clashes. See `Name` Javadoc to learn more.
{: .notice--info}

### Testing the topology

The Service's Kafka Streams topology can be unit tested via the `TopologyBuilderTest` class. 
A bare implementation is provided.

The test class includes an existing test called `shouldNotChangeTheTopologyUnintentionally`.
The intent of this test is to detect any unintentional changes to the Kafka Streams topology.

**Warning:** There are certain categories of topology changes that are not backwards compatible with earlier versions
of a deployed service, e.g. those that change topic names.
Creek recommends always naming operators in the Kafka Streams DSL. (See the [Kafka Streams docs][kafkaStreams] for more information).
{: .notice--warning}

The test compares the topology with the last know topology and fails if they differ.
If the change is intentional, then the `reverse-service/src/test/resources/kafka/streams/expected_topology.txt`
file can be updated to reflect the latest topology.

If you find this test more of a hindrance than a help... delete it! :smile:

See the [Basic Kafka Streams Tutorial][tutorial] to learn how test a simple topology,
and the [Kafka Streams test util][ksTestUtil] documentation for more info.

## Docker

A `Dockerfile` is included in the root of each service's directory and the `build.gradle.kts` file
defines several Docker related tasks. Combined, these controls how the Docker image for the service is built
and published.

**ProTip:** Images built on developer machines that have the [AttachMe][attachMe] plugin installed,
will be built with the AttachMe agent in the `/opt/creek/agent` to enable Docker container debugging. 
Images built on CI build servers, which should be used for Production releases, will not have the agent installed. 
{: .notice--info}

## include directory

The `include` directory defines the `run.sh` script referenced by the services `Dockerfile`, and other configuration,
such as a `log4j2.xml` file to configure service logging.

## Backwards compatability

Because each service is encapsulated within a Docker image, there is no backwards compatibility concerns with changing
the API of classes within the module. 

Care should be taken when changing Kafka Stream topologies to ensure they remain backwards compatible with any
existing deployment.

**Warning:** There are certain categories of topology changes that are not backwards compatible with earlier versions
of a deployed service, e.g. those that change topic names.
Creek recommends always naming operators in the Kafka Streams DSL. (See the [Kafka Streams docs][kafkaStreams] for more information).
{: .notice--warning}

[kafkaStreams]: https://kafka.apache.org/documentation/streams/
[ksTestUtil]: https://kafka.apache.org/documentation/streams/developer-guide/testing.html
[creekKafka]: https://github.com/creek-service/creek-kafka
[tutorial]: https://www.creekservice.org/basic-kafka-streams-demo/
[attachMe]: https://plugins.jetbrains.com/plugin/13263-attachme
[addService]: {{ "/add-service" | relative_url }}
[services]: {{ "/structure/services" | relative_url }}

[todo]: add page on JPMS and how to remove it.