package org.pspa.gcp.modelo.repositorios;

import java.time.Month;

import org.pspa.gcp.modelo.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioCardapio extends JpaRepository<Cardapio, Month> {

}
