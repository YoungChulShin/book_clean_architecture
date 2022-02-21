package study.architecture.buckpal.account.application.port.out;

import java.time.LocalDateTime;
import study.architecture.buckpal.account.domain.Account;
import study.architecture.buckpal.account.domain.Account.AccountId;

public interface LoadAccountPort {

  Account loadAccount(AccountId accountId, LocalDateTime baseLineDate);
}
