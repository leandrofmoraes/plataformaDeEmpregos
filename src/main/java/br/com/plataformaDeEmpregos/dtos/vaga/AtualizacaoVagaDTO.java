package br.com.plataformaDeEmpregos.dtos.vaga;

import java.math.BigDecimal;

import br.com.plataformaDeEmpregos.models.vagas.FormatoDeTrabalho;
import jakarta.validation.constraints.NotNull;

/**
* Record que representa os dados atualizáveis de uma vaga.
*
* @author Leandro F. Moraes
*
*/
public record AtualizacaoVagaDTO(
  @NotNull Long id,
  String titulo,
  String cargo,
  FormatoDeTrabalho formatoDeTrabalho,
  String areaDeAtuacao,
  String cidade,
  String formacaoRequerida,
  Integer experienciaRequerida,
  String habilidadesRequeridas,
  String descricao,
  BigDecimal salario
) {
}
