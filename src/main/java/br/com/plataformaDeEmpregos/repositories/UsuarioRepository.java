package br.com.plataformaDeEmpregos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.plataformaDeEmpregos.models.usuario.UsuarioModel;

/**
 * usuarioRepository
 */
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{

    UserDetails findByLogin(String login);

}
