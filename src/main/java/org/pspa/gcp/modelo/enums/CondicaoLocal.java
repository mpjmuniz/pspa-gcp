package org.pspa.gcp.modelo.enums;

public enum CondicaoLocal implements Enumeracao{
	Beira_de_rio, Sem_Pavimentação, Próximo_a_Valas, Próximo_a_Lixo;
	
	public String toString(){
		return this.name().replace('_', ' ');
	}
}
