package study.architecture.buckpal.account.application.service;

import org.springframework.transaction.annotation.Transactional;
import study.architecture.buckpal.account.application.port.in.SendMoneyCommand;
import study.architecture.buckpal.account.application.port.in.SendMoneyUseCase;

public class SendMoneyService implements SendMoneyUseCase {

  @Transactional
  @Override
  public boolean sendMoney(SendMoneyCommand command) {
    return false;
  }
}
