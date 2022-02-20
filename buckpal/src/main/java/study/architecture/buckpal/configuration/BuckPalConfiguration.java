package study.architecture.buckpal.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.architecture.buckpal.account.application.service.MoneyTransferProperties;
import study.architecture.buckpal.account.domain.Money;

@Configuration
@EnableConfigurationProperties(value = {BuckPalConfigurationProperties.class})
public class BuckPalConfiguration {

  @Bean
  public MoneyTransferProperties moneyTransferProperties(
      BuckPalConfigurationProperties buckPalConfigurationProperties) {
    return new MoneyTransferProperties(
        Money.of(buckPalConfigurationProperties.getTransferThreshold()));
  }
}
