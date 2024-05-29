package br.com.plataformaDeEmpregos.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.plataformaDeEmpregos.models.curriculo.CurriculoModel;

/**
* Interface que herda de JpaRepository e é responsável por realizar operações de CRUD no banco de dados para a entidade Curriculo.
*
* @author Leandro F. Moraes
*
*/
public interface CurriculoRepository extends JpaRepository<CurriculoModel, Long> {
  Page<CurriculoModel> findAllByAtivoTrue(Pageable paginacao);
}
