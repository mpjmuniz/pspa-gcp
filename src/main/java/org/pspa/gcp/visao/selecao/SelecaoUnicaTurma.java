package org.pspa.gcp.visao.selecao;

import org.pspa.gcp.modelo.Participante;
import org.pspa.gcp.modelo.Turma;
import org.pspa.gcp.modelo.repositorios.RepositorioTurma;
import org.pspa.gcp.visao.Apresentador;
import org.pspa.gcp.visao.adaptadores.EntradaObjetos;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javafx.scene.control.SelectionMode;
import javafx.beans.property.SimpleStringProperty;

public class SelecaoUnicaTurma extends Selecao<Turma> {

	// TODO: ajustar entrada de objetos para se adaptar ao objeto dono
	
	/** campo que auxilia a recuperação do elemento selecionado na árvore*/
	private EntradaObjetos<Turma, Participante> campoAuxiliar;
	
	/** campo que guarda o objeto sendo alterado */
	private Participante referencia;
	
	/** 
	 * Construtor para campos
	 * 
	 * @param campoAuxiliar nó JavaFX (TextField) que ajuda a recuperar o elemento selecionado
	 **/
	@SuppressWarnings("unchecked")
	public SelecaoUnicaTurma(ApplicationContext contextoSpring, EntradaObjetos<?, ?> eObjetos){
		super(Turma.class, SelectionMode.SINGLE, contextoSpring);
		this.campoAuxiliar = (EntradaObjetos<Turma, Participante>) eObjetos;
		this.referencia = campoAuxiliar.getDono();
		
		lvCadastros.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		bOk.setOnAction(e -> this.designarItem());
		sSobreposto.setOnCloseRequest(e -> this.designarItem());
		
	}
	
	@Override
	protected void definirRepositorio(){
		repositorio = contextoSpring.getBean(RepositorioTurma.class);
	}
	
	/**
	 * 	destina o item selecionado para o campo definido na criação da seleção
	 * 
	 * 	uso exclusivo para seleção de campos, onde {@code modo_seleção} = 0
	 * */
	private void designarItem() {
		Turma selecao = lvCadastros.getSelectionModel().getSelectedItem();
		
		if(campoAuxiliar != null){
			campoAuxiliar.definirValor(selecao);
		} else	{
			throw new RuntimeException("campo auxiliar não declarado");
		}
		
		try {
		
		if(selecao != null) {
			campoAuxiliar.getTfElemento().setText(selecao.toString());
			
			referencia.setTurma(selecao);
		} else {
			campoAuxiliar.getTfElemento().setText("Nenhum(a)");
		}
		
		} catch(IllegalArgumentException e) {
			Apresentador ap = Apresentador.obterInstancia();
			
			ap.cadastrarAjuda(new SimpleStringProperty(e.getMessage()));
			
			ap.descadastrarAjuda();
			
			campoAuxiliar.getTfElemento().setText("Nenhum(a)");
			
		} finally {
			sSobreposto.close();
		}
		
		
	}
	
	// TODO: pensar num modo de passar turma para objeto (funcionário ou aluno)
}
