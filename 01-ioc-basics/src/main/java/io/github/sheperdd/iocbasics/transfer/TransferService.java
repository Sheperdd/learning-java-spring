package io.github.sheperdd.iocbasics.transfer;

import java.math.BigDecimal;

import io.github.sheperdd.iocbasics.account.AccountRepository;

public class TransferService {

  private final AccountRepository accounts;
  private final FeePolicy feePolicy;

  public TransferService(AccountRepository accounts, FeePolicy feePolicy) {
    this.accounts = accounts;
    this.feePolicy = feePolicy;
  }

  public void transfer(String senderId, String receiverId, BigDecimal amount) {
    // TODO : transfer rules
  }

}
