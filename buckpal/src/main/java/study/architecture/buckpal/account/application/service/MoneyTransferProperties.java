package study.architecture.buckpal.account.application.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import study.architecture.buckpal.account.domain.Money;

@Getter
@AllArgsConstructor
public class MoneyTransferProperties {

  private final Money maximumTransferThreshold;
}
