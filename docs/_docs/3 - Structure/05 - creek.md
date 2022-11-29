---
title: .creek Directory
permalink: /structure/.creek
layout: single
---

The `.creek` directory in repositories created from the template contains the template code needed to add new
service modules to the repository, and a script to add them.  However, it is recommended that new services are 
added to the repository using the [Add Service GitHub Workflow][addService].

In the source `aggregate-template` repository there are also scripts for [bootstrapping a new repository][bootstrapSh] 
and [cleaning up][cleanUpSh] some template specific code.  These can be useful when creating a new template repository
from this template.

[bootstrapSh]: https://github.com/creek-service/aggregate-template/blob/main/.creek/bootstrap.sh
[cleanUpSh]: https://github.com/creek-service/aggregate-template/blob/main/.creek/clean_up.sh
[addService]: {{ "/add-service" | relative_url }}
