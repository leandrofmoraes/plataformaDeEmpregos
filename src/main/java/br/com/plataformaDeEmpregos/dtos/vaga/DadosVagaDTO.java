package br.com.plataformaDeEmpregos.dtos.vaga;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.plataformaDeEmpregos.models.vagas.FormatoDeTrabalho;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import br.com.plataformaDeEmpregos.models.empresa.EmpresaModel;

/**
* Este Record é o DTO que de fato será usado para instanciar um objeto que representa a entidade Vaga.
*
* @author Leandro F. Moraes
*
*/
public record DadosVagaDTO(
  @NotBlank
  String titulo,
  String cargo,
  FormatoDeTrabalho formatoDeTrabalho,
  EmpresaModel empresa,
  String formacaoRequerida,
  Integer experienciaRequerida,
  String habilidadesRequeridas,
  String descricao,
  BigDecimal salario,
  @Past
  LocalDate dataPublicacao
){}
