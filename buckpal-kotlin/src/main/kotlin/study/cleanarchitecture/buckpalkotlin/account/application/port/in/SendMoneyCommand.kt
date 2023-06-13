package study.cleanarchitecture.buckpalkotlin.account.application.port.`in`

import study.cleanarchitecture.buckpalkotlin.account.domain.AccountId
import study.cleanarchitecture.buckpalkotlin.account.domain.Money

class SendMoneyCommand(
    val sourceAccountId: AccountId,
    val targetAccountId: AccountId,
    val money: Money,
) {

    init {
        if (!money.isGreaterThan(Money.ZERO)) {
            throw IllegalArgumentException("Money should greater than zero")
        }
    }
}