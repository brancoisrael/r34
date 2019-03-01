package br.com.r34.negocio.domain.vo.produto;

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
import javax.validation.constraints.NotNull;

import br.com.r34.negocio.domain.vo.ValueObject;

@Entity
@Table(name="tb_produto_venda")
public class ProdutoVenda implements ValueObject{

	private static final long serialVersionUID = -8762660046927875906L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message="Informe o preço de venda do produto")
	@Column(name="preco",nullable=false)
	private double preco;
	
	@NotNull(message="Informe o início da vigência da venda")
	@Column(name="inicio_vigencia",nullable=false)
	private Date inicioVigencia;
	
	@Column(name="fim_vigencia",nullable=true)
	private Date fimVigencia;
	
	@NotNull(message="Informe o produto")
	@ManyToOne(optional=false,cascade=CascadeType.REMOVE)
	@JoinColumn(name="id_produto")
	private Produto produto;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
}
