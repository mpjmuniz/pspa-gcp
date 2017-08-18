package org.pspa.gcp.visao;

import org.pspa.gcp.modelo.Aluno;
import org.pspa.gcp.modelo.repositorios.RepositorioAluno;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;


public class VisaoAlunos extends VisaoCadastros<Aluno>{
	
	protected VisaoAlunos(ApplicationContext contextoSpring) {
		super(Aluno.class);
		
		this.repositorio = contextoSpring.getBean(RepositorioAluno.class);
		
		this.popularVisaoInicialmente();		
		
	}

	@Override
	@Transactional
	public void deletar(Aluno objeto) {
		if(objeto == null) return;
		
		repositorio.delete(objeto.getMid());
	}

	@Override
	protected Aluno atualizarElemento(Aluno elemento) {
		if(elemento == null) return null;
		return repositorio.findOne(elemento.getMid());
	}
}
