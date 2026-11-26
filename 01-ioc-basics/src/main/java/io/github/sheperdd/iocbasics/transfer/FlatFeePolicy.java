package io.github.sheperdd.iocbasics.transfer;

import java.math.BigDecimal;

public class FlatFeePolicy implements FeePolicy {

  private static final BigDecimal FLAT_FEE = new BigDecimal("1.50");

  @Override
  public BigDecimal feeFor(BigDecimal amount) {
    return FLAT_FEE;
  }
}
