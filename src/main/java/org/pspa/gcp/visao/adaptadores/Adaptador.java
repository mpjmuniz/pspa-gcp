package org.pspa.gcp.visao.adaptadores;

import javafx.collections.ObservableMap;

public interface Adaptador<T>{
	public T obterValor();
	
	public void definirValor(T valor);
	
	public Class<T> obterTipo();
	
	public boolean estaValido();
	
	public void apagar();

	public Object getStyle();

	public void setStyle(String string);

	public ObservableMap<Object, Object> getProperties();
 
}
