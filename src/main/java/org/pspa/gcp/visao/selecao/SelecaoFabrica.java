package org.pspa.gcp.visao.selecao;

import org.pspa.gcp.modelo.Aluno;
import org.pspa.gcp.modelo.Produto;
import org.pspa.gcp.modelo.Turma;
import org.pspa.gcp.visao.Lista.ListaElementos;
import org.springframework.context.ApplicationContext;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SelecaoFabrica {
	private ListaElementos<?> leElementos;
	
	private TableView<?> tvElementos;
	
	private TextField tfElemento;
	
	private Class<?> classe;
	
	private SelectionMode modo;
	
	private ApplicationContext contextoSpring;
	
	public SelecaoFabrica(ListaElementos<?> leElementos, TextField tfElemento, TableView<?> tv, Class<?> classe, SelectionMode modo,
			ApplicationContext contextoSpring) {
		super();
		this.tvElementos = tv;
		this.leElementos = leElementos;
		this.tfElemento = tfElemento;
		this.classe = classe;
		this.modo = modo;
		this.contextoSpring = contextoSpring;
	}

	public SelecaoFabrica(ListaElementos<?> leElementos, Class<?> classe, SelectionMode modo,
			ApplicationContext contextoSpring) {
		this(leElementos, null, null, classe, modo, contextoSpring);
	}
	
	public SelecaoFabrica(TextField tfElemento, Class<?> classe, SelectionMode modo,
			ApplicationContext contextoSpring) {
		this(null, tfElemento, null, classe, modo, contextoSpring);
	}
	
	public SelecaoFabrica(TableView<?> tvElementos, Class<?> classe, SelectionMode modo,
			ApplicationContext contextoSpring) {
		this(null, null, tvElementos, classe, modo, contextoSpring);
	}

	@SuppressWarnings("unchecked")
	public Selecao<?> construirSelecao(){
		Selecao<?> selecao = null;
		
		if(modo == null){
			throw new IllegalArgumentException("Defina o modo de seleção");
		}
		
		if(modo == SelectionMode.SINGLE){
			if(tfElemento == null){
				throw new IllegalArgumentException("Defina o textfield que mostrará o item selecionado.");
			} else {
				if(Turma.class.equals(classe)){
					selecao = new SelecaoUnicaTurma(contextoSpring, tfElemento);
				}
			}
		} else {
			if(leElementos == null && tvElementos == null){
				throw new IllegalArgumentException("Defina a lista ou tabela que receberá os elementos"); 
			} else{
				if(leElementos != null){
					if(Aluno.class.equals(classe)){
						selecao = new SelecaoMultiplaAlunos(contextoSpring, (ListaElementos<Aluno>) leElementos);
					}
				} else {
					if(Produto.class.equals(classe)){
						selecao = new SelecaoTabelaProdutos(contextoSpring, (TableView<Produto>) tvElementos);
					}
				}
			}
		}
		
		return selecao;
	}	
}
