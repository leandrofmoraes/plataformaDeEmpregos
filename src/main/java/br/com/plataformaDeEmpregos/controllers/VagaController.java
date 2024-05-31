package br.com.plataformaDeEmpregos.controllers;

// import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import br.com.plataformaDeEmpregos.dtos.vaga.AtualizacaoVagaDTO;
import br.com.plataformaDeEmpregos.dtos.vaga.CadastroVagaDTO;
import br.com.plataformaDeEmpregos.dtos.vaga.DetalhamentoVagaDTO;
import br.com.plataformaDeEmpregos.dtos.vaga.ExibirVagaDTO;
import br.com.plataformaDeEmpregos.services.VagaService;
import jakarta.validation.Valid;

/**
* Classe que representa as operações do CRUD de Vagas
*
* @author Leandro F. Moraes
*
*/
@RestController
@RequestMapping("vaga")
public class VagaController {

  @Autowired
  private VagaService vagaService;

// ----------- POST METHODS ----------------

/**End point para cadastro de vagas no banco de dados.
* Recebe uma requisição via POST com um JSON no corpo da requisição, contendo os dados da vaga a ser cadastrada.
* @param dados CadastroVagaDTO - Os dados contidos no JSON serão mapeados para o DTO (Data Transfer Object) que será usado para instanciar a entidade Vaga.
* @param uriBuilder UriComponentsBuilder - Utilizado para construir a URI de resposta.
* @return ResponseEntity<DetalhamentoVagaDTO> - Retorna um objeto ResponseEntity que contém o código de status HTTP 201 Created, a URI do novo recurso criado e os dados da vaga cadastrada.
*/
  @PostMapping
  @Transactional
  public ResponseEntity<DetalhamentoVagaDTO> cadastrar(@RequestBody @Valid CadastroVagaDTO dados, UriComponentsBuilder uriBuilder){

    var vaga = vagaService.cadastrar(dados);

    var uri = uriBuilder.path("/vaga/{id}").buildAndExpand(vaga.id()).toUri();

    return ResponseEntity.created(uri).body(vaga);
  }

// ------------- GET METHODS ----------------

/**End point para obter a lista de vagas cadastradas no banco de dados.
* Devolve uma lista de vagas cadastradas no banco de dados, com paginação e ordenação, se necessário.
* @param paginacao Pageable - Objeto que contém informações sobre a paginação e ordenação da lista.
* Por padrão, o spring devolve páginas contendo 20 elementos cada. para
* controlar isso, podemos passar alguns parâmetros na url.
* Exemplos: http://localhost:8080?size=1 (para retornar apenas um elemento por
* página)
* http://localhost:8080?size=1&page=1 (para retornar o segundo elemento da
* lista, já que o primeiro é 0)
*
* Podemos também ordenar os elementos. Exemplo: http://localhost:8080?sort=titulo
* (para ordenar por titulo)
* http://localhost:8080?sort=titulo,desc (para ordenar por titulo de forma
* decrescente, a forma crescente é o padrão mas podemos usar 'asc' para deixar
* explícito)
*
* Pdemos também alterar o padrão de 20 elementos por página, usando
* anotação @PageableDefault
* @param @PageableDefault Parâmetro que define o tamanho padrão da página e a ordenação padrão.
* @return ResponseEntity.ok(page) - Retorna um objeto ResponseEntity com o código de status HTTP 200 OK e o {@link DadosDetalhamentoVaga} DTO contendo os dados recuperados do banco de dados.
*/
  @GetMapping
  public ResponseEntity<Page<ExibirVagaDTO>> listar(@PageableDefault(size = 10, sort = { "titulo" }) Pageable paginacao) {

    var page = vagaService.listar(paginacao);
    return ResponseEntity.ok(page);
  }

/**End point para obter os dados de um curriculo específico através do id
* Devolve os dados de um curriculo cadastrado no banco de dados através do id informado na URL.
* @param id Long - {id} é um placeholder que indica que o valor é variável. O valor informado na URL é capturado e passado como parâmetro para o método.
* @return ResponseEntity.ok - Retorna um objeto ResponseEntity com o código de status HTTP 200 OK e o {@link DetalhamentoVagaDTO} contém os dados da vaga.
*/
  @GetMapping("/{id}")
  public ResponseEntity<DetalhamentoVagaDTO> buscar(@PathVariable Long id) {
    var vaga = vagaService.buscar(id);
    return ResponseEntity.ok(vaga);
  }

// ----------- PUT METHODS ----------------

/**End point para atualizar os dados de uma vaga no banco de dados.
* Usa o método PUT para atualizar os dados de uma vaga no banco de dados. Recebe um JSON no corpo da requisição, contendo os dados da vaga a ser atualizada.
* @param dados DadosDetalhamentoVaga - O JSON é mapeado para um DTO (Data Transfer Object) que será usado para atualizar a entidade Vaga.
* @return ResponseEntity.ok - Retorna um objeto ResponseEntity com o código de status HTTP 201 OK e o {@link DadosDetalhamentoVaga} DTO contendo os dados atualizados do curriculo de forma detalhada.
*/
  @PutMapping
  @Transactional
  public ResponseEntity<DetalhamentoVagaDTO> atualizar(@RequestBody @Valid AtualizacaoVagaDTO dados) {

    // VagaService atualiza a vaga com base nos dados Recebidos e devolve um DTO contendo os novos dados.
    var vagaAtualizada = vagaService.atualizarInformacoes(dados);

    return ResponseEntity.ok(vagaAtualizada);
  }

// ------------ DELETE METHODS ---------------

/**End point para desativar uma vaga sem remover do banco de dados.
* Usa o método DELETE com um id para desativar uma vaga. É uma forma de excluir de forma lógica, o cadastro não é removido do banco de dados.
* @param id Long - {id} é um placeholder que indica que o valor é variável. O valor informado na URL é capturado e passado como parâmetro para o método.
* @return ResponseEntity.noContent - Retorna um objeto ResponseEntity com o código de status HTTP 204 No Content.
*/
  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<Void> pausar(@PathVariable Long id) {

    vagaService.pausar(id); //Chama o método que "pausa" o registro, excluindo de forma lógica. Isso é tornar os dados inacessíveis na API mas não excluí-los do banco.

    return ResponseEntity.noContent().build();
  }

/**End point para remover uma vaga do banco de dados.
* Usa o método DELETE com um id passado através da uri "remover" para excluir uma vaga. Este método, exclui definitivamente o cadastro de uma vaga do banco de dados.
* @param id Long - {id} é um placeholder que indica que o valor é variável. O valor informado na URL é capturado e passado como parâmetro para o método.
* @return ResponseEntity.noContent - Retorna um objeto ResponseEntity com o código de status HTTP 204 No Content.
*/
  @DeleteMapping("/remover/{id}")
  @Transactional
  public ResponseEntity<Void> remover(@PathVariable Long id) {

    vagaService.remover(id); //Para exclusão permanente, excluí o registro do banco.

    return ResponseEntity.noContent().build();
  }

}
