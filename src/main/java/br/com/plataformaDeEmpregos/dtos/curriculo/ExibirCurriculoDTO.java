package br.com.plataformaDeEmpregos.dtos.curriculo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.hateoas.Links;

import br.com.plataformaDeEmpregos.models.curriculo.CurriculoModel;
import br.com.plataformaDeEmpregos.models.curriculo.DadosProfissionaisModel;
import br.com.plataformaDeEmpregos.models.curriculo.Formacao;


/**
* Record que representa os dados relevantes de um curriculo para exibição.
* Ele abstrai, escondendo os dados sensíveis.
*
* @author Leandro F. Moraes
*
*/
public record ExibirCurriculoDTO(
  Long id,
  // DadosPessoais dadosPessoais,
  String nome,
  String sobrenome,
  String genero,
  LocalDate dataNascimento,
  String nacionalidade,
  Formacao formacao,
  List<DadosProfissionaisModel> dadosProfissionais,
  Links link
){

  public ExibirCurriculoDTO(CurriculoModel curriculo){
    this(
      curriculo.getId(),
      // curriculo.getDadosPessoais(),
      curriculo.getDadosPessoais().getNome(),
      curriculo.getDadosPessoais().getSobrenome(),
      curriculo.getDadosPessoais().getGenero(),
      curriculo.getDadosPessoais().getDataNascimento(),
      curriculo.getDadosPessoais().getNacionalidade(),
      curriculo.getFormacao(),
      curriculo.getDadosProfissionais(),
      curriculo.getLinks()
    );
  }
}

