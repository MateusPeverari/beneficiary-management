package com.health.beneficiary.infrastructure.adapters.data;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.health.beneficiary.infrastructure.adapters.data.DocumentRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * BeneficiaryRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-13T11:42:47.949547-03:00[America/Sao_Paulo]")
public class BeneficiaryRequest {

  private String nome;

  private String telefone;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dataNascimento;

  @Valid
  private List<@Valid DocumentRequest> documentos = new ArrayList<>();

  /**
   * Default constructor
   * @deprecated Use {@link BeneficiaryRequest#BeneficiaryRequest(String, String, LocalDate, List<@Valid DocumentRequest>)}
   */
  @Deprecated
  public BeneficiaryRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public BeneficiaryRequest(String nome, String telefone, LocalDate dataNascimento, List<@Valid DocumentRequest> documentos) {
    this.nome = nome;
    this.telefone = telefone;
    this.dataNascimento = dataNascimento;
    this.documentos = documentos;
  }

  public BeneficiaryRequest nome(String nome) {
    this.nome = nome;
    return this;
  }

  /**
   * Get nome
   * @return nome
  */
  @NotNull 
  @Schema(name = "nome", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("nome")
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public BeneficiaryRequest telefone(String telefone) {
    this.telefone = telefone;
    return this;
  }

  /**
   * Get telefone
   * @return telefone
  */
  @NotNull 
  @Schema(name = "telefone", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("telefone")
  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public BeneficiaryRequest dataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
    return this;
  }

  /**
   * Get dataNascimento
   * @return dataNascimento
  */
  @NotNull @Valid 
  @Schema(name = "dataNascimento", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("dataNascimento")
  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public BeneficiaryRequest documentos(List<@Valid DocumentRequest> documentos) {
    this.documentos = documentos;
    return this;
  }

  public BeneficiaryRequest addDocumentosItem(DocumentRequest documentosItem) {
    if (this.documentos == null) {
      this.documentos = new ArrayList<>();
    }
    this.documentos.add(documentosItem);
    return this;
  }

  /**
   * Get documentos
   * @return documentos
  */
  @NotNull @Valid 
  @Schema(name = "documentos", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("documentos")
  public List<@Valid DocumentRequest> getDocumentos() {
    return documentos;
  }

  public void setDocumentos(List<@Valid DocumentRequest> documentos) {
    this.documentos = documentos;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BeneficiaryRequest beneficiaryRequest = (BeneficiaryRequest) o;
    return Objects.equals(this.nome, beneficiaryRequest.nome) &&
        Objects.equals(this.telefone, beneficiaryRequest.telefone) &&
        Objects.equals(this.dataNascimento, beneficiaryRequest.dataNascimento) &&
        Objects.equals(this.documentos, beneficiaryRequest.documentos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nome, telefone, dataNascimento, documentos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BeneficiaryRequest {\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    telefone: ").append(toIndentedString(telefone)).append("\n");
    sb.append("    dataNascimento: ").append(toIndentedString(dataNascimento)).append("\n");
    sb.append("    documentos: ").append(toIndentedString(documentos)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

