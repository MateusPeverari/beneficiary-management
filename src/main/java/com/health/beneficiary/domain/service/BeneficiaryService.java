package com.health.beneficiary.domain.service;

import com.health.beneficiary.application.ports.input.BeneficiaryInputPort;
import com.health.beneficiary.application.ports.output.PersistenceOutputPort;
import com.health.beneficiary.domain.model.Beneficiary;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class BeneficiaryService implements BeneficiaryInputPort {
  private final PersistenceOutputPort persistenceOutputPort;
  @Override
  public Beneficiary createBeneficiary(Beneficiary beneficiary) {
    log.info("Create beneficiary: {}", beneficiary);
    return persistenceOutputPort.save(beneficiary);
  }

  @Override
  public List<Beneficiary> listAllBeneficiaries() {
    return persistenceOutputPort.listAll();
  }
}
