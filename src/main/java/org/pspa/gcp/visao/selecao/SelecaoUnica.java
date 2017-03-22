package org.pspa.gcp.visao.selecao;

import org.springframework.context.ApplicationContext;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

public abstract class SelecaoUnica<T> extends Selecao<T> {

	/** campo que auxilia a recuperação do elemento selecionado na árvore*/
	private TextField campoAuxiliar;
	
	/** 
	 * Construtor para campos
	 * 
	 * @param campoAuxiliar nó JavaFX (TextField) que ajuda a recuperar o elemento selecionado
	 **/
	public SelecaoUnica(ApplicationContext contextoSpring, Class<?> cls, TextField campoAuxiliar){
		super(cls, SelectionMode.SINGLE, contextoSpring);
		this.campoAuxiliar = campoAuxiliar;
		
		lvCadastros.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		bOk.setOnAction(e -> this.designarItem());
		sSobreposto.setOnCloseRequest(e -> this.designarItem());
		
	}
	
	/**
	 * 	destina o item selecionado para o campo definido na criação da seleção
	 * 
	 * 	uso exclusivo para seleção de campos, onde {@code modo_seleção} = 0
	 * */
	private void designarItem() {
		T selecao = lvCadastros.getSelectionModel().getSelectedItem();
		
		if(campoAuxiliar != null)
			campoAuxiliar.getProperties().put("obj", selecao);
		else
			throw new RuntimeException("campo auxiliar não declarado");
		
		if(selecao != null) campoAuxiliar.setText(selecao.toString());
		else campoAuxiliar.setText("Nenhum(a)");
		
		sSobreposto.close();
	}
}
