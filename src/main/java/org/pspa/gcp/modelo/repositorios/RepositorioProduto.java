package org.pspa.gcp.modelo.repositorios;

import java.util.List;

import org.pspa.gcp.modelo.Produto;
import org.pspa.gcp.modelo.enums.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioProduto extends JpaRepository<Produto, Integer> {
	public List<Produto> getProdutosByTipo(Tipo tipo);
}
