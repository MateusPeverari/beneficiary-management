package com.health.beneficiary.infrastructure.output.persistence.mapper;

import com.health.beneficiary.domain.model.Document;
import com.health.beneficiary.infrastructure.output.persistence.entity.DocumentEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface DocumentPersistenceMapper {

  List<Document> toDocumentList(List<DocumentEntity> documentEntitieList);
}
