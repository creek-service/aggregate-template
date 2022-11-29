---
title: API module
permalink: /structure/api
layout: single
---

The `api` module defines the public API of the aggregate in the form of an `AggregateDescriptor`.

An `AggregateDescriptor` is similar to the `ServiceDescriptor` used in the [services][services] module.
However, rather than defining metadata about a service, its defining metadata about the aggregate as a whole,
including the public API of the aggregate.  

For example, an aggregate may commonly define the Kafka topics the aggregate is responsible for maintaining.

Other aggregates can reference the descriptor to access the metadata about the public resources the aggregate exposes.

**Note:** The types in this jar can be used by other aggregates, but should not be part of their api,  
i.e. another aggregate's `api` jar should *not* depend on this aggregates `api` jar, 
as this leads to [dependency hell][dependencyHell].
{: .notice--warning}

[todo]: link to aggregate linking tutorial

## Backwards compatability

This module's jar is shared with other aggregates, so care must be taken to maintain backwards compatability
and to avoid [dependency hell][dependencyHell].

To help avoid dependency hell, the api module is virtually dependency free, depending only on lightweight
metadata jars, such as [Creek Kafka's][creekKafkaMd].  
To minimise code sharing, metadata jars contain mainly interfaces defining the resource types
supported by their associated Creek extensions. 
The api module defines internal implementations of these interfaces.

Because other aggregates should not expose types in this API in their own API, there is no need for binary
compatability when making changes. However, any changes should be backwards compatible and taking into
account the compatibility of the serialization format being used.

[creekKafkaMd]: https://github.com/creek-service/creek-kafka/tree/main/metadata
[dependencyHell]: https://en.wikipedia.org/wiki/Dependency_hell
[services]: {{ "/structure/services" | relative_url }}
