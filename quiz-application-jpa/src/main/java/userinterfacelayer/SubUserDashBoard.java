package userinterfacelayer;

import java.util.List;

import customexceptions.InvalidEntryException;
import model.Question;
import model.Quiz;
import model.User;
import service.QuizAllotment;
import service.UserAuthenticationImpl;
import singletonobjects.UtilityObjects;

public class SubUserDashBoard {
	private UserAuthenticationImpl authentication;
	private QuizAllotment quizAllotment;

	SubUserDashBoard() {
		this.authentication = new UserAuthenticationImpl();
		this.quizAllotment = new QuizAllotment();
	}

	public boolean checkUserAuthentication() {
		UtilityObjects.getLoggerInstance().warn("********Welcome to the User DashBoard********");
		try {
			UtilityObjects.getLoggerInstance().warn("Enter the option :  1.LogIn  2.SignUp");
			int option = UtilityObjects.getScannerInstance().nextInt();
			UtilityObjects.getLoggerInstance().warn("Enter the details :");
			UtilityObjects.getLoggerInstance().warn("Enter Sub User id : ");
			String id = UtilityObjects.getScannerInstance().next();
			UtilityObjects.getLoggerInstance().warn("Enter password : ");
			String password = UtilityObjects.getScannerInstance().next();
			User user = new User(id, password, "user");
			if (option == 1) {
				return authentication.logIn(user);
			} else if (option == 2) {
				return authentication.signUp(user);
			} else {
				throw new InvalidEntryException();
			}
		} catch (InvalidEntryException e) {
			UtilityObjects.getLoggerInstance().warn(e.getMessage());
			checkUserAuthentication();
		}
		return false;
	}

	public void assignQuiz() {
		UtilityObjects.getLoggerInstance().warn("Are u ready to take the quiz ? Press OK if you are ready");
		if (UtilityObjects.getScannerInstance().next().equals("OK")) {
			Quiz quiz = quizAllotment.allotQuiz();
			List<Question> questions = quiz.getQuestions();
			int eachQuestionMark = quiz.getTotalMarks() / questions.size();
			int securedMarks = 0;
			for (Question q : questions) {
				UtilityObjects.getLoggerInstance().warn("{} - {} \n{} [Marks = {} ] \n{} ", q.getQuestionId(),
						q.getTitle(), q.getQuestion(), eachQuestionMark, q.getOptions());
				String selectedOption = UtilityObjects.getScannerInstance().next();
				if (q.getAnswer().equals(selectedOption)) {
					securedMarks += eachQuestionMark;
				}
			}
			UtilityObjects.getLoggerInstance().warn("The Quiz is completed.....");
			UtilityObjects.getLoggerInstance().warn("Your total score is : {}", securedMarks);
			UtilityObjects.getLoggerInstance().warn("Press OK to lOG OUT");
			UtilityObjects.getScannerInstance().next();
			UtilityObjects.getLoggerInstance().warn("You are successfully logged out !!!");
		} else {
			UtilityObjects.getLoggerInstance().warn("If you are not ready yet, press OK when u are ready!!");
			assignQuiz();
		}
	}
}
