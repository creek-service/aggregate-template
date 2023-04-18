---
title: Adding a service
permalink: /add-service
description: Learn how the Creek aggregate template makes adding new services to repositories / aggregates as simple as running a GitHub workflow 
layout: single
toc: true
---

Repositories created from the aggregate-template have a script and GitHub workflow for adding new microservices
to the repository. This makes adding the boilerplate code for new services as simple as running a script or a workflow. 

An aggregate repository can contain one or more services. Each service is contained in its own directory and Gradle subproject.

## Modes of operation

There are four modes of operation, each with pros & cons. Three modes are provided by the 
[`add service` GitHub workflow <i class="fas fa-external-link-alt"></i>][addServiceWf]{:target="_blank"}, 
while the last involves running in underlying script directly.

| Mode                                                                    | Compatible with branch protection | Runs checks in PR | Requires permissions to allow actions to open PRs | 
|-------------------------------------------------------------------------|-----------------------------------|-------------------|---------------------------------------------------|
| [Commit directly via workflow](#commit-directly-via-workflow)           | No                                | -                 | No                                                |
| [Raise PR without PAT via Workflow](#raise-pr-without-pat-via-workflow) | Yes                               | No                | Yes                                               |
| [Raise PR with PAT via Workflow](#raise-pr-with-pat-via-workflow)       | Yes                               | Yes               | Yes                                               |
| [Run script locally](#run-script-locally)                               | Yes                               | Yes               | No                                                |

### Commit directly via workflow

**Note:** Committing straight to the main branch is incompatible with some [branch protection rules <i class="fas fa-external-link-alt"></i>][ghBranchProtection]{:target="_blank"}.
There is currently [no way around this <i class="fas fa-external-link-alt"></i>][ghBypassBrandProtection]{:target="_blank"}.
Users wanting to commit straight into the main branch will need to temporarily disable branch protection before running the workflow.
{: .notice--warning}

To commit directly to the main branch using the [`add service` GitHub workflow <i class="fas fa-external-link-alt"></i>][addServiceWf]{:target="_blank"}:

1. Ensure any incompatible branch protection rules are temporarily disabled for the `main` branch. 
2. Navigate to the `Actions` tab in your GitHub repository.
3. Select `Add service module` from the list of available actions on the left
   {% include figure image_path="/assets/images/creek-add-service-workflow.png" alt="Add new service module workflow" %}
4. Click the `Run workflow ▾` button and enter the name of your new service.

   {% include figure image_path="/assets/images/creek-add-service-direct.png" alt="Add service module" %}

   **Note:** Service names must be lowercase. Only alphanumerics and dashes supported.
   {: .notice--warning}

   **ProTip:** End your service names with `-service` to make it clear the module contains a microservice.
   {: .notice--info}
5. Click the [Run workflow](){: .btn .btn--small .btn--disabled .btn--success} button below the service name you entered.

This will kick off a workflow that adds a new module containing the boilerplate code for a new service,
though you may need to refresh the page to view it.

{% include figure image_path="/assets/images/creek-add-service-workflow-running.png" alt="Running workflow" %}

Once the workflow is complete, the new service is ready for you to [build your Kafka Streams Topology][topology] 
and add [system tests][systemTests].

### Raise PR without PAT via Workflow

**Note:** By default, any PR raised from a workflow will **not** run any checks, e.g. CI checks.
This is a [known and deliberate security feature <i class="fas fa-external-link-alt"></i>][ghNoPrChecks]{:target="_blank"}.
It is recommended that users wanting to use PRs to add services should [use a PAT](#raise-pr-with-pat-via-workflow).
{: .notice--warning}

**Note:** Before using the workflow to raise a PR, the repository or organisation must be [configured to allow GitHub actions
to open PRs](#allow-actions-to-open-pr).
{: .notice--warning}

To raise a PR, without a PAT, using the [`add service` GitHub workflow <i class="fas fa-external-link-alt"></i>][addServiceWf]{:target="_blank"}:

1. Navigate to the `Actions` tab in your GitHub repository.
2. Select `Add service module` from the list of available actions on the left
   {% include figure image_path="/assets/images/creek-add-service-workflow.png" alt="Add new service module workflow" %}
3. Click the `Run workflow ▾` button, tick the `Raise as PR` option, and enter the name of your new service.
   {% include figure image_path="/assets/images/creek-add-service-pr.png" alt="Add service module" %}
 
   **Note:** Service names must be lowercase. Only alphanumerics and dashes supported.
   {: .notice--warning}

   **ProTip:** End your service names with `-service` to make it clear the module contains a microservice.
   {: .notice--info}
4. Click the [Run workflow](){: .btn .btn--small .btn--disabled .btn--success} button below the service name you entered.

This will kick off a workflow that adds a new module containing the boilerplate code for a new service,
though you may need to refresh the page to view it.

{% include figure image_path="/assets/images/creek-add-service-workflow-running.png" alt="Running workflow" %}

The workflow will create a PR containing the new service's module.

{% include figure image_path="/assets/images/creek-new-service-pr.png" alt="Pull Request adding new service" %}

Once the workflow is complete, review and merge the PR.
Once merged, the new service is ready for you to [build your Kafka Streams Topology][topology] and add [system tests][systemTests].

#### Allow actions to open PR


For the workflow to be able to raise a PR, you must explicitly allow GitHub Actions to create pull requests.
This setting can be found in a repository's settings under `Actions` > `General` > `Workflow permissions`.

For repositories belonging to an organization, this setting can be managed by admins
in organization settings under `Actions` > `General` > `Workflow permissions`.

In both cases, tick the box to `Allow GitHub Actions to create and approve pull requests`, and `Save` your changes.

{% include figure image_path="/assets/images/creek-allow-workflows-to-create-prs.png" alt="Enable actions opening PRs" %}

### Raise PR with PAT via Workflow

By default, PRs created by the workflow will not have any checks run, e.g. the check to ensure it builds.
This is a [known and deliberate security feature <i class="fas fa-external-link-alt"></i>][ghNoPrChecks]{:target="_blank"}.

However, it is possible to modify the `.github/workflows/add-service.yml` workflow file to use a [Personal Access Token <i class="fas fa-external-link-alt"></i>][ghPAT]{:target="_blank"}.
The PAT will require `write` permissions on `pull-requests`.  The workflow file includes details of what needs to change.

As the workflow runs under the users account or organisation, it is not possible for Creek to set up the PAT. 
Therefore, it is recommended that users wanting to use PRs should set up an PAT and update `add-service.yml` to use it.
It is recommended that users clone the aggregate template repository and to make the changes there.

Once the changes have been made, the workflow can be used [as above](#raise-pr-without-pat-via-workflow). 

### Run script locally

An alternative to running the workflow is to invoke the underlying script directly, locally, and then either
raise a PR containing the changes or push them directly to the main branch.

**Note:** The script requires [zsh <i class="fas fa-external-link-alt"></i>][zsh]{:target="_blank"} to be installed. 
{: .notice--warning}

The script is `./.creek/add_service.sh` and takes a single argument: the name of the new service to add. For example:

```
./.creek/add_service.sh "acme-service"
```

**Note:** Service names must be lowercase. Only alphanumerics and dashes supported.
{: .notice--warning}

**ProTip:** End your service names with `-service` to make it clear the module contains a microservice.
{: .notice--info}

[ghBranchProtection]: https://docs.github.com/en/repositories/configuring-branches-and-merges-in-your-repository/defining-the-mergeability-of-pull-requests/about-protected-branches
[ghBypassBrandProtection]: https://github.com/orgs/community/discussions/13836
[ghNoPrChecks]: https://github.com/orgs/community/discussions/25602
[ghPAT]: https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token
[zsh]: https://zsh.sourceforge.io/
[addServiceWf]: https://github.com/creek-service/aggregate-template/blob/main/.github/workflows/add-service.yml
[topology]: {{ "/structure/service#topology-builder" | relative_url }}
[systemTests]: {{ "/structure/system-tests" | relative_url }}
