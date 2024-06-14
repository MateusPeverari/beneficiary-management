package com.health.beneficiary.infrastructure.output.persistence.repository;

import com.health.beneficiary.infrastructure.output.persistence.entity.DocumentEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, UUID> {
  List<DocumentEntity> findAllByBeneficiaryId(UUID beneficiaryId);
}
