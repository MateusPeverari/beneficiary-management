package com.health.beneficiary.infrastructure.output.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Beneficiary")
@EntityListeners(AuditingEntityListener.class)
public class BeneficiaryEntity {
  @Id
  private UUID beneficiaryId;

  private String nome;

  private String telefone;

  private LocalDate dataNascimento;

  @CreatedDate
  private LocalDateTime dataInclusao;

  @LastModifiedDate
  private LocalDateTime dataAtualizacao;

  @OneToMany(mappedBy = "beneficiaryId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<DocumentEntity> documentos;

}
