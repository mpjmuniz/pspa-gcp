package org.pspa.gcp.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.pspa.gcp.modelo.enums.Funcao;
import org.pspa.gcp.modelo.enums.Grupamento;

@Entity
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer mid;
	
	private String nome;

	private Grupamento grupamento;
	
	@OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Aluno> alunos;
	
	@OneToOne(targetEntity = Funcionario.class, fetch = FetchType.LAZY)
	private List<Funcionario> funcionarios;

	public Turma(String nome) {
		this(null, nome, new ArrayList<Funcionario>(),
				new ArrayList<Aluno>());
	}
	
	public Turma() {
		this(null, "", new ArrayList<Funcionario>(),
				new ArrayList<Aluno>());
	}

	public Turma(Grupamento grupamento, String nome, List<Funcionario> funcionarios, List<Aluno> alunos) {
		this.grupamento = grupamento;
		this.nome = nome;
		this.funcionarios = funcionarios;
		this.alunos = alunos;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public void adicionarAuxiliares(List<Funcionario> auxiliares) {
		this.funcionarios.addAll(auxiliares);
	}
	
	public void adicionarAuxiliares(Funcionario auxiliar) {
		this.funcionarios.add(auxiliar);
	}

	public Grupamento getGrupamento() {
		return grupamento;
	}

	public List<Funcionario> getAuxiliares(){
		List<Funcionario> auxiliares = new ArrayList<>();
		
		for(Funcionario f : funcionarios){
			if(f.getFuncao() == Funcao.Auxiliar){
				auxiliares.add(f);
			}
		}
		
		return auxiliares;
	}
	
	public List<Funcionario> getFuncionarios(){
		
		return this.funcionarios;
	}
	
	public void setFuncionarios(List<Funcionario> fs){
		
		this.funcionarios = fs;
	}
	
	public Funcionario getProfessor() {
		for(Funcionario f : funcionarios){
			if(f.getFuncao() == Funcao.Professor) {
				return f;
			}
		}
		return null;
	}

	public void setGrupamento(Grupamento grupamento) {
		this.grupamento = grupamento;
	}

	public void setProfessor(Funcionario professor) {
		if(professor.getFuncao() != Funcao.Professor){
			throw new IllegalArgumentException("O Funcionário precisa ter a função \"professor\" definida para ser posto como professor.");
		}
		
		for(Funcionario f : funcionarios){
			if(f.getFuncao() == Funcao.Professor){
				funcionarios.remove(f);
			}
			
			funcionarios.add(professor);
		}
		
		this.funcionarios.add(professor);
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
	
	@Override
	public String toString(){
		return this.nome;
	}
}