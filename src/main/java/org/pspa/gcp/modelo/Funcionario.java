package org.pspa.gcp.modelo;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.pspa.gcp.modelo.enums.Funcao;
import org.pspa.gcp.modelo.enums.Sexo;

@Entity
public class Funcionario{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer mid;
	
	private String nome;
	
	protected Sexo sexo;

	private LocalDate dataIngresso;
	private String rg,
			cpf,
			titulo,
			carteiraTrabalho,
			endereco,
			telefone;
	
	private Funcao funcao;
	
	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Turma.class)
	private List<Turma> turmas;
		
	public Funcionario(String nome, Funcao funcao, Sexo sexo, 
			LocalDate dataIngresso, 
			String rg, String cpf, String titulo, 
			String carteiraTrabalho, String endereco, String telefone) {
		
		this.nome = nome;
		this.funcao = funcao;
		this.sexo = sexo;
		this.dataIngresso = dataIngresso;
		this.rg = rg;
		this.cpf = cpf;
		this.titulo = titulo;
		this.carteiraTrabalho = carteiraTrabalho;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	public Funcionario() {
		
		this.nome ="";
		this.sexo = null;
		this.dataIngresso = null;
		this.rg = "";
		this.cpf = "";
		this.titulo = "";
		this.carteiraTrabalho = "";
		this.endereco = "";
		this.telefone = "";
	}

	public Sexo getSexo() {
		return sexo;
	}
	
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	public LocalDate getDataIngresso() {
		return dataIngresso;
	}


	public String getRg() {
		return rg;
	}


	public String getCpf() {
		return cpf;
	}


	public String getTitulo() {
		return titulo;
	}


	public String getCarteiraTrabalho() {
		return carteiraTrabalho;
	}


	public String getEndereco() {
		return endereco;
	}


	public String getTelefone() {
		return telefone;
	}

	
	
	public void setDataIngresso(LocalDate dataIngresso) {
		this.dataIngresso = dataIngresso;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setCarteiraTrabalho(String carteiraTrabalho) {
		this.carteiraTrabalho = carteiraTrabalho;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return this.nome;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	
	public Integer getMid(){
		return this.mid;
	}
	
	public void setMid(Integer id){
		this.mid = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
}
