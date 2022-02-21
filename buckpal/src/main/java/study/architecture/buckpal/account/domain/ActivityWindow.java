package study.architecture.buckpal.account.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lombok.NonNull;
import study.architecture.buckpal.account.domain.Account.AccountId;

public class ActivityWindow {

  private List<Activity> activities;

  public ActivityWindow(@NonNull List<Activity> activities) {
    this.activities = activities;
  }

  public ActivityWindow(@NonNull Activity... activities) {
    this.activities = new ArrayList<>(Arrays.asList(activities));
  }

  public LocalDateTime getStartTimestamp() {
    return activities.stream()
        .min(Comparator.comparing(Activity::getTimestamp))
        .orElseThrow(IllegalStateException::new)
        .getTimestamp();
  }

  public LocalDateTime getEndTimestamp() {
    return activities.stream()
        .max(Comparator.comparing(Activity::getTimestamp))
        .orElseThrow(IllegalStateException::new)
        .getTimestamp();
  }

  public Money calculateBalance(AccountId id) {
    Money depositBalance = activities.stream()
        .filter(activity -> activity.getTargetAccountId().equals(id))
        .map(Activity::getMoney)
        .reduce(Money.ZERO, Money::add);

    Money withdrawalBalance = activities.stream()
        .filter(activity -> activity.getSourceAccountId().equals(id))
        .map(Activity::getMoney)
        .reduce(Money.ZERO, Money::add);

    return Money.add(depositBalance, withdrawalBalance.negate());
  }

  public List<Activity> getActivities() {
    return Collections.unmodifiableList(this.activities);
  }

  public void addActivity(Activity activity) {
    try {
      this.activities.add(activity);
    } catch (Exception ex) {
      String a= ex.getMessage();
    }

  }
}
