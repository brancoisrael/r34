package br.com.r34.negocio.dao.acesso;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.r34.negocio.domain.vo.acesso.Endpoint;

public interface EndPointDAO extends CrudRepository<Endpoint, Long>{

	List<Endpoint> findAll();
	
	List<Endpoint> findByDescricaoContainingIgnoreCase(String descricao);
}
