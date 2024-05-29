package br.com.plataformaDeEmpregos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.plataformaDeEmpregos.repositories.UsuarioRepository;

/**
 * AutenticacaoService
 */

// Por padrão o spring security irá procurar esta classe no projeto a classe que representa o "serviço" de "autenticação".
@Service
public class AutenticacaoService implements UserDetailsService {

  @Autowired
  private UsuarioRepository repository;

  //Toda vez que o usuário tentar se autenticar, o spring security irá chamar este método.
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findByLogin(username);
  }

}
