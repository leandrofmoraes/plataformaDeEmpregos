package br.com.plataformaDeEmpregos.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import br.com.plataformaDeEmpregos.models.vagas.VagaModel;

/**
* Interface que herda de JpaRepository e é responsável por realizar operações no banco de dados para a entidade Vaga.
*
* @author Leandro F. Moraes
*
*/
public interface VagaRepository extends JpaRepository<VagaModel, Long> {
  Page<VagaModel> findAllByAtivoTrue(Pageable paginacao);

  // @Query("""
  //   select e
  //   from Empresa e
  //   where
  //   e.id = :id
  //   """)
  // Empresa getReferenceById(Long id);
}
