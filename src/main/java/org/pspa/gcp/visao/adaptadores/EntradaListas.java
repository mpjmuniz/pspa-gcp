package org.pspa.gcp.visao.adaptadores;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

import org.pspa.gcp.visao.Apresentador;

import javafx.scene.control.Button;

@SuppressWarnings("rawtypes")
public class EntradaListas<T, D> extends Button implements Adaptador<List> {
	
	private List<T> elementos;
	
	private D dono;
	
	private Class<T> classeAssociada;
	
	private Class<D> classeDono;
	
	private Method setter;
	
	@SuppressWarnings("unchecked")
	public EntradaListas(Type tipo, Class<D> clsDona) throws NoSuchMethodException, SecurityException, ClassNotFoundException{
		super();
		
		Apresentador apresentador = Apresentador.obterInstancia();
		
		String textoBotao = null, 
			   texto = null;
		
		texto = tipo.toString();
		final String textoFinal = texto.substring(texto.indexOf("<") + 1, texto.indexOf(">"));
		
		
		classeAssociada = (Class<T>) Class.forName(textoFinal);
		classeDono = clsDona;
		
		setter = classeDono.getDeclaredMethod("set" + textoFinal.substring(textoFinal.lastIndexOf('.') + 1) + "s", List.class);
		
		textoBotao = "Selecionar " + textoFinal.substring(textoFinal.lastIndexOf('.') + 1) + "s";
		
		this.setOnAction((e) -> apresentador.selecionar(this));
		
		this.setText(textoBotao);
	}
	
	@Override
	public boolean estaValido() {
		//return lista.getItems().size() != 0;
		return true;
	}

	@Override
	public List<T> obterValor() {
		return elementos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void definirValor(List valor) {
		this.elementos = (List<T>) valor;
		
		if(dono != null){
			if(valor != null){		
				try {
					setter.invoke(dono, valor);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public Class<List> obterTipo() {
		return List.class;
	}
	
	public Class<T> obterTipoArgumento() {
		return classeAssociada;
	}

	@Override
	public void apagar() {
		if(dono == null) return;
		try {
			this.setter.invoke(dono, (Object) null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void setDono(Object objeto) {
		this.dono = (D) objeto;
	}
	
	public D getDono(){
		return this.dono;
	}
}
