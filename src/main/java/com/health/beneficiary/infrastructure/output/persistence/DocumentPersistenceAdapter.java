package com.health.beneficiary.infrastructure.output.persistence;

import com.health.beneficiary.application.ports.output.DocumentPersistencePort;
import com.health.beneficiary.domain.exception.BeneficiaryErrors;
import com.health.beneficiary.domain.exception.BeneficiaryException;
import com.health.beneficiary.domain.model.Document;
import com.health.beneficiary.infrastructure.output.persistence.mapper.DocumentPersistenceMapper;
import com.health.beneficiary.infrastructure.output.persistence.repository.DocumentRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DocumentPersistenceAdapter implements DocumentPersistencePort {
  private final DocumentRepository documentRepository;
  private final DocumentPersistenceMapper documentPersistenceMapper;

  @Override
  public List<Document> listDocumentByBeneficiary(String beneficiaryId) {
    log.info("Searching documents for beneficiary {}", beneficiaryId);
    var beneficiaryUuid = convertToUuid(beneficiaryId);
    var documentEntityList = documentRepository.findAllByBeneficiaryId(beneficiaryUuid);

    if (documentEntityList.isEmpty()) {
      log.info("Beneficiary not found. id: {}", beneficiaryId);
      throw new BeneficiaryException(BeneficiaryErrors.BENEFICIARY_NOT_FOUND);
    }

    log.info("Found {} documents for beneficiary {}", documentEntityList.size(), beneficiaryId);

    return documentPersistenceMapper.toDocumentList(documentEntityList);
  }

  private UUID convertToUuid(String beneficiaryId) {
    try {
      return UUID.fromString(beneficiaryId);
    } catch (IllegalArgumentException e) {
      throw  new BeneficiaryException(BeneficiaryErrors.WRONG_ID_FORMAT);
    }
  }

}
