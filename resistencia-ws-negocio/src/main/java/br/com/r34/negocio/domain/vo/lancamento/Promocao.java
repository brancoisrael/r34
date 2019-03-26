package br.com.r34.negocio.domain.vo.lancamento;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.r34.negocio.domain.vo.ValueObject;
import br.com.r34.negocio.domain.vo.produto.TipoProduto;

@Entity
@Table(name = "tb_promocao")
public class Promocao implements ValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2187828270496766964L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Min(value=1, message="Quantidade precisa ser maior que zero")
	@NotNull(message="Quantidade do produto relacionado é obrigatório")
	@Column(name="quantidade_produto",nullable=false)
	private int quantidade;
	
	@Min(value=1, message="Valor precisa ser maior que zero")
	@NotNull(message="Informe o valor da promoção")
	@Column(name="valor",nullable=false)
	private double valor;
	
	@NotNull(message="Informe o início da vigência da venda")
	@Column(name="inicio_vigencia",nullable=false,unique=true)
	private Date inicioVigencia;
	
	@Column(name="fim_vigencia",nullable=true,unique=true)
	private Date fimVigencia;
	
	@NotNull(message="Informe o tipo do produto")
	@ManyToOne(optional=false,cascade=CascadeType.REMOVE)
	@JoinColumn(name="id_tipo_produto")
	private TipoProduto tipoProduto;
	
	@Override
	public void setId(long id) {
		this.id = id;		
	}

	@Override
	public long getId() {
		return id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public Date getFimVigencia() {
		return fimVigencia;
	}

	public void setFimVigencia(Date fimVigencia) {
		this.fimVigencia = fimVigencia;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	
	

}
