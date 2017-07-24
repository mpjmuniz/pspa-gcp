package org.pspa.gcp.visao.almoxarifado;

import java.util.List;

import org.pspa.gcp.modelo.Produto;
import org.pspa.gcp.modelo.enums.Tipo;
import org.pspa.gcp.modelo.repositorios.RepositorioProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoEstoque {
	
	@Autowired
	RepositorioProduto repoP;
	
	@Autowired
	public ServicoEstoque(RepositorioProduto repoP){
		this.repoP = repoP;
	}

	public void removerProduto(Produto p) {
		repoP.delete(p);
	}

	public void salvarProduto(Produto p) {
		repoP.save(p);
	}

	public List<Produto> obterTodos() {
		return repoP.findAll();
	}
	
	public List<Produto> obterPorTipo(Tipo tipo){
		return repoP.getProdutosByTipo(tipo);
	}
}
