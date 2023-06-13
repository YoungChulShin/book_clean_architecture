package study.cleanarchitecture.buckpalkotlin.account.adapter.`in`.web

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import study.cleanarchitecture.buckpalkotlin.account.application.port.`in`.SendMoneyCommand
import study.cleanarchitecture.buckpalkotlin.account.application.port.`in`.SendMoneyUseCase
import study.cleanarchitecture.buckpalkotlin.account.domain.AccountId
import study.cleanarchitecture.buckpalkotlin.account.domain.Money

@RestController
class SendMoneyController(
    private val sendMoneyUseCase: SendMoneyUseCase,
) {

    @PostMapping("/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
    fun sendMoney(
        @PathVariable("sourceAccountId") sourceAccountId: Long,
        @PathVariable("targetAccountId") targetAccountId: Long,
        @PathVariable("amount") amount: Long,
    ) {
        val command = SendMoneyCommand(
            sourceAccountId = AccountId(sourceAccountId),
            targetAccountId = AccountId(targetAccountId),
            money = Money.of(amount),
        )

        sendMoneyUseCase.sendMoney(command)
    }
}