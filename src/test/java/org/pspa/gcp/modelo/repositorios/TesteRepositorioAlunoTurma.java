package org.pspa.gcp.modelo.repositorios;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pspa.gcp.modelo.Aluno;
import org.pspa.gcp.modelo.Turma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TesteRepositorioAlunoTurma {

	Aluno a;
	
	Turma t;
	
	@Autowired
	private RepositorioAluno repositorioA;
	
	@Autowired
	private RepositorioTurma repositorioT;
	
	@Before
	public void resetar(){
		repositorioA.deleteAll();
		repositorioT.deleteAll();
		
		a = new Aluno();
		
		t = new Turma();
	}
	
	@After
	public void limpar(){
		repositorioA.deleteAll();
		repositorioT.deleteAll();
		
		a = null;
		
		t = null;
	}
	
	@Test
	public void testarAssociacaoLadoAluno() {
		
		a.setTurma(t);
		
		a = repositorioA.save(a);
				
		Aluno b = repositorioA.findOne(a.getMid());
		Turma t = repositorioT.findOne(b.getTurma().getMid());
		
		Assert.assertThat(repositorioA.findAlunosByTurma(t).get(0).getMid(), is(b.getMid()));
		Assert.assertThat(b.getTurma().getMid(), is(t.getMid()));
	}
	
	@Test
	public void testarAssociacaoLadoTurma() {
		
		ArrayList<Aluno> ls = new ArrayList<Aluno>();
		ls.add(a);
		t.setAlunos(ls);
		
		t = repositorioT.save(t);
		
		Aluno b = repositorioA.findOne(repositorioA.findAlunosByTurma(t).get(0).getMid()); 
		Turma u = repositorioT.findOne(t.getMid());
		
		Assert.assertThat(b.getTurma().getMid(), is(u.getMid()));
	}
	
	@Test
	public void testarAssociacaoLadoTurmaPeloAluno() {
		
		ArrayList<Aluno> ls = new ArrayList<Aluno>();
		ls.add(a);
		t.setAlunos(ls);
		
		a = repositorioA.save(a);		
		
		Aluno b = repositorioA.findOne(a.getMid());
		Turma t = repositorioT.findOne(b.getTurma().getMid());
		
		Assert.assertThat(b.getTurma().getMid(), is(t.getMid()));
	}
	
	@Test
	public void testarComportamentoColecao() {
		
		List<Aluno> ls = new ArrayList<Aluno>();
		ls.add(a);
		t.setAlunos(ls);
		
		a = repositorioA.save(a);
		
		Assert.assertThat(repositorioA.count(), is(1L));
	}
	
	/*
	@Test
	public void testarLazyLoading() {
		
		a = repositorioA.save(a);
		
		List<Aluno> ls = new ArrayList<Aluno>();
		ls.add(a);
		t.setAlunos(ls);
		
		t = repositorioT.save(t);
		a = repositorioA.save(a);
		
		t = repositorioT.findOne(t.getMid());
		a = repositorioA.findOne(a.getMid());
		
		Hibernate.initialize(t.getAlunos());
		
		List<Aluno> s = t.getAlunos();
		
		Assert.assertThat(s, contains(a));
	}*/
}

