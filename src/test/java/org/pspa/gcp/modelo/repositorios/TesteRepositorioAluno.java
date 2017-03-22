package org.pspa.gcp.modelo.repositorios;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pspa.gcp.modelo.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TesteRepositorioAluno {

	@Autowired
	private RepositorioAluno repositorio;
	
	@Before
	public void resetar(){
		repositorio.deleteAll();
	}
	
	@Test
	public void testarObtencaoAlunos() {
		List<Aluno> alunos = repositorio.findAll();
		assertThat(alunos.size(), is(0));
	}
	
	@Test
	public void testarObtencaoAluno(){
		Aluno al = new Aluno();
		al = repositorio.save(al);
		
		Aluno aluno = repositorio.findOne(al.getMid());
		assertThat(aluno.getMid(), is(al.getMid()));
	}
	
	@Test
	public void testarObtencaoNumeroDeAlunos(){
		assertThat(repositorio.count(), is(0L));
	}
	
	@Test
	public void testarCriacaoAlunos(){
		Aluno aluno = new Aluno();
		aluno = repositorio.save(aluno);
		
		Integer id = aluno.getMid();
		assertThat(id, is(notNullValue()));
		
		Aluno denovo = repositorio.findOne(id);
		assertThat(denovo.getMid(), is(id));
		assertThat(denovo.getAno_letivo(), is(aluno.getAno_letivo()));
	}
	
	@Test
	public void testarAtualizaçãoAlunos(){
		Aluno al = new Aluno();
		al = repositorio.save(al);
		Integer id = al.getMid();
		
		Aluno aluno = repositorio.findOne(id);
		String novoAno = "2005";
		aluno.setAno_letivo(novoAno);
		aluno = repositorio.save(aluno);
		
		Aluno denovo = repositorio.findOne(aluno.getMid());
		assertThat(denovo.getAno_letivo(), is(novoAno));
		
	}
	
	@Test
	public void testarDeleçãoAlunos(){
		repositorio.deleteAll();
		assertThat(repositorio.count(), is(0L));
	}
	
}
