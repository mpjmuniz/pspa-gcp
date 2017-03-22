package org.pspa.gcp.visao.selecao;

import org.pspa.gcp.modelo.Aluno;
import org.pspa.gcp.modelo.repositorios.RepositorioAluno;
import org.springframework.context.ApplicationContext;

import javafx.scene.control.ListView;

public class SelecaoMultiplaAlunos extends SelecaoMultipla<Aluno>{

	private RepositorioAluno repositorio;
	
	public SelecaoMultiplaAlunos(ApplicationContext contexto, ListView<Aluno> lista) {
		super(contexto, Aluno.class, lista);
		
	}

	@Override
	protected void popularVisao() {
		this.lvCadastros.getItems().addAll(repositorio.findAll());
	}

	@Override
	protected void definirRepositorio() {
		this.repositorio = contextoSpring.getBean(RepositorioAluno.class);
	}

}
