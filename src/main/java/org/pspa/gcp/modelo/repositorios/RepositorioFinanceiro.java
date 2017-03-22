package org.pspa.gcp.modelo.repositorios;

import java.time.LocalDate;

import org.pspa.gcp.modelo.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioFinanceiro extends JpaRepository<Transacao, LocalDate> {
	// TODO: mudar para um repositorio ordenado pela chave, paginado
}
