package io.github.sheperdd.iocbasics.account;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/** In-memory store of accounts, keyed by account ID. */
public class AccountRepository {

    // ponytail: plain HashMap, not thread-safe. Milestone 5 shows why that matters
    // for a singleton.
    private final Map<String, Account> accounts = new HashMap<>();

    public Optional<Account> findById(String id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public void save(Account account) {
        accounts.put(account.id(), account);
    }
}
