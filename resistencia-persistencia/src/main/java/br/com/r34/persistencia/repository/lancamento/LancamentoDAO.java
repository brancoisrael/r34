package br.com.r34.persistencia.repository.lancamento;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.r34.persistencia.vo.lancamento.Lancamento;

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
			+ "inner join fetch lancamento.resposavelLancamento rl "
			+ "left join fetch lancamento.produtoVenda pv "
			+ "left join fetch pv.produto p "
			+ "where m.email =:email order by lancamento.dataLancamento desc ")
	List<Lancamento> perquisarPorMembro(@Param("email") String email);
	
	@Query("select lancamento from Lancamento lancamento "
			+ "inner join fetch lancamento.membro m "
			+ "inner join fetch lancamento.produtoVenda pv "
			+ "inner join fetch pv.produto p "
			+ "left join lancamento.promocoes promo "
			+ "where m.id =:idMembro and "
			+ "p.id =:idProduto and "
			+ "lancamento.dataLancamento between :dataInicio and :dataFim and "
			+ "promo.id is null and "
			+ "lancamento.id !=:idLancamento")			
	List<Lancamento> pesquisarParaPromocao(@Param("idMembro") long idMembro, @Param("idProduto") long idProduto,
			@Param("dataInicio") Date dataInicio,@Param("dataFim") Date dataFim,
			@Param("idLancamento") long idLancamento, Pageable pageable);
	
	@Query("select lancamento from Lancamento lancamento "
			+ "inner join lancamento.membro m "
			+ "inner join lancamento.promocoes promo "
			+ "where m.id =:idMembro and "
			+ "lancamento.dataLancamento between :dataInicio and :dataFim and "
			+ "promo.id =:idPromocao and "
			+ "lancamento.id !=:idLancamento")			
	List<Lancamento> pesquisarPorPromocao(
			@Param("idMembro") long idMembro,
			@Param("dataInicio") Date dataInicio,
			@Param("dataFim") Date dataFim,
			@Param("idPromocao") long idPromocao, 
			@Param("idLancamento") long idLancamento,
			Pageable pageable);
	
	@Query("select lancamento from Lancamento lancamento "
			+ "inner join fetch lancamento.membro m "
			+ "inner join fetch lancamento.produtoVenda pv "
			+ "inner join fetch pv.produto p "
			+ "inner join lancamento.promocoes promo "
			+ "where lancamento.id =:idLancamento")			
	Lancamento pesquisarById(long idLancamento);
	
	@Query(nativeQuery = true, value = "select sum(valor_lancamento),'DEBITO' from core.tb_lancamento where tipo_lancamento=0 and id_membro=:idMembro " + 
			"union " + 
			"select sum(valor_lancamento),'CREDITO' from core.tb_lancamento where tipo_lancamento=1 and id_membro=:idMembro")
	Double[] somarLancamento(@Param("idMembro")long idMembro);
}
