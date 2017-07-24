package org.pspa.gcp.modelo.enums;

public enum Grupamento implements Enumeracao{
	Maternal_1, Maternal_2;
	
	public String toString(){
		return this.name().replace('_', ' ');
	}
}
