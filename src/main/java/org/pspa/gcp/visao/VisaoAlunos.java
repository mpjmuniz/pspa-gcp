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
		this.lElementos.getItems().clear();
		this.lElementos.getItems().addAll(repositorio.findAll());
	}

	@Override
	@Transactional
	public Aluno persistir(Aluno objeto){
		if(objeto == null) return null;
		
		Aluno aluno = repositorio.findOne(objeto.getMid());
		if(aluno != null) return aluno;
				
		return repositorio.save(objeto);
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
