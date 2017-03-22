package org.pspa.gcp.modelo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class AtividadeSemanal {
	 
	@Id
	private LocalDate segunda;
	
	@ManyToOne
	private AtividadeMensal mes;
	
	@OneToMany(targetEntity = AtividadeDiaria.class, fetch = FetchType.EAGER)
	private List<AtividadeDiaria> dias;
	
	private String avaliacao;

	public AtividadeSemanal(LocalDate segunda, AtividadeMensal mes) {
		super();
		
		if(!segunda.getDayOfWeek().equals(DayOfWeek.MONDAY)){
			throw new IllegalArgumentException("este campo serve somente para a segunda feira desta semana");
		}
		
		this.dias = new ArrayList<>();		
		this.mes = mes;
		this.segunda = segunda;
		this.avaliacao = "";
	}
	
	public AtividadeSemanal(){
		this.dias = null;
		this.mes = null;
		this.segunda = null;
		this.avaliacao = "";
	}

	public LocalDate getSegunda() {
		return segunda;
	}

	public void setSegunda(LocalDate segunda) {
		this.segunda = segunda;
	}

	public List<AtividadeDiaria> getDias() {
		return dias;
	}

	public void setDias(List<AtividadeDiaria> dias) {
		this.dias = dias;
	}

	public String getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}

	public AtividadeMensal getMes() {
		return mes;
	}

	public void setMes(AtividadeMensal mes) {
		this.mes = mes;
	}
}
