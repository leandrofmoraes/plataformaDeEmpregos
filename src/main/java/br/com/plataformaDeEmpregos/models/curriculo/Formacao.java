package br.com.plataformaDeEmpregos.models.curriculo;

import java.time.LocalDate;

import br.com.plataformaDeEmpregos.dtos.curriculo.FormacaoDTO;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Embeddable;

/**
* Classe que representa os dados referente a formação acadêmica.
* Esta classe é uma entidade embutida, ou seja, não possui uma tabela própria no banco de dados, mas é armazenada como parte de outra entidade
*
* @author Leandro F. Moraes
*
*/
@Embeddable
@Getter // Para gerar automaticamente os getters
@NoArgsConstructor // Para gerar automaticamente o construtor sem argumentos
@AllArgsConstructor // Para gerar automaticamente o construtor com todos os argumentos
public class Formacao {

  private String curso;
  private String nivel;
  private String instituicao;

  @Column(name = "data_inicio_curso")
  private LocalDate dataInicio;

  @Column(name = "data_conclusao_curso")
  private LocalDate dataConclusao;

  public Formacao(FormacaoDTO dados) {
    this.curso = dados.curso();
    this.nivel = dados.nivel();
    this.instituicao = dados.instituicao();
    this.dataInicio = dados.dataInicio();
    this.dataConclusao = dados.dataConclusao();
  }

  public void atualizarInformacoes(FormacaoDTO dados) {
    if(dados.curso() != null){
      this.curso = dados.curso();
    }
    if(dados.nivel() != null){
      this.nivel = dados.nivel();
    }
    if(dados.instituicao() != null){
      this.instituicao = dados.instituicao();
    }
    if(dados.dataInicio() != null){
      this.dataInicio = dados.dataInicio();
    }
    if(dados.dataConclusao() != null){
      this.dataConclusao = dados.dataConclusao();
    }
  }
}
