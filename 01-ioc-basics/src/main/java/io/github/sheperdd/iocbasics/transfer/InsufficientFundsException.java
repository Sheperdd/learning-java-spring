package io.github.sheperdd.iocbasics.transfer;

public class InsufficientFundsException extends RuntimeException {

  public InsufficientFundsException(String message) {
    super(message);
  }

}
