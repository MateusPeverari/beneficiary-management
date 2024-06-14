package com.health.beneficiary.application.ports.input;

import com.health.beneficiary.domain.model.Beneficiary;

public interface BeneficiaryInputPort {
  Beneficiary createBeneficiary(Beneficiary beneficiary);
}
