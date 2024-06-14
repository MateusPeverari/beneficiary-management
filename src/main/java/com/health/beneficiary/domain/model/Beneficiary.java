package com.health.beneficiary.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class Beneficiary {
  private UUID beneficiaryId;
  private String nome;
  private String telefone;
  private LocalDate dataNascimento;
  private LocalDateTime dataInclusao;
  private LocalDateTime dataAtualizacao;
  private List<Document> documentos;
}
