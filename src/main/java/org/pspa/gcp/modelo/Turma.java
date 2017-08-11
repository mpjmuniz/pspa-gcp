package org.pspa.gcp.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

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

/**
 * Entidade que representa uma turma presente na instituição
 * 
 * @author Marcelo Pablo
 *
 */
@Entity
public class Turma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer mid;
	
	private Grupamento grupamento;
	
	@OneToOne(mappedBy = "turma", fetch = FetchType.LAZY, optional = true, targetEntity = Funcionario.class)
	private Funcionario professor;
	
	/**
	 * 
	 */

	@OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Aluno> alunos;


	@OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Funcionario.class)
	private List<Funcionario> auxiliares;
	
	public Turma(Grupamento grup) {
		this(1, grup, null, new ArrayList<Funcionario>(),
				new ArrayList<Aluno>());
	}
	
	public Turma() {
		this(1, null, null, new ArrayList<Funcionario>(),
				new ArrayList<Aluno>());
	}

	public Turma(Integer mid, Grupamento grupamento, Funcionario professor, List<Funcionario> funcionarios, List<Aluno> alunos) {
		this.mid = mid;
		this.professor = professor;
		this.grupamento = grupamento;
		this.auxiliares = funcionarios;
		this.alunos = alunos;
	}

	
	public List<Aluno> getAlunos() {
		return alunos;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setAlunos(List alunos) {
		
		this.alunos = (List<Aluno>) alunos;
		
		ConcurrentLinkedQueue<Aluno> al = new ConcurrentLinkedQueue<>(this.alunos);
		if(al != null){
			al.forEach((e) -> e.setTurma(this));
		}
	}

	public void adicionarAuxiliares(Set<Funcionario> auxiliares) {
		this.auxiliares.addAll(auxiliares);
		
		if(auxiliares != null){
			for(Funcionario a : auxiliares){
				a.setTurma(this);
			}
		}
	}
	
	public void adicionarAuxiliar(Funcionario auxiliar) {
		this.auxiliares.add(auxiliar);
		
		auxiliar.setTurma(this);
	}

	public Grupamento getGrupamento() {
		return grupamento;
	}

	
	public List<Funcionario> getAuxiliares(){
		
		return auxiliares;
	}
	
	public List<Funcionario> getFuncionarios(){
		List<Funcionario> lst = new ArrayList<Funcionario>();
		
		lst.add(professor);
		lst.addAll(auxiliares);
		
		return lst;
	}
	
	// mesma coisa, adicionado para funcionar a visão turma reflexivamente
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setFuncionarios(List fs){		
		setAuxiliares((List<Funcionario>) fs);
	}
	
	public void setAuxiliares(List<Funcionario> fs){
		this.auxiliares = fs;

		
		if(fs != null){
			for(Funcionario a : fs){
				a.setTurma(this);
			}
		}
	}
	
	
	public Funcionario getProfessor() {
		return professor;
	}
	
	public Funcionario getFuncionario(){
		return getProfessor();
	}

	public void setGrupamento(Grupamento grupamento) {
		this.grupamento = grupamento;
	}

	public void setProfessor(Funcionario professor) {
			
		if(professor != null && professor.getFuncao() != Funcao.Professor){
			throw new IllegalArgumentException("O Funcionário precisa ter a função \"professor\" definida para ser posto como professor.");
		}
		
		this.professor = professor;
		
		professor.setTurma(this);
	}
	
	public void setFuncionario(Funcionario f){
		if(f == null) return;
		if(f.getFuncao().equals(Funcao.Professor)){
			setProfessor(f);
		} else {
			this.auxiliares.clear();
			adicionarAuxiliar(f);
		}
	}

	public Integer getMid(){
		return this.mid;
	}
	
	public void setMid(Integer id){
		this.mid = id;
	}
	
	@Override
	public String toString(){
		return (grupamento == null) ? "[Nova]" : grupamento.toString();
	}
	
	public void setNome(String nome){
		return;
	}
}
