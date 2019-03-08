package br.com.r34.negocio.domain.dto.lancamento;

import br.com.r34.negocio.domain.dto.DataTransferObject;
import br.com.r34.negocio.domain.vo.lancamento.Lancamento;

public class LancamentoDTO implements DataTransferObject {

	private static final long serialVersionUID = 3876522974429457651L;

	private String message;

	private boolean sucesso;

	private Lancamento lancamento;
	
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

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

}
