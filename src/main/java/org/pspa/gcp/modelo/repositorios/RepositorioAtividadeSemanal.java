package org.pspa.gcp.modelo.repositorios;

import java.time.LocalDate;

import org.pspa.gcp.modelo.AtividadeSemanal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioAtividadeSemanal extends JpaRepository<AtividadeSemanal, LocalDate> {

}
