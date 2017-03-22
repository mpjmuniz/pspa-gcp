package org.pspa.gcp.visao.adaptadores;

import org.pspa.gcp.visao.Apresentador;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class EntradaObjetos<T> extends HBox implements Adaptador<T> {

	private T elemento;
	
	private Class<T> classe;
	
	private TextField tfElemento;
	
	@SuppressWarnings("unchecked")
	public EntradaObjetos(Class<?> cls){
		super();
		
		Apresentador apresentador = Apresentador.obterInstancia();
		
		tfElemento = new TextField();
		Button bElemento;

		String nome = cls.getName();
		tfElemento.setEditable(false);

		classe = (Class<T>) cls;
		
		nome = nome.substring(nome.lastIndexOf(".") + 1);

		bElemento = new Button("Selecionar " + nome);

		bElemento.setOnAction(e -> apresentador.selecionar(cls, tfElemento));
		this.getChildren().addAll(tfElemento, bElemento);
		this.setPadding(new Insets(5));

	}
	
	@Override
	public T obterValor() {
		return elemento;
	}

	@Override
	public void definirValor(T valor) {
		this.elemento = valor;
		
		tfElemento.setText((valor == null) ? "Nenhum" : valor.toString());
	}

	@Override
	public Class<T> obterTipo() {
		return classe;
	}

	@Override
	public boolean estaValido() {
		// TODO: implementar modo de validar regras de negócio: via implementação de métodos com nome do campo + _regra
		return true;
	}

	@Override
	public void apagar() {
		this.elemento = null;
		this.tfElemento.setText("");
	}

}