package br.com.plataformaDeEmpregos.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.plataformaDeEmpregos.controllers.VagaController;
import br.com.plataformaDeEmpregos.dtos.vaga.AtualizacaoVagaDTO;
import br.com.plataformaDeEmpregos.dtos.vaga.CadastroVagaDTO;
import br.com.plataformaDeEmpregos.dtos.vaga.DadosVagaDTO;
import br.com.plataformaDeEmpregos.dtos.vaga.DetalhamentoVagaDTO;
import br.com.plataformaDeEmpregos.dtos.vaga.ExibirVagaDTO;
import br.com.plataformaDeEmpregos.models.vagas.VagaModel;
import br.com.plataformaDeEmpregos.repositories.EmpresaRepository;
import br.com.plataformaDeEmpregos.repositories.VagaRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
* Classe de serviço que instancia a classe que representa a entidade Vaga.
* Ela busca o cadastro da empresa no banco de dados através da interface EmpresaRepository e atualiza a lista de vagas da empresa.
*
* @author Leandro F. Moraes
*
*/
@Service
public class VagaService {

  @Autowired
  private VagaRepository vagaRepository;

  @Autowired
  private EmpresaRepository empresaRepository;

  public DetalhamentoVagaDTO cadastrar(CadastroVagaDTO dados) {

    //Instância uma vaga a partir dos dados recebidos
    var vaga = new VagaModel(new DadosVagaDTO(
      dados.titulo(),
      dados.cargo(),
      dados.formatoDeTrabalho(),
      // empresaRepository.getReferenceById(dados.idEmpresa()),
      // dados.cidade(),

      empresaRepository.findById(
        dados.idEmpresa()
      ).orElseGet(() -> null),

      dados.formacaoRequerida(),
      dados.experienciaRequerida(),
      dados.habilidadesRequeridas(),
      dados.descricao(),
      dados.salario(),
      dados.dataPublicacao()
    ));

    //Adiciona a vaga a uma lista de vagas da empresa antes de salvar no banco de dados
    vaga.getEmpresa().getVagas().add(vaga);


    //cria o DTO com os dados da vaga salva para ser retornado na resposta
    var detalhesDaVaga = new DetalhamentoVagaDTO(
      vaga.getId(),
      vaga.getTitulo(),
      vaga.getCargo(),
      vaga.getFormatoDeTrabalho(),
      vaga.getEmpresa().getNomeFantasia(),
      vaga.getCidade(),
      vaga.getFormacaoRequerida(),
      vaga.getExperienciaRequerida(),
      vaga.getHabilidadesRequeridas(),
      vaga.getDescricao(),
      vaga.getSalario(),
      vaga.getDataPublicacao(),
      null //Sem links
    );

    return detalhesDaVaga;
  }

  public DetalhamentoVagaDTO atualizarInformacoes(AtualizacaoVagaDTO dados) {
    var vaga = vagaRepository.getReferenceById(dados.id());

  //   if(dados.titulo() != null) {
  //     vaga.titulo = dados.titulo();
  //   }
  //   if(dados.cargo() != null) {
  //     vaga.cargo = dados.cargo();
  //   }
  //   if(dados.formatoDeTrabalho() != null){
  //     vaga.formatoDeTrabalho = dados.formatoDeTrabalho();
  //   }
  //   if(dados.formacaoRequerida() != null) {
  //     vaga.formacaoRequerida = dados.formacaoRequerida();
  //   }
  //   if(dados.experienciaRequerida() != null) {
  //     vaga.experienciaRequerida = dados.experienciaRequerida();
  //   }
  //   if(dados.habilidadesRequeridas() != null) {
  //     vaga.habilidadesRequeridas = dados.habilidadesRequeridas();
  //   }
  //   if(dados.descricao() != null) {
  //     vaga.descricao = dados.descricao();
  //   }
  //   if(dados.salario() != null) {
  //     vaga.salario = dados.salario();
  //   }
    BeanUtils.copyProperties(dados, vaga);

    return new DetalhamentoVagaDTO(vaga);
  }

  public void pausar(Long id) {
    var vaga = vagaRepository.getReferenceById(id);
    vaga.setAtivo(false);
  }

  public void remover(Long id) {
    vagaRepository.deleteById(id);
  }

  public Page<ExibirVagaDTO> listar(Pageable paginacao) {
    var listaDeVagas = vagaRepository.findAllByAtivoTrue(paginacao);

    listaDeVagas.forEach(empresa -> {
      Long id = empresa.getId();
      empresa
        .add(
          linkTo(methodOn(VagaController.class)
          .buscar(id))
          .withRel("Detalhar Vaga")
        );
    });

    return listaDeVagas.map(ExibirVagaDTO::new);
  }

  public DetalhamentoVagaDTO buscar(Long id) {
    var vaga = vagaRepository.getReferenceById(id);

    var paginacao = PageRequest.of(0, 10, Sort.by("titulo"));

    vaga
      .add(
        linkTo(methodOn(VagaController.class)
        .listar(paginacao))
        .withRel("Lista de Vagas")
      );

    return new DetalhamentoVagaDTO(vaga);
  }
}
