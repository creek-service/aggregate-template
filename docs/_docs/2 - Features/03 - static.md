---
title: Static code analysis
permalink: /features/static
description: Learn about the static code analysis the Creek aggregate template sets up for you
layout: single
---

The repository come preconfigured for static code analysis by [Spotbugs][spotbugs], [Checkstyle][checkstyle] and [CodeQl][codeQL].

## Spotbugs and Checkstyle

[Spotbugs][spotbugs] and [Checkstyle][checkstyle] can be run locally by running:

```
./gradlew static
```

...and also run as part of the [CI GitHub workflow][buildYml]. 

They are configured in the [Creek common build convention][commonConvention], which can be customised as required.

## CodeQl

[CodeQl][codeQL] runs against each PR. It runs as a [GitHub workflow][codeqlYml]. 

[spotbugs]: https://spotbugs.github.io/
[checkstyle]: https://checkstyle.sourceforge.io/
[codeQL]: https://codeql.github.com/
[buildYml]: https://github.com/creek-service/aggregate-template/blob/main/.github/workflows/build.yml
[codeqlYml]: https://github.com/creek-service/aggregate-template/blob/main/.github/workflows/codeql.yml
[commonConvention]: https://github.com/creek-service/aggregate-template/blob/main/buildSrc/src/main/kotlin/creek-common-convention.gradle.kts