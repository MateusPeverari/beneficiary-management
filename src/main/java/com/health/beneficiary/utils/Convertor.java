package com.health.beneficiary.utils;

import com.health.beneficiary.domain.exception.BeneficiaryErrors;
import com.health.beneficiary.domain.exception.BeneficiaryException;
import java.util.UUID;

public class Convertor {
  public static UUID convertToUuid(String beneficiaryId) {
    try {
      return UUID.fromString(beneficiaryId);
    } catch (IllegalArgumentException e) {
      throw  new BeneficiaryException(BeneficiaryErrors.WRONG_ID_FORMAT);
    }
  }
}
