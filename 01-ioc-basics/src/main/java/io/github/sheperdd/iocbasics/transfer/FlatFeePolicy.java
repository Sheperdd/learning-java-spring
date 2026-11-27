package io.github.sheperdd.iocbasics.transfer;

import java.math.BigDecimal;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "bank.fees.policy", havingValue = "flat")
public class FlatFeePolicy implements FeePolicy {

  private static final BigDecimal FLAT_FEE = new BigDecimal("1.50");

  @Override
  public BigDecimal feeFor(BigDecimal amount) {
    return FLAT_FEE;
  }
}
