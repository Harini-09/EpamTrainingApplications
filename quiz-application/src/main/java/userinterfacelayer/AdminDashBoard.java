package userinterfacelayer;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import customexceptions.InvalidEntryException;
import service.UserAuthentication;

public class AdminDashBoard {
	private static final Logger LOGGER = LogManager.getLogger(QuizApplication.class);
	private Scanner scanner = new Scanner(System.in);
	private UserAuthentication authentication = new UserAuthentication();
	private CrudOperations operations;

	public boolean checkAdminAuthentication() {
		LOGGER.info("********Welcome to the Admin DashBoard********");
		LOGGER.info("Enter detatils to log in to the Application :");
		LOGGER.info("Enter Admin id : ");
		String id = scanner.next();
		LOGGER.info("Enter password : ");
		String password = scanner.next();
		boolean result = authentication.logIn(id, password,"admin");
		return result;
	}

	public CrudOperations selectAdminOperation() {
		LOGGER.info("Do u want to operate on Questions or on Quiz  1.Questions  2.Quiz");
		int selection = scanner.nextInt();
		try {
			if (selection == 1) {
				operations = new QuestionOperationsUI();
			} else if (selection == 2) {
				operations = new QuizOperationsUI();
			} else {
				throw new InvalidEntryException();
			}
		} catch (InvalidEntryException e) {
			LOGGER.info(e);
			selectAdminOperation();
			return operations;
		}
		return operations;
	}

	public void performAdminOperations(CrudOperations operations) {
		int option = 0;
		do {
			try {
				LOGGER.info("Enter which operation do u want to perform 1.Insert 2.Modify 3.Delete 4.View 5.Exit :");
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
				LOGGER.info(e);
			}
		} while (option != 5);
		LOGGER.info("Press OK to lOG OUT");
		scanner.next();
		LOGGER.info("You are successfully logged out !!!");
	}
}
