package org.pspa.gcp.modelo.repositorios;

import java.util.List;

import org.pspa.gcp.modelo.Aluno;
import org.pspa.gcp.modelo.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioAluno extends JpaRepository<Aluno, Integer>{
	
	List<Aluno> findAlunosByNome(String nome);
	
	List<Aluno> findAlunosByTurma(Turma turma);	
}