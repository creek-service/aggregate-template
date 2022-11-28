---
title: Using the template
permalink: /bootstrap
layout: single
---

To create a new GitHub aggregate repository from the template, follow the steps below:

1. Click [<i class="fab fa-fw fa-github"/>&nbsp; Use this template][useTemplate]{: .btn .btn--success} 
   to `Create a new repository` and fill in the details:
   <figure>
     <img src="{{ '/assets/images/creek-create-new-from-agg-template.png' | relative_url }}" alt="Create new aggregate repo">
   </figure>

3. When GitHub creates the new repo, a [boostrap workflow][bootstrapWorkflow] will run to customise the new repository.
   Wait for this workflow to complete in the _Actions_ tab:

   <figure>
     <img src="{{ '/assets/images/creek-repo-bootstrap.png' | relative_url }}" alt="Wait for boostrap workflow">
   </figure>

4. [Clone the new repository][cloneRepo] locally.
5. Finish the initialisation of the repository by running the `clean_up.sh` script from the root of the repository:

   ```
   ./.creek/clean_up.sh
   ```

   This will clean up some scripts, GitHub workflows and stuff that are specific to the source `aggregate-template`.
6. Commit the changes back to the repository on GitHub:
   ```
   git add -A
   git commit -m "init script"
   git push
   ```

The repository is now ready to start developing a new microservice or services. 
See [Add a service][addService] for more info.

In addition, the new repository can be easily configured to publish [jars and Docker images][pubJars] 
and [code coverage][pubCoverage].


[useTemplate]: https://github.com/creek-service/aggregate-template/generate
[bootstrapWorkflow]: https://github.com/creek-service/aggregate-template/blob/main/.github/workflows/bootstrap.yml
[cloneRepo]: https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository
[addService]: {{ "/add-service" | relative_url }}
[pubJars]: {{ "/features/publishing" | relative_url }}
[pubCoverage]: {{ "/features/coverage" | relative_url }}
