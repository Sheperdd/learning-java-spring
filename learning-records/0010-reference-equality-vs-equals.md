# `==` compares identity, `.equals` compares contents (misconception corrected)

Shane wrote `senderId == receiverId` and `currency() != currency()` in `TransferService`. After the correction, he explained from memory why the test uses `new String("A-001")`: it makes a different object with the same text, so an `==` comparison is caught, while two literals share one memory location. He didn't name *interning* himself.

**Evidence:** TransferService review in 01 M2, and quiz Q2.
**Implications:** watch for `==` on objects whenever he compares IDs, entities or enums-vs-objects. In project 03, JPA entity `equals`/`hashCode` builds directly on this.
