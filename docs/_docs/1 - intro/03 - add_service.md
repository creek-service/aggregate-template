---
title: Adding a service
permalink: /add-service
description: Learn how the Creek aggregate template makes adding new services to repositories / aggregates as simple as running a GitHub workflow 
layout: single
---

Repositories created from the aggregate-template have a GitHub workflow for adding new microservices
to the repository. This makes adding the boilerplate code for new services as simple as running a workflow. 
An aggregate repository can one or more services. 
Each service is contained in its own directory and Gradle subproject.

## Adding services

Services can be added using a [GitHub workflow][addServiceWf] at any time. Follow:

1. To add a service, navigate to the `Actions` tab in your GitHub repository.
2. Select `Add service module` from the list of available actions on the left
   {% include figure image_path="/assets/images/creek-add-service-workflow.png" alt="Add new service module workflow" %}
3. Click the `Run workflow ▾` button and enter the name of your new service.
   {% include figure image_path="/assets/images/creek-add-service.png" alt="Add service module" %}

   **Note:** Service names must be lowercase. Only alphanumerics and dashes supported.
   {: .notice--warning}

   **ProTip:** End your service names with `-service` to make it clear the module contains a microservice.
   {: .notice--info}
4. Click the [Run workflow](){: .btn .btn--small .btn--disabled .btn--success} button below the service name you entered.

This will kick off a workflow that adds the new module containing the boilerplate code for a new service, 
though you may need to refresh the page to view it.

{% include figure image_path="/assets/images/creek-add-service-workflow-running.png" alt="Running workflow" %}

Once the workflow is complete, it's ready for you to [build your Kafka Streams Topology][topology] 
and add [system tests][systemTests].

**Note:** At the time of writing, the add-service GitHub workflow attempts to commit changes to the main branch.
This is not compatible with GitHub's branch protection. 
Temporarily remove any branch protection before running the workflow.
[Issue 141](https://github.com/creek-service/aggregate-template/issues/141) tracks the work of enhancing the workflow
to be compatible with branch protection.
{: .notice--warning}

[addServiceWf]: https://github.com/creek-service/aggregate-template/blob/main/.github/workflows/add-service.yml
[topology]: {{ "/structure/service#topology-builder" | relative_url }}
[systemTests]: {{ "/structure/system-tests" | relative_url }}
