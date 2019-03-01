package br.com.r34.negocio.domain.vo.produto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.r34.negocio.domain.vo.ValueObject;

@Entity
@Table(name="tb_produto")
public class Produto implements ValueObject{

	private static final long serialVersionUID = -8469615987538063864L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Size(min=5,max=200,message="Máximo 200 caracteres")
	@NotNull(message="Informe a descricao do produto")
	@Column(name="descricao",nullable=false, length=200)
	private String descricao;

	@Column(name="habilitar_compra",nullable=false)
	private boolean habilitarCompra;
	
	@Column(name="habilitar_venda",nullable=false)
	private boolean habilitarVenda;
	
	@NotNull(message="Informe o tipo do produto")
	@ManyToOne(optional=false,cascade=CascadeType.REMOVE)
	@JoinColumn(name="id_tipo_produto")
	private TipoProduto tipoProduto;

	@OneToMany(mappedBy = "produto", targetEntity = ProdutoVenda.class, fetch = FetchType.LAZY)
	private List<ProdutoVenda> produtosVenda;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public boolean isHabilitarVenda() {
		return habilitarVenda;
	}

	public void setHabilitarVenda(boolean habilitarVenda) {
		this.habilitarVenda = habilitarVenda;
	}

	public boolean isHabilitarCompra() {
		return habilitarCompra;
	}

	public void setHabilitarCompra(boolean habilitarCompra) {
		this.habilitarCompra = habilitarCompra;
	}

	public List<ProdutoVenda> getProdutosVenda() {
		return produtosVenda;
	}

	public void setProdutosVenda(List<ProdutoVenda> produtosVenda) {
		this.produtosVenda = produtosVenda;
	}
	
	
}
