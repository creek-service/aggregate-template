---
title: JSON payloads
permalink: /features/json-payloads
description: Configure schema-validated JSON Kafka payloads in the aggregate template.
layout: single
---

The template supports schema-validated JSON Kafka payloads. The overloads of
`TopicDescriptors.inputTopic`, `outputTopic`, `internalTopic` and `creatableInternalTopic` that
do not take serialization formats use a Kafka-native key and a JSON value. To use another format,
call an overload with explicit key and value `SerializationFormat`s (for example,
`TopicDescriptors.KAFKA_FORMAT`). The example descriptors currently use explicit Kafka-native
formats, so they do not generate JSON schemas out of the box.

## Define a payload

Put payload types shared with other aggregates in `api`; types only used within this aggregate can
live in `services`. Annotate each JSON payload type with
`org.creekservice.api.base.annotation.schema.GeneratesSchema`. Jackson annotations and Swagger
annotations are also available to describe the payload's JSON representation and schema.
Register a topic descriptor using the payload class as its key or value type and a JSON-format overload.
The default JSON topic descriptors associate their values with a JSON schema in the `default`
schema registry.

For example, define a payload in `api` and use the default-format overload in an aggregate
descriptor:

```java
@GeneratesSchema
public record Order(long id) {}

outputTopic("orders", Long.class, Order.class, TopicConfigBuilder.withPartitions(2));
```

The overload above uses a Kafka-native `Long` key and a schema-validated JSON `Order` value.

The `org.creekservice.schema.json` Gradle plugin is applied in both `api` and `services`. It scans
each module for annotated types and generates their schemas. Run
`./gradlew :api:generateJsonSchema :services:generateJsonSchema` to generate schemas explicitly;
`./gradlew check` also runs the generation tasks as part of the build. The generated resources are
under each module's `build/generated/resources/schema/main` directory. Scanning is restricted to
the module itself by the `typeScanning` and `subTypeScanning` module allowlists in its
`build.gradle.kts`; if you move a payload between modules, generate schemas in its new module.

## Build and runtime dependencies

Both `api` and `services` declare Creek's schema annotation, Jackson annotations and Swagger
annotations as `compileOnlyApi` dependencies, with matching `requires static` directives in their
`module-info.java` files. This makes the annotations available at compile time without adding
them as runtime dependencies of the metadata jars. If you add another annotation library to a
modular payload, update that module's dependencies and module descriptor too.

Service applications use `creek-kafka-json-serde` to serialize JSON values and validate them
against schemas at runtime. The template's `example-service` declares this dependency and
`requires creek.kafka.serde.json.schema` in its module descriptor. Keep both when using JSON in a
service; add them to any additional service module that needs JSON payloads. The annotation
dependencies alone do not provide a runtime serde.

If your aggregate does not use JSON payloads, remove the JSON schema plugin, its configuration,
the JSON annotation dependencies and `requires static` directives from `api` and `services`.
Also remove the JSON serde dependency and module requirement from service applications, and use
explicit non-JSON serialization formats in your topic descriptors. The relevant build and module
entries are marked with `Remove if not using JSON` comments.
