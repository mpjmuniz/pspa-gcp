package org.pspa.gcp.visao.adaptadores;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;

import org.pspa.gcp.modelo.enums.Enumeracao;

import javafx.scene.control.ChoiceBox;

public class EntradaEnums extends ChoiceBox<Enumeracao> implements Adaptador<Enumeracao> {

	private Class<? extends Enumeracao> enumeracao;

	public EntradaEnums(Class<? extends Enumeracao> tipo) 
			throws IllegalAccessException, 
				   IllegalArgumentException, 
				   InvocationTargetException, 
				   NoSuchMethodException, 
				   SecurityException {
		super();
		
		this.enumeracao = tipo;
		Object obj = enumeracao.getMethod("values").invoke(null);

		for (int i = 0; i < Array.getLength(obj); i++) {
			this.getItems().add(((Enumeracao) Array.get(obj, i)));
		}
		
	}

	@Override
	public Enumeracao obterValor() {
		return this.getValue();
	}

	@Override
	public void definirValor(Enumeracao valor) {
		this.setValue(valor);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<Enumeracao> obterTipo() {
		return (Class<Enumeracao>) enumeracao;
	}

	@Override
	public boolean estaValido() {
		return this.getValue() != null;
	}

	@Override
	public void apagar() {
		this.setValue(null);
	}
}
