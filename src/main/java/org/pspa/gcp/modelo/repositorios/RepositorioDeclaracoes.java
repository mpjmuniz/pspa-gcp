package org.pspa.gcp.modelo.repositorios;

import org.pspa.gcp.modelo.Declaracao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioDeclaracoes extends JpaRepository<Declaracao, Integer> {
	
}
