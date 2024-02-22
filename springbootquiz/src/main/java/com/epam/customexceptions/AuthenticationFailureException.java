package com.epam.customexceptions;

@SuppressWarnings("serial")
public class AuthenticationFailureException extends Exception {

	public AuthenticationFailureException(String message) {
		super(message);
	}
}
