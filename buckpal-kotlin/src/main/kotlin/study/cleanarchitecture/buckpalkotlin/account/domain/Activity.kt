package study.cleanarchitecture.buckpalkotlin.account.domain

import java.time.LocalDateTime

class Activity(
    val ownerAccountId: AccountId,
    val sourceAccountId: AccountId,
    val targetAccountId: AccountId,
    val timestamp: LocalDateTime,
    val money: Money,
)