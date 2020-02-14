package br.com.r34.persistencia.repository.produto;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.r34.persistencia.vo.produto.Produto;

public interface ProdutoDAO extends CrudRepository<Produto, Long>{
	
	@Query("select distinct p from Produto p "
			+ "inner join fetch p.tipoProduto tp "
			+ "where p.habilitarVenda = true "
			+ "and tp.id =:idTipoProduto ")
	List<Produto> listarProdutoVenda(@Param("idTipoProduto")long idTipoProduto); 
	
	@Query("select distinct p from Produto p "
			+ "inner join fetch p.tipoProduto tp "
			+ "where p.habilitarCompra = true "
			+ "and tp.id =:idTipoProduto ")
	List<Produto> listarProdutoCompra(@Param("idTipoProduto")long idTipoProduto); 
}
