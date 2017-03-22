package org.pspa.gcp.modelo;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Locale.LanguageRange;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cardapio {
	
	@Id
	private Month mes;
	
	private int ano;
	
	private String observacao;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Produto> produtos;
	
	private Integer numeroAlunos,
					numeroFuncionarios;

	private LocalDate ultimaExecucao;
	
	public Cardapio(){
		this.mes = null;
		this.ano = LocalDate.now().getYear();
		this.observacao = "";
		this.produtos = new ArrayList<>();
		this.numeroAlunos = 0;
		this.numeroFuncionarios = 0;
	}
	
	public Cardapio(Month mes, int ano, String observacao, List<Produto> produtos, Integer numeroAlunos,
			Integer numeroFuncionarios) {
		super();
		this.mes = mes;
		this.ano = ano;
		this.observacao = observacao;
		this.produtos = produtos;
		this.numeroAlunos = numeroAlunos;
		this.numeroFuncionarios = numeroFuncionarios;
	}

	public Month getMes() {
		return mes;
	}

	public void setMes(Month mes) {
		this.mes = mes;
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
		this.produtos.add(p);
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public String toString(){
		List<String> ls = new ArrayList<String>();
		ls.add("pt");
		return this.mes.getDisplayName(TextStyle.FULL, Locale.forLanguageTag(Locale.lookupTag(LanguageRange.parse("pt"), ls)));
	}

	public LocalDate getUltimaExecucao() {
		return ultimaExecucao;
	}

	public void setUltimaExecucao(LocalDate ultimaExecucao) {
		this.ultimaExecucao = ultimaExecucao;
	}
}
