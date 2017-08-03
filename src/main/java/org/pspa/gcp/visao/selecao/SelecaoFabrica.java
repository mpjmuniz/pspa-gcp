package org.pspa.gcp.visao.selecao;

import org.pspa.gcp.modelo.Aluno;
import org.pspa.gcp.modelo.Funcionario;
import org.pspa.gcp.modelo.Produto;
import org.pspa.gcp.modelo.Turma;
import org.pspa.gcp.visao.adaptadores.EntradaListas;
import org.pspa.gcp.visao.adaptadores.EntradaObjetos;
import org.springframework.context.ApplicationContext;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;

public class SelecaoFabrica {
	private EntradaListas<?, ?> eListas;
	
	private TableView<?> tvElementos;
	
	private Class<?> classe;
	
	private SelectionMode modo;
	
	private ApplicationContext contextoSpring;
	
	private EntradaObjetos<?, ?> eObjetos; 
	
	public SelecaoFabrica(EntradaListas<?, ?> leElementos, EntradaObjetos<?, ?> eObjetos, TableView<?> tv, Class<?> classe, SelectionMode modo,
			ApplicationContext contextoSpring) {
		super();
		this.eObjetos = eObjetos;
		this.tvElementos = tv;
		this.eListas = leElementos;
		this.classe = classe;
		this.modo = modo;
		this.contextoSpring = contextoSpring;
	}

	public SelecaoFabrica(EntradaListas<?, ?> leElementos, SelectionMode modo,
			ApplicationContext contextoSpring) {
		this(leElementos, null, null, leElementos.obterTipo(), modo, contextoSpring);
	}
	
	public SelecaoFabrica(EntradaObjetos<?, ?> eObjs, SelectionMode modo,
			ApplicationContext contextoSpring) {
		this(null, eObjs, null, eObjs.obterTipo(), modo, contextoSpring);
	}
	
	public SelecaoFabrica(TableView<?> tvElementos, Class<?> classe, SelectionMode modo,
			ApplicationContext contextoSpring) {
		this(null, null, tvElementos, classe, modo, contextoSpring);
	}

	@SuppressWarnings("unchecked")
	public Selecao<?> construirSelecao(Object referencia){
		Selecao<?> selecao = null;
		
		if(modo == null){
			throw new IllegalArgumentException("Defina o modo de seleção");
		}
		
		if(modo == SelectionMode.SINGLE){
			if(eObjetos == null){
				throw new IllegalArgumentException("Defina a entrada de objetos que mostrará o item selecionado.");
			} else {
				if(Turma.class.equals(classe)){
					selecao = new SelecaoUnicaTurma(contextoSpring, eObjetos);
				} else if(Funcionario.class.equals(classe)){
					selecao = new SelecaoUnicaFuncionario(contextoSpring, eObjetos);
				}
			}
		} else {
			if(eListas == null && tvElementos == null){
				throw new IllegalArgumentException("Defina a lista ou tabela que receberá os elementos"); 
			} else{
				if(Produto.class.equals(classe)){
					selecao = new SelecaoTabelaProdutos(contextoSpring, (TableView<Produto>) tvElementos);
				} else if(Aluno.class.equals(eListas.obterTipoArgumento())){
					selecao = new SelecaoMultiplaAlunos(contextoSpring, eListas);
				} else if(Funcionario.class.equals(eListas.obterTipoArgumento())){
					selecao = new SelecaoMultiplaFuncionarios(contextoSpring, eListas);
				}
			}
		}
		
		return selecao;
	}	
}