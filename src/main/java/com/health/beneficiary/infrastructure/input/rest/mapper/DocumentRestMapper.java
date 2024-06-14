package com.health.beneficiary.infrastructure.input.rest.mapper;

import com.health.beneficiary.infrastructure.adapters.data.Document;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface DocumentRestMapper {
  List<Document> toDocumentResponse(List<com.health.beneficiary.domain.model.Document> document);
}
