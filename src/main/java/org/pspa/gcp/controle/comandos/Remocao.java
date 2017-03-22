package org.pspa.gcp.controle.comandos;

import org.pspa.gcp.visao.VisaoCadastros;

public class Remocao<T> implements Comando {

	private T objeto;
	private VisaoCadastros<T> visao;
	
	public Remocao(VisaoCadastros<T> visao, T entidade){
		
		this.objeto = entidade;
		this.visao = visao;
	}
	
	@Override
	public void executar() {
		visao.remover(objeto);
	}

	@Override
	public void desfazer() {
		visao.cadastrar(objeto);
	}

	@Override
	public String obterNome() {
		String nome = objeto.getClass().getCanonicalName();
		
		return "Remoção do " + nome.substring(nome.lastIndexOf('.') + 1) + " " + objeto.toString();
	}
}
