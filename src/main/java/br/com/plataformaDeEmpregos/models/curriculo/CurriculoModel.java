package br.com.plataformaDeEmpregos.models.curriculo;

import java.io.Serializable;
import java.util.List;

// import org.springframework.hateoas.RepresentationModel;

import br.com.plataformaDeEmpregos.models.contato.ContatoModel;
import br.com.plataformaDeEmpregos.models.endereco.EnderecoModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
* Classe que representa a entidade Curriculo.
*
* @author Leandro F. Moraes
*
*/
@Entity
@Table(name = "curriculo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CurriculoModel implements Serializable{
// public class CurriculoModel extends RepresentationModel<CurriculoModel> implements Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch = FetchType.LAZY
  )
  @JoinColumn(name = "curriculo_id") // join column é a coluna que será usada para fazer o join com a tabela de experiência profissional é a chave estrangeira
  private List<DadosProfissionaisModel> dadosProfissionais;

  @Embedded
  private DadosPessoais dadosPessoais;

  @Embedded
  private ContatoModel contato;

  @Embedded
  private EnderecoModel endereco;

  @Embedded
  private Formacao formacao;

  private Boolean ativo;

  public CurriculoModel(
    DadosPessoais dadosPessoais,
    ContatoModel contato,
    EnderecoModel endereco,
    Formacao formacao,
    List<DadosProfissionaisModel> dadosProfissionais
  ) {
    this.dadosPessoais = dadosPessoais;
    this.contato = contato;
    this.endereco = endereco;
    this.formacao = formacao;
    this.dadosProfissionais = dadosProfissionais;
    this.ativo = true;
  }

  // public DadosCurriculo getDadosPessoais(){
  //   return new DadosCurriculo(
  //     this.pessoa, this.contato, this.endereco, this.formacao, this.dadosProfissionais
  //   );
  // }
  // public void atualizarInformacoes(@Valid AtualizacaoCurriculoDTO dados) {
  //
  //   //caso o valor não seja nulo, chama o método atualizarInformacoes da classe.
  //   if(dados.contato() != null){
  //     this.contato.atualizarInformacoes(dados.contato());
  //   }
  //   if(dados.endereco() != null){
  //     this.endereco.atualizarInformacoes(dados.endereco());
  //   }
  //   if(dados.formacao() != null){
  //     this.formacao.atualizarInformacoes(dados.formacao());
  //   }
  //   // if(dados.dadosProfissionais() != null){
  //   //   this.dadosProfissionais.atualizarInformacoes(dados.dadosProfissionais());
  //   // }
  // }

// @Accessor("dadosPessoais.nome")
//   public String getNome() {
//     return dadosPessoais.getNome();
//   }

  // public void desativar() {
  //   this.ativo = false;
  // }
}
