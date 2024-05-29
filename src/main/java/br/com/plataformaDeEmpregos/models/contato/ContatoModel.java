package br.com.plataformaDeEmpregos.models.contato;

import org.springframework.beans.BeanUtils;

import br.com.plataformaDeEmpregos.dtos.contato.ContatoDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* Classe que representa os dados referente ao contato.
* Esta classe é uma entidade embutida, ou seja, não possui uma tabela própria no banco de dados, mas é armazenada como parte de outra entidade
*
* @author Leandro F. Moraes
*
*/
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContatoModel{

  private String telefone;
  private String celular;
  private String email;
  private String site;

  public ContatoModel(ContatoDTO dados) {
    BeanUtils.copyProperties(dados, this);
  }

  public void atualizarInformacoes(ContatoDTO dados) {
    if(dados.telefone() != null){
      this.telefone = dados.telefone();
    }
    if(dados.celular() != null){
      this.celular = dados.celular();
    }
    if(dados.email() != null){
      this.email = dados.email();
    }
    if(dados.site() != null){
      this.site = dados.site();
    }
  }
}
