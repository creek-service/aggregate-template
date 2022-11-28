---
title: Publishing
permalink: /publishing
layout: single
---

By default, the repository will publish its [api][api] jar to [GitHub Packages][ghPackages], to make it available
to other aggregates. No other jars will be published, as these should be internal to the aggregate.

## Customising publishing

Publishing is configured in the [Creek publishing build convention][publishingConvention], which can be customised as needed.
For example, publishing jars to a different location.

todo



When you first publish a package, the default visibility is private. To change the visibility or set access permissions, see "Configuring a package's access control and visibility."


note: you will also need to publish your Docker containers somewhere............


[ghPackages]: https://docs.github.com/en/packages/learn-github-packages/introduction-to-github-packages
[publishingConvention]: https://github.com/creek-service/aggregate-template/blob/main/buildSrc/src/main/kotlin/creek-publishing-convention.gradle.kts
[api]: {{ "/api" | relative_url }}
