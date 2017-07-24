package org.pspa.gcp.visao.almoxarifado;

import java.util.Arrays;
import java.util.List;

import org.pspa.gcp.modelo.Produto;
import org.pspa.gcp.modelo.enums.Tipo;
import org.pspa.gcp.visao.Apresentador;
import org.pspa.gcp.visao.util.Conversor;
import org.springframework.context.ApplicationContext;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class VisaoEstoque extends VBox {
	
	ServicoEstoque servico;
	
	private final TableView<Produto> tabela;
	
	private final Button adicionar,
						 remover;

	private SimpleStringProperty ajuda;
	
	private Apresentador apresentador;		
	
	@SuppressWarnings("unchecked")
	public VisaoEstoque(ApplicationContext contexto) {
		
		servico = contexto.getBean(ServicoEstoque.class);
		
		apresentador = Apresentador.obterInstancia();
		
		this.tabela = new TableView<Produto>();
		
		tabela.getColumns().addAll(obterColunaNome(),
				obterColunaMedida(),
				obterColunaEstoque(),
				obterColunaNecessario(),
				obterColunaComprar());

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
		
		tabela.setPrefHeight(tabela.getMaxHeight());
		tabela.getItems().addAll(obterProdutos());
		
		HBox hbNumCriancas = new HBox(10),
			 hbNumFuncionarios = new HBox(10);
		
		HBox subBase = new HBox(10);
		subBase.getChildren().addAll(adicionar, remover);
		this.setPadding(new Insets(10));
		this.setSpacing(10);
		this.getChildren().addAll(hbNumCriancas, hbNumFuncionarios, tabela, subBase);
		
	}

	private TableColumn<Produto, Integer> obterColunaComprar() {
		TableColumn<Produto, Integer> colunaComprar = new TableColumn<>("A ser Comprado");
		colunaComprar.setEditable(false);
		colunaComprar.setCellValueFactory(new PropertyValueFactory<>("comprar"));
		colunaComprar.setCellFactory(TextFieldTableCell.<Produto, Integer>forTableColumn(new Conversor()));
		
		return colunaComprar;
	}

	private TableColumn<Produto, Integer> obterColunaNecessario() {
		TableColumn<Produto, Integer> colunaNecessario = new TableColumn<>("Necessario");
		colunaNecessario.setCellValueFactory(new PropertyValueFactory<>("necessario"));
		colunaNecessario.setCellFactory(TextFieldTableCell.<Produto, Integer>forTableColumn(new Conversor()));
		colunaNecessario.setOnEditCommit(e -> {
			Produto p = e.getRowValue();
			p.setNecessario(e.getNewValue());
			servico.salvarProduto(p);
			this.atualizar();
		});
		return colunaNecessario;
	}

	protected abstract List<Produto> obterProdutos();

	private TableColumn<Produto, String> obterColunaMedida() {
		TableColumn<Produto, String> colunaMedida = new TableColumn<>("Medida");
		colunaMedida.setCellValueFactory(new PropertyValueFactory<>("medida"));
		colunaMedida.setCellFactory(TextFieldTableCell.<Produto>forTableColumn());
		colunaMedida.setOnEditCommit(e -> {
			Produto p = e.getRowValue();
			p.setMedida(e.getNewValue());
			
			servico.salvarProduto(p);
		});
		
		return colunaMedida;
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
			servico.removerProduto(p);
		}
	}

	private void adicionar() {
		Produto p = new Produto();
		p.setTipo(obterTipo());
		servico.salvarProduto(p);
		this.tabela.getItems().add(p);
	}

	protected abstract Tipo obterTipo();

	private TableColumn<Produto, Integer> obterColunaEstoque() {
		
		TableColumn<Produto, Integer> colunaEstoque = new TableColumn<>("Estocado");
		colunaEstoque.setCellValueFactory(new PropertyValueFactory<>("estoque"));
		colunaEstoque.setCellFactory(TextFieldTableCell.<Produto, Integer>forTableColumn(new Conversor()));
		colunaEstoque.setOnEditCommit(e -> {
			Produto p = e.getRowValue();
			p.setEstoque(e.getNewValue());
			
			servico.salvarProduto(p);
			this.atualizar();
		});
		return colunaEstoque;
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

	public void atualizar() {
		tabela.getItems().clear();
		tabela.getItems().addAll(obterProdutos());
		tabela.refresh();
	}
}
