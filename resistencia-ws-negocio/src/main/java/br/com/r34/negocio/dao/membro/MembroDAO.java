package br.com.r34.negocio.dao.membro;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.r34.negocio.domain.vo.membro.Membro;

public interface MembroDAO extends CrudRepository<Membro, Long> {

	List<Membro> findAllByOrderByNomeAsc();
	
	@Query("select m from Membro m where m.email=:email and m.senha=:senha and m.situacaoMembro = 'SituacaoMembro.ATIVO' ")
	List<Membro> login(@Param("email") String email,@Param("senha") String senha);
	
	/*@Query("select m from Membro order by m.patente desc , m.cargo asc, m.situacaoMembro asc")
	List<Membro> selectAll();*/
}
