package org.pspa.gcp.modelo.enums;

public enum CondicaoLocal implements Enumeracao{
	Beira_de_rio, Sem_Pavimentacao, Proximo_a_Valas, Proximo_a_Lixo;
	
	public String toString(){
		return this.name().replace('_', ' ');
	}
}
