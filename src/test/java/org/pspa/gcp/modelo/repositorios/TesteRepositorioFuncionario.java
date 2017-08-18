package org.pspa.gcp.modelo.repositorios;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pspa.gcp.modelo.Funcionario;
import org.pspa.gcp.modelo.repositorios.RepositorioFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TesteRepositorioFuncionario {

	@Autowired
	private RepositorioFuncionario repositorio;
	
	@Test
	public void testarObtencaoFuncionarios() {
		List<Funcionario> funcionarios = repositorio.findAll();
		assertThat(funcionarios.size(), is(0));
	}
	
	@Test
	public void testarObtencaoFuncionario(){
		Funcionario fn = new Funcionario();
		fn = repositorio.save(fn);
		
		Funcionario funcionario = repositorio.findOne(fn.getMid());
		assertThat(funcionario.getMid(), is(fn.getMid()));
		assertThat(funcionario.getCarteira_trabalho(), is(fn.getCarteira_trabalho()));
	}
	
	@Test
	public void testarObtencaoNumeroDeFuncionarios(){
		assertThat(repositorio.count(), is(0L));
	}
	
	@Test
	public void testarCriacaoFuncionarios(){
		Funcionario funcionario = new Funcionario();
		funcionario = repositorio.save(funcionario);
		
		Integer id = funcionario.getMid();
		assertThat(id, is(notNullValue()));
		
		Funcionario denovo = repositorio.findOne(id);
		assertThat(denovo.getMid(), is(id));
		assertThat(denovo.getCarteira_trabalho(), is(funcionario.getCarteira_trabalho()));
	}
	
	@Test
	public void testarAtualizacaoFuncionarios(){
		Funcionario fn = new Funcionario();
		fn = repositorio.save(fn);
		Integer id = fn.getMid();
		
		Funcionario funcionario = repositorio.findOne(id);
		String novoAno = "???";
		funcionario.setCarteira_trabalho(novoAno);
		funcionario = repositorio.save(funcionario);
		
		Funcionario denovo = repositorio.findOne(funcionario.getMid());
		assertThat(denovo.getCarteira_trabalho(), is(novoAno));
		
	}
	
	@Test
	public void testarDelecaoFuncionarios(){
		repositorio.deleteAll();
		assertThat(repositorio.count(), is(0L));
	}
	
}
