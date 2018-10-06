package br.com.r34.negocio.domain.vo.lancamento;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.r34.negocio.domain.vo.ValueObject;
import br.com.r34.negocio.domain.vo.membro.Membro;
import br.com.r34.negocio.enums.OrigemLancamento;
import br.com.r34.negocio.enums.StatusLancamento;
import br.com.r34.negocio.enums.TipoLancamento;

@Entity
@Table(name = "tb_lancamento")
public class Lancamento implements ValueObject {

	private static final long serialVersionUID = 5940470217930532235L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message="Data do lançamento é obrigatória")
	@Column(name="data_lancamento",nullable=false)
	private Date dataLancamento;
	
	@NotNull(message="Valor do lançamento é obrigatório")
	@Column(name="valor_lancamento",nullable=false,precision=2)
	private double valorLancamento;
	
	@NotNull(message="Tipo do lançamento é obrigatório")
	@Enumerated(EnumType.ORDINAL)
	@Column(name="tipo_lancamento",nullable=false)
	private TipoLancamento tipoLancamento;
	
	@NotNull(message="Status do lançamento é obrigatório")
	@Enumerated(EnumType.ORDINAL)
	@Column(name="status_lancamento",nullable=false)
	private StatusLancamento statusLancamento;
	
	@NotNull(message="Origem do lançamento é obrigatório")
	@Enumerated(EnumType.ORDINAL)
	@Column(name="origem_lancamento",nullable=false)
	private OrigemLancamento origemLancamento;

	@NotNull(message="Descrição do lançamento é obrigatório")
	@ManyToOne(optional=false)
	@JoinColumn(name="id_descricao_lancamento")
	private DescricaoLancamento descricaoLancamento;
		
	@NotNull(message="Selecione o membro")
	@ManyToOne(optional=false)
	@JoinColumn(name="id_membro")
	private Membro membro;
	
	@NotNull(message="Informe o responsável pelo lançamento")
	@ManyToOne(optional=false)
	@JoinColumn(name="id_responsavel_lancamento")
	private Membro resposavelLancamento;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public double getValorLancamento() {
		return valorLancamento;
	}

	public void setValorLancamento(double valorLancamento) {
		this.valorLancamento = valorLancamento;
	}

	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public DescricaoLancamento getDescricaoLancamento() {
		return descricaoLancamento;
	}

	public void setDescricaoLancamento(DescricaoLancamento descricaoLancamento) {
		this.descricaoLancamento = descricaoLancamento;
	}

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public StatusLancamento getStatusLancamento() {
		return statusLancamento;
	}

	public void setStatusLancamento(StatusLancamento statusLancamento) {
		this.statusLancamento = statusLancamento;
	}

	public OrigemLancamento getOrigemLancamento() {
		return origemLancamento;
	}

	public void setOrigemLancamento(OrigemLancamento origemLancamento) {
		this.origemLancamento = origemLancamento;
	}

	public Membro getResposavelLancamento() {
		return resposavelLancamento;
	}

	public void setResposavelLancamento(Membro resposavelLancamento) {
		this.resposavelLancamento = resposavelLancamento;
	}
}