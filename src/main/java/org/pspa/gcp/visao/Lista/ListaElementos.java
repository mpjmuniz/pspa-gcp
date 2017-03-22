package org.pspa.gcp.visao.Lista;

import javafx.scene.control.ListView;

/**
 * Nó para vizualização de listas
 * 
 * @param T
 *            tipo dos elementos na lista
 * 
 * ModoSelecao: 
 * 	UNICA: somente uma célula pode ser selecionada por vez
 *  MULTIPLA: aparecerão caixas de tickagem para seleção de múltiplas células
 * 
 * @author marcelo
 * @version 0.1
 */
public abstract class ListaElementos<T> extends ListView<T> {
	
	/** Cria lista usando a superclasse */
	public ListaElementos() {
		super();

		this.setPrefSize(70, 120);
		this.setEditable(false);
		this.setCellFactory(new FabricaCelulas<T>());
	}
	
}
