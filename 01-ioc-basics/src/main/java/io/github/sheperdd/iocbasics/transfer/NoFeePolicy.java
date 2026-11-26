package io.github.sheperdd.iocbasics.transfer;

import java.math.BigDecimal;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "bank.fees.policy", havingValue = "none")
public class NoFeePolicy implements FeePolicy {

  @Override
  public BigDecimal feeFor(BigDecimal amount) {
    return BigDecimal.ZERO;
  }
}
