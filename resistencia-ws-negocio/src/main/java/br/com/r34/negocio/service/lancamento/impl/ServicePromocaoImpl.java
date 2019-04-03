package br.com.r34.negocio.service.lancamento.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.r34.negocio.dao.lancamento.LancamentoDAO;
import br.com.r34.negocio.dao.lancamento.PromocaoDAO;
import br.com.r34.negocio.domain.vo.lancamento.Lancamento;
import br.com.r34.negocio.domain.vo.lancamento.Promocao;
import br.com.r34.negocio.service.lancamento.ServicePromocao;

@Service
public class ServicePromocaoImpl implements ServicePromocao{

	@Autowired
	private PromocaoDAO promocaoDAO;
	
	@Autowired
	private LancamentoDAO lancamentoDAO;
	
	@Override
	public void acrescentarPromocao(Lancamento lancamento) {
		if(lancamento.getProdutoVenda()==null)
			return;
		
		Promocao promocao = promocaoDAO.perquisarPorProduto(lancamento.getProdutoVenda().getProduto().getId(), lancamento.getDataLancamento());
		
		if(promocao!=null) {
			if(promocao.getFimVigencia()==null)
				promocao.setFimVigencia(new Date());
			
			List<Promocao> promocoes = new ArrayList<>();
			promocoes.add(promocao);
			
			List<Lancamento> lancamentos = lancamentoDAO.pesquisarParaPromocao(lancamento.getMembro().getId(),
					lancamento.getProdutoVenda().getProduto().getId(),
					promocao.getInicioVigencia(), promocao.getFimVigencia(),lancamento.getId(), PageRequest.of(0, 
					promocao.getQuantidade()-1));
			
			if(!CollectionUtils.isEmpty(lancamentos)) {
				for(Lancamento l : lancamentos) {
					l.setPromocoes(promocoes);
					l.setValorLancamento(0);
					l.setObservacao("Promoção: "+ promocao.getQuantidade() +"x"+promocao.getValor() + " " +l.getObservacao());
					lancamentoDAO.save(l);
				}
				lancamento.setValorLancamento(promocao.getValor());
				lancamento.setPromocoes(promocoes);
				lancamento.setObservacao("Promoção: "+ promocao.getQuantidade() +"x"+promocao.getValor() + " " +lancamento.getObservacao());
				lancamentoDAO.save(lancamento);
			}			
		}
	}
}
