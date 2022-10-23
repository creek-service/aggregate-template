[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Coverage Status](https://coveralls.io/repos/github/creek-service/aggregate-template/badge.svg?branch=main)](https://coveralls.io/github/creek-service/aggregate-template?branch=main)
[![build](https://github.com/creek-service/aggregate-template/actions/workflows/gradle.yml/badge.svg)](https://github.com/creek-service/aggregate-template/actions/workflows/gradle.yml)
[![CodeQL](https://github.com/creek-service/aggregate-template/actions/workflows/codeql.yml/badge.svg)](https://github.com/creek-service/aggregate-template/actions/workflows/codeql.yml)

# Aggregate template

This repository is intended as a template for anyone wanting to use Creek when writing microservices.
It is not a requirement that Creek based microservices use this template. 
However, the template sets up a lot of boilerplate code for you. 
Organisations and individuals wanting to build their microservice echo system using Creek may want to
create their own aggregate template, using this template as a starting point.

## Modules

The template comes with the following modules:

* **[api](api)**: defines the public api of the aggregate, i.e. the service descriptor and associated types.
* **[services](services)**: defines all the services in the aggregate, i.e. service descriptors and their associated types.
* **[example-service](example-service)**: an example Kafka Streams microservice.

## Gradle Configuration

The template sets up the following, though you can add and remove things to your hearts content once you've created
your repo from this template:

* Multi-module Gradle Java project, including:
  * Code formatting by [Spotless][1]
  * Static code analysis by [Spotbugs][2] and [Checkstyle][3]
  * Release versioning by the [Axion-release-plugin][4]
  * Code coverage analysis by [Jacoco][5]
  * Code coverage tracking by [Coveralls.io][6]
  * Default set of test dependencies:
    * [Unit5][7]
    * [Mockito][8]
    * [Hamcrest][9]
    * [Guava TestLib][10]
    * [Log4J 2.x][11]
* [GitHub build workflow][12], including:
  * Gradle build
  * [Coveralls.io][6] reporting
  * Automatic incrementing of patch version number
  * Jars published to GitHub Package Repository
* GitHub code owners and PR template.

### Gradle commands

* `./gradlew` should be the go-to local command to run when developing.
              It will run `./gradlew format`, `./gradlew static` and `./gradlew check`.
* `./gradlew format` will format the code using [Spotless][1].
* `./gradlew static` will run static code analysis, i.e. [Spotbugs][2] and [Checkstyle][3].
* `./gradlew check` will run all checks and tests.
* `./gradlew coverage` will generate a cross-module [Jacoco][5] coverage report.

## Creating a new repo from the template

Obviously, you're free to customise any repos you generate from this template as you see fit.
There will be features set up on this project, e.g. using [Coveralls.io][6] for reporting code coverage,
that just don't fit with how you're doing things. Just remove any bits you don't want or need.
Many are here simply because that's how Creek does things internally. 

Taking into account any features you don't need, you can also run through the following list of steps to
finish initializing your new repo:

1. Click the "Use this template" button on the main page and follow the instructions to clone the repo to your account.
2. Initialise the new repo:
   1. Run the `init.sh` script to initialize the repo:
        Note: if on OS X, then first run `brew install gnu-sed` and update the path as indicated in the output 'caveats'.
        
        ```shell
        ./init.sh
        ``` 
   2. Replace/remove this and other README.md!
   3. Commit changes as a PR
3. Customise services:
   1. The `init.sh` will have renamed the example service, 
      now duplicate the module to create any other services you need.
      One module for each service this aggregate will contain.
   2. Add new modules to `settings.grable.kts`. 
   3. Search for `ChangeMe` comments, following the instructions.
   4. Commit changes as a PR

[1]: https://github.com/diffplug/spotless
[2]: https://spotbugs.github.io/
[3]: https://checkstyle.sourceforge.io/
[4]: https://github.com/allegro/axion-release-plugin
[5]: https://www.jacoco.org/jacoco/trunk/doc/
[6]: https://coveralls.io/
[7]: https://junit.org/junit5/docs/current/user-guide/
[8]: https://site.mockito.org/
[9]: http://hamcrest.org/JavaHamcrest/index
[10]: https://github.com/google/guava/tree/master/guava-testlib
[11]: https://logging.apache.org/log4j/2.x/
[12]: .github/workflows/gradle.yml