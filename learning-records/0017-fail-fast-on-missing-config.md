# Fail fast on missing config when the setting is a business rule

Asked what should happen if `bank.fees.policy` isn't set, Shane chose "no default, the app fails to start" over `matchIfMissing`. His reason: in a bank, whether a fee is charged is "a concrete decision", so the app must not guess. That is the core of fail-fast config. A guessed default is wrong either way (overcharging, or quietly losing fee revenue), and a startup failure is caught before customers see it.

**Evidence:** 01 M4 design question. It's documented in the body of commit `7ee9651`. The by-hand check (comment out the property and watch startup fail) was set as an exercise, but its output wasn't shared.
**Implications:** build on this in project 05. `@ConfigurationProperties` with `@Validated` is the typed version of this idea, and readiness probes / the D4 health-check gate are where a failed startup gets caught in deployment. Ask him to contrast it with settings where a default *is* safe (timeouts, page sizes). A test that locks in the startup failure was offered and not written yet.
