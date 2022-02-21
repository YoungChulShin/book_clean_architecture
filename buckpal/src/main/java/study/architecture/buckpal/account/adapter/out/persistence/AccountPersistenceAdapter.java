package study.architecture.buckpal.account.adapter.out.persistence;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.architecture.buckpal.account.application.port.out.LoadAccountPort;
import study.architecture.buckpal.account.application.port.out.UpdateAccountStatePort;
import study.architecture.buckpal.account.domain.Account;
import study.architecture.buckpal.account.domain.Account.AccountId;
import study.architecture.buckpal.account.domain.Activity;

// 계층형 아키텍쳐에서 Reader, Store 기능을 담당
@RequiredArgsConstructor
@Component
public class AccountPersistenceAdapter implements LoadAccountPort, UpdateAccountStatePort {

  private final AccountRepository accountRepository;
  private final ActivityRepository activityRepository;
  private final AccountMapper accountMapper;

  @Override
  public Account loadAccount(AccountId accountId, LocalDateTime baseLineDate) {
    AccountJpaEntity account = accountRepository.findById(accountId.getValue())
        .orElseThrow(EntityNotFoundException::new);

    // 기준 시간 이후의 활동
    List<ActivityJpaEntity> activities =
        activityRepository.findByOwnerSince(accountId.getValue(), baseLineDate);

    // 기준 시간 이전의 입금 내역
    Long depositBalance = orZero(
        activityRepository.getDepositBalanceUntil(accountId.getValue(), baseLineDate));

    // 기준 시간 이전의 출금 내역
    Long withdrawalBalance = orZero(
        activityRepository.getWithdrawalBalanceUntil(accountId.getValue(), baseLineDate));

    return accountMapper.mapToDomainEntity(
        account,
        activities,
        withdrawalBalance,
        depositBalance);
  }

  private Long orZero(Long value) {
    return value == null ? 0L : value;
  }

  @Override
  public void updateActivities(Account account) {
    for (Activity activity : account.getActivityWindow().getActivities()) {
      if (activity.getId() == null) {
        activityRepository.save(accountMapper.mapToJpaEntity(activity));
      }
    }
  }
}
