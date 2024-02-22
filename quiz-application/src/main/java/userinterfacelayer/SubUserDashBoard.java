package userinterfacelayer;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import customexceptions.InvalidEntryException;
import model.Question;
import model.QuizQuestion;
import service.QuizAllotment;
import service.UserAuthentication;

public class SubUserDashBoard {
	private static final Logger LOGGER = LogManager.getLogger(QuizApplication.class);
	private Scanner scanner = new Scanner(System.in);
	private UserAuthentication authentication = new UserAuthentication();
	private QuizAllotment quizAllotment = new QuizAllotment();

	public boolean checkUserAuthentication() {
		LOGGER.info("********Welcome to the User DashBoard********");
		try {
			LOGGER.info("Enter the option :  1.LogIn  2.SignUp");
			int option = scanner.nextInt();
			LOGGER.info("Enter the details :");
			LOGGER.info("Enter Sub User id : ");
			String id = scanner.next();
			LOGGER.info("Enter password : ");
			String password = scanner.next();
			if (option == 1) {
				return authentication.logIn(id, password, "user");
			} else if (option == 2) {
				return authentication.signUp(id, password);
			} else {
				throw new InvalidEntryException();
			}
		} catch (InvalidEntryException e) {
			LOGGER.info(e.getMessage());
			checkUserAuthentication();
		}
		return false;
	}

	public void assignQuiz() {
		LOGGER.info("Are u ready to take the quiz ? Press OK if you are ready");
		if (scanner.next().equals("OK")) {
			List<QuizQuestion> quiz = quizAllotment.allotQuiz();
			Map<String, Question> questions = quizAllotment.getQuestions();
			int totalMarks = quiz.stream().mapToInt(t -> t.getMarks()).sum();
			int securedMarks = 0;
			LOGGER.info("Total Marks alloted for the Quiz = " + totalMarks);
			for (QuizQuestion q : quiz) {
				LOGGER.info(q.getQuestionid() + " - " + q.getQuestiontitle() + "\n" + q.getQuestion() + " [Marks = "
						+ q.getMarks() + "]" + "\n" + q.getOptions());
				String selectedOption = scanner.next();
				if (questions.get(q.getQuestionid()).getAnswer().equals(selectedOption)) {
					securedMarks += q.getMarks();
				}
			}
			LOGGER.info("The Quiz is completed.....");
			LOGGER.info("Your total score is : " + securedMarks + " out of " + totalMarks);
			LOGGER.info("Press OK to lOG OUT");
			scanner.next();
			LOGGER.info("You are successfully logged out !!!");
		} else {
			LOGGER.info("If you are not ready yet, press OK when u are ready!!");
			assignQuiz();
		}
	}
}
