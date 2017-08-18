package org.pspa.gcp.visao;

import org.pspa.gcp.modelo.Funcionario;
import org.pspa.gcp.modelo.repositorios.RepositorioFuncionario;
import org.springframework.context.ApplicationContext;

public class VisaoFuncionarios extends VisaoCadastros<Funcionario>{
	
	protected VisaoFuncionarios(ApplicationContext contexto) {
		super(Funcionario.class);
		
		repositorio = contexto.getBean(RepositorioFuncionario.class);
		
		popularVisaoInicialmente();
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
