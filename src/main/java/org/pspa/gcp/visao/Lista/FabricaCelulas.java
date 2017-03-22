package org.pspa.gcp.visao.Lista;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
/**
 * Classe que provê a mostra adequada dos itens pertencentes à  lista
 */
class FabricaCelulas<T> implements Callback<ListView<T>, ListCell<T>> {

	@Override
	public ListCell<T> call(ListView<T> param) {
		return new ListCell<T>() {
			@Override
			public void updateItem(T item, boolean empty) {
				// Must call super
				super.updateItem(item, empty);

				String name = null;

				// Format name
				if (item == null || empty) {
					// No action to perform
				} else {
					name = item.toString();
				}

				this.setText(name);
				setGraphic(null);
			}
		};
	}
}
