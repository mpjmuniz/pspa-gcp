package org.pspa.gcp.visao.almoxarifado;

import java.util.List;

import org.pspa.gcp.modelo.Produto;
import org.pspa.gcp.modelo.enums.Tipo;
import org.springframework.context.ApplicationContext;

public class VisaoEstoqueDidatico extends VisaoEstoque {

	public VisaoEstoqueDidatico(ApplicationContext contextoSpring) {
		super(contextoSpring);
	}

	@Override
	protected List<Produto> obterProdutos() {
		
		return servico.obterPorTipo(Tipo.Didatico);
	}

	@Override
	protected Tipo obterTipo() {
		return Tipo.Didatico;
	}
}