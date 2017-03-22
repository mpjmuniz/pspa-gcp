package org.pspa.gcp.visao.adaptadores;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;

public class EntradaInteiros extends Spinner<Integer> implements Adaptador<Integer>{
	
	public EntradaInteiros(){
		super(new IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
		
		this.editableProperty().set(true);
	}
	
	public Integer obterValor(){
		return this.getValue();
	}
	
	public void definirValor(Integer valor){
		this.getValueFactory().setValue(valor);
	}

	public Class<Integer> obterTipo() {
		return Integer.class;
	}
	
	public boolean estaValido(){
		return ( this.getValue() != null );
	}

	@Override
	public void apagar() {
		this.getValueFactory().setValue(0);
	}
}