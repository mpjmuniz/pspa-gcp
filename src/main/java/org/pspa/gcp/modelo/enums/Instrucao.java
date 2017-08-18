package org.pspa.gcp.modelo.enums;

public enum Instrucao implements Enumeracao{
	Fundamental_Incompleto,
	Fundamental_Completo,
	Medio_Incompleto,
	Medio_Completo,
	Superior_Incompleto,
	Superior_Completo,
	Tecnico,
	Magisterio;
	
	public String toString(){
		return this.name().replace('_', ' ');
	}
}