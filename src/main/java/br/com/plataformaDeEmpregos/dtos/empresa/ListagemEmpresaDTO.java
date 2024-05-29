package br.com.plataformaDeEmpregos.dtos.empresa;

import br.com.plataformaDeEmpregos.models.empresa.EmpresaModel;

/**
* Record que representa os dados relevantes de uma empresa para exibição.
* Ele abstrai, escondendo os dados sensíveis como CNPJ.
*
* @author Leandro F. Moraes
*
*/
public record ListagemEmpresaDTO(
  Long id,
  String razaoSocial,
  String nomeFantasia,
  String areaDeAtuacao
) {

  public ListagemEmpresaDTO(EmpresaModel empresa){
    this(
      empresa.getId(),
      empresa.getRazaoSocial(),
      empresa.getNomeFantasia(),
      empresa.getAreaDeAtuacao()
    );
  }

}
