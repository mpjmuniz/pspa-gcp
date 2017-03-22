package org.pspa.gcp.visao.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

public class ReflectUtils {

	public static String obterNome(Object item) {
		if (item == null || item == "")
			return "";

		String nome = item.getClass().getName();

		return nome.substring(nome.lastIndexOf('.') + 1) + "s";
	}

	public static Field[] getInstanceVariables(Class<?> cls) {
		List<Field> accum = new LinkedList<>();
		while (cls != null) {
			Field[] fields = cls.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				if (!Modifier.isStatic(fields[i].getModifiers())) {
					accum.add(fields[i]);
				}
			}
			cls = cls.getSuperclass();
		}
		Field[] retvalue = new Field[accum.size()];
		return (Field[]) accum.toArray(retvalue);
	}
}
