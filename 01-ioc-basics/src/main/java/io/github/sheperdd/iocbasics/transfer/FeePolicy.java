package io.github.sheperdd.iocbasics.transfer;

import java.math.BigDecimal;

public interface FeePolicy {
  BigDecimal feeFor(BigDecimal amount);
}
