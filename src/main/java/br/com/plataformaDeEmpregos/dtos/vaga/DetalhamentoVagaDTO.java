package br.com.plataformaDeEmpregos.dtos.vaga;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.plataformaDeEmpregos.models.vagas.FormatoDeTrabalho;
import br.com.plataformaDeEmpregos.models.vagas.VagaModel;

/**
* Record que representa uma vaga cadastrada em detalhes.
*
* @author Leandro F. Moraes
*
*/
public record DetalhamentoVagaDTO(
  Long id,
  String titulo,
  String cargo,
  FormatoDeTrabalho formatoDeTrabalho,
  String nomeEmpresa,
  String cidade,
  String formacaoRequerida,
  Integer experienciaRequerida,
  String habilidadesRequeridas,
  String descricao,
  BigDecimal salario,
  LocalDate dataPublicacao
){
  public DetalhamentoVagaDTO(VagaModel vaga){
    this(
      vaga.getId(),
      vaga.getTitulo(),
      vaga.getCargo(),
      vaga.getFormatoDeTrabalho(),
      vaga.getEmpresa().getNomeFantasia(),
      vaga.getEmpresa().getEndereco().getCidade(),
      vaga.getFormacaoRequerida(),
      vaga.getExperienciaRequerida(),
      vaga.getHabilidadesRequeridas(),
      vaga.getDescricao(),
      vaga.getSalario(),
      vaga.getDataPublicacao()
    );
  }
}

