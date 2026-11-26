package io.github.sheperdd.iocbasics;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.sheperdd.iocbasics.account.Account;
import io.github.sheperdd.iocbasics.account.AccountRepository;
import io.github.sheperdd.iocbasics.transfer.FeePolicy;
import io.github.sheperdd.iocbasics.transfer.TransferService;

class AppConfigTest {

  private static final Currency CAD = Currency.getInstance("CAD");

  private AnnotationConfigApplicationContext context;

  @BeforeEach
  void startContext() {
    context = new AnnotationConfigApplicationContext(AppConfig.class);
  }

  @AfterEach
  void closeContext() {
    context.close();
  }

  @Test
  void repositoryIsASingleton() {
    var first = context.getBean(AccountRepository.class);
    var second = context.getBean(AccountRepository.class);

    assertThat(first).isSameAs(second);
  }

  @Test
  void transferServiceUsesTheConfiguredBeans() {
    var repository = context.getBean(AccountRepository.class);
    var feePolicy = context.getBean(FeePolicy.class);
    var service = context.getBean(TransferService.class);
    repository.save(new Account("A-001", "Alice", new BigDecimal("100.00"), CAD));
    repository.save(new Account("A-002", "John", new BigDecimal("0.00"), CAD));

    var amount = new BigDecimal("50.00");
    service.transfer("A-001", "A-002", amount);

    var expectedSenderBalance = new BigDecimal("100.00").subtract(amount).subtract(feePolicy.feeFor(amount));
    assertThat(repository.findById("A-001").orElseThrow().balance()).isEqualByComparingTo(expectedSenderBalance);
    assertThat(repository.findById("A-002").orElseThrow().balance()).isEqualByComparingTo(amount);
  }
}
