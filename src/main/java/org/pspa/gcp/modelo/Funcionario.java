package org.pspa.gcp.modelo;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.pspa.gcp.modelo.enums.Funcao;
import org.pspa.gcp.modelo.enums.Instrucao;
import org.pspa.gcp.modelo.enums.Sexo;

/**
 * Entidade que representa um funcionário da instituição
 * 
 * @version 0.1
 * 
 * @author Marcelo Pablo
 * */
@Entity
public class Funcionario implements Participante{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer mid;
	
	private String nome;
	
	protected Sexo sexo;

	private LocalDate data_Ingresso,
					  data_Nascimento;
	
	private String RG,
			CPF,
			titulo,
			carteira_Trabalho,
			endereco,
			telefone;
	
	private Funcao funcao;
	
	private Instrucao formacao;
	
	@ManyToOne(optional = true, cascade = CascadeType.ALL)
	@OneToOne(optional = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "turma_id")
	private Turma turma;
		
	public Funcionario(Integer mid, String nome, Funcao funcao, Sexo sexo, 
			LocalDate dataIngresso, LocalDate dataNascimento,
			String rg, String cpf, String titulo, Instrucao formacao,
			String carteiraTrabalho, String endereco, String telefone, Turma turma) {
		
		
		this.mid = mid;
		this.nome = nome;
		this.funcao = funcao;
		this.sexo = sexo;
		this.data_Ingresso = dataIngresso;
		this.data_Nascimento = dataNascimento;
		this.RG = rg;
		this.formacao = formacao;
		this.CPF = cpf;
		this.titulo = titulo;
		this.carteira_Trabalho = carteiraTrabalho;
		this.endereco = endereco;
		this.telefone = telefone;
		this.turma = turma;
	}
	
	public Funcionario() {
		
		this(1, "", null, null, null, null, "", "", "", null, "", "", "", new Turma());
	}

	public Sexo getSexo() {
		return sexo;
	}
	
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	public LocalDate getData_ingresso() {
		return data_Ingresso;
	}


	public String getRG() {
		return RG;
	}


	public String getCPF() {
		return CPF;
	}


	public String getTitulo() {
		return titulo;
	}


	public String getCarteira_trabalho() {
		return carteira_Trabalho;
	}


	public String getEndereco() {
		return endereco;
	}


	public String getTelefone() {
		return telefone;
	}

	
	
	public void setData_ingresso(LocalDate dataIngresso) {
		this.data_Ingresso = dataIngresso;
	}

	public void setRG(String rg) {
		this.RG = rg;
	}

	public void setCPF(String cpf) {
		this.CPF = cpf;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setCarteira_trabalho(String carteiraTrabalho) {
		this.carteira_Trabalho = carteiraTrabalho;
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

	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
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

	public LocalDate getData_nascimento() {
		return data_Nascimento;
	}

	public void setData_nascimento(LocalDate dataNascimento) {
		this.data_Nascimento = dataNascimento;
	}

	public Instrucao getFormacao() {
		return formacao;
	}

	public void setFormacao(Instrucao formacao) {
		this.formacao = formacao;
	}
}
