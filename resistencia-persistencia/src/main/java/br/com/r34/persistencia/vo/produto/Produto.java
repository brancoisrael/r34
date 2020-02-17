package br.com.r34.persistencia.vo.produto;

import java.util.List;
import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.r34.persistencia.vo.ValueObject;
import br.com.r34.persistencia.vo.lancamento.Promocao;

@Entity
@Table(name="tb_produto",schema = "core")
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

	@JsonIgnore
	@OneToMany(mappedBy = "produto", targetEntity = ProdutoVenda.class, fetch = FetchType.LAZY)
	private Set<ProdutoVenda> produtosVenda;
	
	@JsonIgnore
	@OneToMany(mappedBy = "produto", targetEntity = Promocao.class, fetch = FetchType.LAZY)
	private List<Promocao> promocao;
	
	
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

	public Set<ProdutoVenda> getProdutosVenda() {
		return produtosVenda;
	}

	public void setProdutosVenda(Set<ProdutoVenda> produtosVenda) {
		this.produtosVenda = produtosVenda;
	}

	public List<Promocao> getPromocao() {
		return promocao;
	}

	public void setPromocao(List<Promocao> promocao) {
		this.promocao = promocao;
	}
	
	
}
