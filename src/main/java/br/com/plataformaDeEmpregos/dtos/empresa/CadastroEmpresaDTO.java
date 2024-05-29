package br.com.plataformaDeEmpregos.dtos.empresa;

import br.com.plataformaDeEmpregos.dtos.contato.ContatoDTO;
import br.com.plataformaDeEmpregos.dtos.endereco.EnderecoDTO;
import jakarta.validation.Valid;

/**
* Record que representa os dados de serão usados para instanciar um objeto da classe Empresa
* Ele é composto por outros DTOs
*
* @author Leandro F. Moraes
*
*/
public record CadastroEmpresaDTO(
  @Valid
  DadosEmpresaDTO empresa,
  @Valid
  ContatoDTO contato,
  @Valid
  EnderecoDTO endereco
){}

