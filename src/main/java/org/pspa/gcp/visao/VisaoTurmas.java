package org.pspa.gcp.visao;

import org.pspa.gcp.modelo.Turma;
import org.pspa.gcp.modelo.repositorios.RepositorioTurma;
import org.springframework.context.ApplicationContext;

public class VisaoTurmas extends VisaoCadastros<Turma>{

	RepositorioTurma repositorio;
	
	protected VisaoTurmas(ApplicationContext contexto) {
		super(Turma.class);
		
		repositorio = contexto.getBean(RepositorioTurma.class);
	}
	
	@Override
	public void popularVisaoInicialmente(){
		this.lElementos.getItems().addAll(repositorio.findAll());
	}

	@Override
	public Turma persistir(Turma objeto) {
		return repositorio.save(objeto);
	}

	@Override
	public void deletar(Turma objeto) {
		repositorio.delete(objeto);
	}
}
