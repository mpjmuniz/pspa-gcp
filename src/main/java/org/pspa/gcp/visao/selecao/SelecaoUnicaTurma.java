package org.pspa.gcp.visao.selecao;

import org.pspa.gcp.modelo.Participante;
import org.pspa.gcp.modelo.Turma;
import org.pspa.gcp.modelo.repositorios.RepositorioTurma;
import org.pspa.gcp.visao.adaptadores.EntradaObjetos;
import org.springframework.context.ApplicationContext;

import javafx.scene.control.SelectionMode;

public class SelecaoUnicaTurma extends Selecao<Turma> {

	/** campo que auxilia a recuperação do elemento selecionado na árvore*/
	private EntradaObjetos<Turma> campoAuxiliar;
	
	/** campo que guarda o objeto sendo alterado */
	private Participante referencia;
	
	/** 
	 * Construtor para campos
	 * 
	 * @param campoAuxiliar nó JavaFX (TextField) que ajuda a recuperar o elemento selecionado
	 **/
	@SuppressWarnings("unchecked")
	public SelecaoUnicaTurma(ApplicationContext contextoSpring, EntradaObjetos<?> eObjetos){
		super(Turma.class, SelectionMode.SINGLE, contextoSpring);
		this.campoAuxiliar = (EntradaObjetos<Turma>) eObjetos;
		this.referencia = eObjetos.getPartc();
		
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
		
		if(selecao != null) {
			campoAuxiliar.getTfElemento().setText(selecao.toString());
			
			referencia.setTurma(selecao);
		} else {
			campoAuxiliar.getTfElemento().setText("Nenhum(a)");
		}
		
		sSobreposto.close();
	}
	
	// TODO: pensar num modo de passar turma para objeto (funcionário ou aluno)
}
