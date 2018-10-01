package br.com.r34.negocio.domain.vo.lancamento;

import java.util.Set;

import javax.persistence.CascadeType;
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

@Entity
@Table(name = "tb_descricao_lancamento")
public class DescricaoLancamento implements ValueObject{

	private static final long serialVersionUID = -5688797730583972780L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message="Informe a descirção")
	@Size(min=5,message="Descrição deve conter no mínimo 5 caracteres")
	@Column(name="descricao",length=255,nullable=false)
	private String descricao;

	@OneToMany(mappedBy = "descricaoLancamento", targetEntity = Lancamento.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Lancamento> lancamentos;
	
	@OneToMany(mappedBy = "descricaoLancamento", targetEntity = TemplateLancamento.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<TemplateLancamento> templateLancamentos;
	
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

	public Set<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(Set<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public Set<TemplateLancamento> getTemplateLancamentos() {
		return templateLancamentos;
	}

	public void setTemplateLancamentos(Set<TemplateLancamento> templateLancamentos) {
		this.templateLancamentos = templateLancamentos;
	}
	
	
}
