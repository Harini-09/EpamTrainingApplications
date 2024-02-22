package userinterfacelayer;

import java.util.ArrayList;
import java.util.List;

import customexceptions.InvalidEntryException;
import customexceptions.InvalidQuestionIdEntryException;
import customexceptions.InvalidQuizTitleEntryException;
import customexceptions.NoSuchQuestionExistInQuizException;
import model.Question;
import model.Quiz;
import service.CrudOperationsOnQuiz;
import singletonobjects.UtilityObjects;

public class QuizOperationsUI implements CrudOperations {

	private CrudOperationsOnQuiz operations;

	QuizOperationsUI() {
		this.operations = new CrudOperationsOnQuiz();
	}

	@Override
	public void insert() { 
		UtilityObjects.getLoggerInstance().warn("Enter the quiz id : ");
		int quizId = UtilityObjects.getScannerInstance().nextInt();
		UtilityObjects.getLoggerInstance().warn("Enter the quiz title : ");
		String quizTitle = UtilityObjects.getScannerInstance().next();
		UtilityObjects.getLoggerInstance().warn("Enter the no of questions do u want to add to the quiz : ");
		int count = UtilityObjects.getScannerInstance().nextInt();
		UtilityObjects.getLoggerInstance().warn("Enter the total marks for the quiz :");
		int totalMarks = UtilityObjects.getScannerInstance().nextInt();
		List<Question> newQuestions = insertQuestions(count);
		Quiz newQuizQuestion = new Quiz(quizId, quizTitle, newQuestions, totalMarks);
		if (operations.insert(quizId, newQuizQuestion)) {
			UtilityObjects.getLoggerInstance().warn("Quiz added successfully!!!");
		} else {
			UtilityObjects.getLoggerInstance().warn("Quiz is not added successfully!!!");
		}
	}

	@Override
	public void modify() {
		UtilityObjects.getLoggerInstance().warn("Enter the quiz id do u want to modify : ");
		int quizId = UtilityObjects.getScannerInstance().nextInt();
		try {
			operations.isQuizTitlePresent(quizId);
			int option = 0;
			do {
				UtilityObjects.getLoggerInstance().warn(
						"Enter which operation do u want to perform 1.add question\n 2.delete question\n 3.modify question marks\n 4.Exit:");
				option = UtilityObjects.getScannerInstance().nextInt();
				performModificationOperation(option, quizId);
			} while (option != 4);
		} catch (InvalidQuizTitleEntryException e) {
			UtilityObjects.getLoggerInstance().warn(e.getMessage());
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
			} else if (option>4) {
				throw new InvalidEntryException();
			}
		} catch (InvalidEntryException e) {
			UtilityObjects.getLoggerInstance().warn(e.getMessage());
		}
	}

	@Override
	public void delete() {
		UtilityObjects.getLoggerInstance().warn("Enter the quiz id to be deleted : ");
		int quizId = UtilityObjects.getScannerInstance().nextInt();
		try {
			operations.isQuizTitlePresent(quizId);
			if (operations.delete(quizId)) {
				UtilityObjects.getLoggerInstance().warn("Quiz is not deleted successfully!!!");
			} else {
				UtilityObjects.getLoggerInstance().warn("Quiz is deleted successfully!!!");
			}
		} catch (InvalidQuizTitleEntryException e) {
			UtilityObjects.getLoggerInstance().warn(e.getMessage());
			delete();
		}
	}

	@Override
	public void view() {
		List<Quiz> quiz = operations.view();
		if (quiz.isEmpty()) {
			UtilityObjects.getLoggerInstance().warn("The Quiz Library is empty!!");
		} else {
			UtilityObjects.getLoggerInstance().warn("The Quizes in the library are : ");
			UtilityObjects.getLoggerInstance().warn(quiz);
		}
	}

	public List<Question> insertQuestions(int count) {
		List<Question> quizQuestions = new ArrayList<>();
		UtilityObjects.getLoggerInstance().warn("Enter the Question IDs do u want to add to the quiz : \n");
		int iterate = 1;
		while (iterate <= count) {
			UtilityObjects.getLoggerInstance().warn("Enter the question id for question {} : ",iterate);
			String questionId = UtilityObjects.getScannerInstance().next();
			try {
				operations.isQuestionIdPresent(questionId);
				quizQuestions.add(operations.addNewQuestion(questionId));
				iterate++;
			} catch (InvalidQuestionIdEntryException e) {
				UtilityObjects.getLoggerInstance().warn(e.getMessage());
			}
		}
		return quizQuestions;
	}

	private void addQuestion(int quizId) {
		UtilityObjects.getLoggerInstance().warn("Enter the question id for the question to be added :");
		String questionId = UtilityObjects.getScannerInstance().next();
		try {
			operations.isQuestionIdPresent(questionId);
			if (operations.addQuestion(quizId, questionId)) {
				UtilityObjects.getLoggerInstance().warn("The question is successfully added to the quiz title ");
			} else {
				UtilityObjects.getLoggerInstance().warn("The question is not successfully added to the quiz title ");
			}
		} catch (InvalidQuestionIdEntryException e) {
			UtilityObjects.getLoggerInstance().warn(e.getMessage());
			addQuestion(quizId);
		}
	}

	private void deleteQuestion(int quizId) {
		UtilityObjects.getLoggerInstance().warn("Enter the question id for the question to be deleted :");
		String questionId = UtilityObjects.getScannerInstance().next();
		try {
			operations.isQuestionInQuizPresent(quizId, questionId);
			if (operations.removeQuestion(quizId, questionId)) {
				UtilityObjects.getLoggerInstance()
						.warn("The question is not successfully deleted from the quiz title ");
			} else {
				UtilityObjects.getLoggerInstance().warn("The question is successfully deleted from the quiz title ");
			}
		} catch (NoSuchQuestionExistInQuizException e) {
			UtilityObjects.getLoggerInstance().warn(e.getMessage());
			deleteQuestion(quizId);
		}
	}
 
	private void updateTotalMarks(int quizId) {
		UtilityObjects.getLoggerInstance().warn("Enter the new total marks :");
		int newTotalMarks = UtilityObjects.getScannerInstance().nextInt();
		if (operations.updateMarks(quizId, newTotalMarks) == newTotalMarks) {
			UtilityObjects.getLoggerInstance().warn("The marks are successfully updated for the question!!!");
		} else {
			UtilityObjects.getLoggerInstance().warn("The marks are not successfully updated for the question!!!");
		}
	}

}
