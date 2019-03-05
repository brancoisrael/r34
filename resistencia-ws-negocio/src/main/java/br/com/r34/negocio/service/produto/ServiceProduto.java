package br.com.r34.negocio.service.produto;

import java.util.List;

import br.com.r34.negocio.domain.vo.produto.Produto;

public interface ServiceProduto {

	List<Produto> listarProdutoVenda(long idTipoProduto);
}
