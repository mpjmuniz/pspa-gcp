package org.pspa.gcp.visao;

import java.util.Stack;

import org.pspa.gcp.controle.comandos.Comando;
import org.pspa.gcp.modelo.Aluno;
import org.pspa.gcp.modelo.Funcionario;
import org.pspa.gcp.modelo.Inscrito;
import org.pspa.gcp.modelo.Turma;
import org.pspa.gcp.visao.Lista.ListaElementos;
import org.pspa.gcp.visao.almoxarifado.VisaoAlmoxarifado;
import org.pspa.gcp.visao.cardapio.VisaoCardapio;
import org.pspa.gcp.visao.declaracoes.VisaoDeclaracoes;
import org.pspa.gcp.visao.dma.VisaoDistribuicaoMensalAtividades;
import org.pspa.gcp.visao.financas.VisaoFinancas;
import org.pspa.gcp.visao.quadroestrutural.VisaoQuadroEstrutural;
import org.pspa.gcp.visao.selecao.Selecao;
import org.pspa.gcp.visao.selecao.SelecaoFabrica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javafx.beans.property.StringProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Apresentador
 * 
 * Classe que controla toda a camada de apresentação
 * 
 * @author marcelo
 * 
 * @version 0.1
 */
@Component
public class Apresentador {
	
	ApplicationContext contextoSpring;
	
	/** base: nó que hospedará todos as outras telas da aplicação */
	private Base base;

	/** palco: ponto de mostra de toda a estrutura de nós */
	private Stage palco;

	/** O apresentador é um singleton, e como tal, aqui está sua instância */
	private static volatile Apresentador instancia;

	private VisaoAlunos va = null;
	private VisaoCadastros<Inscrito> vi = null;
	private VisaoCadastros<Funcionario> vf = null;
	private VisaoCadastros<Turma> vt = null;
	private VisaoCardapio c = null;
	private VisaoAlmoxarifado a = null;
	private VisaoQuadroEstrutural qe = null;
	private VisaoFinancas f = null;
	private VisaoDeclaracoes d = null;
	private VisaoDistribuicaoMensalAtividades dma = null;
	
	Stack<Comando> pilhaComandos = new Stack<>();
	
	/**
	 * Método de obtenção da instância do apresentador, que só deve existir 1
	 * para toda a aplicação
	 * 
	 * @return referência pra o Apresentador
	 */

	public static Apresentador obterInstancia() {

		return instancia;
	}

	@SuppressWarnings("static-access")
	@Autowired
	public Apresentador(ApplicationContext appContext) {
		contextoSpring = appContext;

		this.instancia = this;
	}

	public void apresentar(Stage palcoPrimario) {
		Scene cena;
		Rectangle2D dimensoes;

		// Apresentador apresentador = Apresentador.obterInstancia();

		this.palco = palcoPrimario;

		// Definição da cena
		this.base = new Base();

		
		
		// Definição do palco
		cena = new Scene(base);
		palco.setScene(cena);
		palco.setTitle("Gestor de Creches Paroquiais");

		dimensoes = Screen.getPrimary().getVisualBounds();
		palco.setX(dimensoes.getMinX());
		palco.setY(dimensoes.getMinY());
		palco.setWidth(dimensoes.getWidth());
		palco.setHeight(dimensoes.getHeight());

		palco.show();
	}

	/**
	 * Método para obtenção do palco sendo usado
	 * 
	 * @return palco da aplicação
	 */
	public Window obterPalco() {
		return palco;
	}

	/**
	 * Define a base da aplicação
	 * 
	 * @param janela
	 *            base da aplicação
	 */
	public void setBase(Base janela) {
		this.base = janela;
	}

	/**
	 * Método para se obter o painel central da base da aplicação
	 * 
	 * @return painel central da base
	 */
	protected BorderPane obterPainel() {
		return base.obterPainel();
	}

	/**
	 * Método para mostrar ajuda no nó dedicado para tal
	 * 
	 * @param msg
	 *            mensagem de ajuda para o usuário
	 */
	public void cadastrarAjuda(StringProperty strp) {
		base.disponibilizarAjuda().bind(strp);
	}

	/* Métodos em necessidade de revisão para desacoplamento */

	protected void abrirVisaoInscricao() {

		if (vi == null) {
			vi = new VisaoInscritos(contextoSpring);
		} else {
			vi.atualizar();
		}

		obterPainel().setCenter(vi);
	}

	protected void abrirVisaoAlunos() {
		if (va == null){
			va = new VisaoAlunos(contextoSpring);
		} else {
			va.atualizar();
		}
		obterPainel().setCenter(va);
	}

	protected void abrirVisaoTurmas() {

		if (vt == null){
			vt = new VisaoTurmas(contextoSpring);
		} else {
			vt.atualizar();
		}

		obterPainel().setCenter(vt);
	}

	protected void abrirVisaoFuncionarios() {

		if (vf == null){
			vf = new VisaoFuncionarios(contextoSpring);
		} else {
			vf.atualizar();
		}
		obterPainel().setCenter(vf);
	}

	public void abrirVisaoCardapio() {
		if (c == null) {
			c = new VisaoCardapio(contextoSpring);
		} else {
			c.atualizar();
		}

		obterPainel().setCenter(c);
	}
	
	public void abrirVisaoAlmoxarifado() {
		if (a == null) {
			a = new VisaoAlmoxarifado(contextoSpring);
		} else {
			a.atualizar();
		}

		obterPainel().setCenter(a);
	}
	
	public void abrirVisaoFinancas() {
		if (f == null) {
			f = new VisaoFinancas(contextoSpring);
		} else {
			f.atualizar();
		}

		obterPainel().setCenter(f);
	}
	
	public void abrirVisaoQuadro() {
		if(qe == null){
			qe = new VisaoQuadroEstrutural(contextoSpring);
		} else {
			qe.atualizar();
		}
		
		obterPainel().setCenter(qe);
	}
	
	public void abrirVisaoDeclaracoes() {
		if(d == null){
			d = new VisaoDeclaracoes(contextoSpring);
		} else {
			d.atualizar();
		}
		
		obterPainel().setCenter(d);
	}
	
	public void abrirVisaoDistribuicaoMensalAtividades() {
		if(dma == null){
			dma = new VisaoDistribuicaoMensalAtividades(contextoSpring);
		} else {
			dma.atualizar();
		}
		
		obterPainel().setCenter(dma);
	}
	
	public void adicionarComando(Comando cmd){
		pilhaComandos.push(cmd);
		
		base.atualizarDesfazer();
	}
	
	public void desfazer(){
		Comando cmd = pilhaComandos.pop();
		
		cmd.desfazer();
	}
	
	public String obterUltimo(){
		
		return pilhaComandos.peek().obterNome();
	}
	
	/**
	 * Método para se selecionar um elemento de uma lista
	 * 
	 * @param tf
	 *            campo que guardará nome e referência do elemento
	 */
	@SuppressWarnings("unused")
	public void selecionar(Class<?> cls, TextField tf) {
		SelecaoFabrica fab = new SelecaoFabrica(tf, cls, SelectionMode.SINGLE, contextoSpring);
		Selecao<?> selecao = fab.construirSelecao();
	}

	/**
	 * Método para se selecionar uma sublista de uma lista
	 * 
	 * @param lista
	 *            nó que mostra uma lista, que guardará a lista selecionada
	 */
	@SuppressWarnings("unused")
	public void selecionar(Class<?> cls, ListaElementos<?> lista) {
		SelecaoFabrica fab = new SelecaoFabrica(lista, cls, SelectionMode.MULTIPLE, contextoSpring);
		Selecao<?> selecao = fab.construirSelecao();
	}
	
	/**
	 * Método para se selecionar elementos de uma lista para uma tabela
	 * 
	 * @param lista
	 *            nó que mostra uma lista, que guardará a lista selecionada
	 */
	@SuppressWarnings("unused")
	public void selecionar(Class<?> cls, TableView<?> tabela) {
		SelecaoFabrica fab = new SelecaoFabrica(tabela, cls, SelectionMode.MULTIPLE, contextoSpring);
		Selecao<?> selecao = fab.construirSelecao();
	}

	protected void designarInscricao(Aluno aluno) {
		abrirVisaoAlunos();
		va.obterListagem().definirObjeto(aluno);
		va.novo();
	}
}