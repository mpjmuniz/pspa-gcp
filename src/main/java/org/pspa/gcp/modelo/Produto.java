package org.pspa.gcp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.pspa.gcp.modelo.enums.Semana;
import org.pspa.gcp.modelo.enums.Tipo;

/**
 * @author Marcelo Pablo
 * 
 * @version 0.1
 * */
@Entity
public class Produto{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String nome;
		
	private String medida;
	
	private int necessario;
	
	private int segunda,
				   terca,
				   quarta,
				   quinta,
				   sexta;
	
	private Semana semana;
	
	private int estoque;
				//balanco;
	
	private Tipo tipo;
	
	public Produto() {
		this.nome = "";
		this.estoque = 0;
		this.necessario = 0;
		this.segunda = 0;
		this.terca = 0;
		this.quarta = 0;
		this.quinta = 0;
		this.sexta = 0;
		this.medida = "";
		this.setTipo(null);
	}
	
	public Produto(String nome, int estoque, Tipo tipo, String medida, int segunda, int terca, int quarta, int quinta, int sexta, Semana semana) {
		this.nome = nome;
		this.estoque = estoque;
		this.segunda = segunda;
		this.medida = medida;
		this.terca = terca;
		this.setTipo(tipo);
		this.quarta = quarta;
		this.quinta = quinta;
		this.sexta = sexta;
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

	public int getSegunda() {
		return segunda;
	}

	public void setSegunda(int segunda) {
		this.segunda = segunda;
	}

	public int getTerca() {
		return terca;
	}

	public void setTerca(int terca) {
		this.terca = terca;
	}

	public int getQuarta() {
		return quarta;
	}

	public void setQuarta(int quarta) {
		this.quarta = quarta;
	}

	public int getQuinta() {
		return quinta;
	}

	public void setQuinta(int quinta) {
		this.quinta = quinta;
	}

	public int getSexta() {
		return sexta;
	}

	public void setSexta(int sexta) {
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

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public int getNecessario() {
		return getSegunda() + getTerca() + getQuarta() + getQuinta() + getSexta() + necessario;
	}
	
	public void setNecessario(int necessario){
		this.necessario = necessario;
	}

	public int getComprar() {
		int qtd = getNecessario() - getEstoque();
		
		return qtd > 0 ? qtd : 0;
	}
}
