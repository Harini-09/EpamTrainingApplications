package com.epam.junitpractise;

public class SwapLast2Chars {
	public String swap(String input) {
		String result = input;
		int length = input.length();
		if(length>=2) {
			String lastchar = Character.toString(input.charAt(length-1));
			String secondlastchar = Character.toString(input.charAt(length-2));
			result = input.substring(0,length-2).concat(lastchar).concat(secondlastchar);
		}
		return result;
	}
}
