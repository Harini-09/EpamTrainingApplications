package com.epam.customexceptions;

@SuppressWarnings("serial")
public class InvalidQuestionIdEntryException extends Exception {

	@Override
	public String getMessage() {
		return "Warning!! The given Question id is not present in the data base. Please enter a valid question id.";
	}
}
