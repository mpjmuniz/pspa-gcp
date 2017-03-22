package org.pspa.gcp.visao.selecao;

import java.util.List;

import org.pspa.gcp.modelo.Produto;
import org.pspa.gcp.modelo.repositorios.RepositorioProduto;
import org.springframework.context.ApplicationContext;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;

public class SelecaoTabelaProdutos extends Selecao<Produto> {

	private RepositorioProduto repositorio;
	
	/** lista que auxilia a recuperação dos elementos selecionados na árvore*/
	private TableView<Produto> tabelaAuxiliar;
	
	/** 
	 * Construtor para tabelas
	 * 
	 * @param tabela nó JavaFX (TableView) que ajuda a recuperar os elementos selecionados
	 * 
	 * 
	 **/
	public SelecaoTabelaProdutos(ApplicationContext contexto, TableView<Produto> tabela) {
		super(Produto.class, SelectionMode.MULTIPLE, contexto);
		
		this.tabelaAuxiliar = tabela;
		
		bOk.setOnAction(e -> this.designarTabela());
		
		sSobreposto.setOnCloseRequest(e -> this.designarTabela());

	}

	@Override
	protected void popularVisao() {
		this.lvCadastros.getItems().addAll(repositorio.findAll());
	}

	@Override
	protected void definirRepositorio() {
		this.repositorio = contextoSpring.getBean(RepositorioProduto.class);
	}
	
	/**
	 * 	destina os itens selecionados para a lista definida na criação da seleção
	 * 
	 * 	uso exclusivo para seleção de vários campos, onde {@code modo_seleção} = 1
	 * */
	
	protected void designarTabela() {
		List<Produto> itens = lvCadastros.getSelectionModel().getSelectedItems();
		
		if(tabelaAuxiliar != null){
			tabelaAuxiliar.getItems().clear();
			tabelaAuxiliar.getItems().addAll(itens);
		} else{
			throw new RuntimeException("Tabela auxiliar não declarada");
		}
		
		sSobreposto.close();
	}
	
}
