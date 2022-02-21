package study.architecture.buckpal.account.application.port.out;

import study.architecture.buckpal.account.domain.Account;

public interface UpdateAccountStatePort {

  void updateActivities(Account account);
}
