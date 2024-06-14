package com.health.beneficiary.domain.service;

import com.health.beneficiary.application.ports.input.BeneficiaryInputPort;
import com.health.beneficiary.application.ports.output.BeneficiaryPersistencePort;
import com.health.beneficiary.domain.model.Beneficiary;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class BeneficiaryService implements BeneficiaryInputPort {
  private final BeneficiaryPersistencePort beneficiaryPersistencePort;
  @Override
  public Beneficiary createBeneficiary(Beneficiary beneficiary) {
    log.info("Create beneficiary: {}", beneficiary);
    return beneficiaryPersistencePort.save(beneficiary);
  }

  @Override
  public List<Beneficiary> listAllBeneficiaries() {
    return beneficiaryPersistencePort.listAll();
  }

  @Override
  public Beneficiary updateBeneficiary(Beneficiary beneficiary, String beneficiaryId) {
    log.info("Update beneficiary {}", beneficiaryId);
    return beneficiaryPersistencePort.update(beneficiary, beneficiaryId);
  }
}
