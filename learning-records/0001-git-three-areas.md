# Git's three areas: a commit snapshots the staging area

Shane can use the working tree → staging area → repository model: he staged a single file for a focused commit, and he knew that an edit made after `git add` is left out of the commit and still shows as modified. He started out thinking "a commit is a snapshot at the moment it's created"; that was corrected to "a commit snapshots the staging area, which `git add` filled at add time." Watch for the same slip when partial staging (`git add -p`) comes up.

**Evidence:** D0 wrap-up quiz, Q1 and Q3.
**Implications:** D1 can start with branches and PRs, not re-teach add/commit. He still checks `git status` *after* committing rather than before, so keep reinforcing "check the staging area before you commit". He needed correcting on Conventional Commits: chose `feat` for a setup commit, and wrote `doc`/past tense. Check his commit messages in D1.
