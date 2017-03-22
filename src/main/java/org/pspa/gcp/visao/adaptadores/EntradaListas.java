package org.pspa.gcp.visao.adaptadores;

import java.lang.reflect.Type;
import java.util.List;

import org.pspa.gcp.visao.Apresentador;
import org.pspa.gcp.visao.Lista.FabricaListas;
import org.pspa.gcp.visao.Lista.ListaElementos;

import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;

public class EntradaListas<T> extends VBox implements Adaptador<List<T>> {

	private ListaElementos<T> lista;
	
	private Type tipo;
	
	public EntradaListas(Type tipo) {
		super();
		
		Apresentador apresentador = Apresentador.obterInstancia();
		
		String textoBotao = null, 
			   texto = null;
		
		Button bElementos = new Button();

		FabricaListas<T> fl = new FabricaListas<>(SelectionMode.MULTIPLE);
		lista = fl.construir();
		
		this.tipo = tipo;
		
		texto = tipo.toString();
		texto = texto.substring(texto.indexOf("<") + 1, texto.indexOf(">"));

		textoBotao = "Selecionar " + texto.substring(texto.lastIndexOf('.') + 1) + "s";

		bElementos.setOnAction(e -> apresentador.selecionar(tipo.getClass(), lista));
		bElementos.setText(textoBotao);
		this.getChildren().addAll(lista, bElementos);
	}

	@Override
	public boolean estaValido() {
		return lista.getItems().size() != 0;
	}

	@Override
	public List<T> obterValor() {
		return lista.getItems();
	}

	@Override
	public void definirValor(List<T> valor) {
		this.lista.getItems().addAll(valor);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<List<T>> obterTipo() {
		return (Class<List<T>>) tipo.getClass();
	}

	@Override
	public void apagar() {
		this.lista.getItems().removeAll(this.lista.getItems());
	}

}
