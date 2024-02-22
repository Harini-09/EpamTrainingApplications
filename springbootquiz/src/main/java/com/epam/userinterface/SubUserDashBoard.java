package com.epam.userinterface;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.customexceptions.InvalidEntryException;
import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.entities.User;
import com.epam.service.QuizAllotment;
import com.epam.service.UserAuthentication;

@Component
public class SubUserDashBoard {

	@Autowired
	private UserAuthentication authentication;

	@Autowired
	private QuizAllotment quizAllotment;
	
	@Autowired
	private Scanner scanner;

	private final Logger logger = LogManager.getLogger(SubUserDashBoard.class);
	
	public boolean checkUserAuthentication() {
		logger.warn("********Welcome to the User DashBoard********");
		try {
			logger.warn("Enter the option :  1.LogIn  2.SignUp");
			int option = scanner.nextInt();
			logger.warn("Enter the details :");
			logger.warn("Enter Sub User id : ");
			String id = scanner.next();
			logger.warn("Enter password : ");
			String password = scanner.next();
			User user = new User(id, password, "admin");
			if (option == 1) {
				return authentication.logIn(user);
			} else if (option == 2) {
				return authentication.signUp(user);
			} else {
				throw new InvalidEntryException();
			}
		} catch (InvalidEntryException e) {
			logger.warn(e.getMessage());
			checkUserAuthentication();
		}
		return false;
	}

	public void assignQuiz() {
		logger.warn("Are u ready to take the quiz ? Press OK if you are ready");
		if (scanner.next().equals("OK")) {
			Quiz quiz = quizAllotment.allotQuiz(1);
			List<Question> questions = quiz.getQuestionsList();
			int eachQuestionMark = quiz.getTotalMarks() / questions.size();
			int securedMarks = 0;
			for (Question q : questions) {
				logger.warn("{} - {} \n{} [Marks = {} ] \n{} ", q.getQuestionId(),
						q.getTitle(), q.getQuestionDescription(), eachQuestionMark, q.getOptions());
				String selectedOption = scanner.next();
				if (q.getAnswer().equals(selectedOption)) {
					securedMarks += eachQuestionMark;
				}
			}
			logger.warn("The Quiz is completed.....");
			logger.warn("Your total score is : {}", securedMarks);
			logger.warn("Press OK to lOG OUT");
			scanner.next();
			logger.warn("You are successfully logged out !!!");
		} else {
			logger.warn("If you are not ready yet, press OK when u are ready!!");
			assignQuiz();
		}
	}
}
