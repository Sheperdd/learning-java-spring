package io.github.sheperdd.iocbasics.transfer;

import java.math.BigDecimal;

import io.github.sheperdd.iocbasics.account.Account;
import io.github.sheperdd.iocbasics.account.AccountRepository;

public class TransferService {

  private final AccountRepository accounts;
  private final FeePolicy feePolicy;

  public TransferService(AccountRepository accounts, FeePolicy feePolicy) {
    this.accounts = accounts;
    this.feePolicy = feePolicy;
  }

  public void transfer(String senderId, String receiverId, BigDecimal amount) {
    if (amount.compareTo(BigDecimal.ZERO) <= 0) { // amount <= 0
      throw new IllegalArgumentException("Amount must be positive: " + amount);
    }

    if (senderId.equals(receiverId)) {
      throw new IllegalArgumentException("Cannot transfer money to yourself: " + senderId);
    }

    Account sender = accounts.findById(senderId)
        .orElseThrow(() -> new IllegalArgumentException("Unknown sender account: " + senderId));
    Account receiver = accounts.findById(receiverId)
        .orElseThrow(() -> new IllegalArgumentException("Unknown receiver account: " + receiverId));

    if (!sender.currency().equals(receiver.currency())) {
      throw new IllegalArgumentException(
          "Cannot transfer different currency money: " + sender.currency() + " to " + receiver.currency());
    }

    BigDecimal totalDebit = amount.add(feePolicy.feeFor(amount));
    if (sender.balance().compareTo(totalDebit) < 0) {
      throw new InsufficientFundsException(senderId + " has " + sender.balance() + " " + sender.currency() + ", needs "
          + totalDebit + " " + sender.currency());
    }

    Account newSender = sender.withBalance(sender.balance().subtract(totalDebit));
    accounts.save(newSender);

    Account newReceiver = receiver.withBalance(receiver.balance().add(amount));
    accounts.save(newReceiver);

  }

}
