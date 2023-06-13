package study.cleanarchitecture.buckpalkotlin.account.domain

import java.math.BigInteger

class Money(
    private val amount: BigInteger,
) {

    companion object {

        val ZERO: Money = Money.of(0L)

        fun of(value: Long) = Money(BigInteger.valueOf(value))

        fun add(a: Money, b: Money) = Money(a.amount.add(b.amount))

        fun subtract(a: Money, b: Money) = Money(a.amount.subtract(b.amount))
    }

    fun plus(money: Money) = Money(this.amount.add(money.amount))

    fun minus(money: Money) = Money(this.amount.minus(money.amount))

    fun negate() = Money(this.amount.negate())

    fun isPositiveOrZero() = this.amount >= BigInteger.ZERO

    fun isPositive() = this.amount > BigInteger.ZERO

    fun isNegative() = this.amount < BigInteger.ZERO

    fun isGreaterThanOrEqualTo(money:Money) = this.amount >= money.amount

    fun isGreaterThan(money:Money) = this.amount > money.amount

    override fun toString(): String {
        return amount.toString()
    }
}