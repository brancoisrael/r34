package br.com.r34.negocio.dao.produto;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.r34.negocio.domain.vo.produto.ProdutoVenda;

public interface ProdutoVendaDAO extends CrudRepository<ProdutoVenda, Long>{

	@Query("select distinct pv from ProdutoVenda pv "
			+ "inner join fetch pv.produto p "
			+ "inner join fetch p.tipoProduto tp "
			+ "where p.id =:idProduto and "
			+ "((:dataLancamento between pv.inicioVigencia and pv.fimVigencia) or "
			+ "(pv.inicioVigencia<=:dataLancamento and pv.fimVigencia is null))")
	List<ProdutoVenda> findByProdutoData(@Param("idProduto")long idProduto,@Param("dataLancamento") Date dataLancamento, Pageable pageable);
}
