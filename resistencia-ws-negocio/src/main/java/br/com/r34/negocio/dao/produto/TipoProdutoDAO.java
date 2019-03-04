package br.com.r34.negocio.dao.produto;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.r34.negocio.domain.vo.produto.TipoProduto;

public interface TipoProdutoDAO extends CrudRepository<TipoProduto, Long> {

	@Query("select distinct tp from TipoProduto tp order by tp.tipoProduto asc")
	List<TipoProduto> findAll();
}
