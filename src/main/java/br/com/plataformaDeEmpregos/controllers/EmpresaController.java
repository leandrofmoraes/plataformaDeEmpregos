package br.com.plataformaDeEmpregos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.plataformaDeEmpregos.dtos.empresa.AtualizacaoEmpresaDTO;
import br.com.plataformaDeEmpregos.dtos.empresa.CadastroEmpresaDTO;
import br.com.plataformaDeEmpregos.dtos.empresa.DetalhamentoEmpresaDTO;
import br.com.plataformaDeEmpregos.dtos.empresa.ListagemEmpresaDTO;
import br.com.plataformaDeEmpregos.services.EmpresaService;
import jakarta.validation.Valid;

/**
 * Classe que representa o Controller de Empresa.
 *
 * @author Leandro F. Moraes
 *
 */

@RestController
@RequestMapping("empresa")
public class EmpresaController {

  @Autowired
  private EmpresaService empresaService;

// ----------- POST METHODS ----------------
/**End point para cadastro de empresa no banco de dados.
* Recebe uma requisição via POST com um JSON no corpo da requisição, contendo os dados da empresa a ser cadastrada.
* @param dados CadastroEmpresaDTO - O JSON é mapeado para um DTO (Data Transfer Object) que será usado para instanciar a entidade Empresa.
* @param uriBuilder UriComponentsBuilder - Utilizado para construir a URI de resposta.
* @return ResponseEntity<DetalhamentoEmpresaDTO> - Retorna um objeto ResponseEntity que contém o código de status HTTP 201 Created, a URI do novo recurso criado e os dados da empresa cadastrada.
*/
  @PostMapping
  @Transactional
  public ResponseEntity<DetalhamentoEmpresaDTO> cadastrar(@RequestBody @Valid CadastroEmpresaDTO dados, UriComponentsBuilder uriBuilder){

    var detalhamentoEmpresa = empresaService.salvar(dados);

    var uri = uriBuilder.path("/empresa/{id}").buildAndExpand(detalhamentoEmpresa.id()).toUri();

    return ResponseEntity.created(uri).body(detalhamentoEmpresa);
  }

// ------------- GET METHODS ----------------

/**End point para obter a lista de empresas cadastradas no banco de dados.
* Devolve uma lista de empresas cadastradas no banco de dados, com paginação e ordenação, se necessário.
* @param paginacao Pageable - Objeto que contém informações sobre a paginação e ordenação da lista.
* @param @PageableDefault Parâmetro que define o tamanho padrão da página e a ordenação padrão.
* @return ResponseEntity.ok(page) - Retorna um objeto ResponseEntity com o código de status HTTP 200 OK e o {@link ListagemEmpresaDTO} contém os dados recuperados do banco de dados.
*/
  @GetMapping
  public ResponseEntity<Page<ListagemEmpresaDTO>> listar(@PageableDefault(size = 10, sort = { "nomeFantasia" }) Pageable paginacao) {

    var page = empresaService.listar(paginacao);
    return ResponseEntity.ok(page);
  }

/**End point para obter os dados de uma empresa através do id
* Devolve os dados de uma empresa cadastrada no banco de dados através do id informado na URL.
* @param id Long - {id} é um placeholder que indica que o valor é variável. O valor informado na URL é capturado e passado como parâmetro para o método.
* @return ResponseEntity.ok - Retorna um objeto ResponseEntity com o código de status HTTP 200 OK e o {@link DadosDetalhamentoEmpresa} DTO contendo os dados da empresa.
*/
  @GetMapping("/{id}")
  public ResponseEntity<DetalhamentoEmpresaDTO> buscar(@PathVariable(value = "id") Long id) {
    var empresa = empresaService.buscar(id);
    return ResponseEntity.ok(empresa);
  }

// ----------- PUT METHODS ----------------

/**End point para atualizar os dados de uma empresa no banco de dados.
* Usa o método PUT para atualizar os dados de uma empresa no banco de dados. Recebe um JSON no corpo da requisição, contendo os dados da empresa a ser atualizada.
* @param dados AtualizacaoEmpresaDTO - O JSON é mapeado para um DTO (Data Transfer Object) que será usado para atualizar a entidade Empresa.
* @return ResponseEntity.ok - Retorna um objeto ResponseEntity com o código de status HTTP 201 OK e o {@link DadosDetalhamentoEmpresa} DTO contendo os dados atualizados da empresa.
*/
  @PutMapping
  @Transactional
  public ResponseEntity<DetalhamentoEmpresaDTO> atualizar(@RequestBody @Valid AtualizacaoEmpresaDTO dados) {

    var empresaAtualizada = empresaService.atualizarInformacoes(dados);

    return ResponseEntity.ok(empresaAtualizada);
  }

// ------------ DELETE METHODS ---------------

/**End point para desativar sem remover uma empresa no banco de dados.
* Usa o método DELETE com um id para desativar uma empresa. É uma forma de excluir de forma lógica, o cadastro não é removido do banco de dados.
* @param id Long - {id} é um placeholder que indica que o valor é variável. O valor informado na URL é capturado e passado como parâmetro para o método.
* @return ResponseEntity.noContent - Retorna um objeto ResponseEntity com o código de status HTTP 204 No Content.
*/
  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<Void> desativar(@PathVariable(value = "id") Long id) {

    empresaService.desativar(id); //Chama o método que "desativa" o registro, excluindo de forma lógica. Isso é tornar os dados inacessíveis na API mas não excluí-los do banco.

    return ResponseEntity.noContent().build();
  }

/**End point para remover uma empresa no banco de dados.
* Usa o método DELETE com um id passado através da uri "remover" para excluir uma empresa. Este método, exclui definitivamente o cadastro de uma empresa do banco de dados.
* @param id Long - {id} é um placeholder que indica que o valor é variável. O valor informado na URL é capturado e passado como parâmetro para o método.
*/
  @DeleteMapping("/remover/{id}")
  @Transactional
  public ResponseEntity<Object> remover(@PathVariable(value = "id") Long id) {

    empresaService.remover(id); //Para exclusão permanente, excluí o registro do banco de dados.

    // return ResponseEntity.noContent().build();
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Empresa deletada com sucesso!");
  }

}
