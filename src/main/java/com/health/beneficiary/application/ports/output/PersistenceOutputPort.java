package com.health.beneficiary.application.ports.output;

import com.health.beneficiary.domain.model.Beneficiary;
import java.util.List;

public interface PersistenceOutputPort {
  Beneficiary save(Beneficiary beneficiary);

  List<Beneficiary> listAll();
}
