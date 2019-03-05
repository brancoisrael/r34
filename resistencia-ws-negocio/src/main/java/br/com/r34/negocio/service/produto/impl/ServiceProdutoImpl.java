package br.com.r34.negocio.service.produto.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.r34.negocio.dao.produto.ProdutoDAO;
import br.com.r34.negocio.domain.vo.produto.Produto;
import br.com.r34.negocio.service.produto.ServiceProduto;

@Service
public class ServiceProdutoImpl implements ServiceProduto{

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Override
	public List<Produto> listarProdutoVenda(long idTipoProduto){
		return produtoDAO.listarProdutoVenda(idTipoProduto);
	}
}
