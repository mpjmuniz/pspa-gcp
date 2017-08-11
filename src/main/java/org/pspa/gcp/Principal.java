package org.pspa.gcp;

import java.io.IOException;

import org.pspa.gcp.visao.Apresentador;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Principal
 * 
 * Classe de entrada da aplicação
 * 
 * @author marcelo
 * 
 * @version 0.1: pronto para entrega
 * */
@SpringBootApplication
public class Principal extends Application{
	
	private ConfigurableApplicationContext contextoSpring;
	
	private Apresentador apresentador;
	
	/**
	 * Função inicial para a biblioteca JavaFX. 
	 * @param palcoPrimario: palco que é usado para 
	 * 	se alocar a estrutura de nós
	 * */
	@Override
	public void start(Stage palcoPrimario){
		apresentador = contextoSpring.getBean(Apresentador.class);
		apresentador.apresentar(palcoPrimario);
	}
	
	@Override
	public void init() throws IOException{
		contextoSpring = springJavaFXIntegrationStart();
	}
	
	private ConfigurableApplicationContext springJavaFXIntegrationStart(){
		SpringApplicationBuilder constr = new SpringApplicationBuilder(Principal.class);
		String[] args = getParameters().getRaw().stream().toArray(String[]::new);
		constr.headless(false);
		return constr.run(args);
	}
	
	/** Função de entrada da aplicação, passada para a biblioteca JavaFX*/
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void stop(){
		contextoSpring.close();
	}
}
