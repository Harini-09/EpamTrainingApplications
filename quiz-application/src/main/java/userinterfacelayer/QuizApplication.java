package userinterfacelayer;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import customexceptions.InvalidEntryException;

public class QuizApplication {
	private static final Logger LOGGER = LogManager.getLogger(QuizApplication.class);
	private Scanner scanner = new Scanner(System.in);
	private static AdminDashBoard adminPortal = new AdminDashBoard();
	private static SubUserDashBoard userPortal = new SubUserDashBoard();
	private static String choice;

	public static void main(String[] args) {
		QuizApplication app = new QuizApplication();
		LOGGER.info("Welcome to the Online Quiz Application");
		boolean isAuthenticate = app.performAuthentication();
		if (isAuthenticate && choice.equals("y")) {
			CrudOperations operations = adminPortal.selectAdminOperation();
			adminPortal.performAdminOperations(operations);
		} else if (isAuthenticate && choice.equals("n")) {
			userPortal.assignQuiz();
		} else {
			LOGGER.info("The credentials are not matched. Please Try Again.");
		}
	}

	private boolean performAuthentication() {
		LOGGER.info("Enter y if u are an admin orelse n if u are a user: ");
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
			LOGGER.info(e);
			performAuthentication();
		}
		return result;
	}

}
