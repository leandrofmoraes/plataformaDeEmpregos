package br.com.plataformaDeEmpregos.infra.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.plataformaDeEmpregos.repositories.UsuarioRepository;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  private TokenService tokenService;

  @Autowired
  private UsuarioRepository repository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    var tokenJWT = recuperarToken(request); // Chama o método que recupera o token do cabeçalho da requisição

    if(tokenJWT != null){ // Se o token não for nulo
      var subject = tokenService.getSubject(tokenJWT); //chama o método que recupera o subject (login) do token
      var usuario = repository.findByLogin(subject); // chama o método que recupera o usuário pelo login passando o subject (login) do token

      var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()); // Cria um objeto do tipo UsernamePasswordAuthenticationToken que é como um DTO do spring que representa um usuário autenticado. que recebe um objeto usuário, um objeto credencial (que no caso não é necessário) e uma lista de permissões (que no caso é o perfil do usuário).
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response); //Necessário para chamar o próximo filtro da cadeia de filtros da aplicação
  }

  private String recuperarToken(HttpServletRequest request) {

    var authorizationHeader = request.getHeader("Authorization"); // Recupera o cabeçalho Authorization da requisição

    if(authorizationHeader != null){ // Se o cabeçalho não existir ou estiver vazio
      // Retorna o token sem a palavra Bearer
      return authorizationHeader.replace("Bearer ", "").trim(); // é simples e a legivel mas depende do formato específico do token JWT (com "Bearer " no início).
    }
    return null; // Se o cabeçalho não existir ou estiver vazio, retorna null
  }
}
