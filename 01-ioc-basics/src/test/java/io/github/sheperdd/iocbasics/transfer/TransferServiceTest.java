package io.github.sheperdd.iocbasics.transfer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.sheperdd.iocbasics.account.Account;
import io.github.sheperdd.iocbasics.account.AccountRepository;

class TransferServiceTest {

  private static final Currency CAD = Currency.getInstance("CAD");
  private static final Currency USD = Currency.getInstance("USD");

  private final AccountRepository repository = new AccountRepository();
  private final TransferService service = new TransferService(repository, new FlatFeePolicy());

  @BeforeEach
  void seedAccounts() {
    repository.save(new Account("A-001", "Alice", new BigDecimal("100.00"), CAD)); // sender
    repository.save(new Account("A-002", "John", new BigDecimal("0.00"), CAD)); // receiver
  }

  private BigDecimal balanceOf(String id) {
    return repository.findById(id).orElseThrow().balance();
  }

  @Test
  void senderPaysAmountPlusFlatFee() {
    service.transfer("A-001", "A-002", new BigDecimal("50.00"));

    assertThat(balanceOf("A-001")).isEqualByComparingTo("48.50");
    assertThat(balanceOf("A-002")).isEqualByComparingTo("50.00");
  }

  @Test
  void noFeePolicyMovesExactAmount() {
    TransferService noFeeService = new TransferService(repository, new NoFeePolicy());

    noFeeService.transfer("A-001", "A-002", new BigDecimal("50.00"));

    assertThat(balanceOf("A-001")).isEqualByComparingTo("50.00");
    assertThat(balanceOf("A-002")).isEqualByComparingTo("50.00");
  }

  @Test
  void rejectsZeroAmount() {
    assertThatThrownBy(() -> service.transfer("A-001", "A-002", new BigDecimal("0.00")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("must be positive");
  }

  @Test
  void rejectsTransferToSameAccount() {
    // new String: a different object with the same text, like an ID parsed from an HTTP request
    assertThatThrownBy(() -> service.transfer("A-001", new String("A-001"), new BigDecimal("10.00")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("yourself");
  }

  @Test
  void rejectsUnknownAccounts() {
    assertThatThrownBy(() -> service.transfer("A-999", "A-002", new BigDecimal("10.00")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("A-999");
    assertThatThrownBy(() -> service.transfer("A-001", "A-999", new BigDecimal("10.00")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("A-999");
  }

  @Test
  void rejectsCurrencyMismatch() {
    repository.save(new Account("U-001", "Bob", new BigDecimal("0.00"), USD));

    assertThatThrownBy(() -> service.transfer("A-001", "U-001", new BigDecimal("10.00")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("CAD")
        .hasMessageContaining("USD");
  }

  @Test
  void balanceExactlyEqualToTotalDebitIsEnough() {
    repository.save(new Account("A-001", "Alice", new BigDecimal("51.50"), CAD));

    service.transfer("A-001", "A-002", new BigDecimal("50.00"));

    assertThat(balanceOf("A-001")).isEqualByComparingTo("0.00");
    assertThat(balanceOf("A-002")).isEqualByComparingTo("50.00");
  }

  @Test
  void oneCentShortOfTotalDebitIsInsufficientFunds() {
    repository.save(new Account("A-001", "Alice", new BigDecimal("51.49"), CAD));

    assertThatThrownBy(() -> service.transfer("A-001", "A-002", new BigDecimal("50.00")))
        .isInstanceOf(InsufficientFundsException.class)
        .hasMessageContaining("A-001");
  }

  @Test
  void refusedTransferChangesNoBalances() {
    repository.save(new Account("A-001", "Alice", new BigDecimal("51.49"), CAD));

    assertThatThrownBy(() -> service.transfer("A-001", "A-002", new BigDecimal("50.00")))
        .isInstanceOf(InsufficientFundsException.class);

    assertThat(balanceOf("A-001")).isEqualByComparingTo("51.49");
    assertThat(balanceOf("A-002")).isEqualByComparingTo("0.00");
  }
}
