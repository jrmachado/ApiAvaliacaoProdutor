package br.com.jrm.test.util;

import java.util.regex.Pattern;

public class NumberCheck {
	private static final Pattern INTEGER_PATTERN = Pattern.compile("-?\\d+");

	public static boolean isInteger(String str) {
		return str != null && INTEGER_PATTERN.matcher(str).matches();
	}

}
