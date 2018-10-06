package br.com.r34.negocio.domain.vo.acesso;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.r34.negocio.domain.vo.ValueObject;

@Entity
@Table(name = "tb_endpoint")
public class Endpoint implements ValueObject{

	private static final long serialVersionUID = -1822389722737103085L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Size(max=255,message="URL deve conter no máximo 255 caracteres")
	@Column(name="descricao",length=255)
	private String descricao;
	
	@NotNull(message="Informe a url")
	@Size(min=5, max=255,message="URL deve conter no mínimo 5 e no máximo 255 caracteres")
	@Column(name="url",length=255,nullable=false,unique=true)
	private String url;
	
	@ManyToMany(mappedBy="endpoints",cascade=CascadeType.PERSIST)
	private Set<RegraAcesso> regrasAcesso;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<RegraAcesso> getRegrasAcesso() {
		return regrasAcesso;
	}

	public void setRegrasAcesso(Set<RegraAcesso> regrasAcesso) {
		this.regrasAcesso = regrasAcesso;
	}
	
	
}
