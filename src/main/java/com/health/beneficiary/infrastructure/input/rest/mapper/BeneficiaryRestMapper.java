package com.health.beneficiary.infrastructure.input.rest.mapper;

import com.health.beneficiary.domain.model.Beneficiary;
import com.health.beneficiary.infrastructure.adapters.data.BeneficiaryRequest;
import com.health.beneficiary.infrastructure.adapters.data.BeneficiaryResponse;
import com.health.beneficiary.infrastructure.adapters.data.BeneficiarySummary;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BeneficiaryRestMapper {
  Beneficiary toBeneficiary(BeneficiaryRequest beneficiaryRequest);

  @Mapping(source = "beneficiaryId", target = "id")
  BeneficiaryResponse toBeneficiaryResponse(Beneficiary beneficiary);

  @Mapping(source = "beneficiaryId", target = "id")
  List<BeneficiarySummary> toBeneficiarySummary(List<Beneficiary> beneficiary);

  @Mapping(source = "beneficiaryId", target = "id")
  BeneficiarySummary toBeneficiarySummary(Beneficiary beneficiary);
}
