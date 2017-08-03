package org.pspa.gcp.modelo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.pspa.gcp.modelo.enums.Funcao;

import junit.framework.Assert;

public class TurmaTests {

	Turma turma;
	
	@Before
	public void setUp() throws Exception {
		turma = new Turma();
	}

	@Test
	public void testProfessor() {
		Funcionario f = new Funcionario();
		
		f.setFuncao(Funcao.Professor);
		
		turma.setFuncionario(f);
		
		Assert.assertEquals(turma.getProfessor(), f);
	}

	@Test
	public void testAuxiliar() {
		Funcionario f = new Funcionario();
		
		f.setFuncao(Funcao.Auxiliar);
		
		turma.setFuncionario(f);
		
		Assert.assertEquals(turma.getAuxiliares().get(0), f);
	}
	
	@Test
	public void testInsercaoAuxiliarUnico() {
		Funcionario f = new Funcionario(),
					g = new Funcionario();
		
		g.setFuncao(Funcao.Auxiliar);
		f.setFuncao(Funcao.Auxiliar);
		
		turma.adicionarAuxiliares(g);
		
		turma.setFuncionario(f);
		
		Assert.assertEquals(turma.getAuxiliares().size(), 1);
	}
	
	@Test
	public void testInsercaoAuxiliarCorreto() {
		Funcionario f = new Funcionario(),
					g = new Funcionario();
		
		g.setFuncao(Funcao.Auxiliar);
		f.setFuncao(Funcao.Auxiliar);
		
		turma.adicionarAuxiliares(g);
		
		turma.setFuncionario(f);
		
		Assert.assertEquals(turma.getAuxiliares().get(0), f);
	}
	
	@Test
	public void testAssociacaoFuncionariosGenerico() {
		List<Funcionario> fs = new ArrayList<>();
		
		Funcionario f = new Funcionario(),
					g = new Funcionario();
		
		fs.add(f);
		fs.add(g);
		
		turma.setFuncionarios(fs);
		
		Assert.assertEquals(turma.getAuxiliares(), fs);
	}
	
	@Test
	public void testAssociacaoAlunosGenerico() {
		List<Aluno> fs = new ArrayList<>();
		
		Aluno f = new Aluno(),
					g = new Aluno();
		
		fs.add(f);
		fs.add(g);
		
		turma.setAlunos(fs);
		
		Assert.assertEquals(turma.getAlunos(), fs);
	}
	
	
}
