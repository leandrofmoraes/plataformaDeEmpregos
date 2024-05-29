package br.com.plataformaDeEmpregos.dtos.contato;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
* Este record é um DTO que representa os dados necessários para instanciar um objeto da classe ContatoModel
*
* @author Leandro F. Moraes
*
*/
public record ContatoDTO(
  String telefone,
  String celular,

  @NotBlank @Email
  String email,
  String site
){}

