package br.com.r34.negocio.domain.vo.membro;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.r34.negocio.domain.vo.ValueObject;
import br.com.r34.negocio.domain.vo.acesso.RegraAcesso;
import br.com.r34.negocio.domain.vo.lancamento.Lancamento;
import br.com.r34.negocio.domain.vo.lancamento.TemplateLancamento;
import br.com.r34.negocio.enums.Cargo;
import br.com.r34.negocio.enums.Patente;
import br.com.r34.negocio.enums.SituacaoMembro;

@Entity
@Table(name = "tb_membro")
public class Membro implements ValueObject {

	private static final long serialVersionUID = 8002032853686500638L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message="Informe o status")
	@Column(name="status",nullable=false)
	private boolean status;
	
	@NotNull(message="Informe o nome do infeliz")
	@Size(min=2,max=255,message="No mínimo 2 e no máximo 255 caracteres")
	@Column(name="nome",length=255,nullable=false)
	private String nome;
	
	@Size(min=0,max=255,message="Máximo 50 caracteres")
	@Column(name="apelido",length=50)
	private String apelido;
	
	@NotNull(message="Informe o e-mail")
	@Size(min=2,max=255,message="No mínimo 2 e no máximo 255 caracteres")
	@Column(name="email",length=255,nullable=false,unique=true)
	private String email;
	
	@NotNull(message="Informe a senha")
	@Size(min=2,max=255,message="No mínimo 2 e no máximo 255 caracteres")
	@Column(name="senha",length=255,nullable=false)
	private String senha;
	
	@NotNull(message="Informe a situação do membro")
	@Column(name="situacao_membro",nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private SituacaoMembro situacaoMembro;

	@Column(name="data_nascimento")
	private Date dataNascimento;
	
	@NotNull(message="Informe a data de entrada")
	@Column(name="data_entrada",nullable=false)
	private Date dataEntrada;
	
	@Column(name="data_saida")
	private Date dataSaida;

	@NotNull(message="Informe a patente")
	@Column(name="patente",nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private Patente patente;
	
	@Column(name="cargo",nullable=true)
	@Enumerated(EnumType.ORDINAL)
	private Cargo cargo;
		
	@OneToMany(mappedBy = "membro", targetEntity = Lancamento.class, fetch = FetchType.LAZY)
	private Set<Lancamento> lancamentos;
	
	@OneToMany(mappedBy = "membro", targetEntity = Lancamento.class, fetch = FetchType.LAZY)
	private Set<Lancamento> responsavelLancamentos;

	@OneToMany(mappedBy = "membro", targetEntity = TemplateLancamento.class, fetch = FetchType.LAZY)
	private Set<TemplateLancamento> templateLancamentos;

	@OneToMany(mappedBy = "membro", targetEntity = Lancamento.class, fetch = FetchType.LAZY)
	private Set<Lancamento> templateResponsavelLancamentos;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="tb_membro_regra_acesso",
	joinColumns={@JoinColumn(name="id_regra_acesso")},
	inverseJoinColumns={@JoinColumn(name="id_membro")})
	private Set<RegraAcesso> regrasAcesso;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public SituacaoMembro getSituacaoMembro() {
		return situacaoMembro;
	}

	public void setSituacaoMembro(SituacaoMembro situacaoMembro) {
		this.situacaoMembro = situacaoMembro;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Patente getPatente() {
		return patente;
	}

	public void setPatente(Patente patente) {
		this.patente = patente;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
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

	public Set<Lancamento> getTemplateResponsavelLancamentos() {
		return templateResponsavelLancamentos;
	}

	public void setTemplateResponsavelLancamentos(Set<Lancamento> templateResponsavelLancamentos) {
		this.templateResponsavelLancamentos = templateResponsavelLancamentos;
	}

	public Set<RegraAcesso> getRegrasAcesso() {
		return regrasAcesso;
	}

	public void setRegrasAcesso(Set<RegraAcesso> regrasAcesso) {
		this.regrasAcesso = regrasAcesso;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
