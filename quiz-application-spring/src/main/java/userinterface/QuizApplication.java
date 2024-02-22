package userinterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import customexceptions.InvalidEntryException;
import singletonobjects.UtilityObjects;

@Configuration
@ComponentScan(basePackages= {"userinterface","service","database","singletonobjects","customexceptions"})
public class QuizApplication {
	
	@Autowired
	private static AdminDashBoard adminPortal;
	
	@Autowired
	private static SubUserDashBoard userPortal;
		
	private static String choice;

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(QuizApplication.class);
		adminPortal=context.getBean(AdminDashBoard.class);
		userPortal = context.getBean(SubUserDashBoard.class);
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
	
	@Bean
	public AdminDashBoard getAdminDashBoard() {
		return new AdminDashBoard();
	}
	
	@Bean
	public SubUserDashBoard getSubUserDashBoard() {
		return new SubUserDashBoard();
	}

}
