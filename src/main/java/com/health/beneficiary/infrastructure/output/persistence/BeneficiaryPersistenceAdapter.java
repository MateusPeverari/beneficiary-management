package com.health.beneficiary.infrastructure.output.persistence;

import com.health.beneficiary.application.ports.output.BeneficiaryPersistencePort;
import com.health.beneficiary.domain.exception.BeneficiaryErrors;
import com.health.beneficiary.domain.exception.BeneficiaryException;
import com.health.beneficiary.domain.model.Beneficiary;
import com.health.beneficiary.infrastructure.output.persistence.mapper.BeneficiaryPersistenceMapper;
import com.health.beneficiary.infrastructure.output.persistence.repository.BeneficiaryRepository;
import com.health.beneficiary.utils.Convertor;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BeneficiaryPersistenceAdapter implements BeneficiaryPersistencePort {
  private final BeneficiaryRepository beneficiaryRepository;
  private final BeneficiaryPersistenceMapper beneficiaryPersistenceMapper;

  @Override
  public Beneficiary save(Beneficiary beneficiary) {
    final var beneficiaryEntity = beneficiaryPersistenceMapper.toBeneficiaryEntity(beneficiary);
    beneficiaryEntity.setBeneficiaryId(UUID.randomUUID());

    var documents = beneficiaryEntity.getDocumentos();
    documents.forEach(
        documentEntity -> documentEntity.setBeneficiaryId(beneficiaryEntity.getBeneficiaryId()));

    var savedBeneficiaryEntity = beneficiaryRepository.save(beneficiaryEntity);
    log.info("Saved beneficiary {}", beneficiary);
    return beneficiaryPersistenceMapper.toBeneficiary(savedBeneficiaryEntity);
  }

  @Override
  public List<Beneficiary> listAll() {
    log.info("Listing all beneficiaries.");
    var beneficiaryList = beneficiaryRepository.findAll();
    log.info("Listing {} beneficiaries.", beneficiaryList.size());

    return beneficiaryPersistenceMapper.toBeneficiaryList(beneficiaryList);
  }

  @Override
  public Beneficiary findById(String beneficiaryId) {
    log.info("Search beneficiary: {}", beneficiaryId);
    var beneficiaryUuid = Convertor.convertToUuid(beneficiaryId);
    var beneficiaryOptional = beneficiaryRepository.findById(beneficiaryUuid);

    if (beneficiaryOptional.isEmpty()) {
      log.info("Beneficiary not found. id {}", beneficiaryId);
      throw new BeneficiaryException(BeneficiaryErrors.BENEFICIARY_NOT_FOUND);
    }
    return beneficiaryPersistenceMapper.toBeneficiary(beneficiaryOptional.get());
  }

  @Override
  public Beneficiary update(Beneficiary beneficiary, String beneficiaryId) {
    log.info("Updating beneficiary {}", beneficiary);
    var beneficiarySaved = findById(beneficiaryId);
    mergeBeneficiary(beneficiary, beneficiarySaved);
    var beneficiaryEntity =
        beneficiaryRepository.save(beneficiaryPersistenceMapper.toBeneficiaryEntity(beneficiary));
    return beneficiaryPersistenceMapper.toBeneficiary(beneficiaryEntity);
  }

  private void mergeBeneficiary(Beneficiary beneficiary, Beneficiary beneficiarySaved) {
    beneficiary.setBeneficiaryId(beneficiarySaved.getBeneficiaryId());
    beneficiary.setDataInclusao(beneficiarySaved.getDataInclusao());
    beneficiary.setDocumentos(beneficiarySaved.getDocumentos());
  }
}
