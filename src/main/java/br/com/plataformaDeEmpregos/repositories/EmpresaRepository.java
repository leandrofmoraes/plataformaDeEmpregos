package br.com.plataformaDeEmpregos.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.plataformaDeEmpregos.models.empresa.EmpresaModel;

/**
* Interface que herda de JpaRepository e é responsável por realizar operações de CRUD no banco de dados para a entidade Empresa.
*
* @author Leandro F. Moraes
*
*/
public interface EmpresaRepository extends JpaRepository<EmpresaModel, Long> {
  Page<EmpresaModel> findAllByAtivoTrue(Pageable paginacao);
}
