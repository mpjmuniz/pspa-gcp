package org.pspa.gcp.visao.adaptadores;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.pspa.gcp.visao.Apresentador;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class EntradaObjetos<T, D> extends HBox implements Adaptador<T> {

	private T elemento;
	
	private D dono;
	
	private Class<T> classeAssociada;
	
	private Class<D> classeDono;
	
	private Method setter;
	
	private TextField tfElemento;
	
	@SuppressWarnings("unchecked")
	public EntradaObjetos(Class<?> clsAssociada, Class<D> clsDona) throws NoSuchMethodException, SecurityException{
		super();
		
		Apresentador apresentador = Apresentador.obterInstancia();
		
		tfElemento = new TextField();
		Button bElemento;

		String nome = clsAssociada.getName();
		tfElemento.setEditable(false);

		classeAssociada = (Class<T>) clsAssociada;
		classeDono = clsDona;
		
		nome = nome.substring(nome.lastIndexOf(".") + 1);

		setter = classeDono.getDeclaredMethod("set" + nome, classeAssociada);
		
		bElemento = new Button("Selecionar " + nome);

		bElemento.setOnAction(e -> apresentador.selecionar(this));
		this.getChildren().addAll(tfElemento, bElemento);
		this.setPadding(new Insets(5));

	}

	@SuppressWarnings("unchecked")
	public void setDono(Object elemento){
		this.dono = (D) elemento;
	}
	
	@Override
	public void apagar() {
		this.tfElemento.setText("");
		this.elemento = null;
		
		// TODO: verificar necessidade de definir valor como null no objeto
	}

	@Override
	public void definirValor(T valor) {
	
		this.elemento = valor;
		
		if(dono != null){
			try {
				setter.invoke(dono, valor);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} 
		
		tfElemento.setText((valor == null) ? "Nenhum" : valor.toString());
	}
	
	@Override
	public boolean estaValido() {
		return true;
	}

	public D getDono() {
		return dono;
	}

	public TextField getTfElemento() {
		return tfElemento;
	}

	@Override
	public Class<T> obterTipo() {
		return classeAssociada;
	}
	
	public Class<D> obterTipoDono() {
		return classeDono;
	}

	@Override
	public T obterValor() {
		return elemento;
	}

	public void setTfElemento(TextField tfElemento) {
		this.tfElemento = tfElemento;
	}

}