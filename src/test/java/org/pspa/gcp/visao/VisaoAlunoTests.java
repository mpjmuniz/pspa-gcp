package org.pspa.gcp.visao;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;
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

public class VisaoAlunoTests extends GcpApplicationTests{
	
	@Before
	public void limparlElementos() {
		abrirVisao("#mBase", "#mAluno");
		((ListView<?>) find("#lElementos")).getItems().clear();
	}
	
	@Test
	public void testarCriacaoNormal() {
		
		cadastrarAlunoComum();
		
		verifyThat("#lElementos", (ListView<?> lbl) -> {
			List<?> lst = lbl.getItems();
			
			return lst.size() == 1;
		});
	}
	
	@Test
	public void testarExclusaoNormal() {
		
		for(int i = 0; i < 3; i++) {
			cadastrarAlunoComum();
		}
		
		removerUmAluno();
		
		verifyThat("#lElementos", (ListView<?> lbl) -> {
			List<?> lst = lbl.getItems();
			
			return lst.size() == 2;
		});
	}
	
	@Test
	public void testarExclusaoDupla() {
		
		for(int i = 0; i < 3; i++) {
			cadastrarAlunoComum();
		}
		
		removerUmAluno();
		removerUmAluno();
		
		verifyThat("#lElementos", (ListView<?> lbl) -> {
			List<?> lst = lbl.getItems();
			
			return lst.size() == 1;
		});
	}

	@Test
	public void testarCriacaoNormalRepetida() {
		
		for(int i = 0; i < 10; i++) {
			cadastrarAlunoComum();
		}
		
		verifyThat("#lElementos", (ListView<?> lbl) -> {
			List<?> lst = lbl.getItems();
			
			return lst.size() == 10;
		});
	}
	
	@Test
	public void testarCriacaoIntercalada() {
		
		for(int i = 0; i < 10; i++) {
			if (i % 2 == 0) cadastrarAlunoComum(); else cadastrarAlunoErrado();
		}
		
		verifyThat("#lElementos", (ListView<?> lbl) -> {
			List<?> lst = lbl.getItems();
			
			return lst.size() == 5;
		});
	}
	
	@Test
	public void testarCriacaoErrada() {

		cadastrarAlunoErrado();
		
		verifyThat("#lElementos", (ListView<?> lbl) -> {
			List<?> lst = lbl.getItems();
			
			return lst.size() == 0;
		});
	}
	
	private void removerUmAluno() {
		clickOn("asdf");
		
		release(new KeyCode[]{});
		release(new MouseButton[]{});
		
		clickOn("Excluir");
	}
	
	public void cadastrarAlunoComum() {
		clickOn("Criar Novo");
		
		release(new KeyCode[]{});
		release(new MouseButton[]{});
		
		preencherCampos("asdf", "20/08/2017", "1");
		
		clickOn("Cadastrar");
	}
	
	public void cadastrarAlunoErrado() {
		clickOn("Criar Novo");
		
		release(new KeyCode[]{});
		release(new MouseButton[]{});
		
		preencherCampos("", "20/08/2017", "1");
		
		clickOn("Cadastrar");
		clickOn("Cancelar");
	}
	
	public void preencherCampos(String entradaStrings, String entradaDatas, String entradaNumeros) {
		ListagemCampos<?> lc = (ListagemCampos<?>) lookup("#listagemCampos").queryAll().iterator().next();
		ScrollPane spCampos = (ScrollPane) lookup("#spCampos").queryAll().iterator().next();
		
		lc.getChildren().forEach((e) -> {
			centerNodeInScrollPane(spCampos, e);
			
			if(e instanceof TextField) {
				clickOn(e).write(entradaStrings);
			} else if(e instanceof DatePicker) {
				clickOn(e).write(entradaDatas);
			} else if(e instanceof ChoiceBox) {
				((ChoiceBox<?>) e).getSelectionModel().selectLast();
			} else if(e instanceof Spinner) {
				clickOn(e).write(entradaNumeros);
			}
		});
	}
	
	public void centerNodeInScrollPane(ScrollPane scrollPane, Node node) {
	    double h = scrollPane.getContent().getBoundsInLocal().getHeight();
	    double y = (node.getBoundsInParent().getMaxY() + 
	                node.getBoundsInParent().getMinY()) / 2.0;
	    double v = scrollPane.getViewportBounds().getHeight();
	    scrollPane.setVvalue(scrollPane.getVmax() * ((y - 0.5 * v) / (h - v)));
	}
	
	public FxRobot abrirVisao(String menu, String subMenu) {
		
		return fr.clickOn((Node) find(menu)).clickOn((Node) find(subMenu));
	}
}
