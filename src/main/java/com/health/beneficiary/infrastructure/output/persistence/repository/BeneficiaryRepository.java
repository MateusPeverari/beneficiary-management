package com.health.beneficiary.infrastructure.output.persistence.repository;

import com.health.beneficiary.infrastructure.output.persistence.entity.BeneficiaryEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<BeneficiaryEntity, UUID> {
}
