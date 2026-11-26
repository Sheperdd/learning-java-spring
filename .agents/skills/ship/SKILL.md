---
name: ship
description: Walk Shane through shipping the current milestone the professional way (branch → commits → PR → CI → merge → deploy), teaching each git/gh step while he types the commands. Use when the user says "ship it", "push this", "open a PR", "deploy", or /ship.
---

# Ship a milestone

Shane is learning how code reaches prod. **He types every command.** Before each step, explain what it does and why professionals do it this way. Run commands yourself only for read-only checks, or if he says "just do it".

Check `ROADMAP.md` for which delivery-track items are done. If a step's item isn't done yet, skip it with one line: "CI comes in D2, so for now we merge without checks."

## Steps
1. **Where am I?**
   - `git status` and `git branch --show-current`.
   - On `main` with changes: stop, and teach why work belongs on a branch (`git switch -c feat/NN-<slug>`).
2. **Ready to ship?**
   - `./mvnw verify` passes locally.
   - Offer `/review-code` if it hasn't been run on this work.
3. **Commit:**
   - Review `git diff` together and stage deliberately (`git add -p` explained; no `git add .` without looking).
   - Shane writes the Conventional Commit message, and Codex critiques it.
   - Split into several commits if the diff mixes concerns.
   - Make sure there are no secrets, `target/` or IDE files.
4. **Push and open the PR:**
   - `git push -u origin <branch>` (explain `-u`).
   - `gh pr create`. The body follows `.github/pull_request_template.md` and links `Closes #N`.
5. **CI** (D2+):
   - `gh pr checks --watch`.
   - On failure: `gh run view --log-failed`, then teach how to read the log. Shane fixes it, pushes again, and CI re-runs.
6. **Self-review on GitHub:**
   - Read the PR's "Files changed" as a reviewer would.
   - Explain what reviewers at a bank look for.
7. **Merge:**
   - `gh pr merge --squash --delete-branch` (explain why squash and why delete).
   - Then `git switch main` and `git pull`.
8. **Deploy** (D4+):
   - Watch the CD run.
   - Hit the deployed `/actuator/health`.
   - Explain what a rollback would look like if it failed.
9. **Release** (D5+, when the milestone finishes the project): tag the release (`gh release create`).

Finish by suggesting `/wrap-up` if this closed a milestone.
