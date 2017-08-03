package org.pspa.gcp.visao.selecao;

import java.util.List;

import org.pspa.gcp.modelo.Aluno;
import org.pspa.gcp.modelo.Funcionario;
import org.pspa.gcp.modelo.Turma;
import org.pspa.gcp.modelo.repositorios.RepositorioFuncionario;
import org.pspa.gcp.visao.adaptadores.EntradaListas;
import org.springframework.context.ApplicationContext;

import javafx.scene.control.SelectionMode;

public class SelecaoMultiplaFuncionarios extends Selecao<Funcionario> {

	/** campo que auxilia a recuperação do elemento selecionado na árvore*/
	private EntradaListas<Funcionario, Turma> campoAuxiliar;
	
	/** campo que guarda o objeto sendo alterado */
	private Turma referencia;
	
	/** 
	 * Construtor para campos
	 * 
	 * @param campoAuxiliar nó JavaFX (TextField) que ajuda a recuperar o elemento selecionado
	 **/
	
	@SuppressWarnings("unchecked")
	protected SelecaoMultiplaFuncionarios(ApplicationContext contexto, EntradaListas<?, ?> eLsts) {
		super(Aluno.class, SelectionMode.MULTIPLE, contexto);
		this.campoAuxiliar = (EntradaListas<Funcionario, Turma>) eLsts;
		this.referencia = campoAuxiliar.getDono();
		
		lvCadastros.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		bOk.setOnAction(e -> this.designarLista());
		sSobreposto.setOnCloseRequest(e -> this.designarLista());
	}
	
	@Override
	protected void popularVisao() {
		this.lvCadastros.getItems().addAll(repositorio.findAll());
		
		List<Funcionario> atuais = ((RepositorioFuncionario) repositorio).findFuncionariosByTurma(referencia);
		
		for(Funcionario f : lvCadastros.getItems()){
			if(atuais.contains(f)) this.lvCadastros.getSelectionModel().select(f);
		}
		
	}
	
	@Override
	protected void definirRepositorio() {
		repositorio = contextoSpring.getBean(RepositorioFuncionario.class);
	}

	/**
	 * 	destina os itens selecionados para o campo definido na criação da seleção
	 * 
	 * */
	private void designarLista() {
		List<Funcionario> selecao = lvCadastros.getSelectionModel().getSelectedItems();
		
		if(campoAuxiliar != null){
			campoAuxiliar.definirValor(selecao);
		} else	{
			throw new RuntimeException("campo auxiliar não declarado");
		}
		
		if(selecao != null) {
			referencia.setAuxiliares(selecao);
		}
		
		sSobreposto.close();
	}
	
}
