package br.com.plataformaDeEmpregos.models.curriculo;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import br.com.plataformaDeEmpregos.dtos.curriculo.DadosPessoaisDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
* Classe que representa os dados pessoais.
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
public class DadosPessoais{

  private String nome;
  private String sobrenome;

  // @Column(unique = true)
  private String cpf;

  // @JsonFormat(pattern = "yyyy-MM-dd")
  @Column(name = "data_nascimento")
  private LocalDate dataNascimento;

  private String genero;
  private String nacionalidade;

  public DadosPessoais(DadosPessoaisDTO dados) {
    BeanUtils.copyProperties(dados, this);
  }
}
