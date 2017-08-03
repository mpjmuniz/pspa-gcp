package org.pspa.gcp.modelo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entidade que representa as atividades desenvolvidas ao longo da semana
 * 
 * @author Marcelo Pablo
 * @version 0.1: pronto para entrega
 * */
@Entity
public class AtividadeSemanal {
	 
	@Id
	private LocalDate segunda;
	
	@OneToMany(targetEntity = AtividadeDiaria.class, fetch = FetchType.EAGER)
	private List<AtividadeDiaria> dias;
	
	private String avaliacao;

	public AtividadeSemanal(LocalDate segunda) {
		super();
		
		if(segunda == null){
			int dias = LocalDate.now().getDayOfWeek().getValue() - 1;
			segunda = LocalDate.now().minusDays(dias);
		}
		
		if(!segunda.getDayOfWeek().equals(DayOfWeek.MONDAY)){
			int dias = segunda.getDayOfWeek().getValue() - 1;
			segunda = segunda.minusDays(dias);
		}
		
		this.dias = new ArrayList<>();		
		this.segunda = segunda;
		this.avaliacao = "";
	}
	
	public AtividadeSemanal(){
		this(null);
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
}
