package br.com.plataformaDeEmpregos.dtos.empresa;

import br.com.plataformaDeEmpregos.models.contato.ContatoModel;
import br.com.plataformaDeEmpregos.models.empresa.EmpresaModel;
import br.com.plataformaDeEmpregos.models.endereco.EnderecoModel;

/**
* Record que representa uma empresa cadastrada em detalhes.
*
* @author Leandro F. Moraes
*
*/
public record DetalhamentoEmpresaDTO(
  Long id,
  String cnpj,
  String razaoSocial,
  String nomeFantasia,
  ContatoModel contato,
  EnderecoModel endereco
) {

  public DetalhamentoEmpresaDTO(EmpresaModel empresa){
    this(
      empresa.getId(),
      empresa.getCnpj(),
      empresa.getRazaoSocial(),
      empresa.getNomeFantasia(),
      empresa.getContato(),
      empresa.getEndereco()
    );
  }

}
