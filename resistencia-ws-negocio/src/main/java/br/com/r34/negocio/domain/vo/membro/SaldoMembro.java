package br.com.r34.negocio.domain.vo.membro;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.r34.negocio.domain.vo.ValueObject;

@Entity
@Table(name = "tb_saldo_membro")
public class SaldoMembro implements ValueObject {

	private static final long serialVersionUID = 7344261434291626041L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message="Saldo do membro não pode ser nulo")
	@Column(name="saldo",nullable=false)
	private double saldo;
	
	@NotNull(message="Data da última atualização não pode ser nula")
	@Column(name="ultima_atualizacao",nullable=false)
	private Date ultimaAtualizacao;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_membro", referencedColumnName = "id",unique=true)
	private Membro membro;
	
	public SaldoMembro() {
		ultimaAtualizacao = new Date();
	}
	
	public SaldoMembro(Membro membro) {
		ultimaAtualizacao = new Date();
		this.membro = membro;
	}
	
	@Override
	public void setId(long id) {
		this.id = id;		
	}

	@Override
	public long getId() {
		return id;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Date getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	
}
