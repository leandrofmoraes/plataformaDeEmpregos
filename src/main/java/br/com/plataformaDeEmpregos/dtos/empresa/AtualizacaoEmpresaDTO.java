package br.com.plataformaDeEmpregos.dtos.empresa;

import br.com.plataformaDeEmpregos.dtos.contato.ContatoDTO;
import br.com.plataformaDeEmpregos.dtos.endereco.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

/**
* Record que representa os dados atualizáveis de uma empresa.
*
* @author Leandro F. Moraes
*
*/
public record AtualizacaoEmpresaDTO(
  @NotNull Long id,
  String razaoSocial,
  String nomeFantasia,
  ContatoDTO contato,
  EnderecoDTO endereco
) {
}
