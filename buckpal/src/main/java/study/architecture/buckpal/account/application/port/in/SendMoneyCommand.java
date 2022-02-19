package study.architecture.buckpal.account.application.port.in;

import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import study.architecture.buckpal.account.domain.Account.AccountId;
import study.architecture.buckpal.account.domain.Money;
import study.architecture.buckpal.common.SelfValidating;

/**
 * 입력 모델
 * 유효성 체크를 같이 한다
 */
@Getter
@EqualsAndHashCode(callSuper = false)
public class SendMoneyCommand extends SelfValidating<SendMoneyCommand> {

  @NotNull
  private final AccountId sourceAccountId;

  @NotNull
  private final AccountId targetAccountId;

  @NotNull
  private final Money money;

  public SendMoneyCommand(
      AccountId sourceAccountId,
      AccountId targetAccountId,
      Money money) {
    this.sourceAccountId = sourceAccountId;
    this.targetAccountId = targetAccountId;
    this.money = money;

    // javax validation을 도움을 받을 수 있는 것은 validateSelf()으로 처리하고,
    // 그렇지 않은 것은 직접 구현해준다
    if (!money.isPositive()) {
      throw new IllegalArgumentException("money");
    }
    this.validateSelf();
  }
}
