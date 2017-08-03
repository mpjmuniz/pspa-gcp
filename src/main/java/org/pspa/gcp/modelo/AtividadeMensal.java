package org.pspa.gcp.modelo;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entidade que representa as atividades desenvolvidas ao longo do mês
 * 
 * @author Marcelo Pablo
 * 
 * @version 0.1: pronto para entrega
 * */
@Entity
public class AtividadeMensal {
	
	private String tema;
	private Integer ano;
	
	private Month mes;
	
	@Id
	private LocalDate primeiroDia;
	
	@OneToMany(targetEntity = AtividadeSemanal.class, fetch = FetchType.LAZY)
	private List<AtividadeSemanal> semanas;

	public AtividadeMensal(LocalDate primeiroDia, String tema, AtividadeSemanal semana) {
		this(primeiroDia, tema);
		semanas.add(semana);
	}
	
	public AtividadeMensal(LocalDate primeiroDia, String tema) {
		super();
		
		if(primeiroDia == null){
			primeiroDia = LocalDate.of(LocalDate.now().getYear(), 
					  LocalDate.now().getMonthValue(), 
					  1);
		}
		
		if(primeiroDia.getDayOfMonth() != 1){
			primeiroDia = primeiroDia.minusDays(primeiroDia.getDayOfMonth() - 1);
		}
		
		this.primeiroDia = primeiroDia;
		this.tema = tema;
		this.ano = primeiroDia.getYear();
		this.mes = primeiroDia.getMonth();
		this.semanas = new ArrayList<>();
	}

	public AtividadeMensal() {
		this(null, "");
	}

	public LocalDate getPrimeiroDia() {
		return primeiroDia;
	}

	public void setPrimeiroDia(LocalDate primeiroDia) {
		this.primeiroDia = primeiroDia;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Month getMes() {
		return mes;
	}

	public void setMes(Month mes) {
		this.mes = mes;
	}

	public List<AtividadeSemanal> getSemanas() {
		return semanas;
	}

	public void setSemanas(List<AtividadeSemanal> semanas) {
		this.semanas = semanas;
	}
}
