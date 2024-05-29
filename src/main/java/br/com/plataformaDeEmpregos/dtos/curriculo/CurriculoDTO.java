package br.com.plataformaDeEmpregos.dtos.curriculo;

import java.util.List;

import br.com.plataformaDeEmpregos.dtos.contato.ContatoDTO;
import br.com.plataformaDeEmpregos.dtos.endereco.EnderecoDTO;
import jakarta.validation.Valid;

/**
* Record que representa os dados de um curriculo
* Ele é composto por outros DTOs
*
* @author Leandro F. Moraes
*
*/
public record CurriculoDTO(
  @Valid DadosPessoaisDTO dadosPessoais,
  @Valid ContatoDTO contato,
  @Valid EnderecoDTO endereco,
  @Valid FormacaoDTO formacao,
  @Valid List<DadosProfissionaisDTO> dadosProfissionais
){}

