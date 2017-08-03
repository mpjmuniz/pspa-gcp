package org.pspa.gcp.visao;

import java.lang.reflect.InvocationTargetException;

import org.pspa.gcp.visao.Lista.ListaElementos;
import org.pspa.gcp.visao.Lista.ListaSelecaoUnica;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;

/**
 * Visão que provê a manipulação de cadastros, de uma dada classe do modelo,
 * listando seus campos, permitindo a sua manipulação e garantindo sua
 * consistência no banco de dados
 * 
 * @param T tipo que será gerenciado      
 * 
 * @version 0.1
 * @author marcelo
 */

@Component
public abstract class VisaoCadastros<T> extends SplitPane {
	
	private enum Estado{
		Novo,Salvo,Cancelado,Editavel
	}
	
	protected Estado estado;
	
	protected Apresentador apresentador;
	
	protected SimpleStringProperty ajuda;
	
	/** Listagem dos campos do tipo T */
	protected ListagemCampos<T> lCampos;

	/** botões de gestão dos registros do tipo T */
	protected Button bNovo, bCadastrar, bAlterar, bExcluir, bEditar, bCancelar, bInscrever, bImprimir;

	/** visão da árvore que mostrará os registros existentes no banco */
	protected ListaElementos<T> lElementos;
	
	protected VBox vbListagem;
	
	/**
	 * Construtor único
	 * 
	 * @param classe
	 *            classe do tipo que será listado
	 */
	protected VisaoCadastros(Class<T> cls) {
		
		apresentador = Apresentador.obterInstancia();
		
		//this.contexto = contexto;
		
		// Exibição dos campos
		this.lCampos = new ListagemCampos<T>(cls);

		ScrollPane sp = new ScrollPane(lCampos);
		sp.setPadding(new Insets(20));

		// Definição da lista de cadastros

		this.lElementos = new ListaSelecaoUnica<T>();
		lElementos.getSelectionModel().selectedItemProperty().addListener(this::mudancaNaSelecao);
		lElementos.setOnMouseClicked(e -> atualizar());
		// Botões de controle
		bNovo = new Button("Criar Novo");
		bNovo.setOnAction(e -> preparar());
		bNovo.setPrefWidth(1000);

		bCadastrar = new Button("Cadastrar");
		bCadastrar.setOnAction(e -> cadastrar());
		bCadastrar.setPrefWidth(1000);

		bAlterar = new Button("Alterar");
		bAlterar.setOnAction(e -> alterar());
		bAlterar.setPrefWidth(1000);

		bExcluir = new Button("Excluir");
		bExcluir.setOnAction(e -> remover());
		bExcluir.setPrefWidth(1000);

		bEditar = new Button("Editar");
		bEditar.setOnAction(e -> editar());
		bEditar.setPrefWidth(1000);

		bCancelar = new Button("Cancelar");
		bCancelar.setOnAction(e -> cancelar());
		bCancelar.setPrefWidth(1000);

		bImprimir = new Button("Imprimir");
		bImprimir.setOnAction(e -> imprimir());
		bImprimir.setPrefWidth(1000);
		
		// Menu lateral esquerdo
		vbListagem = new VBox(10);
		vbListagem.setPadding(new Insets(10));
		vbListagem.getChildren().addAll(lElementos, bNovo, bCadastrar, bAlterar, bExcluir, bEditar, bCancelar, bImprimir);
		
		ajuda = new SimpleStringProperty("Bem vindo!");
		apresentador.cadastrarAjuda(ajuda);

		// Definições de vizualização
		this.getItems().addAll(vbListagem, sp);
		this.setDividerPosition(0, 0.15);
		
		lElementos.getSelectionModel().select(0);
		salvo();
		
		
	}
	
	
		/**
	 * Obtém referência para listagem dos campos deste tipo T
	 * 
	 * @return nó com a listagem dos campos de T
	 */
	protected ListagemCampos<T> obterListagem() {
		return this.lCampos;
	}

	/**
	 * método de obtenção da árvore de listagem dos registros do modelo, e
	 * possivelmente dos relacionados com ele.
	 * 
	 * @return árvore de cadastros deste tipo T
	 */
	protected ListView<T> obterLista() {
		return this.lElementos;
	}

	// Controle


	private void editar() {
		
		if (lElementos.getSelectionModel().isEmpty()) {
			ajuda.set("Selecione o que será editado.");
			return;
		}
		
		lElementos.setDisable(true);
		
		editavel();
	}

	private void cancelar() {
		if(estado.equals(Estado.Novo)){
			lElementos.getItems().remove(lCampos.obterObjeto());
		}
		
		cancelado();
	}


	
	/** método de cadastro de novos registros */
	public void cadastrar() {
		
		if (lCampos.dadosEstaoValidos()) {

			lCampos.construirObjeto();

			T objeto = lCampos.obterObjeto();
			
			cadastrar(objeto);
						
		} else {

			ajuda.set("Problema no cadastro: Alguns dos dados estão inválidos");
		}
	}
	
	public void cadastrar(T objeto){
		this.persistir(objeto);
		lElementos.refresh();
		lCampos.definirObjeto(null);
		salvo();
	}

	/** método de remoção de registros 
	 * 
	 * */
	public void remover() {
		if (lElementos.getSelectionModel().isEmpty()) {
			ajuda.set("Selecione o que será removido.");
			return;
		}

		remover(lElementos.getSelectionModel().getSelectedItem());
		
	}
	
	public void remover(T objeto){
		
		this.deletar(objeto);
		
		this.lElementos.getItems().remove(objeto);
		
		salvo();
	}

	public void alterar(){
		if (lElementos.getSelectionModel().isEmpty()) {
			ajuda.set("Selecione o que será alterado.");
			return;
		}
		
		T alterado = lElementos.getSelectionModel().getSelectedItem(),
		  objeto;
		
		lCampos.definirObjeto(null);
		
		lCampos.construirObjeto();
		
		objeto = lCampos.obterObjeto();
		
		alterar(alterado, objeto);
	}
	
	public void alterar(T anterior, T posterior){
		
		lElementos.getItems().set(lElementos.getItems().indexOf(anterior),	posterior);
		
		this.remover(anterior);
		this.persistir(posterior);
		
		lElementos.refresh();

		lCampos.definirObjeto(null);

		salvo();
	}
	
	public void preparar(){
		this.lElementos.getSelectionModel().clearSelection();
		
		this.lCampos.limpar();
		
		lCampos.construirObjeto();
		
		T objeto = lCampos.obterObjeto();
		
		try {
			objeto.getClass().getMethod("setNome", String.class).invoke(objeto, "[Novo]");
			
		} catch (IllegalAccessException | 
				 IllegalArgumentException | 
				 InvocationTargetException | 
				 NoSuchMethodException	| 
				 SecurityException e) {
			
			e.printStackTrace();
		}
		
		lElementos.getItems().add(objeto);
		
		lElementos.getSelectionModel().select(objeto);
		
		novo();
	}

	// TODO: testar num ambiente apropriado
	private void imprimir() {
		PrinterJob job = PrinterJob.createPrinterJob();
		
		if (job.showPageSetupDialog(apresentador.obterPalco())) {
			
			if (job.showPrintDialog(apresentador.obterPalco())) {
				
				ajuda.bind(job.jobStatusProperty().asString());
				
				if (job.printPage(lCampos)) {
					job.endJob();
				}
				
				ajuda.unbind();
			}
		}
	}
	
	/** método de listagem da visão com cadastros no banco */
	@Transactional
	public abstract void popularVisaoInicialmente();

	@Transactional
	public abstract T persistir(T objeto);
	
	@Transactional
	public abstract void deletar(T objeto);
	
	public void mudancaNaSelecao(ObservableValue<? extends T> observavel, T valorAnterior, T valorAtual) {

		lCampos.definirObjeto(valorAtual);
	}

	
	// Visão

	/**
	 * método que atualiza a tela de acordo com o estado, que no caso é de um
	 * novo registro sendo criado
	 */
	public void novo() {
		for (Node e : lCampos.getChildren()) {
			e.setDisable(false);
		}
		
		estado = Estado.Novo;
		
		this.lElementos.setDisable(true);
		
		if(bInscrever != null) bInscrever.setDisable(false);
		bNovo.setDisable(true);
		bCadastrar.setDisable(false);
		bEditar.setDisable(true);
		bAlterar.setDisable(true);
		bExcluir.setDisable(true);
		bCancelar.setDisable(false);
		bImprimir.setDisable(true);
	}

	/**
	 * método que atualiza a tela de acordo com o estado, que no caso é de uma
	 * edição que foi salva
	 */
	public void salvo() {

		for (Node e : lCampos.getChildren()) {
			e.setDisable(true);
		}
		
		estado = Estado.Salvo;
		
		this.lElementos.setDisable(false);
		
		if(bInscrever != null) bInscrever.setDisable(false);
		bNovo.setDisable(false);
		bCadastrar.setDisable(true);
		bEditar.setDisable(false);
		bAlterar.setDisable(true);
		bExcluir.setDisable(false);
		bCancelar.setDisable(true);
		bImprimir.setDisable(false);
	}

	/**
	 * método que atualiza a tela de acordo com o estado, que no caso é de uma
	 * edição em andamento
	 */
	public void editavel() {
		for (Node e : lCampos.getChildren()) {
			e.setDisable(false);
		}
		
		estado = Estado.Editavel;

		if(bInscrever != null) bInscrever.setDisable(false);
		bNovo.setDisable(true);
		bCadastrar.setDisable(true);
		bEditar.setDisable(true);
		bAlterar.setDisable(false);
		bExcluir.setDisable(true);
		bCancelar.setDisable(false);
		bImprimir.setDisable(true);
	}

	/**
	 * método que atualiza a tela de acordo com o estado, que no caso é de uma
	 * operação sobre os registros cancelada
	 */
	public void cancelado() {
		for (Node e : lCampos.getChildren()) {
			e.setDisable(true);
		}
		
		estado = Estado.Salvo;
		
		this.lElementos.setDisable(false);
		
		this.lCampos.limpar();
		ajuda.set("");
		
		bNovo.setDisable(false);
		bCadastrar.setDisable(true);
		bEditar.setDisable(false);
		bAlterar.setDisable(true);
		bExcluir.setDisable(false);
		bCancelar.setDisable(true);
		bImprimir.setDisable(false);
	}


	public void atualizar() {
		this.popularVisaoInicialmente();
		
		T elemento = this.lElementos.getSelectionModel().getSelectedItem();
		this.lCampos.definirObjeto(atualizarElemento(elemento));
	}


	protected abstract T atualizarElemento(T elemento);
}