package com.epam.userinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.customexceptions.InvalidEntryException;
import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.customexceptions.InvalidQuizIdEntryException;
import com.epam.customexceptions.NoSuchQuestionExistInQuizException;
import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.service.QuizLibraryService;

@Component
public class QuizOperationsUI implements CrudOperations {

	@Autowired
	private QuizLibraryService operations;

	@Autowired
	Scanner scanner;

	private final Logger logger = LogManager.getLogger(QuizOperationsUI.class);

	@Override
	public void insert() {
		logger.warn("Enter the quiz title : ");
		String quizTitle = scanner.next();
		logger.warn("Enter the no of questions do u want to add to the quiz : ");
		int count = scanner.nextInt();
		logger.warn("Enter the total marks for the quiz :");
		int totalMarks = scanner.nextInt();
		List<Question> newQuestions = insertQuestions(count);
		Quiz newQuizQuestion = new Quiz(quizTitle, newQuestions, totalMarks);
		Quiz addedQuiz = operations.insert(newQuizQuestion);
		logger.warn("Quiz added successfully!!!");
		logger.warn(addedQuiz);
	}

	@Override
	public void modify() {
		logger.warn("Enter the quiz id do u want to modify : ");
		int quizId = scanner.nextInt();
		try {
			operations.isQuizTitlePresent(quizId);
			int option = 0;
			do {
				logger.warn(
						"Enter which operation do u want to perform 1.add question\n 2.delete question\n 3.modify question marks\n 4.Exit:");
				option = scanner.nextInt();
				performModificationOperation(option, quizId);
			} while (option != 4);
		} catch (InvalidQuizIdEntryException e) {
			logger.warn(e.getMessage());
			modify();
		}

	}

	private void performModificationOperation(int option, int quizId) {
		try {
			if (option == 1) {
				addQuestion(quizId);
			} else if (option == 2) {
				deleteQuestion(quizId);
			} else if (option == 3) {
				updateTotalMarks(quizId);
			} else if (option > 4) {
				throw new InvalidEntryException();
			}
		} catch (InvalidEntryException e) {
			logger.warn(e.getMessage());
		}
	}

	@Override
	public void delete() {
		logger.warn("Enter the quiz id to be deleted : ");
		int quizId = scanner.nextInt();
		try {
			operations.isQuizTitlePresent(quizId);
			if (operations.delete(quizId)) {
				logger.warn("Quiz is not deleted successfully!!!");
			} else {
				logger.warn("Quiz is deleted successfully!!!");
			}
		} catch (InvalidQuizIdEntryException e) {
			logger.warn(e.getMessage());
			delete();
		}
	}

	@Override
	public void view() {
		List<Quiz> quiz = operations.view();
		if (quiz.isEmpty()) {
			logger.warn("The Quiz Library is empty!!");
		} else {
			logger.warn("The Quizes in the library are : ");
			logger.warn(quiz);
		}
	}

	public List<Question> insertQuestions(int count) {
		List<Question> quizQuestions = new ArrayList<>();
		logger.warn("Enter the Question IDs do u want to add to the quiz : \n");
		int iterate = 1;
		while (iterate <= count) {
			logger.warn("Enter the question id for question {} : ", iterate);
			int questionId = scanner.nextInt();
			try {
				operations.isQuestionIdPresent(questionId);
				quizQuestions.add(operations.addNewQuestion(questionId));
				iterate++;
			} catch (InvalidQuestionIdEntryException e) {
				logger.warn(e.getMessage());
			}
		}
		return quizQuestions;
	}

	private void addQuestion(int quizId) {
		logger.warn("Enter the question id for the question to be added :");
		int questionId = scanner.nextInt();
		try {
			operations.isQuestionIdPresent(questionId);
			if (operations.addQuestion(quizId, questionId)) {
				logger.warn("The question is successfully added to the quiz title ");
			} else {
				logger.warn("The question is not successfully added to the quiz title ");
			}
		} catch (InvalidQuestionIdEntryException e) {
			logger.warn(e.getMessage());
			addQuestion(quizId);
		}
	}

	private void deleteQuestion(int quizId) {
		logger.warn("Enter the question id for the question to be deleted :");
		int questionId = scanner.nextInt();
		try {
			operations.isQuestionInQuizPresent(quizId, questionId);
			if (operations.removeQuestion(quizId, questionId)) {
				logger.warn("The question is not successfully deleted from the quiz title ");
			} else {
				logger.warn("The question is successfully deleted from the quiz title ");
			}
		} catch (NoSuchQuestionExistInQuizException e) {
			logger.warn(e.getMessage());
			deleteQuestion(quizId);
		}
	}

	private void updateTotalMarks(int quizId) {
		logger.warn("Enter the new total marks :");
		int newTotalMarks = scanner.nextInt();
		if (operations.updateMarks(quizId, newTotalMarks) == newTotalMarks) {
			logger.warn("The marks are successfully updated for the question!!!");
		} else {
			logger.warn("The marks are not successfully updated for the question!!!");
		}
	}

}
