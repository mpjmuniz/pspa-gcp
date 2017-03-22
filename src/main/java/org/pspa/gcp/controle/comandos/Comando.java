package org.pspa.gcp.controle.comandos;

public interface Comando {
	public void executar();
	
	public void desfazer();
	
	public String obterNome();
}
