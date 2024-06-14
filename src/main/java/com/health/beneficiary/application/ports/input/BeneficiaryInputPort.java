package com.health.beneficiary.application.ports.input;

import com.health.beneficiary.domain.model.Beneficiary;
import java.util.List;

public interface BeneficiaryInputPort {
  Beneficiary createBeneficiary(Beneficiary beneficiary);

  List<Beneficiary> listAllBeneficiaries();

  Beneficiary updateBeneficiary(Beneficiary beneficiary, String beneficiaryId);
}
