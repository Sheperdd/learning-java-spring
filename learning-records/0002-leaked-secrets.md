# A committed secret lives in history: rotate first, clean up second

Shane worked out on his own that deleting a committed `.env` in a later commit doesn't remove it, because the earlier snapshot still holds it. He also applied the "rotate first, then clean up" order to a new scenario. Gap he didn't spot: `.gitignore` has no effect on files that are already tracked (`git rm --cached` is needed). This was taught in the D0 wrap-up, so check it again when secrets come up in 05/D5.

**Evidence:** D0 `.gitignore` question and wrap-up Q2.
