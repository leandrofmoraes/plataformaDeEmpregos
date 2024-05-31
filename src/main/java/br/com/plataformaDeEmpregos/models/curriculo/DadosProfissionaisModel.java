package br.com.plataformaDeEmpregos.models.curriculo;

import java.io.Serializable;
import java.time.LocalDate;

// import org.springframework.beans.BeanUtils;

import br.com.plataformaDeEmpregos.dtos.curriculo.DadosProfissionaisDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
* Classe que representa a entidade DadosProfissionais.
* Esta é a entidade que será mapeada para tabela "experiencia_profissional" no banco de dados.
*
* @author Leandro F. Moraes
*
*/
@Entity
@Table(name = "experiencia_profissional")
@Getter // Para gerar automaticamente os getters
@NoArgsConstructor // Para gerar automaticamente o construtor sem argumentos
@AllArgsConstructor // Para gerar automaticamente o construtor com todos os argumentos
@EqualsAndHashCode(of = "id") // Para gerar automaticamente o equals e o hashCode com base no ID
public class DadosProfissionaisModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String empresa;
  private String cargo;

  @Column(name = "data_inicio_cargo")
  private LocalDate dataInicio;

  @Column(name = "data_fim_cargo")
  private LocalDate dataFim;

  private String descricao;

  public DadosProfissionaisModel(DadosProfissionaisDTO dados) {
    // BeanUtils.copyProperties(dados, this);
    this.empresa = dados.empresa();
    this.cargo = dados.cargo();
    this.dataInicio = dados.dataInicio();
    this.dataFim = dados.dataFim();
    this.descricao = dados.descricao();
  }

  public void atualizarInformacoes(DadosProfissionaisDTO dados){
    if(dados.empresa() != null){
      this.empresa = dados.empresa();
    }
    if(dados.cargo() != null){
      this.cargo = dados.cargo();
    }
    if(dados.dataInicio() != null){
      this.dataInicio = dados.dataInicio();
    }
    if(dados.dataFim() != null){
      this.dataFim = dados.dataFim();
    }
    if(dados.descricao() != null){
      this.descricao = dados.descricao();
    }
  }
}
