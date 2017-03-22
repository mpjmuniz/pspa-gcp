package org.pspa.gcp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.pspa.gcp.modelo.enums.Semana;


@Entity
public class Produto{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String nome;
		
	private String segunda,
				   terca,
				   quarta,
				   quinta,
				   sexta;
	
	private Semana semana;
	
	private int estoque;
				//balanco;
	
	public Produto() {
		this.nome = "";
		this.estoque = 0;
		this.segunda = "0";
		this.terca = "0";
		this.quarta = "0";
		this.quinta = "0";
		this.sexta = "0";
	}
	
	public Produto(String nome, int estoque, int balanco, String segunda, String terca, String quarta, String quinta, String sexta, Semana semana) {
		this.nome = nome;
		this.estoque = estoque;
		this.segunda = "segunda";
		this.terca = "terca";
		this.quarta = "quarta";
		this.quinta = "quinta";
		this.sexta = "sexta";
		this.semana = semana;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSegunda() {
		return segunda;
	}

	public void setSegunda(String segunda) {
		this.segunda = segunda;
	}

	public String getTerca() {
		return terca;
	}

	public void setTerca(String terca) {
		this.terca = terca;
	}

	public String getQuarta() {
		return quarta;
	}

	public void setQuarta(String quarta) {
		this.quarta = quarta;
	}

	public String getQuinta() {
		return quinta;
	}

	public void setQuinta(String quinta) {
		this.quinta = quinta;
	}

	public String getSexta() {
		return sexta;
	}

	public void setSexta(String sexta) {
		this.sexta = sexta;
	}

	public Semana getSemana() {
		return semana;
	}

	public void setSemana(Semana semana) {
		this.semana = semana;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public String toString(){
		return this.nome;
	}
}
