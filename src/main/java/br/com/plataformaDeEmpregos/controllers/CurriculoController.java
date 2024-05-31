package br.com.plataformaDeEmpregos.controllers;

// import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
// import org.springframework.hateoas.PagedModel;
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

import br.com.plataformaDeEmpregos.dtos.curriculo.AtualizacaoCurriculoDTO;
import br.com.plataformaDeEmpregos.dtos.curriculo.CurriculoDTO;
import br.com.plataformaDeEmpregos.dtos.curriculo.DetalhamentoCurriculoDTO;
import br.com.plataformaDeEmpregos.dtos.curriculo.ExibirCurriculoDTO;
import br.com.plataformaDeEmpregos.services.CurriculoService;
import jakarta.validation.Valid;

/**
* Classe que representa as operações de CRUD para o Curriculo.
*
* @author Leandro F. Moraes
*
*/
@RestController
@RequestMapping("curriculo")
public class CurriculoController {

  @Autowired
  private CurriculoService curriculoService;

// ----------- POST METHODS ----------------

/**End point para cadastro de um curriculo no banco de dados.
* Recebe uma requisição via POST com um JSON no corpo da requisição, contendo os dados do curriculo a ser cadastrado.
* @param dados CurriculoDTO - Os dados contidos no JSON serão mapeados para o DTO (Data Transfer Object) que será usado para instanciar a entidade curriculo.
* @param uriBuilder UriComponentsBuilder - Utilizado para construir a URI de resposta.
* @return ResponseEntity<DetalhamentoCurriculoDTO> - Retorna um objeto ResponseEntity que contém o código de status HTTP 201 Created, a URI do novo recurso criado e os dados do curriculo cadastrado.
*/
  @PostMapping
  @Transactional // para o Spring gerenciar a transação
  public ResponseEntity<DetalhamentoCurriculoDTO> cadastrar(@RequestBody @Valid CurriculoDTO dados, UriComponentsBuilder uriBuilder) {

    var detalhamentoCurriculo = curriculoService.salvar(dados);

    var uri = uriBuilder.path("/curriculo/{id}").buildAndExpand(detalhamentoCurriculo.id()).toUri();

    return ResponseEntity.created(uri).body(detalhamentoCurriculo);
  }

  // ----------- GET METHODS ----------------

/**End point para obter a lista de curriculos cadastrados no banco de dados.
* Devolve uma lista de curriculos cadastrados no banco de dados, com paginação e ordenação, se necessário.
* @param paginacao Pageable - Objeto que contém informações sobre a paginação e ordenação da lista.
* Por padrão, o spring devolve páginas contendo 20 elementos cada. para
* controlar isso, podemos passar alguns parâmetros na url.
* Exemplos: http://localhost:8080?size=1 (para retornar apenas um elemento por
* página)
* http://localhost:8080?size=1&page=1 (para retornar o segundo elemento da
* lista, já que o primeiro é 0)
*
* Podemos também ordenar os elementos. Exemplo: http://localhost:8080?sort=nome
* (para ordenar por nome)
* http://localhost:8080?sort=nome,desc (para ordenar por nome de forma
* decrescente, a forma crescente é o padrão mas podemos usar 'asc' para deixar
* explícito)
*
* É possível alterar o padrão de 20 elementos por página, usando
* anotação @PageableDefault
* @param @PageableDefault Parâmetro que define o tamanho padrão da página e a ordenação padrão.
* @return ResponseEntity.ok(page) - Retorna um objeto ResponseEntity com o código de status HTTP 200 OK e o {@link ExibirCurriculoDTO} contém os dados recuperados do banco de dados.
*/
  @GetMapping
  public ResponseEntity<Page<ExibirCurriculoDTO>> listar(@PageableDefault(size = 10, sort = { "dadosPessoais.nome" }) Pageable paginacao) {

    var page = curriculoService.listar(paginacao);
    return ResponseEntity.ok(page);
  }

/**End point para obter os dados de um curriculo específico através do id
* Devolve os dados de um curriculo cadastrado no banco de dados através do id informado na URL.
* @param id Long - {id} é um placeholder que indica que o valor é variável. O valor informado na URL é capturado e passado como parâmetro para o método.
* @return ResponseEntity.ok - Retorna um objeto ResponseEntity com o código de status HTTP 200 OK e o {@link ExibirCurriculoDTO} contém os dados recuperados do banco de dados.
*/
  @GetMapping("/{id}")
  public ResponseEntity<DetalhamentoCurriculoDTO> buscar(@PathVariable(value = "id") Long id) {
    var curriculo = curriculoService.buscar(id);

    return ResponseEntity.status(HttpStatus.OK).body(curriculo);
    // return ResponseEntity.ok(curriculo);
  }

// ----------- PUT METHODS ----------------

/**End point para atualizar os dados de um curriculo no banco de dados.
* Usa o método PUT para atualizar os dados de um curriculo no banco de dados. Recebe um JSON no corpo da requisição, contendo os dados do curriculo a ser atualizado.
* @param dados DetalhamentoCurriculoDTO - O JSON é mapeado para o DTO que será usado para atualizar a entidade Curriculo.
* @return ResponseEntity.ok - Retorna um objeto ResponseEntity com o código de status HTTP 201 OK e o {@link DadosDetalhamentoEmpresa} DTO contendo os dados atualizados do curriculo de forma detalhada.
*/
  @PutMapping
  @Transactional
  public ResponseEntity<DetalhamentoCurriculoDTO> atualizar(@RequestBody @Valid AtualizacaoCurriculoDTO dados) {

    var curriculoAtualizado = curriculoService.atualizarInformacoes(dados);

    return ResponseEntity.ok(curriculoAtualizado);
  }

// ------------ DELETE METHODS ---------------

/**End point para desativar sem remover um curriculo no banco de dados.
* Excluir de forma lógica é tornar o registro inacessível, mas preservar seus dados no banco de dados.
* @param id Long - {id} é um placeholder que indica que o valor é variável. O valor informado na URL é capturado e passado como parâmetro para o método.
* @return ResponseEntity.noContent - Retorna um objeto ResponseEntity com o código de status HTTP 204 No Content.
*/
  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<Void> desativar(@PathVariable(value = "id") Long id) {

    curriculoService.desativar(id); //Chama o método que "desativa" o registro, excluindo de forma lógica. Isso é tornar os dados inacessíveis na API mas não excluí-los do banco.

    return ResponseEntity.noContent().build();
  }

/**End point para remover um curriculo no banco de dados.
* Usa o método DELETE com um id passado através da uri "remover" para excluir um curriculo. Este método, exclui definitivamente o cadastro de um curriculo do banco de dados.
* @param id Long - {id} é um placeholder que indica que o valor é variável. O valor informado na URL é capturado e passado como parâmetro para o método.
* @return ResponseEntity.noContent - Retorna um objeto ResponseEntity com o código de status HTTP 204 No Content.
*/
  @DeleteMapping("/remover/{id}")
  @Transactional
  public ResponseEntity<Object> remover(@PathVariable(value = "id") Long id) {

    curriculoService.remover(id); //Para exclusão permanente, excluí o registro do banco.

    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Curriculo deletado com sucesso!");
  }
}
