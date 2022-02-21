package study.architecture.buckpal.account.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import study.architecture.buckpal.account.domain.Account.AccountId;
import study.architecture.buckpal.common.AccountTestData;
import study.architecture.buckpal.common.ActivityTestData;

class AccountTest {

  @Test
  void withdrawalSucceeds() {
    // given
    AccountId accountId = new AccountId(1L);
    Account account = AccountTestData.defaultAccount()
        .withAccountId(accountId)
        .withBaselineBalance(Money.of(555L))
        .withActivityWindow(new ActivityWindow(
            ActivityTestData.defaultActivity()
                .withTargetAccount(accountId)
                .withMoney(Money.of(999L))
                .build(),
            ActivityTestData.defaultActivity()
                .withTargetAccount(accountId)
                .withMoney(Money.of(1L))
                .build()))
        .build();

    // when
    boolean success = account.withdraw(Money.of(555L), new AccountId(99L));

    // then
    assertThat(success).isTrue();
    assertThat(account.getActivityWindow().getActivities()).hasSize(3);
    assertThat(account.calcualteBalance()).isEqualTo(Money.of(1000L));
  }
}