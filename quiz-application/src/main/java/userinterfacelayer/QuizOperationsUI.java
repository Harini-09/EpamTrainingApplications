package userinterfacelayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import customexceptions.InvalidEntryException;
import customexceptions.InvalidQuestionIdEntryException;
import customexceptions.InvalidQuizTitleEntryException;
import customexceptions.NoSuchQuestionExistInQuizException;
import model.QuizQuestion;
import service.CrudOperationsOnQuiz;

public class QuizOperationsUI implements CrudOperations {
	private static final Logger LOGGER = LogManager.getLogger(QuizApplication.class);
	private Scanner scanner = new Scanner(System.in);
	private CrudOperationsOnQuiz operations = new CrudOperationsOnQuiz();

	@Override
	public void insert() {
		LOGGER.info("Enter the quiz title : ");
		String quizTitle = scanner.next();
		LOGGER.info("Enter the no of questions do u want to add to the quiz : ");
		int count = scanner.nextInt();
		List<QuizQuestion> newQuestions = insertQuestions(count);
		if (operations.insert(quizTitle, newQuestions)) {
			LOGGER.info("Quiz added successfully!!!");
		} else {
			LOGGER.info("Quiz is not added successfully!!!");
		}
	}

	@Override
	public void modify() {
		LOGGER.info("Enter the quiz title do u want to modify : ");
		String quizTitle = scanner.next();
		try {
			operations.isQuizTitlePresent(quizTitle);
			int option = 0;
			do {
				LOGGER.info(
						"Enter which operation do u want to perform 1.add question\n 2.delete question\n 3.modify question marks\n 4.Exit:");
				option = scanner.nextInt();
				try {
					if (option == 1) {
						addQuestion(quizTitle);
					} else if (option == 2) {
						deleteQuestion(quizTitle);
					} else if (option == 3) {
						updateQuestionMarks(quizTitle);
					} else if (option == 4) {
						option = 4;
					} else {
						throw new InvalidEntryException();
					}
				} catch (InvalidEntryException e) {
					LOGGER.info(e.getMessage());
				}
			} while (option != 4);
		} catch (InvalidQuizTitleEntryException e) {
			LOGGER.info(e.getMessage());
			modify();
		}

	}

	@Override
	public void delete() {
		LOGGER.info("Enter the quiz title to be deleted : ");
		String quizTitle = scanner.next();
		try {
			operations.isQuizTitlePresent(quizTitle);
			if (operations.delete(quizTitle)) {
				LOGGER.info("Quiz is not deleted successfully!!!");
			} else { 
				LOGGER.info("Quiz is deleted successfully!!!");
			}
		} catch (InvalidQuizTitleEntryException e) {
			LOGGER.info(e.getMessage());
			delete();
		}
	}

	@Override
	public void view() {
		LOGGER.info("The Quizes in the library are : ");
		Set<Map.Entry<String, List<QuizQuestion>>> questionSet = operations.view();
		if (questionSet == null) {
			LOGGER.info("The Quiz Library is empty!!");
		} else {
			questionSet.stream().forEach(LOGGER::info);
		}
	}

	public List<QuizQuestion> insertQuestions(int count) {
		List<QuizQuestion> newQuestions = new ArrayList<>();
		LOGGER.info("Enter the Question IDs do u want to add to the quiz : \n");
		int iterate = 1;
		while (iterate <= count) {
			LOGGER.info("Enter the question id for question " + iterate + " :");
			String questionId = scanner.next();
			try {
				operations.isQuestionIdPresent(questionId);
				LOGGER.info("Enter the no of marks for the question : ");
				int marks = scanner.nextInt();
				newQuestions.add(operations.addNewQuestion(questionId, marks));
				iterate++;
			} catch (InvalidQuestionIdEntryException e) {
				LOGGER.info(e.getMessage());
			}
		}
		return newQuestions;
	}

	private void addQuestion(String quizTitle) {
		LOGGER.info("Enter the question id for the question to be added :");
		String questionId = scanner.next();
		LOGGER.info("Enter the no of marks for the question : ");
		int marks = scanner.nextInt();
		try {
			operations.isQuestionIdPresent(questionId);
			if (operations.addQuestion(quizTitle, questionId, marks).equals(questionId)) {
				LOGGER.info("The question is successfully added to the quiz title ");
			} else {
				LOGGER.info("The question is not successfully added to the quiz title ");
			}
		} catch (InvalidQuestionIdEntryException e) {
			LOGGER.info(e.getMessage());
			addQuestion(quizTitle);
		}
	}

	private void deleteQuestion(String quizTitle) {
		LOGGER.info("Enter the question id for the question to be deleted :");
		String questionId = scanner.next();
		try {
			operations.isQuestionInQuizPresent(quizTitle, questionId);
			if (operations.removeQuestion(quizTitle, questionId) == true) {
				LOGGER.info("The question is not successfully deleted from the quiz title ");
			} else {
				LOGGER.info("The question is successfully deleted from the quiz title ");
			}
		} catch (NoSuchQuestionExistInQuizException e) {
			LOGGER.info(e.getMessage());
			deleteQuestion(quizTitle);
		}
	}

	private void updateQuestionMarks(String quizTitle) {
		LOGGER.info("Enter the question id for modifying the question marks :");
		String questionId = scanner.next();
		try {
			operations.isQuestionInQuizPresent(quizTitle, questionId);
			LOGGER.info("Enter the marks do u like to update : ");
			int newMarks = scanner.nextInt();
			if (operations.updateMarks(quizTitle, questionId, newMarks) == newMarks) {
				LOGGER.info("The marks are successfully updated for the question!!!");
			} else {
				LOGGER.info("The marks are not successfully updated for the question!!!");
			}
		} catch (NoSuchQuestionExistInQuizException e) {
			LOGGER.info(e.getMessage());
			updateQuestionMarks(quizTitle);
		}

	}
}
