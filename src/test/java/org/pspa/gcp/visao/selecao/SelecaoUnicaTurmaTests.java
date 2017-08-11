package org.pspa.gcp.visao.selecao;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;


public class SelecaoUnicaTurmaTests extends ApplicationTest{

	Parent mainNode;
	
	@Before
	public void setUpClass() throws Exception{
		return;
	}
	
	@After
	public void afterEachTest() throws TimeoutException{
		FxToolkit.hideStage();
		release(new KeyCode[]{});
		release(new MouseButton[]{});
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		
		stage.show();
	}

	
}
