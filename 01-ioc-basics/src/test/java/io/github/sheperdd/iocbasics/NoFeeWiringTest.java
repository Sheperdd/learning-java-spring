package io.github.sheperdd.iocbasics;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.sheperdd.iocbasics.account.Account;
import io.github.sheperdd.iocbasics.account.AccountRepository;
import io.github.sheperdd.iocbasics.transfer.TransferService;

@SpringBootTest(properties = { "bank.fees.policy=none", "bank.demo.enabled=false" })
class NoFeeWiringTest {

  private static final Currency CAD = Currency.getInstance("CAD");

  private final AccountRepository accounts;
  private final TransferService transfers;

  @Autowired
  NoFeeWiringTest(AccountRepository accounts, TransferService transfers) {
    this.accounts = accounts;
    this.transfers = transfers;
  }

  @Test
  void transferChargesNoFee() {
    accounts.save(new Account("A-001", "Alice", new BigDecimal("100.00"), CAD));
    accounts.save(new Account("A-002", "John", new BigDecimal("0.00"), CAD));

    transfers.transfer("A-001", "A-002", new BigDecimal("50.00"));

    assertThat(accounts.findById("A-001").orElseThrow().balance()).isEqualByComparingTo("50.00");
    assertThat(accounts.findById("A-002").orElseThrow().balance()).isEqualByComparingTo("50.00");
  }
}
