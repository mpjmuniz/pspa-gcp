package org.pspa.gcp.visao.cardapio;

import org.pspa.gcp.modelo.Cardapio;
import org.pspa.gcp.modelo.Produto;
import org.pspa.gcp.modelo.repositorios.RepositorioAluno;
import org.pspa.gcp.modelo.repositorios.RepositorioCardapio;
import org.pspa.gcp.modelo.repositorios.RepositorioFuncionario;
import org.pspa.gcp.modelo.repositorios.RepositorioProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoCardapio {
	
	@Autowired
	RepositorioCardapio repoC;
	
	@Autowired
	RepositorioProduto repoP;
	
	@Autowired
	RepositorioAluno repoA;
	
	@Autowired
	RepositorioFuncionario repoF;
	
	@Autowired
	public ServicoCardapio(RepositorioCardapio repoC, RepositorioAluno repoA, RepositorioFuncionario repoF, RepositorioProduto repoP){
		this.repoP = repoP;
		this.repoC = repoC;
		this.repoA = repoA;
		this.repoF = repoF;
		
	}
	
	public int obterQuantidadeAlunos(){		
		
		return  (int) repoA.count();
	}
	
	public int obterQuantidadeFuncionarios(){
		
		return  (int) repoF.count();
	}
	
	public void salvarCardapio(Cardapio c){
		Cardapio cant = repoC.findOne(c.getId());
		if(cant != null){
			repoC.delete(cant.getId());
		}
		
		repoC.save(c);
	}
	
	public void adicionarProduto(Cardapio c, Produto p){
		repoP.save(p);
		c.adicionarProduto(p);
		repoC.save(c);
	}
	
	public void removerProduto(Cardapio c, Produto p){
		c.removerProduto(p);
		repoC.save(c);
		//repoP.delete(p);
	}
	
	public void salvarProduto(Produto p){
		repoP.save(p);
	}
	
	public Cardapio obterUltimoCardapio(){
		
		Cardapio cardapio = repoC.findOne(0);
		
		if(cardapio == null){
			cardapio = new Cardapio();
			repoC.save(cardapio);
		}
		
		return cardapio;
	}
}
