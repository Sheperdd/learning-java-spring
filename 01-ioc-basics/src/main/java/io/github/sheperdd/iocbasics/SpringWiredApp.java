package io.github.sheperdd.iocbasics;

import java.math.BigDecimal;
import java.util.Currency;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.sheperdd.iocbasics.account.Account;
import io.github.sheperdd.iocbasics.account.AccountRepository;
import io.github.sheperdd.iocbasics.transfer.FeePolicy;
import io.github.sheperdd.iocbasics.transfer.InsufficientFundsException;
import io.github.sheperdd.iocbasics.transfer.TransferService;

public class SpringWiredApp {

  private static final Currency CAD = Currency.getInstance("CAD");

  public static void main(String[] args) {
    try (var context = new AnnotationConfigApplicationContext(AppConfig.class)) {
      AccountRepository repository = context.getBean(AccountRepository.class);
      Account sender = new Account("A-001", "Alice", new BigDecimal("100.00"), CAD);
      Account receiver = new Account("A-002", "John", new BigDecimal("0.00"), CAD);
      repository.save(sender);
      repository.save(receiver);

      TransferService service = context.getBean(TransferService.class);

      service.transfer(sender.id(), receiver.id(), new BigDecimal("50.00"));

      System.out.println(sender.id() + ": " + repository.findById(sender.id()).orElseThrow().balance());
      System.out.println(receiver.id() + ": " + repository.findById(receiver.id()).orElseThrow().balance());

      try {
        service.transfer(sender.id(), receiver.id(), new BigDecimal("50.00")); // throws InsufficientFundsException
      } catch (InsufficientFundsException e) {
        System.out.println(e.getMessage());
      }
    }
  }

}
