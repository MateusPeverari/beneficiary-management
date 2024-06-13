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
 * Document
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-13T11:42:47.949547-03:00[America/Sao_Paulo]")
public class Document {

  private String id;

  private String tipoDocumento;

  private String descricao;

  /**
   * Default constructor
   * @deprecated Use {@link Document#Document(String, String)}
   */
  @Deprecated
  public Document() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Document(String tipoDocumento, String descricao) {
    this.tipoDocumento = tipoDocumento;
    this.descricao = descricao;
  }

  public Document id(String id) {
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

  public Document tipoDocumento(String tipoDocumento) {
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

  public Document descricao(String descricao) {
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
    Document document = (Document) o;
    return Objects.equals(this.id, document.id) &&
        Objects.equals(this.tipoDocumento, document.tipoDocumento) &&
        Objects.equals(this.descricao, document.descricao);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tipoDocumento, descricao);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Document {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

