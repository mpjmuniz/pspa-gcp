package org.pspa.gcp.visao.cardapio;

import java.util.Arrays;

import org.pspa.gcp.modelo.Cardapio;
import org.pspa.gcp.modelo.Produto;
import org.pspa.gcp.modelo.enums.Semana;
import org.pspa.gcp.visao.Apresentador;
import org.pspa.gcp.visao.adaptadores.EntradaInteiros;
import org.pspa.gcp.visao.almoxarifado.ServicoEstoque;
import org.pspa.gcp.visao.util.Conversor;
import org.springframework.context.ApplicationContext;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VisaoCardapio extends VBox {
	
	ServicoCardapio servico;
	ServicoEstoque servicoAux;
	
	private Cardapio atual;
	
	private final TableView<Produto> tabela;
	
	private final Button adicionar,
						 remover, 
						 salvar,
						 imprimir;
	
	private final TextArea taObs;
	
	private SimpleStringProperty ajuda;
	
	private Label lNumCriancas,
				  lNumFuncionarios;
	
	private EntradaInteiros eiNumCriancas,
							eiNumFuncionarios;
	
	private Apresentador apresentador;
	
	@SuppressWarnings("unchecked")
	public VisaoCardapio(ApplicationContext contexto) {
		
		servico = contexto.getBean(ServicoCardapio.class);
		servicoAux = contexto.getBean(ServicoEstoque.class);
		
		atual = servico.obterUltimoCardapio();
		
		apresentador = Apresentador.obterInstancia();
		
		this.tabela = new TableView<Produto>();
		
		tabela.getColumns().addAll(obterColunaNome(),
								 obterColinaMedida(),
								 obterColunaSegunda(),
								 obterColunaTerca(),
								 obterColunaQuarta(),
								 obterColunaQuinta(),
								 obterColunaSexta(),
								 obterColunaSemana());

		tabela.getColumns().forEach(e -> {
			e.setPrefWidth((apresentador.obterPalco().getWidth() / tabela.getColumns().size()) - 1);
		});
		
		tabela.setEditable(true);
		
		tabela.setPrefHeight(tabela.getMaxHeight());
		
		ajuda = new SimpleStringProperty("");
		apresentador.cadastrarAjuda(ajuda);
		
		adicionar = new Button("Adicionar");
		adicionar.setOnAction(e -> adicionar());
		
		remover = new Button("Remover");
		remover.setOnAction(e -> remover());
		
		salvar = new Button("Salvar");
		salvar.setOnAction(e -> salvar());
		
		imprimir = new Button("Imprimir");
		imprimir.setOnAction(e -> imprimir());
				
		HBox hbNumCriancas = new HBox(10),
			 hbNumFuncionarios = new HBox(10);
		
		lNumCriancas = new Label("Numero de Criancas: ");
		lNumFuncionarios = new Label("Numero de Funcionarios: ");
		
		eiNumCriancas = new EntradaInteiros();
		
		eiNumFuncionarios = new EntradaInteiros();
		
		hbNumCriancas.getChildren().addAll(lNumCriancas, eiNumCriancas);
		hbNumFuncionarios.getChildren().addAll(lNumFuncionarios, eiNumFuncionarios);
		
		taObs = new TextArea();
		
		atualizarVisao();
		
		HBox subBase = new HBox(10);
		subBase.getChildren().addAll(salvar, adicionar, remover, imprimir);
		subBase.setSpacing(15);
		this.setPadding(new Insets(10));
		this.setSpacing(10);
		this.getChildren().addAll(hbNumCriancas, hbNumFuncionarios, tabela, subBase, taObs);
		
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

	private void salvar() {
		atual.setObservacao(taObs.getText());
		atual.setNumeroAlunos(eiNumCriancas.obterValor());
		atual.setNumeroFuncionarios(eiNumFuncionarios.obterValor());
	
		for(Produto p : tabela.getItems()){
			
			servico.adicionarProduto(atual, p);
		}	
		
		
		
		servico.salvarCardapio(atual);
	}

	private void atualizarVisao() {
		
		Integer num = atual.getNumeroAlunos();
		if(num == null){
			num = servico.obterQuantidadeAlunos();
		}
		eiNumCriancas.definirValor(num);
		
		num = atual.getNumeroFuncionarios();
		if(num == null){
			num = servico.obterQuantidadeFuncionarios();
		}
		
		eiNumFuncionarios.definirValor(num);
		
		for(Produto p : tabela.getItems()){
			servico.adicionarProduto(atual, p);
		}
		
		this.tabela.setItems(FXCollections.observableArrayList(atual.getProdutos()));
		
		this.taObs.setText(atual.getObservacao());
	}

	private void remover() {
		TableViewSelectionModel<Produto> tsm = tabela.getSelectionModel();
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
		Produto p;
		for(int i = selectedIndices.length - 1; i >= 0; i--) {
			tsm.clearSelection(selectedIndices[i].intValue());
			 p = tabela.getItems().remove(selectedIndices[i].intValue());
			 servico.removerProduto(atual, p);
		}
	}

	private void adicionar() {
		apresentador.selecionar(Produto.class, tabela, atual);
	}

	private TableColumn<Produto, String> obterColunaNome() {
		TableColumn<Produto, String> colunaNome = new TableColumn<>("Produto");
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaNome.setCellFactory(TextFieldTableCell.<Produto>forTableColumn());
		colunaNome.setOnEditCommit(e -> {
			Produto p = e.getRowValue();
			p.setNome(e.getNewValue());
			
			servico.salvarProduto(p);
		});
		
		return colunaNome;
	}
	
	private TableColumn<Produto, String> obterColinaMedida() {
		TableColumn<Produto, String> colunaMedida = new TableColumn<>("Medida");
		colunaMedida.setCellValueFactory(new PropertyValueFactory<>("medida"));
		colunaMedida.setEditable(false);
		
		return colunaMedida;
	}
	
	private TableColumn<Produto, Integer> obterColunaSexta() {
		TableColumn<Produto, Integer> colunaSexta = new TableColumn<>("Sexta");
		colunaSexta.setCellValueFactory(new PropertyValueFactory<>("sexta"));
		colunaSexta.setCellFactory(TextFieldTableCell.<Produto, Integer>forTableColumn(new Conversor()));
		colunaSexta.setOnEditCommit(e -> {
			Produto p = e.getRowValue();
			p.setSexta(e.getNewValue());
			
			servico.salvarProduto(p);
		});
		return colunaSexta;
	}
	
	private TableColumn<Produto, Integer> obterColunaQuinta() {
		TableColumn<Produto, Integer> colunaQuinta = new TableColumn<>("Quinta");
		colunaQuinta.setCellValueFactory(new PropertyValueFactory<>("quinta"));
		colunaQuinta.setCellFactory(TextFieldTableCell.<Produto, Integer>forTableColumn(new Conversor()));
		colunaQuinta.setOnEditCommit(e -> {
			Produto p = e.getRowValue();
			p.setQuinta(e.getNewValue());
			
			servico.salvarProduto(p);
		});
		return colunaQuinta;
	}
	
	private TableColumn<Produto, Integer> obterColunaQuarta() {
		TableColumn<Produto, Integer> colunaQuarta = new TableColumn<>("Quarta");
		colunaQuarta.setCellValueFactory(new PropertyValueFactory<>("quarta"));
		colunaQuarta.setCellFactory(TextFieldTableCell.<Produto, Integer>forTableColumn(new Conversor()));
		colunaQuarta.setOnEditCommit(e -> {
			Produto p = e.getRowValue();
			p.setQuarta(e.getNewValue());
			servico.salvarProduto(p);
		});
		return colunaQuarta;
	}
	
	private TableColumn<Produto, Integer> obterColunaTerca() {
		TableColumn<Produto, Integer> colunaTerca = new TableColumn<>("Terca");
		colunaTerca.setCellValueFactory(new PropertyValueFactory<>("terca"));
		colunaTerca.setCellFactory(TextFieldTableCell.<Produto, Integer>forTableColumn(new Conversor()));
		colunaTerca.setOnEditCommit(e -> {
			Produto p = e.getRowValue();
			p.setTerca(e.getNewValue());
			
			servico.salvarProduto(p);
		});
		return colunaTerca;
	}
	
	private TableColumn<Produto, Integer> obterColunaSegunda() {
		TableColumn<Produto, Integer> colunaSegunda = new TableColumn<>("Segunda");
		colunaSegunda.setCellValueFactory(new PropertyValueFactory<>("segunda"));
		colunaSegunda.setCellFactory(TextFieldTableCell.<Produto, Integer>forTableColumn(new Conversor()));
		colunaSegunda.setOnEditCommit(e -> {
			Produto p = e.getRowValue();
			p.setSegunda(e.getNewValue());
			
			servico.salvarProduto(p);
		});
		return colunaSegunda;
	}
	
	private TableColumn<Produto, Semana> obterColunaSemana() {
		TableColumn<Produto, Semana> colunaSemana = new TableColumn<>("Semana");
		
		// By default, all cells are have null values
		colunaSemana.setCellValueFactory(
				cellData -> {
					Semana s = cellData.getValue().getSemana();
					return new ReadOnlyObjectWrapper<Semana>(s);
				});
		
		// Set a ComboBoxTableCell, so we can selects a value from a list
		colunaSemana.setCellFactory(
			ComboBoxTableCell.<Produto, Semana>forTableColumn(Semana.values()));
		
		// Add an event handler to handle the edit commit event.
		// It displays the selected value on the standard output
		colunaSemana.setOnEditCommit(e -> {
			Produto p = e.getRowValue();
			p.setSemana(e.getNewValue());
			servico.salvarProduto(p);
		});
		
		return colunaSemana;
	}

	public void atualizar() {
		this.atualizarVisao();
	} 
}
