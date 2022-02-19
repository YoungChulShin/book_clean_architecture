package study.architecture.buckpal.account.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import study.architecture.buckpal.account.application.port.in.SendMoneyCommand;
import study.architecture.buckpal.account.application.port.in.SendMoneyUseCase;
import study.architecture.buckpal.account.domain.Account.AccountId;
import study.architecture.buckpal.account.domain.Money;

@RestController
@RequiredArgsConstructor
class SendMoneyController {

  private final SendMoneyUseCase sendMoneyUseCase;

  @PostMapping(path = "/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
  void sendMoney(
      @PathVariable("sourceAccountId") Long sourceAccountId,
      @PathVariable("targetAccountId") Long targetAccountId,
      @PathVariable("amount") Long amount) {

    // 입력 모델 생성
    SendMoneyCommand command = new SendMoneyCommand(
        new AccountId(sourceAccountId),
        new AccountId(targetAccountId),
        Money.of(amount));

    // 유스케이스 호출
    sendMoneyUseCase.sendMoney(command);
  }
}
