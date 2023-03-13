---
title: Aggregate Repository
permalink: /
layout: single
header:
  overlay_color: "#000"
  overlay_filter: "0.5"
  overlay_image: /assets/images/background-1.png
excerpt: Quickly bootstrap a new repository that's preconfigured for rapid development & testing of Kafka Streams based microservices.
---

The [aggregate-template][aggTemp] repository is a GitHub template repository that can be used to quickly boostrap 
new aggregate repositories, avoiding the manual repository and project setup.

[<i class="fab fa-fw fa-github"/>&nbsp; View on GitHub][aggTemp]{: .btn .btn--success}

**ProTip:** An _aggregate_ is simply a logical grouping of services that provide a business function 
via a defined api. i.e. An aggregate is a level of abstraction above a single microservice.
This is also known as a ['Bounded Context' in DDD nomenclature][bcDDD].
{: .notice--info}

**ProTip:** Large organisations may choose to create additional layers of abstraction by creating aggregates of aggregates.
{: .notice--info}

**ProTip:**
Organisations will probably want to create their own template repository from this template, which contains
additional organisation-specific customisations.
{: .notice--info}

[aggTemp]: https://github.com/creek-service/aggregate-template
[bcDDD]: https://martinfowler.com/bliki/BoundedContext.html