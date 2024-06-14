package com.health.beneficiary.application.ports.output;

import com.health.beneficiary.domain.model.Document;
import java.util.List;

public interface DocumentPersistencePort {
  List<Document> listDocumentByBeneficiary(String beneficiaryId);
}
