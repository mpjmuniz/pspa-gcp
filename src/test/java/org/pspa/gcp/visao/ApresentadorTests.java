package org.pspa.gcp.visao;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pspa.GcpApplicationTests;
import org.pspa.gcp.Principal;
import org.testfx.api.FxRobot;
import org.testfx.api.FxRobotException;
import org.testfx.api.FxToolkit;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public class ApresentadorTests extends GcpApplicationTests{
	
	@Test(expected = NoSuchElementException.class)
	public void testarAberturaNada() {
		fr.clickOn((Node) find("#inexistente"));
	}
	
	@Test
	public void testarAberturaAlunos() {
		abrirVisao("#mBase", "#mAluno");
		
		verifyThat("Ano Letivo", (Label lbl) -> {
			String text = lbl.getText();
			
			return text.contains("Ano Letivo");
			
		});
	}
	
	@Test
	public void testarAberturaFuncionarios() {
		
		abrirVisao("#mBase", "#mFuncionario");
		
		verifyThat("Data Ingresso", (Label lbl) -> {
			String text = lbl.getText();
			
			return text.contains("Data Ingresso");
			
		});
	}
	
	@Test
	public void testarAberturaTurmas() {
		abrirVisao("#mBase", "#mTurma");
		
		verifyThat("Grupamento", (Label lbl) -> {
			String text = lbl.getText();
			
			return text.contains("Grupamento");
			
		});
	}
	
	@Test
	public void testarAberturaInscritos() {
		abrirVisao("#mInscricao", "Aluno");
		
		verifyThat("Visitado", (Label lbl) -> {
			String text = lbl.getText();
			
			return text.contains("Visitado");
			
		});
	}
	
	@Test
	public void testarAberturaQuadroEstrutural() {
		abrirVisao("#mBase", "Quadro Estrutural");
		
		verifyThat("Imprimir", (Button lbl) -> {
			String text = lbl.getText();
			
			return text.contains("Imprimir");
			
		});
	}
	
	@Test
	public void testarAberturaCardapio() {
		abrirVisao("#mRefeicao", "#mCardapio");
		
		verifyThat("Numero de Criancas: ", (Label lbl) -> {
			String text = lbl.getText();
			
			return text.contains("Numero de Criancas: ");
			
		});
	}
	
	@Test
	public void testarAberturaEstoqueLimpeza() {
		abrirVisao("#mRefeicao", "#mEstoqueLimpeza");
		
		verifyThat("Adicionar", (Button lbl) -> {
			String text = lbl.getText();
			
			return text.contains("Adicionar");
			
		});
	}
	
	@Test
	public void testarAberturaDMA() {
		abrirVisao("#mControle", "#mDistribuicao");
		
		verifyThat("Tema do Mes", (Label lbl) -> {
			String text = lbl.getText();
			
			return text.contains("Tema do Mes");
			
		});
	}
	
	@Test
	public void testarAberturaFinancas() {
		abrirVisao("#mControle", "#mFinancas");
		
		verifyThat("Adicionar", (Button lbl) -> {
			String text = lbl.getText();
			
			return text.contains("Adicionar");
			
		});
	}
	
	@Test
	public void testarAberturaDeclaracoes() {
		abrirVisao("#mControle", "#mDeclaracoes");
		
		verifyThat("Adicionar", (Button lbl) -> {
			String text = lbl.getText();
			
			return text.contains("Adicionar");
			
		});
	}
	
	@Test
	public void testarAberturaEstoqueConsumo() {
		abrirVisao("#mRefeicao", "#mEstoqueConsumo");
		
		verifyThat("Adicionar", (Button lbl) -> {
			String text = lbl.getText();
			
			return text.contains("Adicionar");
			
		});
	}
	
	@Test
	public void testarAberturaEstoqueDidatico() {
		abrirVisao("#mRefeicao", "#mEstoqueDidatico");
		
		verifyThat("Adicionar", (Button lbl) -> {
			String text = lbl.getText();
			
			return text.contains("Adicionar");
			
		});
	}
	
	public FxRobot abrirVisao(String menu, String subMenu) {
		
		return fr.clickOn((Node) find(menu)).clickOn((Node) find(subMenu));
	}
}
