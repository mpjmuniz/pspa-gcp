package org.pspa.gcp.controle.comandos;

import org.pspa.gcp.visao.VisaoCadastros;

public class Alteracao<T> implements Comando {

	private T objetoAnterior;
	private T objetoPosterior;
	private VisaoCadastros<T> visao;
	
	public Alteracao(VisaoCadastros<T> visao, T entidadeAnterior, T entidadePosterior){
		
		this.objetoAnterior = entidadeAnterior;
		this.objetoPosterior = entidadePosterior;
		this.visao = visao;
	}
	
	@Override
	public void executar() {
		visao.alterar(objetoAnterior, objetoPosterior);
	}

	@Override
	public void desfazer() {
		visao.alterar(objetoPosterior, objetoAnterior);
	}

	@Override
	public String obterNome() {
		String nome = objetoAnterior.getClass().getCanonicalName();
		
		return "Alteração do " + nome.substring(nome.lastIndexOf('.') + 1) + " " + objetoAnterior.toString();
	}
}
