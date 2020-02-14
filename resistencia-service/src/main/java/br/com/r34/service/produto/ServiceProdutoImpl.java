package br.com.r34.service.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.r34.persistencia.dto.produto.ProdutoVendaDTO;
import br.com.r34.persistencia.repository.produto.ProdutoDAO;
import br.com.r34.persistencia.repository.produto.ProdutoVendaDAO;
import br.com.r34.persistencia.vo.produto.Produto;
import br.com.r34.persistencia.vo.produto.ProdutoVenda;

@Service
public class ServiceProdutoImpl{

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private ProdutoVendaDAO produtoVendaDAO;
	
	
	public List<Produto> listarProdutoVenda(long idTipoProduto){
		return produtoDAO.listarProdutoVenda(idTipoProduto);
	}
	
	
	public ProdutoVenda buscarPorProdutoData(ProdutoVendaDTO produtoVendaDTO) {
		List<ProdutoVenda> lista = produtoVendaDAO.findByProdutoData(produtoVendaDTO.getIdProdutoVenda(),produtoVendaDTO.getDataLancamento(), PageRequest.of(0, 1));
		
		if(!CollectionUtils.isEmpty(lista))
			return lista.get(0);
		
		return null;
	}
	
	
	public List<ProdutoVenda> buscarPorProdutoData(ProdutoVendaDTO produtoVendaDTO, Pageable pageable) {
		return produtoVendaDAO.findByProdutoData(produtoVendaDTO.getIdProdutoVenda(),produtoVendaDTO.getDataLancamento(), pageable);
	}
}
