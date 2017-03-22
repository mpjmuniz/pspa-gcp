package org.pspa.gcp.visao.financas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.pspa.gcp.modelo.Transacao;
import org.pspa.gcp.modelo.repositorios.RepositorioFinanceiro;
import org.pspa.gcp.visao.Apresentador;
import org.springframework.context.ApplicationContext;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VisaoFinancas extends VBox {

	RepositorioFinanceiro repositorio;
	
	private TableView<Transacao> tabela;
	
	private final Button adicionar,
						 remover, 
						 imprimir;
	
	private SimpleStringProperty ajuda;
	
	private Apresentador apresentador;
	
	@SuppressWarnings("unchecked")
	public VisaoFinancas(ApplicationContext contextoSpring) {
		
		this.repositorio = contextoSpring.getBean(RepositorioFinanceiro.class);
		
		apresentador = Apresentador.obterInstancia();
		
		this.tabela = new TableView<>();
		
		tabela.getColumns().addAll(obterColunaData(),
				 obterColunaDescricao(),
				 obterColunaEntrada(),
				 obterColunaSaida(),
				 obterColunaBalanco());
		
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
		
		HBox subBase = new HBox(10);
		subBase.getChildren().addAll(adicionar, remover, imprimir);
		subBase.setSpacing(15);
		this.setPadding(new Insets(10));
		this.setSpacing(10);
		this.getChildren().addAll(tabela, subBase);
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
		TableViewSelectionModel<Transacao> tsm = tabela.getSelectionModel();
		if (tsm.isEmpty()) {

			ajuda.set("Selecione as entradas que serão removidas");
			
			return;
		}
		
		// Get all selected row indices in an array
		ObservableList<Integer> list = tsm.getSelectedIndices();
		Integer[] selectedIndices = new Integer[list.size()];
		selectedIndices = list.toArray(selectedIndices);

		// Sort the array
		Arrays.sort(selectedIndices);
		
		// Delete rows (last to first)
		Transacao t;
		for(int i = selectedIndices.length - 1; i >= 0; i--) {
			tsm.clearSelection(selectedIndices[i].intValue());
			 t = tabela.getItems().remove(selectedIndices[i].intValue());
			 repositorio.delete(t);
		}
	}

	private void adicionar() {
		Transacao t = new Transacao();
		tabela.getItems().add(t);
		repositorio.save(t);
	}

	private TableColumn<Transacao, Double> obterColunaSaida() {
		TableColumn<Transacao, Double> colunaSaida = new TableColumn<>("Saida");
		colunaSaida.setCellValueFactory(new PropertyValueFactory<>("saida"));
		
		colunaSaida.setOnEditCommit(e -> {
			Transacao t = e.getRowValue();
			t.setSaida(e.getNewValue());
			repositorio.save(t);
		});
		
		return colunaSaida;
	}

	private TableColumn<Transacao, Double> obterColunaEntrada() {
		TableColumn<Transacao, Double> colunaEntrada = new TableColumn<>("Entrada");
		colunaEntrada.setCellValueFactory(new PropertyValueFactory<>("entrada"));
		
		colunaEntrada.setOnEditCommit(e -> {
			Transacao t = e.getRowValue();
			t.setSaida(e.getNewValue());
			repositorio.save(t);
		});
		
		return colunaEntrada;
	}
	
	private TableColumn<Transacao, Double> obterColunaBalanco() {
		TableColumn<Transacao, Double> colunaBalanco = new TableColumn<>("Balanço");
		colunaBalanco.setCellValueFactory(new PropertyValueFactory<>("balanco"));
		colunaBalanco.setEditable(false);
		
		return colunaBalanco;
	}

	private TableColumn<Transacao, String> obterColunaDescricao() {
		TableColumn<Transacao, String> colunaDescricao = new TableColumn<>("Descrição");
		colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		colunaDescricao.setCellFactory(TextFieldTableCell.<Transacao>forTableColumn());
		colunaDescricao.setOnEditCommit(e -> {
			Transacao t = e.getRowValue();
			t.setDescricao(e.getNewValue());
			
			repositorio.save(t);
		});
		
		return colunaDescricao;
	}

	private TableColumn<Transacao, LocalDate> obterColunaData() {
		
		TableColumn<Transacao, LocalDate> colunaData = new TableColumn<>("Data");
		colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
		
		// Set a custom cell factory for Birth Date column
		colunaData.setCellFactory(col -> {
			TableCell<Transacao, LocalDate> cell
					= new TableCell<Transacao, LocalDate>() {
						@Override
						public void updateItem(LocalDate item, boolean empty) {
							super.updateItem(item, empty);

							// Cleanup the cell before populating it
							this.setText(null);
							this.setGraphic(null);

							if (!empty) {
								String formattedDob = DateTimeFormatter.ofPattern("dd/MM/yyyy")
								.format(item);
								this.setText(formattedDob);
							}
						}
					};
			return cell;
		});
		
		return colunaData;
	}

	public void atualizar() {
		tabela.refresh();
	}
}
