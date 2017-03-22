package org.pspa.gcp.visao.cardapio;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

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
	RepositorioCardapio repositorio;
	
	@Autowired
	RepositorioProduto repoP;
	
	@Autowired
	RepositorioAluno repoA;
	
	@Autowired
	RepositorioFuncionario repoF;
	
	@Autowired
	public ServicoCardapio(RepositorioCardapio repoC, RepositorioAluno repoA, RepositorioFuncionario repoF, RepositorioProduto repoP){
		this.repoP = repoP;
		this.repositorio = repoC;
		this.repoA = repoA;
		this.repoF = repoF;
		
		if(LocalDate.now().getMonth() == Month.JANUARY){
			List<Cardapio> cs = repositorio.findAll();
			
			for(Cardapio c : cs){
				if(c.getAno() != LocalDate.now().getYear()){
					repositorio.delete(c);
				}
			}
		}
	}
	
	public Cardapio obterCardapio(){
		Cardapio c = repositorio.findOne(LocalDate.now().getMonth());
		
		if(c == null){
			Cardapio cAnt = repositorio.findOne(LocalDate.now().getMonth().minus(1));
			
			c = new Cardapio(LocalDate.now().getMonth(), 
						     LocalDate.now().getYear(),
							 "", 
							 (cAnt != null) ? cAnt.getProdutos() : new ArrayList<>(),
						     (cAnt != null) ? cAnt.getNumeroAlunos() : 0,
						     (cAnt != null) ? cAnt.getNumeroFuncionarios() : 0);
			
			repositorio.save(c);
		}
		
		return c;
	}
	
	public Cardapio obterCardapio(Month month){
		
		return repositorio.findOne(month);
	}
	
	public int obterQuantidadeAlunos(){		
		
		return  (int) repoA.count();
	}
	
	public int obterQuantidadeFuncionarios(){
		
		return  (int) repoF.count();
	}
	
	public void salvarCardapio(Cardapio c){
		repositorio.save(c);
	}
	
	public void adicionarProduto(Cardapio c, Produto p){
		repoP.save(p);
		c.adicionarProduto(p);
		repositorio.save(c);
	}
	
	public void removerProduto(Cardapio c, Produto p){
		c.removerProduto(p);
		repositorio.save(c);
		repoP.delete(p);
	}
	
	public void salvarProduto(Produto p){
		repoP.save(p);
	}
	
	public List<Cardapio> obterCardapios(){
		List<Cardapio> ls = repositorio.findAll(); 
		
		if(ls.size() == 0){
			LocalDate agora = LocalDate.now();
			Cardapio c = new Cardapio(agora.getMonth(), agora.getYear(), "", new ArrayList<>(), 0 , 0); 
			
			ls.add(c);
			repositorio.save(c);
		}
		
		return ls;
	}
}
