package org.pspa.gcp.modelo.repositorios;

import java.time.LocalDate;

import org.pspa.gcp.modelo.AtividadeMensal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioAtividadeMensal extends JpaRepository<AtividadeMensal, LocalDate> {

}
