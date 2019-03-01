package br.com.r34.negocio.dao.produto;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.r34.negocio.domain.vo.produto.Produto;

public interface ProdutoDAO extends CrudRepository<Produto, Long>{
	
	@Query("select distinct p from Produto p "
			+ "inner join fetch p.tipoProduto "
			+ "where p.habilitarVenda = true")
	List<Produto> listarProdutoVenda(); 
	
	@Query("select distinct p from Produto p "
			+ "inner join fetch p.tipoProduto "
			+ "where p.habilitarCompra = true")
	List<Produto> listarProdutoCompra(); 
}
