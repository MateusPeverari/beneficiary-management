package com.health.beneficiary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.health.beneficiary.infrastructure.adapters.data.BeneficiaryRequest;
import com.health.beneficiary.infrastructure.adapters.data.BeneficiaryResponse;
import com.health.beneficiary.infrastructure.adapters.data.BeneficiarySummary;
import com.health.beneficiary.infrastructure.adapters.data.DeleteResponse;
import com.health.beneficiary.infrastructure.adapters.data.DocumentRequest;
import com.health.beneficiary.infrastructure.adapters.data.UpdateBeneficiaryRequest;
import com.health.beneficiary.infrastructure.output.persistence.repository.BeneficiaryRepository;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
class BeneficiaryTests {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private BeneficiaryRepository beneficiaryRepository;

  BeneficiaryTests() {
  }

  @BeforeEach
  void init() {
    beneficiaryRepository.deleteAll();
  }

  @Test
  @DisplayName("Test create a beneficiary")
  void createBeneficiaryTest() throws Exception {
    // GIVEN
    var beneficiaryRequest = getBeneficiaryRequest();

    // WHEN
    var mvcResult = createBeneficiary(objectMapper.writeValueAsString(beneficiaryRequest));

    // THEN
    var response = getBeneficiaryResponse(mvcResult);

    assertEquals(beneficiaryRequest.getDocumentos().size(), response.getDocumentos().size());
    assertEquals(beneficiaryRequest.getNome(), response.getNome());
    assertEquals(beneficiaryRequest.getTelefone(), response.getTelefone());
    assertEquals(beneficiaryRequest.getDataNascimento(), response.getDataNascimento());
    assertNotNull(response.getId());
    assertEquals(
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")),
        response.getDataInclusao().substring(0, 16));
  }


  @Test
  @DisplayName("Test update a beneficiary")
  void updateBeneficiaryTest() throws Exception {
    // GIVEN
    var beneficiaryUpdateRequest = getUpdateBeneficiaryRequest();
    var beneficiaryRequest = getBeneficiaryRequest();
    var beneficiaryCreatedResponse =
        createBeneficiary(objectMapper.writeValueAsString(beneficiaryRequest));
    var beneficiaryCreated = getBeneficiaryResponse(beneficiaryCreatedResponse);

    // WHEN
    var mvcResult = updateBeneficiary(objectMapper.writeValueAsString(beneficiaryUpdateRequest),
        beneficiaryCreated.getId());

    // THEN
    var response = getBeneficiaryResponse(mvcResult);

    assertEquals(beneficiaryUpdateRequest.getNome(), response.getNome());
    assertEquals(beneficiaryUpdateRequest.getTelefone(), response.getTelefone());
    assertEquals(beneficiaryUpdateRequest.getDataNascimento(), response.getDataNascimento());
    assertNotNull(response.getId());
    assertEquals(
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")),
        response.getDataInclusao().substring(0, 16));
  }

  @Test
  @DisplayName("Test get all beneficiaries")
  void getBeneficiariesTest() throws Exception {
    // GIVEN
    var beneficiaryRequest = getBeneficiaryRequest();
    var beneficiaryCreatedResponse =
        createBeneficiary(objectMapper.writeValueAsString(beneficiaryRequest));
    getBeneficiaryResponse(beneficiaryCreatedResponse);

    // WHEN
    var mvcResult = getAllBeneficiaries();

    // THEN
    var response = getAllBeneficiariesResponse(mvcResult);

    assertEquals(beneficiaryRepository.count(), response.size());
  }

  @Test
  @DisplayName("Test delete a beneficiary")
  void deleteBeneficiaryTest() throws Exception {
    // GIVEN
    var beneficiaryRequest = getBeneficiaryRequest();
    var beneficiaryCreatedResponse =
        createBeneficiary(objectMapper.writeValueAsString(beneficiaryRequest));
    getBeneficiaryResponse(beneficiaryCreatedResponse);
    var beneficiaryCreated = getBeneficiaryResponse(beneficiaryCreatedResponse);

    // WHEN
    var mvcResult = deleteBeneficiary(beneficiaryCreated.getId());

    // THEN
    var response = getDeleteResponse(mvcResult);

    assertEquals(0, beneficiaryRepository.count());
    assertEquals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")),
        response.getDeletedAt().toString().substring(0, 16));
    assertEquals("Beneficiary deleted successfully!", response.getMessage());
  }


  private static BeneficiaryRequest getBeneficiaryRequest() {
    var beneficiaryRequest = new BeneficiaryRequest();
    beneficiaryRequest.setNome("John Doe");
    beneficiaryRequest.setTelefone("11 999998888");
    beneficiaryRequest.setDataNascimento(LocalDate.parse("1990-01-01"));
    beneficiaryRequest.setDocumentos(getDocumentRequest());

    return beneficiaryRequest;
  }

  private static UpdateBeneficiaryRequest getUpdateBeneficiaryRequest() {
    var beneficiaryRequest = new UpdateBeneficiaryRequest();
    beneficiaryRequest.setNome("Jane Doe");
    beneficiaryRequest.setTelefone("11 999997777");
    beneficiaryRequest.setDataNascimento(LocalDate.parse("1980-01-01"));

    return beneficiaryRequest;
  }

  private static List<DocumentRequest> getDocumentRequest() {
    var documentRequest = new DocumentRequest();
    documentRequest.setDescricao("5987656587");
    documentRequest.setTipoDocumento("CPF");

    var documentList = new ArrayList<DocumentRequest>();
    documentList.add(documentRequest);

    return documentList;
  }

  private MvcResult createBeneficiary(String beneficiaryRequest) throws Exception {
    return this.mockMvc.perform(post("/beneficiaries")
            .contentType(APPLICATION_JSON)
            .content(beneficiaryRequest))
        .andReturn();
  }

  private MvcResult updateBeneficiary(String updateBeneficiaryRequest, String beneficiaryId)
      throws Exception {
    return this.mockMvc.perform(put("/beneficiaries/" + beneficiaryId)
            .contentType(APPLICATION_JSON)
            .content(updateBeneficiaryRequest))
        .andReturn();
  }

  private MvcResult getAllBeneficiaries()
      throws Exception {
    return this.mockMvc.perform(get("/beneficiaries")).andReturn();
  }

  private MvcResult deleteBeneficiary(String beneficiaryId)
      throws Exception {
    return this.mockMvc.perform(delete("/beneficiaries/" + beneficiaryId)).andReturn();
  }

  private BeneficiaryResponse getBeneficiaryResponse(MvcResult beneficiaryResult)
      throws JsonProcessingException, UnsupportedEncodingException {
    return objectMapper.readValue(beneficiaryResult.getResponse().getContentAsString(),
        BeneficiaryResponse.class);
  }

  private List<BeneficiarySummary> getAllBeneficiariesResponse(MvcResult beneficiaryResult)
      throws JsonProcessingException, UnsupportedEncodingException {
    return objectMapper.readValue(beneficiaryResult.getResponse().getContentAsString(),
        new TypeReference<List<BeneficiarySummary>>() {
        });
  }

  private DeleteResponse getDeleteResponse(MvcResult beneficiaryResult)
      throws JsonProcessingException, UnsupportedEncodingException {
    return objectMapper.readValue(beneficiaryResult.getResponse().getContentAsString(),
        DeleteResponse.class);
  }

}
