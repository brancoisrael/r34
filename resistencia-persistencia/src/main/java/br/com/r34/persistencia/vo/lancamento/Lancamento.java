package br.com.r34.persistencia.vo.lancamento;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.r34.persistencia.enums.OrigemLancamento;
import br.com.r34.persistencia.enums.StatusLancamento;
import br.com.r34.persistencia.enums.TipoLancamento;
import br.com.r34.persistencia.vo.ValueObject;
import br.com.r34.persistencia.vo.membro.Membro;
import br.com.r34.persistencia.vo.produto.ProdutoVenda;

@Entity
@Table(name = "tb_lancamento",schema = "core")
public class Lancamento implements ValueObject, Cloneable{

	private static final long serialVersionUID = 5940470217930532235L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message="Data do lançamento é obrigatória")
	@Column(name="data_lancamento",nullable=false)
	private Date dataLancamento;
	
	@Column(name="criado_em",nullable=false)
	private Date criadoEm;
	
	@NotNull(message="Valor do lançamento é obrigatório")
	@Column(name="valor_lancamento",nullable=false,precision=2)
	private double valorLancamento;
	
	@Min(value=1, message="Quantidade precisa ser maior que zero")
	@NotNull(message="Quantidade do produto relacionado é obrigatório")
	@Column(name="quantidade_produto",nullable=false)
	private int quantidade;
	
	@Size(max=2000,message="Limite de 2000 caracteres no campo observação")
	@Column(name="observacao",nullable=true,length=2000)
	private String observacao;
	
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
		
	@NotNull(message="Selecione o membro")
	@ManyToOne(optional=false)
	@JoinColumn(name="id_membro")
	private Membro membro;
	
	@NotNull(message="Informe o responsável pelo lançamento")
	@ManyToOne(optional=false)
	@JoinColumn(name="id_responsavel_lancamento")
	private Membro resposavelLancamento;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="id_produto_venda")
	private ProdutoVenda produtoVenda;
	
	@ManyToMany
	@JoinTable(name = "tb_lancamento_promocao", 
			  joinColumns = @JoinColumn(name = "id_lancamento"), 
			  inverseJoinColumns = @JoinColumn(name = "id_promocao"))
	private List<Promocao> promocoes;
	
	@Override
    public Lancamento clone() throws CloneNotSupportedException {
        return (Lancamento) super.clone();
    }
	
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

	public Date getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public ProdutoVenda getProdutoVenda() {
		return produtoVenda;
	}

	public void setProdutoVenda(ProdutoVenda produtoVenda) {
		this.produtoVenda = produtoVenda;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<Promocao> getPromocoes() {
		return promocoes;
	}

	public void setPromocoes(List<Promocao> promocoes) {
		this.promocoes = promocoes;
	}
}
