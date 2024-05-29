package br.com.plataformaDeEmpregos.dtos.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
* Este record é um DTO que representa os dados necessários para instanciar um objeto da classe Endereco
*
* @author Leandro F. Moraes
*
*/
public record EnderecoDTO(
  @NotBlank @Pattern(regexp = "\\d{8}")
  String cep,
  @NotBlank
  String logradouro,
  Integer numero,
  String complemento,
  @NotBlank
  String bairro,
  @NotBlank
  String cidade,
  @NotBlank
  String uf
){}

