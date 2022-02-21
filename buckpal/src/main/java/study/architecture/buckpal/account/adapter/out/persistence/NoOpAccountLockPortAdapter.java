package study.architecture.buckpal.account.adapter.out.persistence;

import org.springframework.stereotype.Component;
import study.architecture.buckpal.account.application.port.out.AccountLockPort;
import study.architecture.buckpal.account.domain.Account.AccountId;

@Component
public class NoOpAccountLockPortAdapter implements AccountLockPort {

  @Override
  public void lockAccount(AccountId accountId) {
    // do nothing
  }

  @Override
  public void releaseAccount(AccountId accountId) {
    // do nothing
  }
}
