package org.pspa.gcp.visao.almoxarifado;

import java.util.Arrays;

import org.pspa.gcp.modelo.Produto;
import org.pspa.gcp.modelo.repositorios.RepositorioProduto;
import org.pspa.gcp.visao.Apresentador;
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

public class VisaoAlmoxarifado extends VBox {
	
	RepositorioProduto repositorio;
	
	private final TableView<Produto> tabela;
	
	private final Button adicionar,
						 remover;

	private SimpleStringProperty ajuda;
	
	private Apresentador apresentador;
	
	@SuppressWarnings("unchecked")
	public VisaoAlmoxarifado(ApplicationContext contexto) {
		
		repositorio = contexto.getBean(RepositorioProduto.class);
		
		apresentador = Apresentador.obterInstancia();
		
		this.tabela = new TableView<Produto>();
		
		tabela.getColumns().addAll(obterColunaNome(),
								 obterColunaEstoque());

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
		tabela.getItems().addAll(repositorio.findAll());
		
		HBox hbNumCriancas = new HBox(10),
			 hbNumFuncionarios = new HBox(10);
		
		HBox subBase = new HBox(10);
		subBase.getChildren().addAll(adicionar, remover);
		this.setPadding(new Insets(10));
		this.setSpacing(10);
		this.getChildren().addAll(hbNumCriancas, hbNumFuncionarios, tabela, subBase);
		
	}

	public void decrescerQuantidade(Produto p, int quantidade){
		p.setEstoque(p.getEstoque() - quantidade);
		repositorio.save(p);
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
			 repositorio.delete(p);
		}
	}

	private void adicionar() {
		Produto p = new Produto();
		repositorio.save(p);
		this.tabela.getItems().add(p);
	}

	private TableColumn<Produto, Integer> obterColunaEstoque() {
		TableColumn<Produto, Integer> colunaEstoque = new TableColumn<>("Estocado");
		colunaEstoque.setCellValueFactory(new PropertyValueFactory<>("estoque"));
		
		colunaEstoque.setOnEditCommit(e -> {
			Produto p = e.getRowValue();
			p.setEstoque(e.getNewValue());
			repositorio.save(p);
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
			
			repositorio.save(p);
		});
		
		return colunaNome;
	}

	public void atualizar() {
		tabela.refresh();
	}
}
