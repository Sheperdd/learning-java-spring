# Commit messages: writes them himself now; type names still slip

In 01 M3, Shane wrote every commit message himself, as LR-0013 asked. Subjects stayed short, and every one used the sub-project scope `01-ioc-basics`. The PR title was a clean `feat(01-ioc-basics): ...`, which matters because squash-merge makes it the commit on `main`. One slip: `tests(...)` instead of the Conventional Commits type `test`.

**Status:** supersedes [[0013-commit-messages-d1-followup]].
**Evidence:** PR #7 commits `2f5f318`, `5ad8a74`, `eb0374c`, and the PR title.
**Implications:** stop checking length and scope. Only flag a wrong type (`test`, `refactor`, `docs`, `ci`). Next step: a body that explains *why* on non-obvious commits.
