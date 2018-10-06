package br.com.r34.negocio.service.lancamento;

import java.util.List;

import br.com.r34.negocio.domain.dto.lancamento.LancamentoDTO;
import br.com.r34.negocio.domain.vo.lancamento.Lancamento;

public interface ServiceLancamento {

	LancamentoDTO inserir(Lancamento lancamento);	
	
	LancamentoDTO deletar(long id);
	
	LancamentoDTO atualizar(Lancamento lancamento);
	
	List<Lancamento> pesquisarPorMembro(long idMembro);
}
