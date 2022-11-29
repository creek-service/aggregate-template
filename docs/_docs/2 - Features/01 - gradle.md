---
title: Gradle
permalink: /features/gradle
layout: single
---

The repository used the [Gradle][gradle] build tool. 

Gradle does not need to be installed on the local machine.
Simple running `./gradlew` will automatically download all required files.

## Gradle commands

The following, non-exhaustive list, of Gradle commands should provide a good starting point for developing with Gradle:

* `./gradlew format` will format the code using [Spotless][spotless].
* `./gradlew static` will run static code analysis, i.e. [Spotbugs][2] and [Checkstyle][3].
* `./gradlew check` will run all checks and tests.
* `./gradlew coverage` will generate a cross-module [Jacoco][5] coverage report.
* `./gradlew cV` to log out the repo's current version.
  
**ProTip:** Simply running `./gradlew` without any commands will run the default commands, which are
`format`, `static` and `check`.
{: .notice--info}

[gradle]: https://gradle.org/
[spotless]: https://github.com/diffplug/spotless
[2]: https://spotbugs.github.io/
[3]: https://checkstyle.sourceforge.io/
[4]: https://github.com/allegro/axion-release-plugin