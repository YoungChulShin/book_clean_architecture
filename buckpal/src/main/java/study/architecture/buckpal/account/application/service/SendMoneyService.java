package study.architecture.buckpal.account.application.service;

import study.architecture.buckpal.account.application.port.in.SendMoneyCommand;
import study.architecture.buckpal.account.application.port.in.SendMoneyUseCase;

public class SendMoneyService implements SendMoneyUseCase {

  @Override
  public boolean sendMoney(SendMoneyCommand command) {
    return false;
  }
}
