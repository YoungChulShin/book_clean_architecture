package study.architecture.buckpal.account.application.port.in;

import study.architecture.buckpal.account.domain.Account.AccountId;
import study.architecture.buckpal.account.domain.Money;

public interface GetAccountBalanceQuery {

  Money getAccountBalance(AccountId accountId);

}
