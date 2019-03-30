package br.com.r34.negocio.dao.lancamento;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.r34.negocio.domain.vo.lancamento.Lancamento;

public interface LancamentoDAO extends CrudRepository<Lancamento, Long> {
	
	@Query("select lancamento from Lancamento lancamento "
			+ "inner join fetch lancamento.membro m "
			+ "inner join fetch lancamento.resposavelLancamento rl "
			+ "left join fetch lancamento.produtoVenda pv "
			+ "left join fetch pv.produto p "
			+ "where m.id =:idMembro order by lancamento.dataLancamento desc ")
	List<Lancamento> perquisarPorMembro(@Param("idMembro") long idMembro);
	
	@Query("select lancamento from Lancamento lancamento "
			+ "inner join fetch lancamento.membro m "
			+ "inner join fetch lancamento.produtoVenda pv "
			+ "inner join fetch pv.produto p "
			+ "left join lancamento.promocoes promo "
			+ "where m.id =:idMembro and "
			+ "p.id =:idProduto and "
			+ "lancamento.dataLancamento between :dataInicio and :dataFim and "
			+ "promo.id is null")			
	List<Lancamento> pesquisarParaPromocao(@Param("idMembro") long idMembro, @Param("idProduto") long idProduto,
			@Param("dataInicio") Date dataInicio,@Param("dataFim") Date dataFim, Pageable pageable);
}
