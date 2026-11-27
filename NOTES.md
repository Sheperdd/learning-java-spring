# Teaching notes

Shane's preferences and working notes. Update this whenever he states a preference about how he wants to be taught.

- The goal is an EQ Bank software engineering internship, so tie lessons to real banking/fintech practice where it fits.
- New to Git, GitHub and CI/CD: has never pushed code to prod. Explain every git/gh command before he runs it.
- Wants best practices taught with the reasoning: what / why here / how.
- Default output style is Learning: he writes the meaningful code, guided by `TODO(human)`.
- **Terminal is Git Bash only, never PowerShell.** All commands are in bash syntax; env vars go in `~/.bashrc`.
- He prefers the GitHub web UI for *writing* issues and PR descriptions. Offer `gh ... --web` or the web steps alongside the CLI command.
- He can't scroll far back in long chats. When a question refers to an earlier answer, restate it briefly instead of saying "as in Q4", and keep decisions in files (README, CONTEXT.md).
- `git log` dropped him into the pager and he got stuck. Prefer `git log --oneline -N` in instructions.
- He sometimes hands off repetitive or prose work: "write the remaining tests", "create the commit message", "do the glossary/README yourself". Do it, then point out what to read closely. Commit messages are still a skill he's practising, so nudge him to write the next one himself (see LR-0013).
- Environment: Windows 11, Docker Desktop, gh CLI.
  - Java 25 LTS is being installed (Temurin, via winget).
  - Oracle Java 23 is also installed, and its `javapath` entry is on the system PATH. `~/.bashrc` pins `JAVA_HOME` so Java 25 wins.
- From 01 M4 on he may bundle code and wrap-up docs into one PR instead of a separate `docs:` PR. That's fine for a solo learning repo; keep the commits separate (`docs:` commit for the wrap-up).
- He uses the "just write it" escape hatch for test files ("write the test file, then explain why"). Write them, explain them, and don't log them as evidence of understanding.
- Sometimes asks to skip the `/wrap-up` quiz and have Claude write the wrap-up files. Respect it, but open the next session with one retrieval question on the skipped topic (e.g. `isSameAs` vs `isEqualTo`).
