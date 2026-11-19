---
name: review-code
description: Teacher-style review of code Shane wrote in this learning repo. Wraps /review (standards + spec), /ponytail-review (over-engineering) and /security-review, then turns findings into what/why/fix lessons with hints before answers. Use when the user says "review my code", "check my work", "is this right", or /review-code.
argument-hint: "[branch, commit, or file — default: current branch vs main]"
---

# Teaching code review

The goal is that Shane **learns to spot the issue next time**, not just that the code gets fixed.

## 1. Gather findings
- Target: the argument. By default, the current branch diff against `main` (`git diff main...HEAD`), or uncommitted changes if he's on `main`.
- Run `/review` against that point:
  - **Standards** are the Java / Spring / delivery baselines in the root `CLAUDE.md`.
  - **Spec** is the milestone's GitHub issue or the README milestone.
- Run `/ponytail-review` on the same diff, for over-engineering.
- If the diff touches auth, input handling, money, SQL, secrets or CI workflows, also run `/security-review`. This is a bank; say why it matters.

## 2. Teach the findings
- Rank the findings: correctness and security, then design, then style. Show **at most 5**; mention that there are more if so.
- Present each finding like this:
  - **What:** `file:line`, and what's wrong, in one sentence.
  - **Why it matters:** the concrete failure or cost, tied to production or banking where it applies.
  - **Hint:** a question or nudge that lets Shane find the fix himself.
- Give the full fix only after he tries, or if he asks.
- Call out **what he did well** too, especially a best practice used correctly without prompting. That is evidence for a learning record (note it for `/wrap-up`).

## 3. After fixes
- Re-check only the changed lines.
- If a finding exposed a misconception (not a typo), note it for a learning record.
