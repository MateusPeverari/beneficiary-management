package com.health.beneficiary.infrastructure.input.rest;

import com.health.beneficiary.application.ports.input.DocumentInputPort;
import com.health.beneficiary.infrastructure.adapters.DocumentsApi;
import com.health.beneficiary.infrastructure.adapters.data.Document;
import com.health.beneficiary.infrastructure.input.rest.mapper.DocumentRestMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class DocumentController implements DocumentsApi {
  private final DocumentInputPort documentInputPort;
  private final DocumentRestMapper documentRestMapper;
  @Override
  public ResponseEntity<List<Document>> getBeneficiaryDocuments(String beneficiaryId) {
    log.info("Request to list all documents for beneficiary: {}", beneficiaryId);

    var documentList = documentInputPort.listDocumentsByBeneficiary(beneficiaryId);
    return ResponseEntity.ok(documentRestMapper.toDocumentResponse(documentList));
  }
}
