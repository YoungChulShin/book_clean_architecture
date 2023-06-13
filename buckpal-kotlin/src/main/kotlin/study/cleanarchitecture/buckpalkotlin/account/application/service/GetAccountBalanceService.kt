package study.cleanarchitecture.buckpalkotlin.account.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.cleanarchitecture.buckpalkotlin.account.application.port.`in`.GetAccountBalanceQuery
import study.cleanarchitecture.buckpalkotlin.account.application.port.out.LoadAccountPort
import study.cleanarchitecture.buckpalkotlin.account.domain.AccountId
import study.cleanarchitecture.buckpalkotlin.account.domain.Money
import java.time.LocalDateTime

@Service
class GetAccountBalanceService(
    private val loadAccountPort: LoadAccountPort,
) : GetAccountBalanceQuery {

    @Transactional(readOnly = true)
    override fun getAccountBalance(accountId: AccountId): Money {
        return loadAccountPort.loadAccount(accountId, LocalDateTime.now())
            ?.calculateBalance() ?: throw IllegalArgumentException("Can't find account")
    }
}