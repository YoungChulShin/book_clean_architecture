package study.architecture.buckpal.account.application.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.architecture.buckpal.account.application.port.out.LoadAccountPort;
import study.architecture.buckpal.account.domain.Account;
import study.architecture.buckpal.account.application.port.in.SendMoneyCommand;
import study.architecture.buckpal.account.application.port.in.SendMoneyUseCase;

@Service
@RequiredArgsConstructor
public class SendMoneyService implements SendMoneyUseCase {

  private final LoadAccountPort loadAccountPort;
  private final MoneyTransferProperties moneyTransferProperties;

  @Transactional
  @Override
  public boolean sendMoney(SendMoneyCommand command) {
    checkThreshold(command);

    LocalDateTime baselineDate = LocalDateTime.now().minusDays(10);

    Account sourceAccount = loadAccountPort.loadAccount(command.getSourceAccountId(), baselineDate);
    Account targetAccount = loadAccountPort.loadAccount(command.getTargetAccountId(), baselineDate);



    return false;
  }

  private void checkThreshold(SendMoneyCommand command) {
    if (command.getMoney().isGreaterThan(moneyTransferProperties.getMaximumTransferThreshold())) {
      throw new ThresholdExceededException(
          moneyTransferProperties.getMaximumTransferThreshold(),
          command.getMoney());
    }
  }
}
