package org.pspa.gcp.visao.adaptadores;

import javafx.scene.control.CheckBox;

public class EntradaBooleanos extends CheckBox implements Adaptador<Boolean> {

	public EntradaBooleanos() {
		super();
	}

	public EntradaBooleanos(String text) {
		super(text);
	}

	@Override
	public Boolean obterValor() {
		return this.isSelected();
	}

	@Override
	public void definirValor(Boolean valor) {
		this.setSelected(valor);
	}

	@Override
	public Class<Boolean> obterTipo() {
		return Boolean.class;
	}

	@Override
	public boolean estaValido() {
		return true;
	}

	@Override
	public void apagar() {
		this.setSelected(false);
	}

}
