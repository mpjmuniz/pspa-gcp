package org.pspa.gcp.visao.Lista;

import javafx.scene.control.SelectionMode;

public class FabricaListas<T> {
	
	public static ListaElementos<?> construir(SelectionMode selectionMode){
		if(selectionMode.equals(SelectionMode.SINGLE)){
			return new ListaSelecaoUnica<>();
		} else {
			return new ListaSelecaoMultipla<>();
		}
	}
}
