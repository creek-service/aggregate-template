---
title: Versioning
permalink: /versioning
layout: single
---

Versioning of build artefacts is handled by the [Axion-release Gradle plugin][axion], applied in the root `build.gradle.kts`
file. 

The plugin uses [Semantic Versioning][semVer], with version numbers in the form `<Majar>.<Minor>.<Patch>`, for example:
`2.0.13`.  Version numbers are stored as tags in Git.

## Unique version per build

By default, each commit to the repository will trigger a [CI GitHub workflow][buildYml], 
which will increment the patch version and build the project.

This ensures that each code change is matched by a version number change, making it very easy to trace back a 
service's version number to its code.

## Setting the next version number

The [Version GitHub workflow][versionYml] can be used to manual set the version number the next build will pick up:

1. Navigate to the `Actions` tab in your GitHub repository.
2. Select `Set next version` from the list of available actions on the left.
3. Click the `Run workflow ▾` button and either enter the version part to increment, (`Marjor`, `Minor` or `Patch`),
   or enter a complete version number to set, e.g. `1.3.19`
   <figure>
     <img src="{{ '/assets/images/creek-set-version-workflow.png' | relative_url }}" alt="Create new aggregate repo">
   </figure>
4. Click [Run workflow](){: .btn .btn--small .btn--disabled .btn--success} button below.

This will kick off a workflow that sets the new version umber, though you may need to refresh the page to view it.
Wait for the workflow to complete before committing any new code.

[axion]: https://github.com/allegro/axion-release-plugin
[semVer]: https://semver.org/
[buildYml]: https://github.com/creek-service/aggregate-template/blob/main/.github/workflows/build.yml
[versionYml]: https://github.com/creek-service/aggregate-template/blob/main/.github/workflows/version.yml