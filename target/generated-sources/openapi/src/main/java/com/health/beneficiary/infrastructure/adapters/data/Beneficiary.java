package com.health.beneficiary.infrastructure.adapters.data;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.health.beneficiary.infrastructure.adapters.data.Document;
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
 * Beneficiary
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-13T11:42:47.949547-03:00[America/Sao_Paulo]")
public class Beneficiary {

  private String id;

  private String nome;

  private String telefone;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dataNascimento;

  @Valid
  private List<@Valid Document> documentos;

  public Beneficiary id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Beneficiary nome(String nome) {
    this.nome = nome;
    return this;
  }

  /**
   * Get nome
   * @return nome
  */
  
  @Schema(name = "nome", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nome")
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Beneficiary telefone(String telefone) {
    this.telefone = telefone;
    return this;
  }

  /**
   * Get telefone
   * @return telefone
  */
  
  @Schema(name = "telefone", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("telefone")
  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public Beneficiary dataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
    return this;
  }

  /**
   * Get dataNascimento
   * @return dataNascimento
  */
  @Valid 
  @Schema(name = "dataNascimento", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dataNascimento")
  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public Beneficiary documentos(List<@Valid Document> documentos) {
    this.documentos = documentos;
    return this;
  }

  public Beneficiary addDocumentosItem(Document documentosItem) {
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
  @Valid 
  @Schema(name = "documentos", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("documentos")
  public List<@Valid Document> getDocumentos() {
    return documentos;
  }

  public void setDocumentos(List<@Valid Document> documentos) {
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
    Beneficiary beneficiary = (Beneficiary) o;
    return Objects.equals(this.id, beneficiary.id) &&
        Objects.equals(this.nome, beneficiary.nome) &&
        Objects.equals(this.telefone, beneficiary.telefone) &&
        Objects.equals(this.dataNascimento, beneficiary.dataNascimento) &&
        Objects.equals(this.documentos, beneficiary.documentos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, telefone, dataNascimento, documentos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Beneficiary {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

