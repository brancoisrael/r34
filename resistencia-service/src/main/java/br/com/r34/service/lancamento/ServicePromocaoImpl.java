package br.com.r34.service.lancamento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.r34.persistencia.repository.lancamento.LancamentoDAO;
import br.com.r34.persistencia.repository.lancamento.PromocaoDAO;
import br.com.r34.persistencia.vo.lancamento.Lancamento;
import br.com.r34.persistencia.vo.lancamento.Promocao;

@Service
public class ServicePromocaoImpl {

	@Autowired
	private PromocaoDAO promocaoDAO;

	@Autowired
	private LancamentoDAO lancamentoDAO;

	
	public void acrescentarPromocao(Lancamento lancamento) {
		if (lancamento.getProdutoVenda() == null)
			return;

		Promocao promocao = promocaoDAO.perquisarPorProduto(lancamento.getProdutoVenda().getProduto().getId(),
				lancamento.getDataLancamento());

		if (promocao != null) {
			Date dataFim = promocao.getFimVigencia();
			if (dataFim == null)
				dataFim = new Date();

			List<Promocao> promocoes = new ArrayList<>();
			promocoes.add(promocao);

			List<Lancamento> lancamentos = lancamentoDAO.pesquisarParaPromocao(lancamento.getMembro().getId(),
					lancamento.getProdutoVenda().getProduto().getId(), promocao.getInicioVigencia(), dataFim,
					lancamento.getId(), PageRequest.of(0, promocao.getQuantidade() - 1));

			if (!CollectionUtils.isEmpty(lancamentos) && lancamentos.size() == promocao.getQuantidade() - 1) {
				for (Lancamento l : lancamentos) {
					l.setPromocoes(promocoes);
					l.setValorLancamento(0);
					l.setObservacao("Promoção: " + promocao.getQuantidade() + "x" + promocao.getValor() + " "
							+ l.getObservacao());
					lancamentoDAO.save(l);
				}
				lancamento.setValorLancamento(promocao.getValor());
				lancamento.setPromocoes(promocoes);
				lancamento.setObservacao("Promoção: " + promocao.getQuantidade() + "x" + promocao.getValor() + " "
						+ lancamento.getObservacao());
				lancamentoDAO.save(lancamento);
			}
		}

	}

	
	public void removerPromocao(Lancamento lanc) {

		if(CollectionUtils.isEmpty(lanc.getPromocoes()))
			return;
		
		Promocao promocao = lanc.getPromocoes().get(0);
		Date dataFim = promocao.getFimVigencia();
		if (dataFim == null)
			dataFim = new Date();

		List<Lancamento> lancamentos = lancamentoDAO.pesquisarPorPromocao(lanc.getMembro().getId(),
				promocao.getInicioVigencia(), dataFim, promocao.getId(), lanc.getId(),
				PageRequest.of(0, promocao.getQuantidade() - 1));

		lanc.setPromocoes(null);
		lanc.setValorLancamento(0);
		for (Lancamento l : lancamentos) {
			l.setPromocoes(null);
			l.setValorLancamento(lanc.getProdutoVenda().getPreco());
			l.setObservacao(l.getObservacao()
					.replace("Promoção: " + promocao.getQuantidade() + "x" + promocao.getValor() + " ", ""));
			lancamentoDAO.save(l);
			acrescentarPromocao(l);
		}

	}
}
