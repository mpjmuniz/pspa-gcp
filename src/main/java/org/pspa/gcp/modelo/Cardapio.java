package org.pspa.gcp.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entidade que representa o cardápio, ou seja, o conjunto de produtos para um período de tempo
 * 
 * @author Marcelo Pablo
 * 
 * @version 0.1
 * */
@Entity
public class Cardapio {
	
	@Id
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private String observacao;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Produto> produtos;
	
	private Integer numeroAlunos,
					numeroFuncionarios;
	
	public Cardapio(){
		this.id = 0;
		this.observacao = "";
		this.produtos = new ArrayList<>();
		this.numeroAlunos = 0;
		this.numeroFuncionarios = 0;
	}
	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Integer getNumeroAlunos() {
		return numeroAlunos;
	}

	public void setNumeroAlunos(Integer numeroAlunos) {
		this.numeroAlunos = numeroAlunos;
	}

	public Integer getNumeroFuncionarios() {
		return numeroFuncionarios;
	}

	public void setNumeroFuncionarios(Integer numeroFuncionarios) {
		this.numeroFuncionarios = numeroFuncionarios;
	}
	
	public void removerProduto(Produto p){
		this.produtos.remove(p);
	}
	
	public void adicionarProduto(Produto p){
		if(this.produtos.contains(p)) return;
		this.produtos.add(p);
	}
	
	public String toString(){
		return "Cardápio em " + LocalDate.now().format(DateTimeFormatter.ofPattern("DD/MM/YYYY"));
	}

}
