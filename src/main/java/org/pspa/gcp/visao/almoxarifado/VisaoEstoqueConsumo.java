package org.pspa.gcp.visao.almoxarifado;

import java.util.List;

import org.pspa.gcp.modelo.Produto;
import org.pspa.gcp.modelo.enums.Tipo;
import org.springframework.context.ApplicationContext;

public class VisaoEstoqueConsumo extends VisaoEstoque {
	
	public VisaoEstoqueConsumo(ApplicationContext contexto) {
		super(contexto);
	}

	protected List<Produto> obterProdutos() {
		
		return servico.obterPorTipo(Tipo.Consumo);
	}

	@Override
	protected Tipo obterTipo() {
		return Tipo.Consumo;
	}
	
	
}
