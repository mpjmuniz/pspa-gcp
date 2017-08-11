package org.pspa.gcp.visao;

import static org.pspa.gcp.visao.util.StringUtils.identificarNome;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import org.pspa.gcp.modelo.enums.Enumeracao;
import org.pspa.gcp.visao.adaptadores.Adaptador;
import org.pspa.gcp.visao.adaptadores.EntradaBooleanos;
import org.pspa.gcp.visao.adaptadores.EntradaDatas;
import org.pspa.gcp.visao.adaptadores.EntradaEnums;
import org.pspa.gcp.visao.adaptadores.EntradaInteiros;
import org.pspa.gcp.visao.adaptadores.EntradaListas;
import org.pspa.gcp.visao.adaptadores.EntradaObjetos;
import org.pspa.gcp.visao.adaptadores.EntradaTexto;
import org.pspa.gcp.visao.adaptadores.Identificador;
import org.pspa.gcp.visao.util.ReflectUtils;
import org.pspa.gcp.visao.util.StringUtils;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * @author marcelo
 * @version 0.2
 * 
 *          Listagem de campos de um dado tipo @param T.
 * 
 *          inspeciona, via reflexão, os campos membros de T, e gera um campo na
 *          interface, específico para cada tipo de campo membro.
 * 
 * 
 */

public class ListagemCampos<T> extends GridPane {

	/**
	 * a classe sendo inspecionada
	 */
	private Class<T> classe;

	/**
	 * uma instância da classe, que será listada
	 */
	private T objeto;

	/**
	 * Construtor
	 * 
	 * cria o grid e preenche com os campos, todos vazios
	 */
	public ListagemCampos(Class<T> cls) {
		super();
		
		// this.setGridLinesVisible(true);
		this.setHgap(10);
		this.setVgap(10);

		this.descobrirCampos(cls);
	}

	/**
	 * atribui à listagem um objeto para ser descrito
	 * 
	 * @param objeto:
	 *            objeto à ser descrito
	 */
	protected void definirObjeto(T objeto) {
		this.objeto = objeto;

		if(objeto != null) descreverObjeto();
	}

	/**
	 * obtém o objeto sendo descrito
	 * 
	 * @return o campo-membro objeto.
	 */
	protected T obterObjeto() {

		return this.objeto;
	}

	/**
	 * Obtém um nó na posição especificada, no grid
	 * 
	 * @param linha:
	 *            linha no grid
	 * @param coluna:
	 *            coluna no grid
	 * 
	 * @return nó na posição dada
	 */
	protected Adaptador<?> obterNoPelaPosicao(int linha, int coluna) {
		Node no = null;

		for (Node node : this.getChildren()) {
			
			if (GridPane.getColumnIndex(node) == coluna 
					&& GridPane.getRowIndex(node) == linha) {
				
				no = node;
				break;
			}
		}
		
		return (Adaptador<?>) no;
	}

	// Construção de cena

	/**
	 * preenche o grid com os campos e seus nomes, em ordem de declaração
	 * 
	 * @param classe:
	 *            classe à ser inspecionada
	 */
	private void descobrirCampos(Class<T> classe) {
		
		this.classe = classe;
		
		Field[] campos = ReflectUtils.getInstanceVariables(classe);

		for (int i = 0; i < campos.length; i++) {
			
			if(!campos[i].isAnnotationPresent(GeneratedValue.class)){
				this.addRow(i, 
						new Identificador(identificarNome(campos[i].getName())), 
						identificarCampo(campos[i])
						);
			}
		}
	}

	/**
	 * obtém um campo apropriado para se preencher um valor de um dado campo
	 * 
	 * @param campo:
	 *            campo à ser decidido
	 * 
	 * @return nó, encapsulado no supertipo Node apropriado para se preencher o
	 *         tipo do campo passado.
	 **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Node identificarCampo(Field campo) {
		try {
			Node no = null;

			if(campo.getType() == Integer.class){
				
				no = new EntradaInteiros();
				
			} else if (campo.getType() == String.class) {

				no = new EntradaTexto();

			} else if (campo.getType() == LocalDate.class) {

				no = new EntradaDatas();

			} else if (campo.getType() == Boolean.class) {
				
				no = new EntradaBooleanos();

			} else if (campo.getType().isEnum()) {

				no = new EntradaEnums((Class< ? extends Enumeracao>) campo.getType());

			} else if (campo.getType() == List.class) {
				
				no = new EntradaListas(campo.getGenericType(), classe);

			} else if (campo.getType().isAnnotationPresent(Entity.class)) {

				no = new EntradaObjetos(campo.getType(), classe);
				
			} else {
				throw new RuntimeException(
						"problema com o campo: " 
						+ campo.getName() 
						+ ", do tipo : " 
						+ campo.getType().getSimpleName());
			}
			
			no.getProperties().put("style", no.getStyle());

			return no;
			
		} catch (IllegalArgumentException 
				 | SecurityException 
				 | NoSuchMethodException 
				 | IllegalAccessException
				 | InvocationTargetException
				 | ClassNotFoundException e) {
			
			throw new RuntimeException("Problema na reflexão, de fora: " 
									   + e.getLocalizedMessage());
		}
	}

	// manutenção do objeto

	/**
	 * constrói um objeto novo à partir dos campos preenchidos na listagem.
	 * 
	 * objeto é disponibilizado pelo campo objeto, desta classe.
	 */
	@SuppressWarnings("unchecked")
	protected void construirObjeto() {

		Class<T> cls = this.classe;
		
		try {

			if (this.objeto == null){
				this.objeto = this.classe.newInstance();
			}

				String nome;
				Method setter;
				Adaptador<String> rotulo;
				Adaptador<?> campo;

				// 1 para pular o MID
				for (int i = 1; i < ReflectUtils.getInstanceVariables(cls).length; i++) {
					
					rotulo = (Adaptador<String>) obterNoPelaPosicao(i, 0);
					campo = obterNoPelaPosicao(i, 1);

					// Pensar como implantar input de listas
					
					nome = rotulo.obterValor();
					nome = StringUtils.reverterNome(nome);
					
					setter = cls.getMethod("set" + nome, campo.obterTipo());
					setter.invoke(objeto, campo.obterValor());

				}

		} catch (InstantiationException 
					| SecurityException 
					| NoSuchMethodException 
					| IllegalArgumentException
					| InvocationTargetException 
					| IllegalAccessException e) {

			System.out.println("Problema na reflexão, construção do objeto: " + e.getMessage());
		}
	}

	/**
	 * descreve os valores do objeto atual nos campos da listagem
	 */
	@SuppressWarnings("unchecked")
	private void descreverObjeto() {
		if (this.objeto == null)
			throw new IllegalArgumentException("é necessário escolher um item para ser descrito");
		
		try {
			String nome;
			Method getter;
			Adaptador<Object> campo;
			Adaptador<String> rotulo;
			Object valor;

			for (int i = 1; i < ReflectUtils.getInstanceVariables(classe).length; i++) {
				rotulo = (Adaptador<String>) obterNoPelaPosicao(i, 0);
				campo = (Adaptador<Object>) obterNoPelaPosicao(i, 1);

				nome = rotulo.obterValor();
				nome = StringUtils.reverterNome(nome);
				
				if(campo instanceof EntradaObjetos){
					((EntradaObjetos<?, ?>) campo).setDono(objeto);
				} else if(campo instanceof EntradaListas){
					((EntradaListas<?, ?>) (Object) campo).setDono(objeto);
				}
				
				getter = this.classe.getMethod("get" + nome);
				valor = getter.invoke(objeto);
				campo.definirValor(valor);

			}

		} catch (IllegalAccessException | SecurityException | NoSuchMethodException | IllegalArgumentException
				| InvocationTargetException e) {

			System.out.println("Problema na reflexão, descrição do objeto: " + e.getMessage());
		}
	}

	/**
	 * verifica validade nos campos do grid
	 */
	protected boolean dadosEstaoValidos() {
		boolean validade = true;

		Adaptador<?> no = null;
		
		for (int i = 1; i < ReflectUtils.getInstanceVariables(classe).length; i++) {
			try {
				no = obterNoPelaPosicao(i, 1);
				
				if(!no.estaValido()){
					validade = false;
					no.setStyle("-fx-border-style: solid inside;" + "-fx-border-color: red;");
				} else {
					no.setStyle((String) no.getProperties().get("style"));
				}
			} catch (NumberFormatException e) {
				validade = false;
				no.setStyle("-fx-border-style: solid inside;" + "-fx-border-color: red;");
			}
		}

		return validade;
	}
	
	protected void limpar() {

		Adaptador<?> no = null;
		
		for (int i = 1; i < ReflectUtils.getInstanceVariables(classe).length; i++) {
			
			no = obterNoPelaPosicao(i, 1);
			
			no.apagar();
			
			no.setStyle((String) no.getProperties().get("style"));
		
		}

	}
}
