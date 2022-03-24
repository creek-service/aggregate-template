# Aggregate ids

Defines type safe wrappers around simple id types.
* This jar is shared with other aggregates.
* To avoid dependency hell in production it should be pretty much dependency free.
* The types in this jar can be used by downstream services and can be part of their api,
  i.e. a downstream `api` jar *can* depend on this `id` jar.

## Backwards compatability

Because downstream `api` jars can use types in this module any changes to this module _must_ be binary compatible,
otherwise dependency hell can ensue...