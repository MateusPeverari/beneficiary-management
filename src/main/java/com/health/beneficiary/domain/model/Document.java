package com.health.beneficiary.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class Document {
  private UUID id;
  private String tipoDocumento;
  private String descricao;
  private LocalDateTime dataInclusao;
  private LocalDateTime dataAtualizacao;
}
