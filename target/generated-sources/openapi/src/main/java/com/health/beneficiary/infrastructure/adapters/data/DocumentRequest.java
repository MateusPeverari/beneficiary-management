package com.health.beneficiary.infrastructure.adapters.data;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * DocumentRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-13T11:42:47.949547-03:00[America/Sao_Paulo]")
public class DocumentRequest {

  private String tipoDocumento;

  private String descricao;

  /**
   * Default constructor
   * @deprecated Use {@link DocumentRequest#DocumentRequest(String, String)}
   */
  @Deprecated
  public DocumentRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public DocumentRequest(String tipoDocumento, String descricao) {
    this.tipoDocumento = tipoDocumento;
    this.descricao = descricao;
  }

  public DocumentRequest tipoDocumento(String tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
    return this;
  }

  /**
   * Get tipoDocumento
   * @return tipoDocumento
  */
  @NotNull 
  @Schema(name = "tipoDocumento", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("tipoDocumento")
  public String getTipoDocumento() {
    return tipoDocumento;
  }

  public void setTipoDocumento(String tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
  }

  public DocumentRequest descricao(String descricao) {
    this.descricao = descricao;
    return this;
  }

  /**
   * Get descricao
   * @return descricao
  */
  @NotNull 
  @Schema(name = "descricao", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("descricao")
  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocumentRequest documentRequest = (DocumentRequest) o;
    return Objects.equals(this.tipoDocumento, documentRequest.tipoDocumento) &&
        Objects.equals(this.descricao, documentRequest.descricao);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tipoDocumento, descricao);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DocumentRequest {\n");
    sb.append("    tipoDocumento: ").append(toIndentedString(tipoDocumento)).append("\n");
    sb.append("    descricao: ").append(toIndentedString(descricao)).append("\n");
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

