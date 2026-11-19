package io.github.sheperdd.iocbasics.account;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

/**
 * A customer's store of money in one currency. Immutable: a new balance means a
 * new Account.
 */
public record Account(String id, String owner, BigDecimal balance, Currency currency) {

    public Account {
        Objects.requireNonNull(id, "id can not be null");
        Objects.requireNonNull(owner, "owner can not be null");
        Objects.requireNonNull(balance, "balance can not be null");
        Objects.requireNonNull(currency, "currency can not be null");

        if (id.isBlank()) {
            throw new IllegalArgumentException("id cannot be blank");
        }
        if (owner.isBlank()) {
            throw new IllegalArgumentException("owner cannot be blank");
        }

        if (balance.compareTo(BigDecimal.ZERO) < 0) { // balance < 0
            throw new IllegalArgumentException("Balance of " + id + " must not be negative: " + balance);
        }
    }

    public Account withBalance(BigDecimal newBalance) {
        return (new Account(id, owner, newBalance, currency));
    }
}
