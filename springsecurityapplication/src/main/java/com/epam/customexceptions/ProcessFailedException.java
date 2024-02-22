package com.epam.customexceptions;

@SuppressWarnings("serial")
public class ProcessFailedException extends Exception{
	
	public ProcessFailedException(String message){
		super(message);
	}
}
