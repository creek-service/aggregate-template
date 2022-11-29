---
title: Services Module
permalink: /structure/services
layout: single
---

The `services` module contains the service descriptor of each service within the aggregate.

**ProTip:** A service descriptor define metadata about a service, including the name of the service's Docker image
and the external resources the service interacts with, for example a Kafka topic, or a Database.
{: .notice--info}

The `services` jar is used by the system tests to discover services in the aggregate. 
To avoid dependency hell when running system tests it should be pretty much dependency free.

## Backwards compatability

Because the jar is not shared outside the aggregate, there is no backwards compatibility concerns.