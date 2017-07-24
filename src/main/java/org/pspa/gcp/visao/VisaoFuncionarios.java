package org.pspa.gcp.visao;

import org.pspa.gcp.modelo.Funcionario;
import org.pspa.gcp.modelo.repositorios.RepositorioFuncionario;
import org.springframework.context.ApplicationContext;

public class VisaoFuncionarios extends VisaoCadastros<Funcionario>{

	RepositorioFuncionario repositorio;
	
	protected VisaoFuncionarios(ApplicationContext contexto) {
		super(Funcionario.class);
		
		repositorio = contexto.getBean(RepositorioFuncionario.class);
		
		popularVisaoInicialmente();
	}
	
	@Override
	public void popularVisaoInicialmente(){
		this.lElementos.getItems().addAll(repositorio.findAll());
	}

	@Override
	public Funcionario persistir(Funcionario objeto) {
		return repositorio.save(objeto);
	}

	@Override
	public void deletar(Funcionario objeto) {
		repositorio.delete(objeto);
	}
}
