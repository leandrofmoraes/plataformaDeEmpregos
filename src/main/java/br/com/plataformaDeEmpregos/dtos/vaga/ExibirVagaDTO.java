package br.com.plataformaDeEmpregos.dtos.vaga;

import java.time.LocalDate;

import org.springframework.hateoas.Links;

import br.com.plataformaDeEmpregos.models.vagas.FormatoDeTrabalho;
import br.com.plataformaDeEmpregos.models.vagas.VagaModel;

/**
* Record que representa uma vaga cadastrada em detalhes.
*
* @author Leandro F. Moraes
*
*/
public record ExibirVagaDTO(
  Long id,
  String titulo,
  String cargo,
  FormatoDeTrabalho formatoDeTrabalho,
  String nomeEmpresa,
  String cidade,
  LocalDate dataPublicacao,
  Links links
){
  public ExibirVagaDTO(VagaModel vaga){
    this(
      vaga.getId(),
      vaga.getTitulo(),
      vaga.getCargo(),
      vaga.getFormatoDeTrabalho(),
      vaga.getEmpresa().getNomeFantasia(),
      vaga.getEmpresa().getEndereco().getCidade(),
      vaga.getDataPublicacao(),
      vaga.getLinks()
    );
  }
}

