package org.pspa.gcp.visao.selecao;

import org.pspa.gcp.visao.Apresentador;
import org.pspa.gcp.visao.Lista.FabricaListas;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/** 
 * 	Janela de Seleção de valores dentre uma lista
 * 
 * 	Provê um nó JavaFX de seleção de elementos de qualquer tipo, com mecanismo de retorno deste nó 	
 * 
 * 	@author marcelo
 *  @version 0.1
 * */

@Component
public abstract class Selecao<T> extends VBox {
	
	/** ajuda à ligar janela da seleção à janela principal*/
	Apresentador apresentador;
	
	/** lista que mostra os elementos do tipo especificado*/
	protected ListView<T> lvCadastros;
	
	/** botão de confirmação da seleção*/
	protected Button bOk;
	
	/** palco onde residirá o nó*/
	protected Stage sSobreposto;
	
	ApplicationContext contextoSpring;
	
	JpaRepository<T, ?> repositorio;
	
	/** 
	 * Construtor principal
	 * 
	 * @param lista nó JavaFX (ListView) que ajuda a recuperar os elementos selecionados
	 * 
	 * @param campo nó JavaFX (TextField) que ajuda a recuperar o elemento selecionado
	 * 
	 * dependendo do valor de modo_seleção, um dos parâmetros é utilizado. 
	 * O outro pode ser nulo, sem problemas. 
	 * */
	protected Selecao(Class<?> cls, SelectionMode mod, ApplicationContext contexto) {
		super();

		this.contextoSpring = contexto;
		
		apresentador = Apresentador.obterInstancia();
		
		FabricaListas<T> fl = new FabricaListas<>(mod);
		
		lvCadastros = fl.construir();
		
		definirRepositorio();
		popularVisao();
		
		bOk = new Button("Ok");
		
		sSobreposto = new Stage();
		sSobreposto.initOwner(apresentador.obterPalco());
		sSobreposto.initModality(Modality.APPLICATION_MODAL);

		this.getChildren().addAll(lvCadastros, bOk);
		
		Scene cenaSobreposta = new Scene(this);
		sSobreposto.setScene(cenaSobreposta);
		sSobreposto.show();
	}	
	
	protected void popularVisao() {
		this.lvCadastros.getItems().addAll(repositorio.findAll());
	}
	
	protected abstract void definirRepositorio();
}