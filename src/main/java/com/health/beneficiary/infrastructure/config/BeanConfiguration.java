package com.health.beneficiary.infrastructure.config;

import com.health.beneficiary.application.ports.output.PersistenceOutputPort;
import com.health.beneficiary.domain.service.BeneficiaryService;
import com.health.beneficiary.infrastructure.output.persistence.PersistenceAdapter;
import com.health.beneficiary.infrastructure.output.persistence.mapper.PersistenceMapper;
import com.health.beneficiary.infrastructure.output.persistence.repository.BeneficiaryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
  @Bean
  public BeneficiaryService beneficiaryService(PersistenceOutputPort persistenceOutputPort) {
    return new BeneficiaryService(persistenceOutputPort);
  }

  @Bean
  public PersistenceAdapter eventPersistenceAdapter(BeneficiaryRepository beneficiaryRepository,
                                                    PersistenceMapper persistenceMapper) {
    return new PersistenceAdapter(beneficiaryRepository, persistenceMapper);
  }
}
