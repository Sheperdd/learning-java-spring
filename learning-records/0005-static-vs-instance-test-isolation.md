# `static` is shared and instance fields are per object (misconception corrected)

Shane thought a `static` repository field would give each test a fresh repository. That was corrected: `static` means one copy per class, and JUnit 5 creates a new test-class instance per `@Test`, so a plain instance field is fresh for every test. He then used an instance field correctly. He hasn't yet explained the idea back unprompted.

**Evidence:** `AccountRepositoryTest` design question in 01 M1.
**Implications:** milestone 5 (singleton scope) builds directly on this. A singleton bean is "the `static` case" for the whole app. Ask him to make that link himself before explaining it.
