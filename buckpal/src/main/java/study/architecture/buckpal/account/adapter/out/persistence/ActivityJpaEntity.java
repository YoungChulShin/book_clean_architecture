package study.architecture.buckpal.account.adapter.out.persistence;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "activity")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private LocalDateTime timestamp;

  @Column
  private Long ownerAccountId;

  @Column
  private Long sourceAccountId;

  @Column
  private Long targetAccountId;

  @Column
  private Long amount;
}
