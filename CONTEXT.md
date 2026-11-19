# Banking domain

The shared business language of the small bank the learning projects model. It grows project by project. Tech terms live in `GLOSSARY.md`, not here.

## Language

### Accounts

**Account**:
A customer's store of money in exactly one currency, identified by an account ID (e.g. `A-001`).
_Avoid_: wallet, user

**Balance**:
The amount of money an **Account** holds right now. It never goes below zero (no overdraft).
_Avoid_: funds (fine in "insufficient funds"), total

### Transfers

**Transfer**:
Moving an amount of money from one **Account** to another in the same currency. It either happens completely or not at all.
_Avoid_: payment, transaction (reserved for the database meaning)

**Sender**:
The **Account** money leaves in a **Transfer**. It also pays the **Fee**.
_Avoid_: source, from-account, payer

**Receiver**:
The **Account** money arrives in during a **Transfer**. It receives the full amount; no fee is deducted.
_Avoid_: target, destination, payee

**Fee**:
The charge the bank takes for a **Transfer**, paid by the **Sender** on top of the amount.
_Avoid_: charge, commission

**Fee policy**:
The bank's rule that decides the **Fee** for a **Transfer**. Exactly one is in force at a time (e.g. flat fee, or no fee during a promotion).
_Avoid_: pricing, fee strategy

**Total debit**:
Amount plus **Fee**: what actually leaves the **Sender**'s **Balance**.
_Avoid_: total, cost

**Insufficient funds**:
The **Sender**'s **Balance** is smaller than the **Total debit**, so the **Transfer** is refused.
_Avoid_: overdrawn, NSF
