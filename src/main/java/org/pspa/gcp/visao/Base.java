package org.pspa.gcp.visao;

import java.io.IOException;
import java.net.URL;
import java.util.EmptyStackException;
import java.util.ResourceBundle;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;


/**
 * Base
 * 
 * painel que guardará todas as telas da aplicação
 * 
 * @author marcelo
 * @version 0.1
 * */
public class Base extends BorderPane {
	
	private Apresentador apresentador;
	
	/*	Componentes injetados pela biblioteca Java FX*/
	
	/** rótulo que armazena a mensagem de ajuda*/
	@FXML
	private Label lAjuda;

	/** menus expansíveis*/
	@FXML
	private Menu mBase, mInscricao, mEditar;

	@FXML
	private MenuItem mAluno, mFuncionario, mTurma, mInsAluno, mDesfazer, mCardapio, mAlmoxarifado, 
					 mQuadro, mFinancas, mDeclaracoes, mDistribuicao;

	/** barra de menus para seleção das telas*/
	@FXML
	private MenuBar barraMenus;

	/** campo usado pela biblioteca JavaFX */
	@FXML
	private URL location;

	/** campo usado pela biblioteca JavaFX*/
	@FXML
	private ResourceBundle resources;

	/** Método de construção do painel à partir da marcação FXML*/
	public Base() {
		
		apresentador = Apresentador.obterInstancia();
		
		// Carregar o arquivo de definição da estrutura da tela, .fxml
		URL fxmlUrl = this.getClass().getClassLoader().getResource("org/pspa/gcp/visao/fxml/base.fxml");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(fxmlUrl);
		
		// Definir organização lógica e controle da tela 
		loader.setRoot(this);
		loader.setController(this);
		
		// Carregar
		try {
			loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	/**
	 * função chamada pela biblioteca JavaFX para inicialização desta tela, especificada por FXML.
	 * */
	@FXML
	private void initialize() {
		
		// Define os controles
		mAluno.setOnAction(e -> apresentador.abrirVisaoAlunos());
		mFuncionario.setOnAction(e -> apresentador.abrirVisaoFuncionarios());
		mInsAluno.setOnAction(e -> apresentador.abrirVisaoInscricao());
		mTurma.setOnAction(e -> apresentador.abrirVisaoTurmas());
		mCardapio.setOnAction(e -> apresentador.abrirVisaoCardapio());
		mAlmoxarifado.setOnAction(e -> apresentador.abrirVisaoAlmoxarifado());
		mQuadro.setOnAction(e -> apresentador.abrirVisaoQuadro());
		mFinancas.setOnAction(e -> apresentador.abrirVisaoFinancas());
		mDeclaracoes.setOnAction(e -> apresentador.abrirVisaoDeclaracoes());
		mDistribuicao.setOnAction(e -> apresentador.abrirVisaoDistribuicaoMensalAtividades());
		mDesfazer.setOnAction(e -> desfazer());
		mDesfazer.setDisable(true);
	}
	
	protected void atualizarDesfazer(){
		if(mDesfazer.isDisable()) mDesfazer.setDisable(false);
		
		try {
			mDesfazer.setText("Desfazer " + apresentador.obterUltimo());
		} catch(EmptyStackException e){
			mDesfazer.setDisable(true);
		}
	}
	
	private void desfazer() {
		apresentador.desfazer();
		
		atualizarDesfazer();
	}

	/** método de obtenção do nó que mostrará a ajuda para o usuário
	 *  @return rótulo de ajuda
	 * */
	protected StringProperty disponibilizarAjuda() {
		this.lAjuda.textProperty().addListener(this::mudarCor);
		return this.lAjuda.textProperty();
	}

	private void mudarCor(ObservableValue<? extends String> observavel, 
						  String valorAnterior, 
						  String valorAtual) {
		if(!"Bem vindo!".equals(valorAtual)){
			this.lAjuda.setStyle("-fx-padding: 10; -fx-text-fill: red;"
					+ " -fx-font-weight: bold;");
		}else{
			this.lAjuda.setStyle("-fx-padding: 10; -fx-text-fill: blue;"
					+ " -fx-font-weight: bold;");
		}
	}

	/** método de obtenção do painel principal
	 *  @return painel*/
	protected BorderPane obterPainel() {
		return this;
	}
}
