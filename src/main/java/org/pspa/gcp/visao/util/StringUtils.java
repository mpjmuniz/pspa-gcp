package org.pspa.gcp.visao.util;

public class StringUtils {

	public static String capitalizar(String entrada) {

		if(entrada == null || entrada == ""){
			return "";
		}
		
		char[] ca = entrada.toCharArray();
		ca[0] = Character.toUpperCase(ca[0]);

		return new String(ca);
	}
	
	public static String diminuir(String entrada) {

		if(entrada == null || entrada == ""){
			return "";
		}
		
		char[] ca = entrada.toCharArray();
		ca[0] = Character.toLowerCase(ca[0]);

		return new String(ca);
	}

	public static String identificarNome(String nome) {
		if (nome == null || nome == "")
			return "";

		String[] partes = nome.split("_");
		String atual;
		
		for (int i = 0; i < partes.length; i++) {
			atual = partes[i];
			
			if ((!"de".equals(atual)) && (!"com".equals(atual)) && (!"da".equals(atual))) {
				partes[i] = capitalizar(atual);
			}
		}

		return String.join(" ", partes);
	}

	public static String reverterNome(String nome) {
		if (nome == null || nome == "")
			return "";

		String[] partes = nome.split(" ");
		String atual;
		
		boolean primeiro = true;

		for (int i = 0; i < partes.length; i++) {
			atual = partes[i];
			
			if (!"de".equals(atual) && !"da".equals(atual) && !"com".equals(atual) && !primeiro) {
				if (atual.length() > 1 && Character.isLowerCase(atual.charAt(1)))
					partes[i] = diminuir(atual);
			}

			if (primeiro)
				primeiro = false;
		}

		return String.join("_", partes);
	}
}
