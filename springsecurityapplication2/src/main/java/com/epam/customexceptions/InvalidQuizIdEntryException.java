package com.epam.customexceptions;

@SuppressWarnings("serial")
public class InvalidQuizIdEntryException extends Exception {

	@Override
	public String getMessage() {
		return "Warning!! The entered Quiz Id is not present in the data base. Please enter a valid Quiz Id.";
	}
}
