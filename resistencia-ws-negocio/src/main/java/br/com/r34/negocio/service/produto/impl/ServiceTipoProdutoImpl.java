package br.com.r34.negocio.service.produto.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.r34.negocio.dao.produto.TipoProdutoDAO;
import br.com.r34.negocio.domain.vo.produto.TipoProduto;
import br.com.r34.negocio.service.produto.ServiceTipoProduto;

@Service
public class ServiceTipoProdutoImpl implements ServiceTipoProduto{

	@Autowired
	private TipoProdutoDAO tipoProdutoDAO;
	
	@Override
	public List<TipoProduto> listarTipoProduto(){
		return tipoProdutoDAO.findAll();
	}
}
