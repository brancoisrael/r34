package br.com.r34.persistencia.repository.acesso;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.r34.persistencia.vo.acesso.RegraAcesso;

public interface RegraAcessoDAO extends CrudRepository<RegraAcesso, Long> {

	List<RegraAcesso> findAll();

	@Query("select regraAcesso from RegraAcesso regraAcesso "
			+ "inner join fetch regraAcesso.membros membro "
			+ "inner join fetch regraAcesso.endpoints endpoints " 
			+ "where membro.id =:idMembro ")
	List<RegraAcesso> findByMembro(@Param("idMembro") long idMembro);
}
