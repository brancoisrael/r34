package br.com.r34.negocio.service.produto.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.r34.negocio.dao.produto.ProdutoDAO;
import br.com.r34.negocio.dao.produto.ProdutoVendaDAO;
import br.com.r34.negocio.domain.dto.produto.ProdutoVendaDTO;
import br.com.r34.negocio.domain.vo.produto.Produto;
import br.com.r34.negocio.domain.vo.produto.ProdutoVenda;
import br.com.r34.negocio.service.produto.ServiceProduto;

@Service
public class ServiceProdutoImpl implements ServiceProduto{

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private ProdutoVendaDAO produtoVendaDAO;
	
	@Override
	public List<Produto> listarProdutoVenda(long idTipoProduto){
		return produtoDAO.listarProdutoVenda(idTipoProduto);
	}
	
	@Override
	public ProdutoVenda buscarPorProdutoData(ProdutoVendaDTO produtoVendaDTO) {
		List<ProdutoVenda> lista = produtoVendaDAO.findByProdutoData(produtoVendaDTO.getIdProdutoVenda(),produtoVendaDTO.getDataLancamento(), PageRequest.of(0, 1));
		
		if(!CollectionUtils.isEmpty(lista))
			return lista.get(0);
		
		return null;
	}
	
	@Override
	public List<ProdutoVenda> buscarPorProdutoData(ProdutoVendaDTO produtoVendaDTO, Pageable pageable) {
		return produtoVendaDAO.findByProdutoData(produtoVendaDTO.getIdProdutoVenda(),produtoVendaDTO.getDataLancamento(), pageable);
	}
}
