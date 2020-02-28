package br.com.r34.persistencia.repository.acesso;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.r34.persistencia.vo.acesso.Endpoint;

public interface EndPointDAO extends CrudRepository<Endpoint, Long>{

	List<Endpoint> findAll();
	
	List<Endpoint> findByDescricaoContainingIgnoreCase(String descricao);
	
	@Query(nativeQuery = true,value = "select distinct ed.url " + 
			"from core.tb_endpoint ed " + 
			"inner join core.tb_endpoint_regra_acesso era on ed.id=era.id_endpoint " + 
			"inner join core.tb_regra_acesso ra on ra.id=era.id_regra_acesso " + 
			"inner join core.tb_membro_regra_acesso mra on mra.id_regra_acesso = ra.id " + 
			"inner join core.tb_membro m on m.id = mra.id_membro " + 
			"where m.email=:email and ed.left_menu=true")
	List<String> findByMembro(@Param("email") String email);
}
