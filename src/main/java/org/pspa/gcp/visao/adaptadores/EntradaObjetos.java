package org.pspa.gcp.visao.adaptadores;

import org.pspa.gcp.modelo.Participante;
import org.pspa.gcp.modelo.Turma;
import org.pspa.gcp.visao.Apresentador;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class EntradaObjetos<T> extends HBox implements Adaptador<T> {

	private T elemento;
	
	private Participante partc;
	
	private Class<T> classe;
	
	private TextField tfElemento;
	
	public TextField getTfElemento() {
		return tfElemento;
	}

	public void setTfElemento(TextField tfElemento) {
		this.tfElemento = tfElemento;
	}

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

		bElemento.setOnAction(e -> apresentador.selecionar(this));
		this.getChildren().addAll(tfElemento, bElemento);
		this.setPadding(new Insets(5));

	}
	
	public Participante getPartc() {
		return partc;
	}

	public void setPartc(Participante partc) {
		this.partc = partc;
	}

	@Override
	public T obterValor() {
		return elemento;
	}

	@Override
	public void definirValor(T valor) {
		this.elemento = valor;
		this.partc.setTurma((Turma) valor);
		tfElemento.setText((valor == null) ? "Nenhum" : valor.toString());
	}

	@Override
	public Class<T> obterTipo() {
		return classe;
	}

	@Override
	public boolean estaValido() {
		return true;
	}

	@Override
	public void apagar() {
		this.elemento = null;
		this.tfElemento.setText("");
	}

}