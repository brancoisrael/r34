package br.com.r34.negocio.domain.dto.lancamento;

import java.util.ArrayList;
import java.util.List;

import br.com.r34.negocio.domain.dto.DataTransferObject;
import br.com.r34.negocio.domain.vo.lancamento.Lancamento;
import br.com.r34.negocio.domain.vo.membro.SaldoMembro;

public class LancamentoDTO implements DataTransferObject {

	private static final long serialVersionUID = 3876522974429457651L;

	private String message;

	private boolean sucesso;

	private List<Lancamento> lancamentos;
	
	private SaldoMembro saldoMembro;
	
	public void addLancamento(Lancamento lancamento) {
		if(lancamentos==null)
			lancamentos = new ArrayList<>();
		lancamentos.add(lancamento);
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public SaldoMembro getSaldoMembro() {
		return saldoMembro;
	}

	public void setSaldoMembro(SaldoMembro saldoMembro) {
		this.saldoMembro = saldoMembro;
	}

}
