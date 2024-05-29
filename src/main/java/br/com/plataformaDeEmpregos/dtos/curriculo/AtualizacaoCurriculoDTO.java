package br.com.plataformaDeEmpregos.dtos.curriculo;

import br.com.plataformaDeEmpregos.dtos.contato.ContatoDTO;
import br.com.plataformaDeEmpregos.dtos.endereco.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

/**
* Record que representa os dados atualizáveis de um curriculo.
*
* @author Leandro F. Moraes
*
*/
public record AtualizacaoCurriculoDTO(
  @NotNull Long id,
  ContatoDTO contato,
  EnderecoDTO endereco,
  FormacaoDTO formacao,
  DadosProfissionaisDTO dadosProfissionais
) {
}
