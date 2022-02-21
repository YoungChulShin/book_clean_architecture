package study.architecture.buckpal.account.application.port.out;

import study.architecture.buckpal.account.domain.Account.AccountId;

public interface AccountLockPort {

  void lockAccount(AccountId accountId);

  void releaseAccount(AccountId accountId);
}
