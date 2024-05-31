package br.com.plataformaDeEmpregos.models.empresa;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

// import br.com.plataformaDeEmpregos.dtos.empresa.AtualizacaoEmpresaDTO;
import br.com.plataformaDeEmpregos.dtos.empresa.CadastroEmpresaDTO;
import br.com.plataformaDeEmpregos.dtos.empresa.DadosEmpresaDTO;
import br.com.plataformaDeEmpregos.models.contato.ContatoModel;
import br.com.plataformaDeEmpregos.models.endereco.EnderecoModel;
import br.com.plataformaDeEmpregos.models.vagas.VagaModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
// import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* Classe que representa a entidade Empresa.
* Esta é a entidade que será mapeada para tabela "empresa" no banco de dados.
*
* @author Leandro F. Moraes
*
*/
@Entity
@Table(name = "empresa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// @EqualsAndHashCode(of = "id")
public class EmpresaModel extends RepresentationModel<EmpresaModel> implements Serializable {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // @Column(unique = true)
  private String cnpj;

  @Column(name = "razao_social")
  private String razaoSocial;

  @Column(name = "nome_fantasia")
  private String nomeFantasia;

  @Column(name = "area_de_atuacao")
  private String areaDeAtuacao;

  @Embedded
  private ContatoModel contato;

  @Embedded
  private EnderecoModel endereco;

  @OneToMany(
    mappedBy = "empresa",
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch = FetchType.LAZY
  )
  List<VagaModel> vagas;

  private Boolean ativo;

  private EmpresaModel(DadosEmpresaDTO dados) {
    this.ativo = true;
    this.cnpj = dados.cnpj();
    this.razaoSocial = dados.razaoSocial();
    this.nomeFantasia = dados.nomeFantasia();
    this.areaDeAtuacao = dados.areaDeAtuacao();
  }

  public EmpresaModel(CadastroEmpresaDTO dados) {

    // BeanUtils.copyProperties(this, dados);
    this(dados.empresa());
    this.contato = new ContatoModel(dados.contato());
    this.endereco = new EnderecoModel(dados.endereco());
  }

  // public void atualizarInformacoes(@Valid AtualizacaoEmpresaDTO dados) {
  //
  //   if(dados.razaoSocial() != null){
  //     this.razaoSocial = dados.razaoSocial();
  //   }
  //
  //   if(dados.nomeFantasia() != null){
  //     this.nomeFantasia = dados.nomeFantasia();
  //   }
  //
  //   if(dados.contato() != null){
  //     this.contato.atualizarInformacoes(dados.contato());
  //   }
  //   if(dados.endereco() != null){
  //     this.endereco.atualizarInformacoes(dados.endereco());
  //   }
  // }
  //
  // public void desativar() {
  //   this.ativo = false;
  // }
}
