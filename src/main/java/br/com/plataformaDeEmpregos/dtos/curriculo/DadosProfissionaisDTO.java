package br.com.plataformaDeEmpregos.dtos.curriculo;

import java.time.LocalDate;

import jakarta.validation.constraints.Past;

/**
* Record que representa os dados necessários para instanciar um objeto da classe DadosProfissionais.
*
* @author Leandro F. Moraes
*
*/
public record DadosProfissionaisDTO(
  String empresa,
  String cargo,
  @Past
  LocalDate dataInicio,
  LocalDate dataFim,
  String descricao
){

}

