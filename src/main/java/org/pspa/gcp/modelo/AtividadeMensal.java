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

	public AtividadeMensal(LocalDate primeiroDia, String tema, Integer ano, Month mes, AtividadeSemanal semana) {
		this(primeiroDia, tema, ano, mes);
		semanas.add(semana);
	}
	
	public AtividadeMensal(LocalDate primeiroDia, String tema, Integer ano, Month mes) {
		super();
		
		if(primeiroDia.getDayOfMonth() != 1){
			throw new IllegalArgumentException("O dia deve ser o primeiro de um dado mês");
		}
		
		this.tema = tema;
		this.ano = ano;
		this.mes = mes;
		this.semanas = new ArrayList<>();
	}

	public AtividadeMensal() {
		this(LocalDate.of(LocalDate.now().getYear(), 
						  LocalDate.now().getMonthValue(), 
						  1), 
			 "", 
			 LocalDate.now().getYear(), 
			 LocalDate.now().getMonth());
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
