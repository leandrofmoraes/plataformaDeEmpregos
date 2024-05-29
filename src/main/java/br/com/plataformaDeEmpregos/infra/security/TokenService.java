package br.com.plataformaDeEmpregos.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.plataformaDeEmpregos.models.usuario.UsuarioModel;


@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;

  public String gerarToken(UsuarioModel usuario) {
    try {
      var algoritmo = Algorithm.HMAC256(secret);
      return JWT.create()
      .withIssuer("API Voll.med")
      .withSubject(usuario.getLogin())
      // .withClaim("id", usuario.getId()) // É possivel adicionar mais claims (informações) ao token to tipo chave-valor
      .withExpiresAt(dataExpiracao()) // Chama o método que atribui a data de expiração do token
      .sign(algoritmo);
    }catch (JWTCreationException exception){
       throw new RuntimeException("Erro ao gerar token JWT", exception);
    }
  }

  public String getSubject(String tokenJWT) {
    try {
      var algoritmo = Algorithm.HMAC256(secret);
      return JWT.require(algoritmo)
      .withIssuer("API Voll.med")
      .build()
      .verify(tokenJWT)
      .getSubject();

    } catch (JWTVerificationException exception){
      throw new RuntimeException("Token JWT inválido ou expirado", exception);
    }
  }

  private Instant dataExpiracao() {
    // Pega a hora atual e adiciona 2 horas que é o tempo de expiração do token, depois converte para o tipo Instant com o fuso horário de Brasília
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}
