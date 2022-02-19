package study.architecture.buckpal.account.application.port.in;

import static org.junit.jupiter.api.Assertions.*;

import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import study.architecture.buckpal.account.domain.Account.AccountId;
import study.architecture.buckpal.account.domain.Money;

class SendMoneyCommandTest {

  @Test
  void constructor_withNullSourceAccountId_throwsException() {
    // when, then
    ConstraintViolationException exception = assertThrows(
        ConstraintViolationException.class,
        () -> new SendMoneyCommand(null, null, null));
  }

  @Test
  void constructor_withNegativeMoney_throwsException() {
    // when, then
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new SendMoneyCommand(new AccountId(1L), new AccountId(2L), Money.of(-1L)));
  }
}