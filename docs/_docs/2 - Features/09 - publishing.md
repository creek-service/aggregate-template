---
title: Publishing
permalink: /features/publishing
description: Learn about the Java library and Docker image publishing the Creek aggregate template sets up for you
layout: single
toc: true
---

By default, the repository will publish: 
 * The [api][api] jar to the GitHub Packages' [Gradle Registry][ghGradleReg], to make it available to other aggregates.
   No other jars will be published, as these should be considered internal to the aggregate.
 * Each service's Docker image to GitHub Packages' [Container Registry][ghContainerReg], to make it available for deployment.
   
   **Note:** Deployment of the Docker images to an environment is outside the scope of this tutorial.
   {: .notice--warning}

## Publishing jars

Jars are published by running the `publish` Gradle task:

```
./gradlew publish
```

This is executed as part of the [CI GitHub workflow][buildYml] when code is pushed into the `main` branch.

### Customising jar publishing

Publishing is configured in the [Creek publishing build convention][publishingConvention], which can be customised as needed.
For example, if you wanted to publish jars to a different location.

## Publishing Docker images

Docker images are published by running the `pushAppImage` Grade task, built using the [com.bmuschko.docker-remote-api][dockerPlugin] Gradle plugin:

```
./gradlew pushAppImage
```

**Note:** You must be authenticated with the GitHub [Container Registry][ghContainerReg] to push images. 
This is best left to the [CI GitHub workflow][buildYml].
{: .notice--warning}

### Customising Docker image push

Takes for building and pushing Docker images are in each service's `build.gradle.kts` file, which can be customised as needed.

In addition, the Docker image name for each service is also defined in the service's descriptor.

**Note:** The Docker image name in a service's `build.gradle.kts` and descriptor _must_ be kept in sync.
{: .notice--warning}

[ghGradleReg]: https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry
[ghContainerReg]: https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-container-registry
[publishingConvention]: https://github.com/creek-service/aggregate-template/blob/main/buildSrc/src/main/kotlin/creek-publishing-convention.gradle.kts
[buildYml]: https://github.com/creek-service/aggregate-template/blob/main/.github/workflows/build.yml
[dockerPlugin]: https://plugins.gradle.org/plugin/com.bmuschko.docker-remote-api
[api]: {{ "/structure/api" | relative_url }}
