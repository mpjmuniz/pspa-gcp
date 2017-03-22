package org.pspa.gcp.visao.Lista;

import javafx.scene.control.SelectionMode;

public class FabricaListas<T> {
	
	private SelectionMode selectionMode;
	
	public FabricaListas(SelectionMode selectionMode){
		this.selectionMode = selectionMode;
	}
	
	public ListaElementos<T> construir(){
		if(this.selectionMode.equals(SelectionMode.SINGLE)){
			return new ListaSelecaoUnica<>();
		} else {
			return new ListaSelecaoMultipla<>();
		}
	}
}
