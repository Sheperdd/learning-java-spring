package io.github.sheperdd.iocbasics;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import io.github.sheperdd.iocbasics.account.Account;
import io.github.sheperdd.iocbasics.account.AccountRepository;
import io.github.sheperdd.iocbasics.transfer.TransferService;

@SpringBootTest(properties = { "bank.fees.policy=flat", "bank.demo.enabled=false" })
class FlatFeeWiringTest {

  private static final Currency CAD = Currency.getInstance("CAD");

  private final AccountRepository accounts;
  private final TransferService transfers;
  private final ApplicationContext context;

  @Autowired
  FlatFeeWiringTest(AccountRepository accounts, TransferService transfers, ApplicationContext context) {
    this.accounts = accounts;
    this.transfers = transfers;
    this.context = context;
  }

  @Test
  void transferChargesTheSenderTheFlatFee() {
    accounts.save(new Account("A-001", "Alice", new BigDecimal("100.00"), CAD));
    accounts.save(new Account("A-002", "John", new BigDecimal("0.00"), CAD));

    transfers.transfer("A-001", "A-002", new BigDecimal("50.00"));

    assertThat(accounts.findById("A-001").orElseThrow().balance()).isEqualByComparingTo("48.50");
    assertThat(accounts.findById("A-002").orElseThrow().balance()).isEqualByComparingTo("50.00");
  }

  @Test
  void repositoryIsASingleton() {
    assertThat(context.getBean(AccountRepository.class)).isSameAs(accounts);
  }
}
