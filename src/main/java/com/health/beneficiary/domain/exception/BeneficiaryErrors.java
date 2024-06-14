package com.health.beneficiary.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum BeneficiaryErrors {
  BENEFICIARY_GENERIC_ERROR("BENEFICIARY_ERROR_9999", "Generic Error"),
  BENEFICIARY_VALIDATION_ERROR("BENEFICIARY_ERROR_9998", "Validation Error");

  private final String code;
  private final String message;
}
