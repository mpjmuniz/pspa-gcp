package org.pspa.gcp.modelo.repositorios;

import java.util.List;

import org.pspa.gcp.modelo.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioFuncionario extends JpaRepository<Funcionario, Integer>{
	List<Funcionario> findFuncionariosByNome(String nome);
}
