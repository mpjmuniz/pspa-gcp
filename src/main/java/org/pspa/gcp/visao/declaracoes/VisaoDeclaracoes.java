package org.pspa.gcp.visao.declaracoes;

import java.util.Arrays;

import org.pspa.gcp.modelo.Declaracao;
import org.pspa.gcp.modelo.repositorios.RepositorioDeclaracoes;
import org.pspa.gcp.visao.Apresentador;
import org.springframework.context.ApplicationContext;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;

// TODO: ajustar cenas para consistẽncia de uso das declarações
public class VisaoDeclaracoes extends VBox{

	RepositorioDeclaracoes repositorio;
	
	private TableView<Declaracao> tabela;
	
	private Apresentador apresentador;
	
	private SimpleStringProperty ajuda;
	
	private Button adicionar,
				   remover,
				   imprimir;
	
	private HTMLEditor visor;
	
	@SuppressWarnings("unchecked")
	public VisaoDeclaracoes(ApplicationContext contextoSpring) {
		this.repositorio = contextoSpring.getBean(RepositorioDeclaracoes.class);
			
		apresentador = Apresentador.obterInstancia();
		
		this.tabela = new TableView<Declaracao>();
		
		tabela.getColumns().addAll(obterColunaProposito(),
								 obterColunaCorpo());

		tabela.getColumns().forEach(e -> {
			e.setPrefWidth((apresentador.obterPalco().getWidth() / tabela.getColumns().size()) - 1);
			
		});
		
		tabela.setEditable(true);
		
		ajuda = new SimpleStringProperty("");
		apresentador.cadastrarAjuda(ajuda);
		
		this.setSpacing(10);
		
		adicionar = new Button("Adicionar");
		adicionar.setOnAction(e -> adicionar());
		
		remover = new Button("Remover");
		remover.setOnAction(e -> remover());
		
		imprimir = new Button("Imprimir");
		imprimir.setOnAction(e -> imprimir());
		
		tabela.setPrefHeight(tabela.getMaxHeight());
		
		this.visor = new HTMLEditor();
		
		HBox subBase = new HBox(10);
		subBase.getChildren().addAll(adicionar, remover, imprimir);
		subBase.setSpacing(15);
		this.setPadding(new Insets(10));
		this.setSpacing(10);
		this.getChildren().addAll(tabela, subBase, visor);
		
	}

	// TODO: testar num ambiente apropriado
	private void imprimir() {
		PrinterJob job = PrinterJob.createPrinterJob();
		
		if (job.showPageSetupDialog(apresentador.obterPalco())) {
			
			if (job.showPrintDialog(apresentador.obterPalco())) {
				
				ajuda.bind(job.jobStatusProperty().asString());
				
				if (job.printPage(tabela)) {
					job.endJob();
				}
				
				ajuda.unbind();
			}
		}
	}

	private void remover() {
		TableViewSelectionModel<Declaracao> tsm = tabela.getSelectionModel();
		if (tsm.isEmpty()) {

			ajuda.set("Selecione os produtos que serão removidos");
			
			return;
		}
		
		// Get all selected row indices in an array
		ObservableList<Integer> list = tsm.getSelectedIndices();
		Integer[] selectedIndices = new Integer[list.size()];
		selectedIndices = list.toArray(selectedIndices);

		// Sort the array
		Arrays.sort(selectedIndices);
		
		// Delete rows (last to first)
		Declaracao p;
		for(int i = selectedIndices.length - 1; i >= 0; i--) {
			tsm.clearSelection(selectedIndices[i].intValue());
			 p = tabela.getItems().remove(selectedIndices[i].intValue());
			 repositorio.delete(p);
		}
	}

	private void adicionar() {
		Declaracao p = new Declaracao();
		repositorio.save(p);
		this.tabela.getItems().add(p);
	}

	private TableColumn<Declaracao, String> obterColunaCorpo() {
		TableColumn<Declaracao, String> colunaCorpo = new TableColumn<>("Corpo");
		colunaCorpo.setCellValueFactory(new PropertyValueFactory<>("corpo"));
		colunaCorpo.setCellFactory(TextFieldTableCell.<Declaracao>forTableColumn());
		colunaCorpo.setOnEditCommit(e -> {
			Declaracao p = e.getRowValue();
			p.setCorpo(e.getNewValue());
			
			repositorio.save(p);
		});
		
		return colunaCorpo;
	}

	private TableColumn<Declaracao, String> obterColunaProposito() {
		TableColumn<Declaracao, String> colunaProposito = new TableColumn<>("Propósito");
		colunaProposito.setCellValueFactory(new PropertyValueFactory<>("proposito"));
		colunaProposito.setCellFactory(TextFieldTableCell.<Declaracao>forTableColumn());
		colunaProposito.setOnEditCommit(e -> {
			Declaracao p = e.getRowValue();
			p.setProposito(e.getNewValue());
			
			repositorio.save(p);
		});
		
		return colunaProposito;
	}

	public void atualizar() {
		return;	
	}
}
