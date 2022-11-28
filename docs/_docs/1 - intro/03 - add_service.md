---
title: Adding a service
permalink: /add-service
layout: single
---

<link rel="stylesheet" href="{{ '/assets/css/main.css' | relative_url }}">

Aggregates contain one or more services. Each service is contained in its own directory and Gradle subproject.

## Adding services

Services can be added using a GitHub workflow at any time. Foolow:

1. To add a service, navigate to the `Actions` tab in your GitHub repository.
2. Select `Add service module` from the list of available actions on the left
3. Click the `Run workflow ▾` button and enter the name of your new service.
   <figure>
     <img src="{{ '/assets/images/creek-add-service.png' | relative_url }}" alt="Create new aggregate repo">
   </figure>

   **Note:** Service names must be lowercase. Only alphanumerics and dashes supported.
   {: .notice--warning}

   **ProTip:** End your service names with `-service` to make it clear the module contains a microservice.
   {: .notice--info}
4. Click [Run workflow](){: .btn .btn--small .btn--disabled .btn--success} button below.

This will kick off a workflow that adds the new service module, though you may need to refresh the page to view it.
The workflow will add a module container the boilerplate code for a new service.

Once the workflow is complete, it's ready for you to [build your Kafka Streams Topology][topology] 
and add [system tests][systemTests].

[topology]: {{ "/structure/service#topology-builder" | relative_url }}
[systemTests]: {{ "/structure/system-tests" | relative_url }}

