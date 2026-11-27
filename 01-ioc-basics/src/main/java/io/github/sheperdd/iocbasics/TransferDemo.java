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
import jakarta.annotation.PostConstruct;

@Component
@ConditionalOnProperty(name = "bank.demo.enabled", havingValue = "true")
public class TransferDemo implements CommandLineRunner {

  private static final Currency CAD = Currency.getInstance("CAD");

  private static final String senderId = "A-001";
  private static final String receiverId = "A-002";

  private final AccountRepository accounts;
  private final TransferService service;

  public TransferDemo(AccountRepository accounts, TransferService service) {
    this.accounts = accounts;
    this.service = service;
  }

  @PostConstruct
  public void seedAccounts() {
    Account sender = new Account(senderId, "Alice", new BigDecimal("100.00"), CAD);
    Account receiver = new Account(receiverId, "John", new BigDecimal("0.00"), CAD);

    accounts.save(sender);
    accounts.save(receiver);
  }

  @Override
  public void run(String... args) {

    service.transfer(senderId, receiverId, new BigDecimal("50.00"));

    System.out.println(senderId + ": " + accounts.findById(senderId).orElseThrow().balance());
    System.out.println(receiverId + ": " + accounts.findById(receiverId).orElseThrow().balance());

    try {
      service.transfer(senderId, receiverId, new BigDecimal("50.00")); // throws InsufficientFundsException
    } catch (InsufficientFundsException e) {
      System.out.println(e.getMessage());
    }
  }
}
