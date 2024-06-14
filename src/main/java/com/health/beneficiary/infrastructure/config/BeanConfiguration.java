package com.health.beneficiary.infrastructure.config;

import com.health.beneficiary.application.ports.output.BeneficiaryPersistencePort;
import com.health.beneficiary.application.ports.output.DocumentPersistencePort;
import com.health.beneficiary.domain.service.BeneficiaryService;
import com.health.beneficiary.domain.service.DocumentService;
import com.health.beneficiary.infrastructure.output.persistence.BeneficiaryPersistenceAdapter;
import com.health.beneficiary.infrastructure.output.persistence.DocumentPersistenceAdapter;
import com.health.beneficiary.infrastructure.output.persistence.mapper.BeneficiaryPersistenceMapper;
import com.health.beneficiary.infrastructure.output.persistence.mapper.DocumentPersistenceMapper;
import com.health.beneficiary.infrastructure.output.persistence.repository.BeneficiaryRepository;
import com.health.beneficiary.infrastructure.output.persistence.repository.DocumentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
  @Bean
  public BeneficiaryService beneficiaryService(
      BeneficiaryPersistencePort beneficiaryPersistencePort) {
    return new BeneficiaryService(beneficiaryPersistencePort);
  }

  @Bean
  public BeneficiaryPersistenceAdapter eventPersistenceAdapter(
      BeneficiaryRepository beneficiaryRepository,
      BeneficiaryPersistenceMapper beneficiaryPersistenceMapper) {
    return new BeneficiaryPersistenceAdapter(beneficiaryRepository, beneficiaryPersistenceMapper);
  }


  @Bean
  public DocumentService documentService(DocumentPersistencePort documentPersistencePort) {
    return new DocumentService(documentPersistencePort);
  }

  @Bean
  public DocumentPersistenceAdapter documentPersistenceAdapter(
      DocumentRepository documentRepository, DocumentPersistenceMapper documentPersistenceMapper,
      BeneficiaryPersistencePort beneficiaryPersistencePort) {
    return new DocumentPersistenceAdapter(documentRepository, documentPersistenceMapper,
        beneficiaryPersistencePort);
  }
}
