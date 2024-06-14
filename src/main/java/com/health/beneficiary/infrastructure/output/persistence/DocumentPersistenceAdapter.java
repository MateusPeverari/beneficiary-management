package com.health.beneficiary.infrastructure.output.persistence;

import com.health.beneficiary.application.ports.output.BeneficiaryPersistencePort;
import com.health.beneficiary.application.ports.output.DocumentPersistencePort;
import com.health.beneficiary.domain.exception.BeneficiaryErrors;
import com.health.beneficiary.domain.exception.BeneficiaryException;
import com.health.beneficiary.domain.model.Document;
import com.health.beneficiary.infrastructure.output.persistence.mapper.DocumentPersistenceMapper;
import com.health.beneficiary.infrastructure.output.persistence.repository.DocumentRepository;
import com.health.beneficiary.utils.Convertor;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DocumentPersistenceAdapter implements DocumentPersistencePort {
  private final DocumentRepository documentRepository;
  private final DocumentPersistenceMapper documentPersistenceMapper;
  private final BeneficiaryPersistencePort beneficiaryPersistencePort;

  @Override
  public List<Document> listDocumentByBeneficiary(String beneficiaryId) {
    //TODO IMPROVE BENEFICIARY NOT FOUND / NO DOCUMENTS
    log.info("Searching documents for beneficiary {}", beneficiaryId);
    var beneficiaryUuid = Convertor.convertToUuid(beneficiaryId);

   beneficiaryPersistencePort.findById(beneficiaryId);

    var documentEntityList = documentRepository.findAllByBeneficiaryId(beneficiaryUuid);

    if (documentEntityList.isEmpty()) {
      log.info("Beneficiary not found. id: {}", beneficiaryId);
      throw new BeneficiaryException(BeneficiaryErrors.BENEFICIARY_DOES_NOT_HAVE_DOCUMENTS);
    }

    log.info("Found {} documents for beneficiary {}", documentEntityList.size(), beneficiaryId);

    return documentPersistenceMapper.toDocumentList(documentEntityList);
  }
}
