package org.pspa.gcp.controle.comandos;

import org.pspa.gcp.visao.VisaoCadastros;

public class Insercao<T> implements Comando {

	private T objeto;
	private VisaoCadastros<T> visao;
	
	public Insercao(VisaoCadastros<T> visao, T entidade){
		
		this.objeto = entidade;
		this.visao = visao;
	}
	
	@Override
	public void executar() {
		visao.cadastrar(objeto);
	}

	@Override
	public void desfazer() {
		visao.remover(objeto);
	}

	@Override
	public String obterNome() {
		String nome = objeto.getClass().getCanonicalName();
		
		return "Adição do " + nome.substring(nome.lastIndexOf('.') + 1) + " " + objeto.toString();
	}
}
