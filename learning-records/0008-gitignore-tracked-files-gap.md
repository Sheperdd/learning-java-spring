# `.gitignore` doesn't untrack files: still a gap after two sessions

Shane reliably recalls "rotate the secret first, then clean up" (D0, and again in the 01 M1 wrap-up). Twice now, though, he hasn't recalled that `.gitignore` only affects *untracked* files, or that `git rm --cached` is the command that stops tracking a committed file. In the quiz he said "it's tracked because of the previous commit", which is the right instinct without the rule or the command.

**Evidence:** D0 wrap-up Q2; 01 M1 wrap-up Q3.
**Implications:** this replaces 0002's "check again in 05/D5". Bring it up sooner: have him *do* `git rm --cached` on a throwaway file in a branch during D2, because a hands-on exercise sticks better than a third explanation.
