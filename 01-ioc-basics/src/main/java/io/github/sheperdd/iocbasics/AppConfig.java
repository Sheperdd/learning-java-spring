package io.github.sheperdd.iocbasics;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.sheperdd.iocbasics.account.AccountRepository;
import io.github.sheperdd.iocbasics.transfer.FeePolicy;
import io.github.sheperdd.iocbasics.transfer.FlatFeePolicy;
import io.github.sheperdd.iocbasics.transfer.TransferService;

@Configuration
public class AppConfig {

  @Bean
  AccountRepository accountRepository() {
    return new AccountRepository();
  }

  @Bean
  FeePolicy feePolicy() {
    return new FlatFeePolicy();
  }

  @Bean
  TransferService transferService(AccountRepository repo, FeePolicy fees) {
    return new TransferService(repo, fees);
  }

}
