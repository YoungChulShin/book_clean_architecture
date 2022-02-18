package study.architecture.buckpal.account.domain;

import java.time.LocalDateTime;
import lombok.Value;

public class Account {

  private AccountId id;
  private Money baseLineBalance;
  private ActivityWindow activityWindow;

  public Money calcualteBalance() {
    return Money.add(
        this.baseLineBalance,
        this.activityWindow.calculateBalance(this.id)
    );
  }

  public boolean withdraw(Money money, AccountId targetAccountId) {
    if (!mayWtitdraw(money)) {
      return false;
    }

    Activity withdrawal = new Activity(
        this.id,
        this.id,
        targetAccountId,
        LocalDateTime.now(),
        money);

    activityWindow.addActivity(withdrawal);
    return true;
  }

  private boolean mayWtitdraw(Money money) {
    return Money.add(this.calcualteBalance(), money.negate()).isPositive();
  }

  public boolean deposit(Money money, AccountId sourceAccountId) {
    Activity deposit = new Activity(
        this.id,
        sourceAccountId,
        this.id,
        LocalDateTime.now(),
        money);

    activityWindow.addActivity(deposit);
    return true;
  }

  @Value
  public static class AccountId {

    private Long value;
  }
}
