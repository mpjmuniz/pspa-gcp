package org.pspa.gcp.visao.selecao;

import java.util.List;

import org.pspa.gcp.modelo.Aluno;
import org.pspa.gcp.modelo.Turma;
import org.pspa.gcp.modelo.repositorios.RepositorioAluno;
import org.pspa.gcp.visao.adaptadores.EntradaListas;
import org.springframework.context.ApplicationContext;

import javafx.scene.control.SelectionMode;

/** Classe responsável por selecionar vários alunos de um repositório*/
public class SelecaoMultiplaAlunos extends Selecao<Aluno> {

	/** campo que auxilia a recuperação do elemento selecionado na árvore*/
	private EntradaListas<Aluno, Turma> campoAuxiliar;
	
	/** campo que guarda o objeto sendo alterado */
	private Turma referencia;
	
	/** 
	 * Construtor para campos
	 * 
	 * @param campoAuxiliar nó JavaFX (TextField) que ajuda a recuperar o elemento selecionado
	 **/
	
	@SuppressWarnings("unchecked")
	protected SelecaoMultiplaAlunos(ApplicationContext contexto, EntradaListas<?, ?> eLsts) {
		super(Aluno.class, SelectionMode.MULTIPLE, contexto);
		this.campoAuxiliar = (EntradaListas<Aluno, Turma>) eLsts;
		this.referencia = campoAuxiliar.getDono();
		
		lvCadastros.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		bOk.setOnAction(e -> this.designarLista());
		sSobreposto.setOnCloseRequest(e -> this.designarLista());
	}

	@Override
	protected void definirRepositorio() {
		repositorio = contextoSpring.getBean(RepositorioAluno.class);
	}

	/**
	 * 	destina os itens selecionados para o campo definido na criação da seleção
	 * 
	 * */
	private void designarLista() {
		List<Aluno> selecao = lvCadastros.getSelectionModel().getSelectedItems();
		
		if(campoAuxiliar != null){
			campoAuxiliar.definirValor(selecao);
		} else	{
			throw new RuntimeException("campo auxiliar não declarado");
		}
		
		if(selecao != null) {
			referencia.setAlunos(selecao);
		}
		
		sSobreposto.close();
	}
}
