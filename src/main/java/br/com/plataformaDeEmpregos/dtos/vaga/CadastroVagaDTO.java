package br.com.plataformaDeEmpregos.dtos.vaga;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.plataformaDeEmpregos.models.vagas.FormatoDeTrabalho;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

/**
* Record que representa os dados recebidos no JSON através da classe controller para cadastro de vaga.
*
* @author Leandro F. Moraes
*
*/
public record CadastroVagaDTO(
  @NotBlank
  String titulo,
  String cargo,
  FormatoDeTrabalho formatoDeTrabalho,
  Long idEmpresa,
  String formacaoRequerida,
  Integer experienciaRequerida,
  String habilidadesRequeridas,
  String descricao,
  BigDecimal salario,
  @Past
  LocalDate dataPublicacao
){}
