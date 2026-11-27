package io.github.sheperdd.iocbasics;

import java.math.BigDecimal;
import java.util.Currency;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import io.github.sheperdd.iocbasics.account.Account;
import io.github.sheperdd.iocbasics.account.AccountRepository;
import io.github.sheperdd.iocbasics.transfer.InsufficientFundsException;
import io.github.sheperdd.iocbasics.transfer.TransferService;

@Component
@ConditionalOnProperty(name = "bank.demo.enabled", havingValue = "true")
public class TransferDemo implements CommandLineRunner {

  private static final Currency CAD = Currency.getInstance("CAD");

  private final AccountRepository accounts;
  private final TransferService service;

  public TransferDemo(AccountRepository accounts, TransferService service) {
    this.accounts = accounts;
    this.service = service;
  }

  @Override
  public void run(String... args) {
    Account sender = new Account("A-001", "Alice", new BigDecimal("100.00"), CAD);
    Account receiver = new Account("A-002", "John", new BigDecimal("0.00"), CAD);
    accounts.save(sender);
    accounts.save(receiver);

    service.transfer(sender.id(), receiver.id(), new BigDecimal("50.00"));

    System.out.println(sender.id() + ": " + accounts.findById(sender.id()).orElseThrow().balance());
    System.out.println(receiver.id() + ": " + accounts.findById(receiver.id()).orElseThrow().balance());

    try {
      service.transfer(sender.id(), receiver.id(), new BigDecimal("50.00")); // throws InsufficientFundsException
    } catch (InsufficientFundsException e) {
      System.out.println(e.getMessage());
    }
  }
}
