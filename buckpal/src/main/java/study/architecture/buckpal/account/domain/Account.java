package study.architecture.buckpal.account.domain;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {

  private AccountId id;
  private Money baseLineBalance;
  private ActivityWindow activityWindow;

  public static Account withId(
      AccountId accountId,
      Money baseLineBalance,
      ActivityWindow activityWindow) {
    return new Account(accountId, baseLineBalance, activityWindow);
  }

  public Optional<AccountId> getId() {
    return Optional.ofNullable(id);
  }

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
