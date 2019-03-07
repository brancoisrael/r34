package br.com.r34.negocio.domain.dto.produto;

import java.util.Date;

import br.com.r34.negocio.domain.dto.DataTransferObject;

public class ProdutoVendaDTO implements DataTransferObject{

	private static final long serialVersionUID = 1718167156381497320L;

	private long idProdutoVenda;
	
	private Date dataLancamento;
	
	private String message;
	
	private boolean sucesso;
	
	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;	
	}

	@Override
	public boolean isSucesso() {
		return sucesso;
	}

	@Override
	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	public long getIdProdutoVenda() {
		return idProdutoVenda;
	}

	public void setIdProdutoVenda(long idProdutoVenda) {
		this.idProdutoVenda = idProdutoVenda;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

}
