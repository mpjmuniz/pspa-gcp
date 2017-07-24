package org.pspa.gcp.visao.almoxarifado;

import java.util.List;

import org.pspa.gcp.modelo.Produto;
import org.pspa.gcp.modelo.enums.Tipo;
import org.springframework.context.ApplicationContext;

public class VisaoEstoqueLimpeza extends VisaoEstoque{

	public VisaoEstoqueLimpeza(ApplicationContext contexto) {
		super(contexto);
	}

	protected List<Produto> obterProdutos() {
		
		return servico.obterPorTipo(Tipo.Limpeza);	
	}

	@Override
	protected Tipo obterTipo() {
		return Tipo.Limpeza;
	}
	
	

}
