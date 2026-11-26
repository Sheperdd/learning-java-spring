# Boundary tests: he finds the forbidden side, and forgets the allowed side

Shane listed `acceptsZeroBalance` on his own, but he first picked `-1.00` for the negative test. He moved to `-0.01` once shown a bug (`intValue() < 0`) that only the boundary value catches. In the wrap-up quiz he named only `10,000.01` for a $10,000 limit and missed that `10,000.00` must be accepted.

**Evidence:** 01 M1 `AccountTest`; wrap-up Q2.
**Implications:** in M2's `TransferService` tests, the key boundary is "balance exactly equals the total debit must succeed". Ask for both sides before he writes them.
