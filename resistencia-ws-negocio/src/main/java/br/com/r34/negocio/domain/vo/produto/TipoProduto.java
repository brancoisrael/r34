package br.com.r34.negocio.domain.vo.produto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.r34.negocio.domain.vo.ValueObject;
import br.com.r34.negocio.domain.vo.lancamento.Promocao;

@Entity
@Table(name="tb_tipo_produto")
public class TipoProduto implements ValueObject{

	private static final long serialVersionUID = 7704754802604164440L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Size(min=0,max=20,message="Máximo 20 caracteres")
	@NotNull(message="Informe o tipo do produto")
	@Column(name="tipo_produto",nullable=false, length=20, unique=true)
	private String tipoProduto;
	
	@OneToMany(mappedBy = "tipoProduto", targetEntity = Produto.class, fetch = FetchType.LAZY)
	private List<Produto> produtos;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
}
