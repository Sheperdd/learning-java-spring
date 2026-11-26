# A test must be able to fail, and it should check the promise, not the implementation

Shane's first test was `assertThat(new Account(...));` with nothing chained, which asserts nothing. That was corrected, and every later test made real assertions on all relevant values. He also switched from `containsSame` to `contains` once shown that a database-backed repository returns a new object. He went with `assertThatNoException` until he saw it couldn't hand back the created object, and then used a plain statement plus assertions.

**Evidence:** 01 M1 `AccountTest` and `AccountRepositoryTest` reviews.
**Implications:** it's unconfirmed whether he did the "break it on purpose" check. Ask him to do one live in M2 before trusting a new test.
