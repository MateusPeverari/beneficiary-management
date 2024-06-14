package com.health.beneficiary.infrastructure.input.rest;

import com.health.beneficiary.application.ports.input.BeneficiaryInputPort;
import com.health.beneficiary.infrastructure.adapters.BeneficiariesApi;
import com.health.beneficiary.infrastructure.adapters.data.BeneficiaryRequest;
import com.health.beneficiary.infrastructure.adapters.data.BeneficiaryResponse;
import com.health.beneficiary.infrastructure.adapters.data.BeneficiarySummary;
import com.health.beneficiary.infrastructure.adapters.data.UpdateBeneficiaryRequest;
import com.health.beneficiary.infrastructure.input.rest.mapper.BeneficiaryRestMapper;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class BeneficiaryController implements BeneficiariesApi {
  private final BeneficiaryInputPort beneficiaryInputPort;
  private final BeneficiaryRestMapper beneficiaryRestMapper;

  @Override
  public ResponseEntity<BeneficiaryResponse> createBeneficiary(
      BeneficiaryRequest beneficiaryRequest) {
    log.info("Request to create beneficiary: {}", beneficiaryRequest);
    var beneficiaryCreated = beneficiaryInputPort.createBeneficiary(
        beneficiaryRestMapper.toBeneficiary(beneficiaryRequest));

    var beneficiaryResponse = beneficiaryRestMapper.toBeneficiaryResponse(beneficiaryCreated);
    log.info("Beneficiary created. Sending response: {}", beneficiaryResponse);

    return ResponseEntity.status(HttpStatus.CREATED).body(beneficiaryResponse);
  }

  @Override
  public ResponseEntity<List<BeneficiarySummary>> listAllBeneficiaries() {
    log.info("Request to list all beneficiaries");
    var beneficiariesList = beneficiaryInputPort.listAllBeneficiaries();

    var beneficiariesResponse = beneficiaryRestMapper.toBeneficiarySummary(beneficiariesList);

    return ResponseEntity.ok(beneficiariesResponse);
  }

  @Override
  public ResponseEntity<BeneficiaryResponse> updateBeneficiary(String beneficiaryId,
                                                               UpdateBeneficiaryRequest updateBeneficiaryRequest) {
    log.info("Request to update beneficiary: {}", beneficiaryId);
    var beneficiaryUpdated = beneficiaryInputPort.updateBeneficiary(
        beneficiaryRestMapper.toBeneficiary(updateBeneficiaryRequest), beneficiaryId);

    var beneficiaryResponse = beneficiaryRestMapper.toBeneficiaryResponse(beneficiaryUpdated);
    log.info("Beneficiary created. Sending response: {}", beneficiaryResponse);

    return ResponseEntity.status(HttpStatus.CREATED).body(beneficiaryResponse);
  }
}
