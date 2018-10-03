package br.com.r34.negocio.domain.vo.acesso;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.r34.negocio.domain.vo.ValueObject;
import br.com.r34.negocio.domain.vo.membro.Membro;
import br.com.r34.negocio.enums.PerfilAcesso;

@Entity
@Table(name = "tb_regra_acesso")
public class RegraAcesso implements ValueObject {

	private static final long serialVersionUID = -8770307674110008037L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message="Informe o perfil de acesso ao sistema")
	@Column(name="rules_acesso",nullable=false, unique=true)
	@Enumerated(EnumType.ORDINAL)
	private PerfilAcesso perfilAcesso;
	
	@ManyToMany(mappedBy="regrasAcesso",cascade=CascadeType.ALL)
	private Set<Endpoint> endpoints;
	
	@ManyToMany(mappedBy="regrasAcesso",cascade=CascadeType.ALL)
	private Set<Membro> membros;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PerfilAcesso getPerfilAcesso() {
		return perfilAcesso;
	}

	public void setPerfilAcesso(PerfilAcesso perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}

	public Set<Membro> getMembros() {
		return membros;
	}

	public void setMembros(Set<Membro> membros) {
		this.membros = membros;
	}

	public Set<Endpoint> getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(Set<Endpoint> endpoints) {
		this.endpoints = endpoints;
	}
	
	
}
