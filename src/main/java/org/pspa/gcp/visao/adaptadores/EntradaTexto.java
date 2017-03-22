package org.pspa.gcp.visao.adaptadores;

import javafx.scene.control.TextField;

public class EntradaTexto extends TextField implements Adaptador<String> {

	public EntradaTexto() {
		super();
	}

	public EntradaTexto(String text) {
		super(text);
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
		
		return (texto != null) && !("".equals(texto));
	}

	@Override
	public void apagar() {
		this.setText("");
	}

}
