package com.health.beneficiary.domain.exception;

import lombok.Data;

@Data
public class BeneficiaryException extends RuntimeException {
  private final BeneficiaryErrors beneficiaryErrors;
}
