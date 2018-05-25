package com.adminportal.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryConstants {
	
	public final static String CON = "CON";
	
	public final static Map<String, String> mapOfCountries = new HashMap<String, String>(){
		{
			put("United States", "United States");
			put("Australia", "Australia");
			put("Bangladesh", "Bangladesh");
			put("Canada", "Canada");
			put("United Kingdom", "United Kingdom");
		}
	};
	
	public final static List<String> listOfCountryCode = new ArrayList<>(mapOfCountries.keySet());
	public final static List<String> listOfCountryName = new ArrayList<>(mapOfCountries.values());
	

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static String randomAlphaNumeric(int count) {
	StringBuilder builder = new StringBuilder();
	while (count-- != 0) {
	int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
	builder.append(ALPHA_NUMERIC_STRING.charAt(character));
	}
	return builder.toString();
	}
}
