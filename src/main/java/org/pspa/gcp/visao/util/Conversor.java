package org.pspa.gcp.visao.util;

import javafx.util.StringConverter;

public class Conversor extends StringConverter<Integer>{

	@Override
	public String toString(Integer object) {
		return String.format("%1$d", object);
	}

	@Override
	public Integer fromString(String string) {
		return Integer.parseInt(string);
	}
	
}