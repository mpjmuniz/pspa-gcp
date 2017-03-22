package org.pspa.gcp.visao.quadroestrutural;

import java.util.List;

import org.pspa.gcp.modelo.Turma;
import org.pspa.gcp.modelo.repositorios.RepositorioAluno;
import org.pspa.gcp.modelo.repositorios.RepositorioFuncionario;
import org.pspa.gcp.modelo.repositorios.RepositorioTurma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoQuadroEstrutural {
	
	@Autowired
	RepositorioAluno repositorioA;
	
	@Autowired
	RepositorioTurma repositorioT;
	
	@Autowired
	RepositorioFuncionario repositorioF;

	@Autowired
	public ServicoQuadroEstrutural(RepositorioAluno repositorioA, RepositorioTurma repositorioT,
			RepositorioFuncionario repositorioF) {
		super();
		this.repositorioA = repositorioA;
		this.repositorioF = repositorioF;
		this.repositorioT = repositorioT;
	}

	public void salvarTurma(Turma t) {
		repositorioT.save(t);
	}

	public List<Turma> obterTurmas() {
		return repositorioT.findAll();
	}
	
	
}
