package br.com.r34.negocio.service.produto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import br.com.r34.negocio.domain.dto.produto.ProdutoVendaDTO;
import br.com.r34.negocio.domain.vo.produto.Produto;
import br.com.r34.negocio.domain.vo.produto.ProdutoVenda;

public interface ServiceProduto {

	List<Produto> listarProdutoVenda(long idTipoProduto);
	
	ProdutoVenda buscarPorProdutoData(ProdutoVendaDTO produtoVendaDTO);
	
	List<ProdutoVenda> buscarPorProdutoData(ProdutoVendaDTO produtoVendaDTO,Pageable pageable); 
}
