package study.architecture.buckpal.account.application.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import study.architecture.buckpal.account.application.port.in.GetAccountBalanceQuery;
import study.architecture.buckpal.account.application.port.out.LoadAccountPort;
import study.architecture.buckpal.account.domain.Account;
import study.architecture.buckpal.account.domain.Account.AccountId;
import study.architecture.buckpal.account.domain.Money;

@RequiredArgsConstructor
public class GetAccountBalanceService implements GetAccountBalanceQuery {

  private final LoadAccountPort loadAccountPort;

  @Override
  public Money getAccountBalance(AccountId accountId) {
    Account account = loadAccountPort.loadAccount(accountId, LocalDateTime.now());
    return account.calcualteBalance();
  }
}
