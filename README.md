[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Coverage Status](https://coveralls.io/repos/github/creek-service/aggregate-template/badge.svg?branch=main)](https://coveralls.io/github/creek-service/aggregate-template?branch=main)
[![build](https://github.com/creek-service/aggregate-template/actions/workflows/build.yml/badge.svg)](https://github.com/creek-service/aggregate-template/actions/workflows/build.yml)
[![CodeQL](https://github.com/creek-service/aggregate-template/actions/workflows/codeql.yml/badge.svg)](https://github.com/creek-service/aggregate-template/actions/workflows/codeql.yml)

# Creek aggregate repo starter

Quickly bootstrap new aggregate repositories and get up and developing Creek microservices.

Click the [Use this template][useThisTemplate] to create a new repository from this template.

See the [docs](https://www.creekservice.org/aggregate-template) for more information.









* A bare [Kafka Streams][kafkaSteams] microservice, utilising [Creek](https://www.creekservice.org).



   
* The following [GitHub build workflow][12]:
    * [Add service]
    * [CI build](.github/workflows/build.yml)
    * [CodeQl][codeQL] [build](.github/workflows/codeql.yml)
    * [Workflow to manually set the next version](.github/workflows/version.yml)

The template comes with the following modules:

* **[api](api)**: defines the public api of the aggregate, i.e. the service descriptor and associated types.
* **[services](services)**: defines all the services in the aggregate, i.e. service descriptors and their associated types.
* **[example-service](example-service)**: an example Kafka Streams microservice.


1. Repo settings:
    1. General
        1. disable wiki
        2. enable discussions
        3. only allow squash merging
        4. allow auto-merge
        5. auto delete branches
    2. Branches
        1. Protect main branch
            1. Require PR
                1. Require approval
                2. Dismiss stale
            2. Require status checks
                1. CodeQL
                2. build
                3. build_pages
    3. Pages
        1. Build from actions
        2. enforce https

### Gradle commands





[12]: .github/workflows/build.yml

[kafkaSteams]: https://kafka.apache.org/documentation/streams/

[ghPackages]:
[useThisTemplate]: https://github.com/creek-service/aggregate-template/generate
[ghSecret]: https://docs.github.com/en/codespaces/managing-codespaces-for-your-organization/managing-encrypted-secrets-for-your-repository-and-organization-for-github-codespaces