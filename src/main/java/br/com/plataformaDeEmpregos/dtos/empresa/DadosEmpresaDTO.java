package br.com.plataformaDeEmpregos.dtos.empresa;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.NotBlank;

/**
* Record que representa os dados necessários para instanciar um objeto da classe Empresa.
* Este DTO possui dados sensíveis como CNPJ e razão social, mas não possui dados de contato e endereço, que são representados por outras classes que irão compor a classe Empresa.
*
* @author Leandro F. Moraes
*
*/
public record DadosEmpresaDTO(
  @NotBlank @CNPJ
  String cnpj,
  @NotBlank
  String razaoSocial,
  String nomeFantasia,
  String areaDeAtuacao
){}

