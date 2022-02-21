package study.architecture.buckpal.account.application.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.architecture.buckpal.account.application.port.out.AccountLockPort;
import study.architecture.buckpal.account.application.port.out.LoadAccountPort;
import study.architecture.buckpal.account.application.port.out.UpdateAccountStatePort;
import study.architecture.buckpal.account.domain.Account;
import study.architecture.buckpal.account.application.port.in.SendMoneyCommand;
import study.architecture.buckpal.account.application.port.in.SendMoneyUseCase;
import study.architecture.buckpal.account.domain.Account.AccountId;

@Service
@RequiredArgsConstructor
public class SendMoneyService implements SendMoneyUseCase {

  private final LoadAccountPort loadAccountPort;
  private final AccountLockPort accountLockPort;
  private final UpdateAccountStatePort updateAccountStatePort;
  private final MoneyTransferProperties moneyTransferProperties;

  @Transactional
  @Override
  public boolean sendMoney(SendMoneyCommand command) {
    checkThreshold(command);

    LocalDateTime baselineDate = LocalDateTime.now().minusDays(10);

    Account sourceAccount = loadAccountPort.loadAccount(command.getSourceAccountId(), baselineDate);
    Account targetAccount = loadAccountPort.loadAccount(command.getTargetAccountId(), baselineDate);

    AccountId sourceAccountId = sourceAccount.getId()
        .orElseThrow(() -> new IllegalStateException("expected source account id not to be empty"));
    AccountId targetAccountId = targetAccount.getId()
        .orElseThrow(() -> new IllegalStateException("expected target account id not to be empty"));

    accountLockPort.lockAccount(sourceAccountId);
    if (!sourceAccount.withdraw(command.getMoney(), sourceAccountId)) {
      accountLockPort.releaseAccount(sourceAccountId);
      return false;
    }

    accountLockPort.lockAccount(targetAccountId);
    if (!targetAccount.deposit(command.getMoney(), targetAccountId)) {
      accountLockPort.releaseAccount(sourceAccountId);
      accountLockPort.releaseAccount(targetAccountId);
      return false;
    }

    updateAccountStatePort.updateActivities(sourceAccount);
    updateAccountStatePort.updateActivities(targetAccount);

    accountLockPort.releaseAccount(sourceAccountId);
    accountLockPort.releaseAccount(targetAccountId);
    return true;
  }

  private void checkThreshold(SendMoneyCommand command) {
    if (command.getMoney().isGreaterThan(moneyTransferProperties.getMaximumTransferThreshold())) {
      throw new ThresholdExceededException(
          moneyTransferProperties.getMaximumTransferThreshold(),
          command.getMoney());
    }
  }
}
