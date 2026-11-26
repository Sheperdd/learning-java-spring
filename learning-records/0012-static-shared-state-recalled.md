# `static` vs instance test state: recalled, with the flakiness nuance still new

Shane explained from memory that a `static` repository belongs to the class and isn't fresh per test. He overstated the effect ("breaks any test"). He didn't see that `@BeforeEach` re-saving by ID would mask most of it, or that the real risk is order-dependent (flaky) tests from leftover data.

**Evidence:** wrap-up quiz Q3 for 01 M2.
**Implications:** singleton scope in M5 can build on this directly. Bring up the flaky-test angle again there, because shared mutable state in a singleton fails the same quiet way.
