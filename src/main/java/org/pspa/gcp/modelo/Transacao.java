package org.pspa.gcp.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transacao {
	
	@Id
	private LocalDate data;
	
	private String descricao;
	
	public Transacao(LocalDate data, String descricao, Double entrada, Double saida, Double balanco) {
		super();
		this.data = data;
		this.descricao = descricao;
		this.entrada = entrada;
		this.saida = saida;
		this.balanco = balanco;
	}

	// TODO verificar melhor tipo para isso
	private Double entrada,
				   saida, 
				   balanco;
	
	public Transacao(){
		this.data = null;
		this.descricao = "";
		this.entrada = 0.0;
		this.saida = 0.0;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getEntrada() {
		return entrada;
	}

	public void setEntrada(Double entrada) {
		this.entrada = entrada;
		
		this.balanco = this.entrada - this.saida;
	}

	public Double getSaida() {
		return saida;
	}

	public void setSaida(Double saida) {
		this.saida = saida;
		
		this.balanco = this.entrada - this.saida;
	}

	public Double getBalanco() {
		return balanco;
	}	
}
