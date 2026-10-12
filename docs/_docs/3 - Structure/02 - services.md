---
title: Services Module
permalink: /structure/services
description: Learn about the structure and features of the 'services' module, which defines metadata for each microservice defined in the repository / aggregate
layout: single
---

The `services` module contains the service descriptor of each service within the aggregate.

**ProTip:** A service descriptor define metadata about a service, including the name of the service's Docker image
and the external resources the service interacts with, for example a Kafka topic, or a Database.
{: .notice--info}

The `services` jar is used by the system tests to discover services in the aggregate. 
To avoid dependency hell when running system tests it should be pretty much dependency free.

Payload types used only within this aggregate can live in `services`. Like `api`, this module
generates JSON schemas for its `@GeneratesSchema`-annotated types. See [JSON payloads][jsonPayloads]
for the schema generation and annotation setup.

## Backwards compatability

Because the jar is not shared outside the aggregate, there is no backwards compatibility concerns.

[jsonPayloads]: {{ "/features/json-payloads" | relative_url }}
