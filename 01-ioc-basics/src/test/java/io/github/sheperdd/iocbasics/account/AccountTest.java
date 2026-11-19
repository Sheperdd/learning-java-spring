package io.github.sheperdd.iocbasics.account;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.Test;

class AccountTest {

    private static final Currency CAD = Currency.getInstance("CAD");

    @Test
    void createsAccountWithGivenValues() {
        String id = "A-001";
        String owner = "John";
        BigDecimal balance = new BigDecimal("100.00");

        Account account = new Account(id, owner, balance, CAD);

        assertThat(account.id()).isEqualTo(id);
        assertThat(account.owner()).isEqualTo(owner);
        assertThat(account.balance()).isEqualByComparingTo(balance);
        assertThat(account.currency()).isEqualTo(CAD);
    }

    @Test
    void acceptsZeroBalance() {
        var account = new Account("A-001", "John", new BigDecimal("0.00"), CAD);

        assertThat(account.balance()).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    void rejectsNegativeBalance() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Account("A-001", "John", new BigDecimal("-0.01"), CAD))
                .withMessageContaining("must not be negative");
    }

    @Test
    void rejectsNullComponents() {
        assertThatNullPointerException().isThrownBy(() -> new Account(null, "John", new BigDecimal("10.00"), CAD))
                .withMessageContaining("id");

        assertThatNullPointerException().isThrownBy(() -> new Account("A-001", null, new BigDecimal("10.00"), CAD))
                .withMessageContaining("owner");

        assertThatNullPointerException().isThrownBy(() -> new Account("A-001", "John", null, CAD))
                .withMessageContaining("balance");

        assertThatNullPointerException().isThrownBy(() -> new Account("A-001", "John", new BigDecimal("10.00"), null))
                .withMessageContaining("currency");
    }

    @Test
    void rejectsBlankIdAndOwner() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Account(" ", "John", new BigDecimal("10.00"), CAD))
                .withMessage("id cannot be blank");

        assertThatIllegalArgumentException().isThrownBy(() -> new Account("A-001", " ", new BigDecimal("10.00"), CAD))
                .withMessage("owner cannot be blank");
    }

    @Test
    void withBalanceReturnsCopyAndLeavesOriginalUnchanged() {
        String id = "A-001";
        String owner = "John";
        BigDecimal balance = new BigDecimal("10.00");
        Account account = new Account(id, owner, balance, CAD);

        BigDecimal newBalance = new BigDecimal("0.00");
        Account newAccount = account.withBalance(newBalance);

        assertThat(account.balance()).isEqualByComparingTo(balance);

        assertThat(newAccount.id()).isEqualTo(id);
        assertThat(newAccount.owner()).isEqualTo(owner);
        assertThat(newAccount.balance()).isEqualByComparingTo(newBalance);
        assertThat(newAccount.currency()).isEqualTo(CAD);
    }

    @Test
    void withBalanceRejectsNegativeBalance() {
        String id = "A-001";
        String owner = "John";
        BigDecimal balance = new BigDecimal("10.00");
        Account account = new Account(id, owner, balance, CAD);

        assertThatIllegalArgumentException().isThrownBy(() -> account.withBalance(new BigDecimal("-0.01")))
                .withMessageContaining("must not be negative");
    }
}
