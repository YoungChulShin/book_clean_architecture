package study.cleanarchitecture.buckpalkotlin.account.application.port.`in`

interface SendMoneyUseCase {

    fun sendMoney(command: SendMoneyCommand): Boolean
}