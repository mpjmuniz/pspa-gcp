package org.pspa.gcp.modelo.repositorios;

import java.util.List;

import org.pspa.gcp.modelo.Inscrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioInscrito extends JpaRepository<Inscrito, Integer>{
	
	List<Inscrito> findInscritosByNome(String nome);
	
}
