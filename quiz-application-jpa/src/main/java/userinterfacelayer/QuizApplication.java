package userinterfacelayer;

import customexceptions.InvalidEntryException;
import singletonobjects.UtilityObjects;
 
public class QuizApplication {
	
	private static AdminDashBoard adminPortal = new AdminDashBoard();
	private static SubUserDashBoard userPortal = new SubUserDashBoard();
	private static String choice;

	public static void main(String[] args) {
		UtilityObjects.getLoggerInstance().warn("Welcome to the Online Quiz Application");
		boolean isAuthenticate = QuizApplication.performAuthentication();
		if (isAuthenticate && choice.equals("y")) {
			CrudOperations operations = adminPortal.selectAdminOperation();
			adminPortal.performAdminOperations(operations);
		} else if (isAuthenticate && choice.equals("n")) {
			userPortal.assignQuiz();
		} else {  
			UtilityObjects.getLoggerInstance().warn("The credentials are not matched. Please Try Again.");
		}		 
	}

	private static boolean performAuthentication() {
		UtilityObjects.getLoggerInstance().warn("Enter y if u are an admin orelse n if u are a user: ");
		choice = UtilityObjects.getScannerInstance().next();
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
			UtilityObjects.getLoggerInstance().warn(e);
			performAuthentication();
		}
		return result;
	}

}
