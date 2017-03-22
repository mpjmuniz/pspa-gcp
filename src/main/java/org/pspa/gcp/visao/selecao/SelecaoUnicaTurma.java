package org.pspa.gcp.visao.selecao;

import org.pspa.gcp.modelo.Turma;
import org.pspa.gcp.modelo.repositorios.RepositorioTurma;
import org.springframework.context.ApplicationContext;

import javafx.scene.control.TextField;

public class SelecaoUnicaTurma extends SelecaoUnica<Turma> {

	private RepositorioTurma repositorio;
	
	public SelecaoUnicaTurma(ApplicationContext contextoSpring, TextField campoAuxiliar) {
		super(contextoSpring, Turma.class, campoAuxiliar);
	}

	@Override
	protected void popularVisao() {
		repositorio.findAll();		
	}
	
	@Override
	protected void definirRepositorio(){
		repositorio = contextoSpring.getBean(RepositorioTurma.class);
	}
	
}
