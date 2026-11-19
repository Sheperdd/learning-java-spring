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
