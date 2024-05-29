package br.com.plataformaDeEmpregos.dtos.usuario;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;

public record AutenticacaoDTO(
  @NotNull String login,
  @NotNull @Length(min = 4) String senha
){
}
