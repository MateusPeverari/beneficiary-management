package com.health.beneficiary.application.ports.output;

import com.health.beneficiary.domain.model.Beneficiary;

public interface PersistenceOutputPort {
  Beneficiary save(Beneficiary beneficiary);
}
