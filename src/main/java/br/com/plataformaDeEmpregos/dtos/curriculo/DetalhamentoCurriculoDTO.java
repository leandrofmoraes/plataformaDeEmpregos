package br.com.plataformaDeEmpregos.dtos.curriculo;

import java.util.List;

import org.springframework.hateoas.Links;

import br.com.plataformaDeEmpregos.models.contato.ContatoModel;
import br.com.plataformaDeEmpregos.models.curriculo.CurriculoModel;
import br.com.plataformaDeEmpregos.models.curriculo.DadosPessoais;
import br.com.plataformaDeEmpregos.models.curriculo.DadosProfissionaisModel;
import br.com.plataformaDeEmpregos.models.curriculo.Formacao;
import br.com.plataformaDeEmpregos.models.endereco.EnderecoModel;

/**
* Record que representa um curriculo cadastrado em detalhes.
*
* @author Leandro F. Moraes
*
*/
public record DetalhamentoCurriculoDTO(
  Long id,
  DadosPessoais dadosPessoais,
  ContatoModel contato,
  EnderecoModel endereco,
  Formacao formacao,
  List<DadosProfissionaisModel> dadosProfissionais,
  Links links
){

  public DetalhamentoCurriculoDTO(CurriculoModel curriculo){
    this(
      curriculo.getId(),
      curriculo.getDadosPessoais(),
      curriculo.getContato(),
      curriculo.getEndereco(),
      curriculo.getFormacao(),
      curriculo.getDadosProfissionais(),
      curriculo.getLinks()
    );
  }
}

