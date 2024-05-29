package br.com.plataformaDeEmpregos.models.endereco;

import org.springframework.beans.BeanUtils;

import br.com.plataformaDeEmpregos.dtos.endereco.EnderecoDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* Classe que representa os dados referente ao Endereço.
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
public class EnderecoModel{

  private String cep;
  private String logradouro;
  private Integer numero;
  private String complemento;
  private String bairro;
  private String cidade;
  private String uf;

  public EnderecoModel(EnderecoDTO dados){
    BeanUtils.copyProperties(dados, this);
  }

  public void atualizarInformacoes(EnderecoDTO dados) {
    if(dados.cep() != null){
      this.cep = dados.cep();
    }
    if(dados.logradouro() != null){
      this.logradouro = dados.logradouro();
    }
    if(dados.numero() != null){
      this.numero = dados.numero();
    }
    if(dados.complemento() != null){
      this.complemento = dados.complemento();
    }
    if(dados.bairro() != null){
      this.bairro = dados.bairro();
    }
    if(dados.cidade() != null){
      this.cidade = dados.cidade();
    }
    if(dados.uf() != null){
      this.uf = dados.uf();
    }
  }

}
