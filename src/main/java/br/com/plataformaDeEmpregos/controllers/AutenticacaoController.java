package br.com.plataformaDeEmpregos.controllers;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.plataformaDeEmpregos.dtos.usuario.AutenticacaoDTO;
import br.com.plataformaDeEmpregos.infra.security.DadosTokenJWT;
import br.com.plataformaDeEmpregos.infra.security.TokenService;
import br.com.plataformaDeEmpregos.models.usuario.UsuarioModel;
/**
* Classe que representa as operações de CRUD para o Curriculo.
*
* @author Leandro F. Moraes
*
*/
@RestController
@RequestMapping("/login")
public class AutenticacaoController {

  @Autowired
  private AuthenticationManager authenticationManager; //AuthenticationManager é uma interface do Spring que é responsável por disparar o processo de autenticação.

  @Autowired
  private TokenService tokenService;

  @PostMapping
  public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid AutenticacaoDTO dados) {
    var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()); //Convertendo do nosso DTO para o DTO do Spring
    var authentication = authenticationManager.authenticate(authenticationToken);

    var tokenJWT = tokenService.gerarToken((UsuarioModel) authentication.getPrincipal());

    return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
  }

}
