package br.com.r34.negocio.dao.lancamento;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.r34.negocio.domain.vo.lancamento.TemplateLancamento;

public interface TemplateLancamentoDAO extends CrudRepository<TemplateLancamento, Long> {
	
	@Query("select tempLancamento from TemplateLancamento tempLancamento inner join fetch tempLancamento.membro m where m.id =:idMembro order by tempLancamento.diaLancamento desc")
	List<TemplateLancamento> perquisarPorMembro(@Param("idMembro") long idMembro);
}
