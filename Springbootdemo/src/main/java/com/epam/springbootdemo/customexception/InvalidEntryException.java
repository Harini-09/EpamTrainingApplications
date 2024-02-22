package com.epam.springbootdemo.customexception;

@SuppressWarnings("serial")
public class InvalidEntryException extends RuntimeException{
    @Override
    public String getMessage() {
    	return "Invalid Entry!!...Please Enter Valid Entry";
    }
}
