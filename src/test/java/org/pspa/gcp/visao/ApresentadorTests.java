package org.pspa.gcp.visao;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pspa.gcp.Principal;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public class ApresentadorTests extends ApplicationTest{

	
	@Before
	public void setUpClass() throws Exception{
		ApplicationTest.launch(Principal.class);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.show();
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Node> T find(final String query){
		return (T) lookup(query).queryAll().iterator().next();
	}
	
	@After
	public void tearDown() throws TimeoutException {
		FxToolkit.hideStage();
		release(new KeyCode[]{});
		release(new MouseButton[]{});
	}

	@Test
	public void test() {
		abrirVisaoAlunos();
		//fail("Not yet implemented");
	}
	
	public void abrirVisaoAlunos(){
		FxRobot fr = new FxRobot();
		
		fr.clickOn((Node) find("Listagem"), MouseButton.PRIMARY);
	}
}
