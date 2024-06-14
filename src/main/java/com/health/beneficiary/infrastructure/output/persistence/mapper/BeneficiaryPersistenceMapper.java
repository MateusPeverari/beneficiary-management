package com.health.beneficiary.infrastructure.output.persistence.mapper;

import com.health.beneficiary.domain.model.Beneficiary;
import com.health.beneficiary.infrastructure.output.persistence.entity.BeneficiaryEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface BeneficiaryPersistenceMapper {
  BeneficiaryEntity toBeneficiaryEntity(Beneficiary beneficiary);

  Beneficiary toBeneficiary(BeneficiaryEntity beneficiaryEntity);

  List<Beneficiary> toBeneficiaryList(List<BeneficiaryEntity> beneficiaryEntityList);
}
