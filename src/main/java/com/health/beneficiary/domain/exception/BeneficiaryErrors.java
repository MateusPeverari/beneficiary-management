package com.health.beneficiary.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum BeneficiaryErrors {
  BENEFICIARY_GENERIC_ERROR("BENEFICIARY_ERROR_9999", "Generic Error"),
  BENEFICIARY_VALIDATION_ERROR("BENEFICIARY_ERROR_9998", "Validation Error"),
  WRONG_ID_FORMAT("BENEFICIARY_ERROR_1000", "Id must be a UUID"),
  BENEFICIARY_NOT_FOUND("BENEFICIARY_ERROR_1001", "Beneficiary not found");

  private final String code;
  private final String message;
}
