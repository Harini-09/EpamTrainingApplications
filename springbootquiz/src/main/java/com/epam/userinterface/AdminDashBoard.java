package com.epam.userinterface;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.epam.customexceptions.InvalidEntryException;
import com.epam.entities.User;
import com.epam.service.UserAuthentication;

@Component
public class AdminDashBoard {

	@Autowired
	private UserAuthentication authentication;

	@Autowired
	@Qualifier("questionOperationsUI")
	private CrudOperations questionOperations;

	@Autowired
	@Qualifier("quizOperationsUI")
	private CrudOperations quizOperations;

	@Autowired
	Scanner scanner;

	private final Logger logger = LogManager.getLogger(AdminDashBoard.class);

	public boolean checkAdminAuthentication() {
		logger.warn("********Welcome to the Admin DashBoard********");
		logger.warn("Enter detatils to log in to the Application :");
		logger.warn("Enter Admin id : ");
		String id = scanner.next();
		logger.warn("Enter password : ");
		String password = scanner.next();
		User user = new User(id, password, "admin");
		return authentication.logIn(user);
	}

	public CrudOperations selectAdminOperation() {
		logger.warn("Do u want to operate on Questions or on Quiz  1.Questions  2.Quiz");
		int selection = scanner.nextInt();
		try {
			if (selection == 1) {
				return questionOperations;
			} else if (selection == 2) {
				return quizOperations;
			} else {
				throw new InvalidEntryException();
			}
		} catch (InvalidEntryException e) {
			logger.warn(e);
			selectAdminOperation();
		}
		return null;
	}

	public void performAdminOperations(CrudOperations operations) {
		int option = 0;
		do {
			try {
				logger.warn("Enter which operation do u want to perform 1.Insert 2.Modify 3.Delete 4.View 5.Exit :");
				option = scanner.nextInt();
				if (option == 1) {
					operations.insert();
				} else if (option == 2) {
					operations.modify();
				} else if (option == 3) {
					operations.delete();
				} else if (option == 4) {
					operations.view();
				} else if (option == 5) {
					option = 5;
				} else {
					throw new InvalidEntryException();
				}
			} catch (InvalidEntryException e) {
				logger.warn(e);
			}
		} while (option != 5);
		logger.warn("Press OK to lOG OUT");
		scanner.next();
		logger.warn("You are successfully logged out !!!");
	}
}
