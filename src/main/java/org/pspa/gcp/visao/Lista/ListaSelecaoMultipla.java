package org.pspa.gcp.visao.Lista;

import javafx.scene.control.SelectionMode;

public class ListaSelecaoMultipla<T> extends ListaElementos<T> {
	public ListaSelecaoMultipla(){
		super();
		
		this.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
}
