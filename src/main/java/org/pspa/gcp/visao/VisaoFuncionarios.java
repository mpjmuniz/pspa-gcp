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
		this.lElementos.getItems().clear();
		this.lElementos.getItems().addAll(repositorio.findAll());
	}

	@Override
	public Funcionario persistir(Funcionario objeto) {
		if(objeto == null) return null;
		
		Funcionario obj = repositorio.findOne(objeto.getMid());
		if(obj != null) return obj;
		
		return repositorio.save(objeto);
	}

	@Override
	public void deletar(Funcionario objeto) {
		if(objeto == null) return;
		
		repositorio.delete(objeto.getMid());
	}

	@Override
	protected Funcionario atualizarElemento(Funcionario elemento) {
		if(elemento == null) return null;
		return repositorio.findOne(elemento.getMid());
	}
}
