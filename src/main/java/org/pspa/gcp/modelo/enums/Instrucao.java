package org.pspa.gcp.modelo.enums;

public enum Instrucao implements Enumeracao{
	Fundamental_Incompleto,
	Fundamental_Completo,
	Médio_Incompleto,
	Médio_Completo,
	Superior_Incompleto,
	Superior_Completo,
	Técnico,
	Magistério;
	
	public String toString(){
		return this.name().replace('_', ' ');
	}
}