package br.com.r34.negocio.dao.lancamento;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.r34.negocio.domain.vo.lancamento.DescricaoLancamento;

public interface DescricaoLancamentoDAO extends CrudRepository<DescricaoLancamento, Long> {

	List<DescricaoLancamento> findAllByOrderByDescricao();
	
	List<DescricaoLancamento> findByDescricao(String descricao);
	
	@Query("select d from DescricaoLancamento d where d.descricao like :descricao")
	List<DescricaoLancamento> buscarPorDescricao(@Param("descricao") String descricao);
}
