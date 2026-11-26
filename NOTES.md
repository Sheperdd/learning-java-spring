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
- Environment: Windows 11, Docker Desktop, gh CLI.
  - Java 25 LTS is being installed (Temurin, via winget).
  - Oracle Java 23 is also installed, and its `javapath` entry is on the system PATH. `~/.bashrc` pins `JAVA_HOME` so Java 25 wins.
