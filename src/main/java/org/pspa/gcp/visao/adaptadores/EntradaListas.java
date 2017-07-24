package org.pspa.gcp.visao.adaptadores;

import java.lang.reflect.Type;
import java.util.List;

import org.pspa.gcp.visao.Apresentador;
import org.pspa.gcp.visao.Lista.FabricaListas;
import org.pspa.gcp.visao.Lista.ListaElementos;

import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;

@SuppressWarnings("rawtypes")
public class EntradaListas<T> extends VBox implements Adaptador<List> {

	/* TODO: modificar usos desta classe para usos de uma janela particular que lista os elementos participantes da relação*/
	
	private ListaElementos<T> lista;
	
	public EntradaListas(Type tipo) {
		super();
		
		Apresentador apresentador = Apresentador.obterInstancia();
		
		String textoBotao = null, 
			   texto = null;
		
		Button bElementos = new Button();

		FabricaListas<T> fl = new FabricaListas<>(SelectionMode.MULTIPLE);
		lista = fl.construir();
		
		texto = tipo.toString();
		final String textoFinal = texto.substring(texto.indexOf("<") + 1, texto.indexOf(">"));

		textoBotao = "Selecionar " + textoFinal.substring(textoFinal.lastIndexOf('.') + 1) + "s";

		bElementos.setOnAction((e) -> {
			
			try {
				apresentador.selecionar(Class.forName(textoFinal), lista);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		
		bElementos.setText(textoBotao);
		this.getChildren().addAll(lista, bElementos);
	}

	@Override
	public boolean estaValido() {
		//return lista.getItems().size() != 0;
		return true;
	}

	@Override
	public List<T> obterValor() {
		return lista.getItems();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void definirValor(List valor) {
		if(valor != null && valor.size() > 0){		
			this.lista.getItems().addAll((List<T>) valor);
		}
	}

	@Override
	public Class<List> obterTipo() {
		return List.class;
	}

	@Override
	public void apagar() {
		this.lista.getItems().removeAll(this.lista.getItems());
	}
}
