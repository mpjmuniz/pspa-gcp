package org.pspa.gcp.modelo.repositorios;

import org.pspa.gcp.modelo.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioProduto extends JpaRepository<Produto, Integer> {
	
}
