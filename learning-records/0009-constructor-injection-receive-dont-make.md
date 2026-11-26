# Constructor injection: a class receives its collaborators, it doesn't make them

Shane can use constructor injection correctly and explain the payoff. Unprompted, he said a third fee policy means "a new class that implements FeePolicy, used when creating the TransferService; TransferService does not change" (Open/Closed). He worked out that dependencies must be created before dependents, and he separated collaborators (constructor) from request data (method parameters) after one hint. His first instinct was "making a feePolicy object" in the constructor, and that was corrected.

**Evidence:** 01 M2 design questions, `HandWiredApp`, and quiz Q1 (he named DI, the lock-in, and the fix).
**Implications:** in his review comments, "less control" is still his argument. He doesn't yet name the testing cost (you can't pass a `NoFeePolicy` or a fake). In M3, ask him what `@Bean` methods replace in `HandWiredApp` before showing it, and get him to name the dependency graph himself.
