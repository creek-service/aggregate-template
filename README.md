[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![codecov](https://codecov.io/gh/creek-service/aggregate-template/branch/main/graph/badge.svg)](https://codecov.io/gh/creek-service/aggregate-template)
[![build](https://github.com/creek-service/aggregate-template/actions/workflows/build.yml/badge.svg)](https://github.com/creek-service/aggregate-template/actions/workflows/build.yml)
[![CodeQL](https://github.com/creek-service/aggregate-template/actions/workflows/codeql.yml/badge.svg)](https://github.com/creek-service/aggregate-template/actions/workflows/codeql.yml)
[![OpenSSF Scorecard](https://api.securityscorecards.dev/projects/github.com/creek-service/aggregate-template/badge)](https://api.securityscorecards.dev/projects/github.com/creek-service/aggregate-template) <!--- init:remove --->

# Creek aggregate repo starter

Quickly bootstrap new aggregate repositories and quickly developing Creek microservices.

Click the [Use this template][useThisTemplate] to create a new repository from this template.

See the [docs](https://www.creekservice.org/aggregate-template) for more information.

## Using this template to create a Creek tutorial

If using this template to create a new Creek Tutorial, then there are some additional steps required:

1. Customise the repositories settings in GitHub:
   1. General
        1. disable wiki
        2. disable issues
        3. enable discussions
        4. disable projects 
        5. only allow squash merging
        6. allow auto-merge
        7. auto delete branches
   2. Branches
       1. Protect main branch
           1. Require PR
               1. Require approval
               2. Dismiss stale
           2. Require status checks
               1. CodeQL
               2. build
               3. build_pages
               4. codecov/patch
   3. Pages
       1. Build from actions
       2. enforce https
3. On the main page in GitHub, in about, set `Use your GitHub Pages website`.
4. Add the new tutorial to the [tutorials page][tutorials] on creekservice.org
5. Add the new tutorial sitemap.xml to the root aggregate sitemap.xml: todo

[useThisTemplate]: https://github.com/creek-service/aggregate-template/generate
[tutorials]: https://github.com/creek-service/creek-service.github.io/blob/main/_pages/tutorials.md
