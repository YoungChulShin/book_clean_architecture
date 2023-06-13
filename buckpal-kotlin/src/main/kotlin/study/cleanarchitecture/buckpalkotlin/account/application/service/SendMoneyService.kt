package study.cleanarchitecture.buckpalkotlin.account.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.cleanarchitecture.buckpalkotlin.account.application.port.`in`.SendMoneyCommand
import study.cleanarchitecture.buckpalkotlin.account.application.port.`in`.SendMoneyUseCase
import study.cleanarchitecture.buckpalkotlin.account.application.port.out.AccountLockPort
import study.cleanarchitecture.buckpalkotlin.account.application.port.out.LoadAccountPort
import study.cleanarchitecture.buckpalkotlin.account.application.port.out.UpdateAccountStatePort

@Service
class SendMoneyService(
    private val loadAccountPort: LoadAccountPort,
    private val accountLockPort: AccountLockPort,
    private val updateAccountStatePort: UpdateAccountStatePort,
) : SendMoneyUseCase {

    @Transactional
    override fun sendMoney(command: SendMoneyCommand): Boolean {
        // TODO: 비니지스 모델 규칙 검증
        // TODO: 모델 상태 조작
        // TODO: 출력 값 변환
    }
}