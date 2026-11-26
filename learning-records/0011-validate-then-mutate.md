# Validate everything, then mutate: how all-or-nothing works without transactions

Shane ordered the transfer rules himself (parameter checks → lookups → currency → funds → save) and said the save step has to come last. He named "validate before mutating" as his main lesson in PR #5.

**Evidence:** rule-ordering question in 01 M2, and the PR #5 description.
**Implications:** in project 03, start `@Transactional` from here. Ask him what breaks when the *second* save fails after the first one succeeded. That's the case this ordering can't protect against.
