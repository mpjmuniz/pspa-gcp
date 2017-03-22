package org.pspa.gcp.visao.selecao;

import java.util.List;

import org.springframework.context.ApplicationContext;

import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public abstract class SelecaoMultipla<T> extends Selecao<T> {

	/** lista que auxilia a recuperação dos elementos selecionados na árvore*/
	private ListView<T> listaAuxiliar;
	
	/** 
	 * Construtor para listas
	 * 
	 * @param lista nó JavaFX (ListView) que ajuda a recuperar os elementos selecionados
	 * 
	 * 
	 **/
	public SelecaoMultipla(ApplicationContext contexto, Class<?> cls, ListView<T> lista){
		super(cls, SelectionMode.MULTIPLE, contexto);
		
		this.listaAuxiliar = lista;
		
		lvCadastros.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		bOk.setOnAction(e -> this.designarLista());
		
		sSobreposto.setOnCloseRequest(e -> this.designarLista());

	}
	

	/**
	 * 	destina os itens selecionados para a lista definida na criação da seleção
	 * 
	 * 	uso exclusivo para seleção de vários campos, onde {@code modo_seleção} = 1
	 * */
	protected void designarLista() {
		List<T> itens = lvCadastros.getSelectionModel().getSelectedItems();
		
		if(listaAuxiliar != null){
			listaAuxiliar.getItems().clear();
			listaAuxiliar.getItems().addAll(itens);
		} else{
			throw new RuntimeException("Lista auxiliar não declarada");
		}
		
		sSobreposto.close();
	}
}
