package com.health.beneficiary.application.ports.input;

import com.health.beneficiary.domain.model.Document;
import java.util.List;

public interface DocumentInputPort {
  List<Document> listDocumentsByBeneficiary(String beneficiaryId);
}
