package br.com.r34.negocio.dao.lancamento;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.r34.negocio.domain.vo.lancamento.Lancamento;

public interface LancamentoDAO extends CrudRepository<Lancamento, Long> {
	
	@Query("select lancamento from Lancamento lancamento inner join fetch lancamento.membro m where m.id =:idMembro order by lancamento.dataLancamento desc ")
	List<Lancamento> perquisarPorMembro(@Param("idMembro") long idMembro);
}
