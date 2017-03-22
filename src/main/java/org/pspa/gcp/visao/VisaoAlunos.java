package org.pspa.gcp.visao;

import org.pspa.gcp.modelo.Aluno;
import org.pspa.gcp.modelo.repositorios.RepositorioAluno;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;


public class VisaoAlunos extends VisaoCadastros<Aluno>{

	private RepositorioAluno repositorio;
	
	protected VisaoAlunos(ApplicationContext contextoSpring) {
		super(Aluno.class);
		
		this.repositorio = contextoSpring.getBean(RepositorioAluno.class);
		
		this.popularVisaoInicialmente();		
		
	}
	
	@Override
	@Transactional
	public void popularVisaoInicialmente(){
		this.lElementos.getItems().addAll(repositorio.findAll());
	}

	@Override
	@Transactional
	public Aluno persistir(Aluno objeto){
		return repositorio.save(objeto);
	}

	@Override
	@Transactional
	public void deletar(Aluno objeto) {
		repositorio.delete(objeto);
	}
}
