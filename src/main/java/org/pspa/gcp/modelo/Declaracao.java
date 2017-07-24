package org.pspa.gcp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Marcelo Pablo
 * 
 * @version 0.1
 * */
@Entity
public class Declaracao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String cabecalho,
				   corpo,
				   rodape,
				   proposito;

	public Declaracao() {
		super();
		this.cabecalho = "";
		this.corpo = "";
		this.rodape = "";
		this.proposito = "";
	}

	public Declaracao(String cabecalho, String corpo, String rodape, String proposito) {
		super();
		this.cabecalho = cabecalho;
		this.corpo = corpo;
		this.rodape = rodape;
		this.proposito = proposito;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCabecalho() {
		return cabecalho;
	}

	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	public String getRodape() {
		return rodape;
	}

	public void setRodape(String rodape) {
		this.rodape = rodape;
	}

	public String getProposito() {
		return proposito;
	}

	public void setProposito(String proposito) {
		this.proposito = proposito;
	}
	
	
}