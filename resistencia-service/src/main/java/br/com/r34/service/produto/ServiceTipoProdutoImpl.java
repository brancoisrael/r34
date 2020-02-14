package br.com.r34.service.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.r34.persistencia.repository.produto.TipoProdutoDAO;
import br.com.r34.persistencia.vo.produto.TipoProduto;

@Service
public class ServiceTipoProdutoImpl{

	@Autowired
	private TipoProdutoDAO tipoProdutoDAO;
	
	public List<TipoProduto> listarTipoProduto(){
		return tipoProdutoDAO.findAll();
	}
}
