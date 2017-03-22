package org.pspa.gcp.visao.Lista;

import javafx.scene.control.SelectionMode;

public class ListaSelecaoUnica<T> extends ListaElementos<T> {
	public ListaSelecaoUnica(){
		super();
		
		this.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
}
