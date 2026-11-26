# Java Spring + Delivery Glossary

The canonical terms for this workspace, covering Spring, Java, git and CI/CD. A term is added only once Shane can use it correctly (see `/wrap-up`).

## Terms
### Git

**Commit**:
A permanent snapshot of the **staging area**. It records an author, a message and its parent commit, and it is identified by a hash.
_Avoid_: save, version

**Staging area**:
The proposed next **commit**: the version of every tracked file that `git commit` will snapshot. `git add` updates it.
_Avoid_: index (git's internal name, fine to recognise), cache

### GitHub

**Pull request**:
A request to merge a branch into `main`, shown as a reviewable diff with discussion. Every change in this workspace lands through one.
_Avoid_: PR is fine as an abbreviation; avoid "merge request" (GitLab's name)

**Squash merge**:
Merging a **pull request** by combining all its **commits** into a single new **commit** on `main`, whose message is the PR title.
_Avoid_: squash commit (that's the result, not the action)

### Java

**Immutable object**:
An object whose state can't change after it's constructed. To "change" it, you build a new one (e.g. a record and its `withBalance` copy).
_Avoid_: read-only, constant (a `final` field holding a mutable object is not immutable)
