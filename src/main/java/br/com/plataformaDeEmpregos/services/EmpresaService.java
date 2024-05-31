package br.com.plataformaDeEmpregos.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.plataformaDeEmpregos.controllers.EmpresaController;
import br.com.plataformaDeEmpregos.dtos.empresa.AtualizacaoEmpresaDTO;
import br.com.plataformaDeEmpregos.dtos.empresa.CadastroEmpresaDTO;
import br.com.plataformaDeEmpregos.dtos.empresa.DetalhamentoEmpresaDTO;
import br.com.plataformaDeEmpregos.dtos.empresa.ListagemEmpresaDTO;
import br.com.plataformaDeEmpregos.models.empresa.EmpresaModel;
import br.com.plataformaDeEmpregos.repositories.EmpresaRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
* Classe de serviço que gerência e abstrai as operações da entidade com banco de dados através da interface repository.
*
* @author Leandro F. Moraes
*
*/
@Service
public class EmpresaService{

  @Autowired
  EmpresaRepository empresaRepository;

  public DetalhamentoEmpresaDTO salvar(CadastroEmpresaDTO dados) {

    //Instância uma empresa antes de salvar
    var empresa = new EmpresaModel(dados);
    // empresa.setAtivo(true);

    empresaRepository.save(empresa);

    // retorna um DTO com os dados da empresa de forma detalhada
    return new DetalhamentoEmpresaDTO(empresa);
  }

  public DetalhamentoEmpresaDTO atualizarInformacoes(AtualizacaoEmpresaDTO dados) {
    var empresa = empresaRepository.getReferenceById(dados.id());

    BeanUtils.copyProperties(dados, empresa);

    return new DetalhamentoEmpresaDTO(empresa);
  }

  public Page<ListagemEmpresaDTO> listar(Pageable paginacao){
    var listaDeEmpresas = empresaRepository.findAllByAtivoTrue(paginacao);

    listaDeEmpresas.forEach(empresa -> {
      Long id = empresa.getId();
      empresa
        .add(
          linkTo(methodOn(EmpresaController.class)
          .buscar(id))
          .withRel("Detalhar Empresa")
        );
    });

    return listaDeEmpresas.map(ListagemEmpresaDTO::new);
  }

  public DetalhamentoEmpresaDTO buscar(Long id) {
    var empresa = empresaRepository.getReferenceById(id);

    var paginacao = PageRequest.of(0, 10, Sort.by("nomeFantasia"));

    empresa
      .add(
        linkTo(methodOn(EmpresaController.class)
        .listar(paginacao))
        .withRel("Lista de Empresas")
      );

    return new DetalhamentoEmpresaDTO(empresa);
  }

  public void desativar(Long id) {
    var empresa = empresaRepository.getReferenceById(id);
    empresa.setAtivo(false);
  }

  public void remover(Long id) {
    empresaRepository.deleteById(id);
  }
}
