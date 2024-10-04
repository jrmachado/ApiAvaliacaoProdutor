package br.com.jrm.test.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

	public static List<String> splitStringAndOuVirgula(String input) {
		// Substitui " and " por "," para facilitar a separação
		String modifiedInput = input.replace(" and ", ",");

		// Divide a string por ","
		String[] nameArray = modifiedInput.split(",");

		// Remove espaços em branco e add na lista
		List<String> nameList = new ArrayList<>();
		for (String name : nameArray) {
			nameList.add(name.trim());
		}

		return nameList;
	}
}
