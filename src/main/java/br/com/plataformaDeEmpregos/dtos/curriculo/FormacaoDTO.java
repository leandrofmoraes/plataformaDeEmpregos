package br.com.plataformaDeEmpregos.dtos.curriculo;

import java.time.LocalDate;

import jakarta.validation.constraints.Past;

/**
* Este record é um DTO que representa os dados de formação acadêmica, necessários para instanciar um objeto da classe Formacao
*
* @author Leandro F. Moraes
*
*/
public record FormacaoDTO(
  String curso,
  String nivel,
  String instituicao,
  @Past LocalDate dataInicio,
  LocalDate dataConclusao
){

}
