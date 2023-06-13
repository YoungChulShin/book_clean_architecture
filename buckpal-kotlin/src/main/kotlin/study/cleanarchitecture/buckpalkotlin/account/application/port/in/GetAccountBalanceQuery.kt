package study.cleanarchitecture.buckpalkotlin.account.application.port.`in`

import study.cleanarchitecture.buckpalkotlin.account.domain.AccountId
import study.cleanarchitecture.buckpalkotlin.account.domain.Money

interface GetAccountBalanceQuery {

    fun getAccountBalance(accountId: AccountId): Money
}