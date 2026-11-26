package io.github.sheperdd.iocbasics.transfer;

import java.math.BigDecimal;

public class NoFeePolicy implements FeePolicy {

  @Override
  public BigDecimal feeFor(BigDecimal amount) {
    return BigDecimal.ZERO;
  }
}
