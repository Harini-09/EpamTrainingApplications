package userinterfacelayer;

import customexceptions.InvalidEntryException;
import model.User;
import service.UserAuthenticationImpl;
import singletonobjects.UtilityObjects;

public class AdminDashBoard {
	private UserAuthenticationImpl authentication;
	private CrudOperations operations;

	AdminDashBoard() {
		this.authentication = new UserAuthenticationImpl();
	}

	public boolean checkAdminAuthentication() {
		UtilityObjects.getLoggerInstance().warn("********Welcome to the Admin DashBoard********");
		UtilityObjects.getLoggerInstance().warn("Enter detatils to log in to the Application :");
		UtilityObjects.getLoggerInstance().warn("Enter Admin id : ");
		String id = UtilityObjects.getScannerInstance().next();
		UtilityObjects.getLoggerInstance().warn("Enter password : ");
		String password = UtilityObjects.getScannerInstance().next();
		User user = new User(id, password, "admin");
		return authentication.logIn(user);
	}

	public CrudOperations selectAdminOperation() {
		UtilityObjects.getLoggerInstance().warn("Do u want to operate on Questions or on Quiz  1.Questions  2.Quiz");
		int selection = UtilityObjects.getScannerInstance().nextInt();
		try {
			if (selection == 1) {
				operations = new QuestionOperationsUI();
			} else if (selection == 2) {
				operations = new QuizOperationsUI();
			} else {
				throw new InvalidEntryException();
			}
		} catch (InvalidEntryException e) {
			UtilityObjects.getLoggerInstance().warn(e);
			selectAdminOperation();
			return operations;
		}
		return operations;
	}

	public void performAdminOperations(CrudOperations operations) {
		int option = 0;
		do {
			try {
				UtilityObjects.getLoggerInstance()
						.warn("Enter which operation do u want to perform 1.Insert 2.Modify 3.Delete 4.View 5.Exit :");
				option = UtilityObjects.getScannerInstance().nextInt();
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
				UtilityObjects.getLoggerInstance().warn(e);
			}
		} while (option != 5);
		UtilityObjects.getLoggerInstance().warn("Press OK to lOG OUT");
		UtilityObjects.getScannerInstance().next();
		UtilityObjects.getLoggerInstance().warn("You are successfully logged out !!!");
	}
}
