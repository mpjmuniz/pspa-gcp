package org.pspa.gcp.visao.adaptadores;

import javafx.scene.control.Label;

public class Identificador extends Label implements Adaptador<String> {

	public Identificador(String texto) {
		super(texto);
	}

	@Override
	public String obterValor() {
		return this.getText();
	}

	@Override
	public void definirValor(String valor) {
		this.setText(valor);
	}

	@Override
	public Class<String> obterTipo() {
		return String.class;
	}

	@Override
	public boolean estaValido() {
		String texto = this.getText();
		
		return texto != null && ! "".equals(texto);
	}

	@Override
	public void apagar() {
		return;
	}

}
