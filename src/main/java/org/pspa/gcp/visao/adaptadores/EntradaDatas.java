package org.pspa.gcp.visao.adaptadores;

import java.time.LocalDate;

import javafx.scene.control.DatePicker;

public class EntradaDatas extends DatePicker implements Adaptador<LocalDate> {

	public EntradaDatas() {
		super();
	}

	public EntradaDatas(LocalDate localDate) {
		super(localDate);
	}

	@Override
	public LocalDate obterValor() {
		return this.getValue();
	}

	@Override
	public void definirValor(LocalDate valor) {
		this.setValue(valor);
	}

	@Override
	public Class<LocalDate> obterTipo() {
		return LocalDate.class;
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
