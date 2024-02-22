package com.epam.userinterface;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.customexceptions.InvalidEntryException;

@Component
public class QuizApplication {
	
	@Autowired
	private AdminDashBoard adminPortal;
	
	@Autowired
	private SubUserDashBoard userPortal;
		
	@Autowired
	Scanner scanner;
	
	private final Logger logger = LogManager.getLogger(QuizApplication.class);
	
	private String choice;

	public void displayConsole() {
		logger.warn("Welcome to the Online Quiz Application");
		boolean isAuthenticate = performAuthentication();
		if (isAuthenticate && choice.equals("y")) {
			CrudOperations operations = adminPortal.selectAdminOperation(); 
			adminPortal.performAdminOperations(operations);
		} else if (isAuthenticate && choice.equals("n")) {
			userPortal.assignQuiz();
		} else {  
			logger.warn("The credentials are not matched. Please Try Again.");
		}		 
	}

	private boolean performAuthentication() {
		logger.warn("Enter y if u are an admin orelse n if u are a user: ");
		choice = scanner.next();
		boolean result = true;
		try {
			if (choice.equals("y")) {
				result = adminPortal.checkAdminAuthentication();
			} else if (choice.equals("n")) {
				result = userPortal.checkUserAuthentication();
			} else {
				throw new InvalidEntryException();
			}
		} catch (InvalidEntryException e) {
			logger.warn(e);
			performAuthentication();
		}
		return result;
	}

}
