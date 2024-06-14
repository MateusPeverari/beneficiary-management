package com.health.beneficiary.infrastructure.output.persistence.mapper;

import com.health.beneficiary.domain.model.Beneficiary;
import com.health.beneficiary.infrastructure.output.persistence.entity.BeneficiaryEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PersistenceMapper {
  BeneficiaryEntity toBeneficiaryEntity(Beneficiary beneficiary);

  Beneficiary toBeneficiary(BeneficiaryEntity beneficiaryEntity);
}
