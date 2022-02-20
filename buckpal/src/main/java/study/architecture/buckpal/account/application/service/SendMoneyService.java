package study.architecture.buckpal.account.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import study.architecture.buckpal.configuration.BuckPalConfigurationProperties;
import study.architecture.buckpal.account.application.port.in.SendMoneyCommand;
import study.architecture.buckpal.account.application.port.in.SendMoneyUseCase;

@RequiredArgsConstructor
public class SendMoneyService implements SendMoneyUseCase {

  private final BuckPalConfigurationProperties configurationProperties;

  @Transactional
  @Override
  public boolean sendMoney(SendMoneyCommand command) {

    return false;
  }

  private void checkThreshold(SendMoneyCommand command) {

  }
}
