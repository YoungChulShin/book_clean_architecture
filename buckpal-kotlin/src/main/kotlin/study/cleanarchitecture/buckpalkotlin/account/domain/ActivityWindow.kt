package study.cleanarchitecture.buckpalkotlin.account.domain

class ActivityWindow(
    private val activities: MutableList<Activity>,
) {

    fun calculateBalance(id: AccountId): Money {
        val depositBalance = activities.stream()
            .filter { it.targetAccountId == id }
            .map { it.money }
            .reduce(Money.ZERO, Money::add)

        val withdrawalBalance = activities.stream()
            .filter { it.sourceAccountId == id }
            .map { it.money }
            .reduce(Money.ZERO, Money::add)

        return Money.add(depositBalance, withdrawalBalance.negate())
    }

    fun addActivity(activity: Activity) = this.activities.add(activity)
}
