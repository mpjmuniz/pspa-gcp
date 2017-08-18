package org.pspa;

import java.util.ResourceBundle;
import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.pspa.gcp.Principal;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public class GcpApplicationTests extends ApplicationTest{

	protected FxRobot fr;
	
	@BeforeClass
	public static void setUpClass() throws Exception{
		System.setProperty("testfx.robot", "glass");
		System.setProperty("testfx.headless", "true");
		System.setProperty("prism.order", "sw");
		System.setProperty("prism.text", "t2k");
		System.setProperty("java.awt.headless", "true");
	
		ApplicationTest.launch(Principal.class);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.show();
		fr = new FxRobot();
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Node> T find(final String query){
		return (T) lookup(query).queryAll().iterator().next();
	}
	
	@After
	public void tearDown() throws TimeoutException {
		//FxToolkit.hideStage();
		release(new KeyCode[]{});
		release(new MouseButton[]{});
	}

}
