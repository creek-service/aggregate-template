---
title: Code formatting
permalink: /features/formatting
description: Learn about the code formatting the Creek aggregate template sets up for you
layout: single
---

The repository uses the [Spotless][spotless] Gradle plugin to standardise code formatting and thereby
minimise PR sizes and code churn due to formatting differences.

The default [CI GitHub workflow][buildYml] will fail if code does not adhere to the required standard.

## Formatting code

Format local code changes before submission by running:

```
./gradlew format
```

## Customising formatting

Spotless is configured in the [Creek common build convention][commonConvention], which can be customised as needed.

[spotless]: https://github.com/diffplug/spotless
[buildYml]: https://github.com/creek-service/aggregate-template/blob/main/.github/workflows/build.yml
[commonConvention]: https://github.com/creek-service/aggregate-template/blob/main/buildSrc/src/main/kotlin/creek-common-convention.gradle.kts