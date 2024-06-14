package com.health.beneficiary.domain.service;

import com.health.beneficiary.application.ports.input.DocumentInputPort;
import com.health.beneficiary.application.ports.output.DocumentPersistencePort;
import com.health.beneficiary.domain.model.Document;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class DocumentService implements DocumentInputPort {
  private final DocumentPersistencePort documentPersistencePort;
  @Override
  public List<Document> listDocumentsByBeneficiary(String beneficiaryId) {
    return documentPersistencePort.listDocumentByBeneficiary(beneficiaryId);
  }
}
