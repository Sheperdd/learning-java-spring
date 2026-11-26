---
status: superseded by LR-0013
---

# Conventional Commits: types and mood are now reliable; subjects still need tightening

Across the first PR (4 commits), Shane picked the right type every time (`chore`, `docs`, `feat`), used the imperative mood, and added scopes after a single prompt. These were all D0 slips (see 0001). The remaining habits: an overlong first subject that listed three things ("X, Y and Z" belongs in the body), and "update" for a file that was new. He checked `git status` *before* committing this time.

**Evidence:** PR #2 commits and their critiques.
**Implications:** stop critiquing type and mood. Next, focus on subject accuracy and using the body for *why*. The PR title as the squash-commit message is understood.
