package study.cleanarchitecture.buckpalkotlin.account.application.port.out

import study.cleanarchitecture.buckpalkotlin.account.domain.Account
import study.cleanarchitecture.buckpalkotlin.account.domain.AccountId
import java.time.LocalDateTime

interface LoadAccountPort {

    fun loadAccount(accountId: AccountId, baseLineDate: LocalDateTime): Account?
}