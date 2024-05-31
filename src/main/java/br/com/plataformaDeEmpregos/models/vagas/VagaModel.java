package br.com.plataformaDeEmpregos.models.vagas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
// import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// import br.com.plataformaDeEmpregos.dtos.vaga.AtualizacaoVagaDTO;
import br.com.plataformaDeEmpregos.dtos.vaga.DadosVagaDTO;
import br.com.plataformaDeEmpregos.models.empresa.EmpresaModel;

/**
* Classe que representa a entidade Vaga.
* Esta é a entidade que será mapeada para tabela "vaga" no banco de dados e se relaciona com a tabela de empresa.
*
* @author Leandro F. Moraes
*
*/
@Entity
@Table(name = "vaga")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// @EqualsAndHashCode(of = "id")
public class VagaModel extends RepresentationModel<VagaModel> implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String titulo;
  private String cargo;

  @Enumerated(EnumType.STRING)
  @Column(name = "formato_de_trabalho")
  private FormatoDeTrabalho formatoDeTrabalho;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "empresa_id")
  private EmpresaModel empresa;

  private String cidade;

  @Column(name = "formacao_requerida")
  private String formacaoRequerida;

  @Column(name = "experiencia_requerida")
  private Integer experienciaRequerida;

  @Column(name = "habilidades_requeridas")
  private String habilidadesRequeridas;

  private String descricao;
  private BigDecimal salario;

  @Column(name = "data_publicacao")
  private LocalDate dataPublicacao;

  private Boolean ativo;

  public VagaModel(DadosVagaDTO dados) {
    this.ativo = true;
    this.titulo = dados.titulo();
    this.cargo = dados.cargo();
    this.formatoDeTrabalho = dados.formatoDeTrabalho();
    this.empresa = dados.empresa();
    this.cidade = dados.empresa().getEndereco().getCidade();
    this.formacaoRequerida = dados.formacaoRequerida();
    this.experienciaRequerida = dados.experienciaRequerida();
    this.habilidadesRequeridas = dados.habilidadesRequeridas();
    this.descricao = dados.descricao();
    this.salario = dados.salario();
    this.dataPublicacao = dados.dataPublicacao();
  }

}
