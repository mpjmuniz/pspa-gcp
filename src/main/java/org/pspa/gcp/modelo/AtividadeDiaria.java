package org.pspa.gcp.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entidade que representa as atividades desenvolvidas num dia
 * 
 * versão 0.1: pronto para entrega
 * */
@Entity
public class AtividadeDiaria {
	
	@Id
	private LocalDate dia;
	
	@OneToMany(targetEntity = ConteudoDidatico.class, fetch = FetchType.EAGER)
	private List<ConteudoDidatico> conteudos;
	
	public AtividadeDiaria() {
		this(LocalDate.now(), new ArrayList<>());
	}

	public AtividadeDiaria(LocalDate dia, List<ConteudoDidatico> conteudos) {
		super();
		this.dia = dia;
		this.conteudos = conteudos;
	}
	
	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public List<ConteudoDidatico> getConteudos() {
		return conteudos;
	}

	public void setConteudos(List<ConteudoDidatico> conteudos) {
		this.conteudos = conteudos;
	}
}