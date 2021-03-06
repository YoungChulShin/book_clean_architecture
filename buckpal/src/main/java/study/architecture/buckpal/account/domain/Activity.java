package study.architecture.buckpal.account.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

@Getter
@AllArgsConstructor
public class Activity {

  private ActivityId id;

  @NonNull
  private final Account.AccountId ownerAccountId;

  @NonNull
  private final Account.AccountId sourceAccountId;

  @NonNull
  private final Account.AccountId targetAccountId;

  @NonNull
  private final LocalDateTime timestamp;

  @NonNull
  private final Money money;

  public Activity(
      @NonNull Account.AccountId ownerAccountId,
      @NonNull Account.AccountId sourceAccountId,
      @NonNull Account.AccountId targetAccountId,
      @NonNull LocalDateTime timestamp,
      @NonNull Money money) {
    this.id = null;
    this.ownerAccountId = ownerAccountId;
    this.sourceAccountId = sourceAccountId;
    this.targetAccountId = targetAccountId;
    this.timestamp = timestamp;
    this.money = money;
  }

  @Value
  public static class ActivityId {

    private final Long value;
  }
}
