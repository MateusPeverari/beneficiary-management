package com.health.beneficiary.infrastructure.input.rest;

import com.health.beneficiary.application.ports.input.BeneficiaryInputPort;
import com.health.beneficiary.infrastructure.adapters.BeneficiariesApi;
import com.health.beneficiary.infrastructure.adapters.data.BeneficiaryRequest;
import com.health.beneficiary.infrastructure.adapters.data.BeneficiaryResponse;
import com.health.beneficiary.infrastructure.input.rest.mapper.BeneficiaryRestMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    return ResponseEntity.ok(beneficiaryResponse);
  }
}
