---
name: wrap-up
description: End-of-session/milestone wrap-up for this learning repo — retrieval quiz, learning records, glossary and README updates, ponytail debt check, roadmap ticks, commit suggestion. Use when the user says "wrap up", "done for today", "end session", or /wrap-up.
---

# Wrap up

All teaching-workspace files live at the **repo root** (see the root `AGENTS.md`), never in the sub-project.

1. **Retrieval quiz.**
   - Ask 2–3 questions about today's concepts, one at a time, and wait for each answer. Make them recall or apply questions, not yes/no.
   - Include **one question from an earlier session** (spacing; pick it from `learning-records/`).
   - Give feedback on each answer: what's right, what's missing, and a pointer to the source.
2. **Learning records.**
   - Follow the `/teach` skill's LEARNING-RECORD-FORMAT (`~/.Codex/skills/teach/LEARNING-RECORD-FORMAT.md`).
   - Write a record in `learning-records/NNNN-slug.md` **only** when Shane showed real understanding, disclosed prior knowledge, or had a misconception corrected. Covering a topic doesn't count.
   - Mark records that this one replaces as superseded.
3. **Glossary.** Add terms he can now use correctly to `GLOSSARY.md` (GLOSSARY-FORMAT). Ask him to define the term first, then tighten his wording.
4. **Project README.** Add a few bullets under "What I learned", **in his words** (ask him, then edit lightly).
5. **Debt.**
   - Run `/ponytail-debt` on the sub-project.
   - If it finds `ponytail:` shortcuts that a later roadmap project will fix, note them in that project's README milestones.
6. **Roadmap.** Tick off finished milestones, projects and delivery-track items in `ROADMAP.md`. Add any project ideas that came up to "Ideas parking lot".
7. **Notes.** If he expressed a teaching preference, add it to `NOTES.md`.
8. **Commit.**
   - If the workspace files changed, suggest he commit them on a branch (`docs: wrap-up <topic>`).
   - Before D1 he can commit to `main`; from D1 on, they go through a PR via `/ship`.
9. **Next time.** One line: what the next session should start with.
