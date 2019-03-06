package br.com.r34.negocio.dao.membro;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.r34.negocio.domain.vo.membro.Membro;
import br.com.r34.negocio.enums.Cargo;

public interface MembroDAO extends CrudRepository<Membro, Long> {

	List<Membro> findAllByOrderByNomeAsc();
	
	@Query("select m from Membro m where m.email=:email and m.senha=:senha and m.situacaoMembro = 'SituacaoMembro.ATIVO' ")
	List<Membro> login(@Param("email") String email,@Param("senha") String senha);
	
	@Query("select m from Membro m order by  m.situacaoMembro asc, m.patente desc , m.cargo asc")
	List<Membro> selectAll();
	
	@Query("select m from Membro m where m.status=:status order by  m.nome asc")
	List<Membro> selectByStatus(@Param("status")boolean status);
	
	Membro findById(long id);
	
	@Query("select count(m.id) from Membro m where m.cargo=:cargo and m.id!=:id ")
	int findByCargo(@Param("cargo")Cargo cargo,@Param("id") long id);
		
}
