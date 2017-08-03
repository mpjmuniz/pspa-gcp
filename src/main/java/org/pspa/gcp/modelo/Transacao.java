package org.pspa.gcp.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entidade que representa uma transação monetária
 * @author Marcelo Pablo
 *
 */
@Entity
public class Transacao {
	
	@Id
	private LocalDate data;
	
	private String descricao;
	
	public Transacao(LocalDate data, String descricao, Double entrada, Double saida) {
		super();
		this.data = data;
		this.descricao = descricao;
		this.entrada = entrada;
		this.saida = saida;
	}

	// TODO verificar melhor tipo para isso
	private Double entrada,
				   saida; 
	
	public Transacao(){
		this(null, "", 0.0, 0.0);
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
	}

	public Double getSaida() {
		return saida;
	}

	public void setSaida(Double saida) {
		this.saida = saida;
	}

	public Double getBalanco() {
		return entrada - saida;
	}	
}
