package study.cleanarchitecture.buckpalkotlin.account.domain

import java.time.LocalDateTime

class Account(
    /**
     * ID
     */
    private val id: AccountId,
    /**
     * activityWindow 이전의 금액
     */
    private val baselineBalance: Money,
    /**
     * 변경 내역. SlidingWindows 처럼 최근 일정 기간 동안의 내역을 가진다
     */
    private val activityWindow: ActivityWindow,
) {
    /**
     * 잔액 계산
     */
    fun calculateBalance(): Money {
        return Money.add(this.baselineBalance, this.activityWindow.calculateBalance(id))
    }

    /**
     * 출금
     */
    fun withdraw(money: Money, targetAccountId: AccountId): Boolean {
        if (!mayWithdraw(money)) {
            return false
        }

        val withdrawal = Activity(
            ownerAccountId = this.id,
            sourceAccountId = this.id,
            targetAccountId = targetAccountId,
            timestamp = LocalDateTime.now(),
            money = money
        )

        this.activityWindow.addActivity(withdrawal)
        return true
    }

    private fun mayWithdraw(money: Money): Boolean {
        return Money.subtract(this.calculateBalance(), money).isPositive()
    }

    fun deposit(money: Money, sourceAccountId: AccountId): Boolean {
        val deposit = Activity(
            ownerAccountId = this.id,
            sourceAccountId = sourceAccountId,
            targetAccountId = this.id,
            timestamp = LocalDateTime.now(),
            money = money
        )

        this.activityWindow.addActivity(deposit)
        return true
    }
}