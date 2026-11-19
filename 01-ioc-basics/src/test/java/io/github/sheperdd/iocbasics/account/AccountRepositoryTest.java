package io.github.sheperdd.iocbasics.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class AccountRepositoryTest {

    private final AccountRepository repository = new AccountRepository();

    private static final Currency CAD = Currency.getInstance("CAD");

    @Test
    void findsSavedAccountById() {
        String id = "A-001";
        String owner = "John";
        BigDecimal balance = new BigDecimal("100.00");

        Account account = new Account(id, owner, balance, CAD);

        repository.save(account);

        Optional<Account> result = repository.findById(id);

        assertThat(result).contains(account);
    }

    @Test
    void returnsEmptyForUnknownId() {
        String id = "A-001";
        String owner = "John";
        BigDecimal balance = new BigDecimal("100.00");

        Account account = new Account(id, owner, balance, CAD);

        repository.save(account);

        Optional<Account> result = repository.findById("A-999");

        assertThat(result).isEmpty();
    }

    @Test
    void saveReplacesAccountWithSameId() {
        String id = "A-001";
        String owner = "John";
        BigDecimal balance = new BigDecimal("100.00");

        Account account = new Account(id, owner, balance, CAD);

        repository.save(account);

        BigDecimal newBal = new BigDecimal("10.00");
        Account accountNewBal = account.withBalance(newBal);

        repository.save(accountNewBal);

        Optional<Account> found = repository.findById(id);

        assertThat(found.orElseThrow().balance()).isEqualByComparingTo(newBal);
    }
}
