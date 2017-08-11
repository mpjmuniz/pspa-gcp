package org.pspa.gcp.modelo.repositorios;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pspa.gcp.modelo.Aluno;
import org.pspa.gcp.modelo.Turma;
import org.pspa.gcp.modelo.enums.Instrucao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TesteRepositorioAluno {

	Aluno a;
	
	@Autowired
	private RepositorioAluno repositorio;
	
	@Before
	public void preparar(){		
		repositorio.deleteAll();
		a = new Aluno();
	}
	
	@After
	public void limpar(){		
		a = null;
		
		repositorio.deleteAll();
	}
	
	@Test
	public void testarObtencaoAlunosVazio() {
		List<Aluno> alunos = repositorio.findAll();
		assertThat(alunos.size(), is(0));
	}
	
	@Test
	public void testarObtencaoAlunosComDez() {
		Aluno l;
		
		for(int i = 0; i < 10; i++){
			l = new Aluno();
			repositorio.save(l);
		}
		
		List<Aluno> alunos = repositorio.findAll();
		assertThat(alunos.size(), is(10));
	}
	
	@Test
	public void testarObtencaoAluno(){
		a = repositorio.save(a);
		
		Aluno aluno = repositorio.findOne(a.getMid());
		assertThat(aluno.getMid(), is(a.getMid()));
	}
	
	@Test
	public void testarObtencaoNumeroDeAlunos(){
		assertThat(repositorio.count(), is(0L));
	}
	
	@Test
	public void testarCriacaoAlunos(){
		a = repositorio.save(a);
		
		Integer id = a.getMid();
		assertThat(id, is(notNullValue()));
		
		Aluno denovo = repositorio.findOne(id);
		assertThat(denovo.getMid(), is(id));
		assertThat(denovo.getAno_letivo(), is(a.getAno_letivo()));
	}
	
	@Test
	public void testarAtualizaçãoAlunos(){
		a = repositorio.save(a);
		Integer id = a.getMid();
		
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
	
	@Test
	public void testarAlteraçãoInts(){
		
		a = repositorio.save(a);
		
		Aluno al = repositorio.findOne(a.getMid());
		
		al.setID(50);
		
		al = repositorio.save(al);
		
		Aluno b = repositorio.findOne(al.getMid());
		
		assertThat(b.getID(), is(50));
	}
	
	@Test
	public void testarAlteraçãoEnums(){
		
		a = repositorio.save(a);
		
		Aluno al = repositorio.findOne(a.getMid());
		
		al.setPai_instrução(Instrucao.Superior_Completo);
		
		al = repositorio.save(al);
		
		Aluno b = repositorio.findOne(al.getMid());
		
		assertThat(b.getPai_instrução(), is(Instrucao.Superior_Completo));
	}
	
	@Test
	public void testarAlteraçãoBooleans(){
		
		a = repositorio.save(a);
		
		Aluno al = repositorio.findOne(a.getMid());
		
		al.setFrequentou_EI(false);
		
		al = repositorio.save(al);
		
		Aluno b = repositorio.findOne(al.getMid());
		
		assertThat(b.getFrequentou_EI(), is(false));
	}
	
	@Test
	public void testarAlteraçãoDatas(){
		
		a = repositorio.save(a);
		
		Aluno al = repositorio.findOne(a.getMid());
		
		al.setData_de_nascimento(LocalDate.now().minusDays(5));
		
		al = repositorio.save(al);
		
		Aluno b = repositorio.findOne(al.getMid());
		
		assertThat(b.getData_de_nascimento(), is(LocalDate.now().minusDays(5)));
	}
}
