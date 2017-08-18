package org.pspa.gcp.visao.selecao;

import org.pspa.gcp.modelo.Funcionario;
import org.pspa.gcp.modelo.Turma;
import org.pspa.gcp.modelo.repositorios.RepositorioFuncionario;
import org.pspa.gcp.visao.Apresentador;
import org.pspa.gcp.visao.adaptadores.EntradaObjetos;
import org.springframework.context.ApplicationContext;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.SelectionMode;

public class SelecaoUnicaFuncionario extends Selecao<Funcionario> {

	
	/** campo que auxilia a recuperação do elemento selecionado na árvore*/
	private EntradaObjetos<Funcionario, Turma> campoAuxiliar;
	
	/** campo que guarda o objeto sendo alterado */
	private Turma referencia;
	
	/** 
	 * Construtor para campos
	 * 
	 * @param campoAuxiliar nó JavaFX (TextField) que ajuda a recuperar o elemento selecionado
	 **/
	@SuppressWarnings("unchecked")
	public SelecaoUnicaFuncionario(ApplicationContext contextoSpring, EntradaObjetos<?, ?> eObjetos){
		super(Funcionario.class, SelectionMode.SINGLE, contextoSpring);
		this.campoAuxiliar = (EntradaObjetos<Funcionario, Turma>) eObjetos;
		this.referencia = campoAuxiliar.getDono();
		
		lvCadastros.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		bOk.setOnAction(e -> this.designarItem());
		sSobreposto.setOnCloseRequest(e -> this.designarItem());
		
	}
	
	@Override
	protected void definirRepositorio(){
		repositorio = contextoSpring.getBean(RepositorioFuncionario.class);
	}
	
	/**
	 * 	destina o item selecionado para o campo definido na criação da seleção
	 * 
	 * 	uso exclusivo para seleção de campos, onde {@code modo_seleção} = 0
	 * */
	private void designarItem() {
		Funcionario selecao = lvCadastros.getSelectionModel().getSelectedItem();
		
		if(campoAuxiliar != null){
			campoAuxiliar.definirValor(selecao);
		} else	{
			throw new RuntimeException("campo auxiliar não declarado");
		}
		
		try {
		
		if(selecao != null) {
			campoAuxiliar.getTfElemento().setText(selecao.toString());
			
			referencia.setProfessor(selecao);
		} else {
			campoAuxiliar.getTfElemento().setText("Nenhum(a)");
		}
		
		} catch(Exception e) {
			
			Apresentador ap = Apresentador.obterInstancia();
			
			ap.cadastrarAjuda(new SimpleStringProperty(e.getMessage()));
			
			ap.descadastrarAjuda();
			
			campoAuxiliar.getTfElemento().setText("Nenhum(a)");
		}
		
		
		sSobreposto.close();
	}
}
