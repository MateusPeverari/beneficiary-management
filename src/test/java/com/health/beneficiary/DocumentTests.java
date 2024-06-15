package com.health.beneficiary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.health.beneficiary.infrastructure.adapters.data.Document;
import com.health.beneficiary.infrastructure.output.persistence.entity.BeneficiaryEntity;
import com.health.beneficiary.infrastructure.output.persistence.entity.DocumentEntity;
import com.health.beneficiary.infrastructure.output.persistence.repository.BeneficiaryRepository;
import com.health.beneficiary.infrastructure.output.persistence.repository.DocumentRepository;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@AutoConfigureWireMock(port = 0)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DocumentTests {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private DocumentRepository documentRepository;
  @Autowired
  private BeneficiaryRepository beneficiaryRepository;
  private static final UUID BENEFICIARY_UUID = UUID.randomUUID();

  DocumentTests() {
  }

  @BeforeEach
  void init() {
    documentRepository.deleteAll();
    beneficiaryRepository.deleteAll();

    var beneficiaryEntity = new BeneficiaryEntity();
    beneficiaryEntity.setBeneficiaryId(BENEFICIARY_UUID);
    beneficiaryEntity.setNome("John Doe");
    beneficiaryEntity.setDataInclusao(LocalDateTime.now());
    beneficiaryEntity.setTelefone("11 999998888");
    beneficiaryEntity.setDataAtualizacao(LocalDateTime.now());
    beneficiaryEntity.setDocumentos(getDocuments());

    beneficiaryRepository.save(beneficiaryEntity);
  }

  @Test
  @DisplayName("Test get all documents from beneficiary")
  void getDocumentsTest() throws Exception {
    // GIVEN
    var beneficiaryId = BENEFICIARY_UUID.toString();

    // WHEN
    var mvcResult = getDocuments(beneficiaryId);

    // THEN
    var response = getDocumentsResponse(mvcResult);

    assertEquals(documentRepository.count(), response.size());
  }

  private MvcResult getDocuments(String beneficiaryId)
      throws Exception {
    return this.mockMvc.perform(get("/documents/" + beneficiaryId)).andReturn();
  }

  private List<Document> getDocumentsResponse(MvcResult documentResult)
      throws JsonProcessingException, UnsupportedEncodingException {
    return objectMapper.readValue(documentResult.getResponse().getContentAsString(),
        new TypeReference<List<Document>>() {
        });
  }

  private static List<DocumentEntity> getDocuments() {
    var CPF = new DocumentEntity();
    CPF.setDescricao("5987656587");
    CPF.setTipoDocumento("CPF");
    CPF.setBeneficiaryId(BENEFICIARY_UUID);
    CPF.setId(UUID.randomUUID());

    var RG = new DocumentEntity();
    RG.setDescricao("987654320");
    RG.setTipoDocumento("RG");
    RG.setBeneficiaryId(BENEFICIARY_UUID);
    RG.setId(UUID.randomUUID());

    var documentList = new ArrayList<DocumentEntity>();
    documentList.add(RG);
    documentList.add(CPF);

    return documentList;
  }

}
