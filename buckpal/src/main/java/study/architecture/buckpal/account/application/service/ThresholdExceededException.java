package study.architecture.buckpal.account.application.service;

import study.architecture.buckpal.account.domain.Money;

public class ThresholdExceededException extends RuntimeException {

  public ThresholdExceededException(Money threshold, Money actual) {
    super(String.format(
        "Maximum threshold for transferring money exceeded: tried to transfer %s but threshold is %s!",
        actual, threshold));
  }
}
