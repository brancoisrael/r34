package br.com.r34.persistencia.repository.acesso;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.r34.persistencia.vo.acesso.RegraAcesso;

public interface RegraAcessoDAO extends CrudRepository<RegraAcesso, Long> {

	List<RegraAcesso> findAll();

}
