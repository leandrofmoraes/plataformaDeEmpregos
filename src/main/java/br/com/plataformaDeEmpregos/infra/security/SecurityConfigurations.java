package br.com.plataformaDeEmpregos.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Classe usada para definir as configurações de segurança com Spring Security.
 *
 * @author Leandro F Moraes
*/
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

  @Autowired
  private SecurityFilter securityFilter;

  /*
   * A anotação @Bean serve para exportar uma classe para o Spring, fazendo com que ele consiga carrega-la e realize
   * a sua injeção de dependência em outras classes
  */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    /*
      * CSRF = Cross-Site Request Forgery. É uma proteção contra ataques que envolvem a execução de ações não autorizadas.
      * O processo padrão de autenticação do Spring Security é baseado em formulário e sessões, que são gerenciadas por cookies.
      * É necessário desabilitar, para evitar redundância pois o próprio tolken que iremos utilizar, já fornece uma proteção contra esse tipo de ataque.
    */
    return http.csrf(csrf -> csrf.disable())
    .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    .authorizeHttpRequests(req -> {
      req.requestMatchers(HttpMethod.POST, "/login").permitAll(); // Tudo na API é restrito, exceto o login. Toda requisicao que for do tipo POST para /login, será permitida.
      req.requestMatchers("/v3/api-docs/**","/swagger-ui.html", "/swagger-ui/**").permitAll();
      req.requestMatchers(HttpMethod.DELETE, "/curriculo").hasRole("ADMIN"); // Apenas o ADMIN pode deletar médicos.
      req.requestMatchers(HttpMethod.DELETE, "/empresa").hasRole("ADMIN"); // Apenas o ADMIN pode deletar pacientes.
      req.requestMatchers(HttpMethod.DELETE, "/vaga").hasRole("ADMIN"); // Apenas o ADMIN pode deletar pacientes.
      req.anyRequest().authenticated(); // Qualquer outra requisição que não seja a de login, será necessária a autenticação.
    })
    .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) //Chama o filtro de segurança que criamos, antes do filtro padrão do spring.
    .build();
  }

  @Bean //Será injetada na classe AuthenticacaoController para chamar o método authenticate que recebe um DTO chamado UsernamePasswordAuthenticationToken
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  // Define qual algoritmo de criptografia de senha será utilizado
  // As senhas devem ser criptografadas antes de armazenar no banco de dados.
  // O Spring Security fornece uma interface chamada PasswordEncoder que possui um método chamado encode que recebe uma senha e retorna a mesma criptografada.
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
