package br.com.plataformaDeEmpregos.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.plataformaDeEmpregos.dtos.curriculo.AtualizacaoCurriculoDTO;
import br.com.plataformaDeEmpregos.dtos.curriculo.CurriculoDTO;
import br.com.plataformaDeEmpregos.dtos.curriculo.DetalhamentoCurriculoDTO;
import br.com.plataformaDeEmpregos.dtos.curriculo.ExibirCurriculoDTO;
import br.com.plataformaDeEmpregos.models.contato.ContatoModel;
import br.com.plataformaDeEmpregos.models.curriculo.CurriculoModel;
import br.com.plataformaDeEmpregos.models.curriculo.DadosPessoais;
import br.com.plataformaDeEmpregos.models.curriculo.DadosProfissionaisModel;
import br.com.plataformaDeEmpregos.models.curriculo.Formacao;
import br.com.plataformaDeEmpregos.models.endereco.EnderecoModel;
import br.com.plataformaDeEmpregos.repositories.CurriculoRepository;


/**
* Classe de serviço que cria uma entidade Curriculo e abstrai as operações se salvar no banco de dados através da interface repository.
*
* @author Leandro F. Moraes
*
*/
@Service
public class CurriculoService{

  @Autowired
  CurriculoRepository curriculoRepository;

  public DetalhamentoCurriculoDTO salvar(CurriculoDTO dados) {

    List<DadosProfissionaisModel> dadosProfissionais = dados.dadosProfissionais()
    .stream() // Cria um fluxo de elementos a partir da lista de DadosProfissionaisCadastro
    .map(DadosProfissionaisModel::new) // Cria uma instância de DadosProfissionais para cada DadosProfissionaisCadastro
    .collect(Collectors.toList()); // Collectors é uma classe utilitária que fornece métodos para criar coleções a partir de um fluxo de elementos aqui, transforma o fluxo de elementos de DadosProfissionaisCadastro em uma set de DadosProfissionais

    //Instância um curriculo antes de salvar
     CurriculoModel curriculo = new CurriculoModel(
      new DadosPessoais(dados.dadosPessoais()),
      new ContatoModel(dados.contato()),
      new EnderecoModel(dados.endereco()),
      new Formacao(dados.formacao()),
      dadosProfissionais);

    curriculoRepository.save(curriculo);

    // retorna um DTO com os dados do curriculo salvo de forma detalhada
    return new DetalhamentoCurriculoDTO(curriculo);
  }

  public DetalhamentoCurriculoDTO atualizarInformacoes(AtualizacaoCurriculoDTO dados) {
    var curriculo = curriculoRepository.getReferenceById(dados.id());

    BeanUtils.copyProperties(dados, curriculo);

    return new DetalhamentoCurriculoDTO(curriculo);
  }

  public void desativar(Long id) {
    var curriculo = curriculoRepository.getReferenceById(id);
    curriculo.setAtivo(false);
  }

  public void remover(Long id) {
    curriculoRepository.deleteById(id);
  }

  public Page<ExibirCurriculoDTO> listar(Pageable paginacao){
    return curriculoRepository.findAllByAtivoTrue(paginacao).map(ExibirCurriculoDTO::new);
  }

  public ExibirCurriculoDTO buscar(Long id) {
    var curriculo = curriculoRepository.getReferenceById(id);
    return new ExibirCurriculoDTO(curriculo);
  }

}
