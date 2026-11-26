# Money comparisons use compareTo against 0, never equals or == 1

Shane uses `isEqualByComparingTo` for every `BigDecimal` assertion without being reminded (6 tests in 01 M1). He first wrote `balance.compareTo(ZERO) == 1` for "negative", which was inverted and relied on the exact return value. That was corrected to "compare the result against 0", and he fixed it himself after tracing it by hand. In the quiz he gave the right fix but not the reason: that `equals` compares scale (`5.00` vs `5`).

**Evidence:** 01 M1 `Account` bug and fix; wrap-up Q1.
**Implications:** in 02/M2 fee and debit arithmetic, check he uses `compareTo` in *production* code too (`fee.equals(ZERO)` is the likely slip), and ask him for the "scale" reason rather than giving it.
