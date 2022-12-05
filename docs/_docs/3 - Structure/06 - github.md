---
title: .github Directory
permalink: /structure/.github
layout: single
toc: true
---

The `.github` directory contains files to customise your GitHub experience.

## GitHub Dependabot 

The [dependabot.yml][dependabotYml] file configures the [GitHub Dependabot][ghDepBot] to periodically 
scan the repository and raise PRs to update dependencies when new versions are released.

## GitHub Workflows

The template repository defines the following [GitHub workflows][ghWfs] in new repositories:

### Add Service Workflow

The [Add new service workflow][addServiceWf] will add a new service to an existing repository created from the template.
See [addService] for more information.

### CI Build workflow

The [CI build workflow][buildWf] builds and tests the services in the aggregate, 
and publishes api jars and Docker images.

### CodeQL workflow

The [CodeCQ workflow][codeQlWf] runs [CodeQL analysis][codeQL] across the code.

### Set Version workflow 

The [Set Version workflow][setVerWf] allows the _major_, _minor_ or _patch_ number of the next build to be incremented,
or for the next version to be set to a specific version.


[ghWfs]: https://docs.github.com/en/actions/using-workflows
[addServiceWf]: https://github.com/creek-service/aggregate-template/blob/main/.github/workflows/add-service.yml
[buildWf]: https://github.com/creek-service/aggregate-template/blob/main/.github/workflows/build.yml
[codeQlWf]: https://github.com/creek-service/aggregate-template/blob/main/.github/workflows/codeql.yml
[setVerWf]: https://github.com/creek-service/aggregate-template/blob/main/.github/workflows/version.yml
[ghDepBot]: https://github.com/dependabot
[codeQL]: https://codeql.github.com/
[addService]: {{ "/add-service" | relative_url }}