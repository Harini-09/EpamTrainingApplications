package service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import customexceptions.InvalidQuestionIdEntryException;
import customexceptions.InvalidQuizTitleEntryException;
import customexceptions.NoSuchQuestionExistInQuizException;
import database.QuizDAO;
import model.QuizQuestion;

public class CrudOperationsOnQuiz {

	private QuizDAO quizDao = new QuizDAO();

	public boolean insert(String quizTitle, List<QuizQuestion> newQuestions) {
		return quizDao.createQuiz(quizTitle, newQuestions);
	}

	public String addQuestion(String quizTitle, String questionId, int marks) {
		return quizDao.addQuestion(quizTitle, questionId, marks);
	}

	public boolean removeQuestion(String quizTitle, String questionId) {
		return quizDao.removeQuestion(quizTitle, questionId);
	}

	public int updateMarks(String quizTitle, String quesitonId, int newmarks) {
		return quizDao.updateMarks(quizTitle, quesitonId, newmarks);
	}

	public boolean delete(String quizTitle) {
		return quizDao.removeQuiz(quizTitle);
	}

	public Set<Map.Entry<String, List<QuizQuestion>>> view() {
		return quizDao.viewQuiz();
	}

	public QuizQuestion addNewQuestion(String questionId, int marks) {
		return quizDao.addNewQuestion(questionId, marks);
	}

	public void isQuestionIdPresent(String questionId) throws InvalidQuestionIdEntryException {
		quizDao.isQuestionIdPresent(questionId);
	}

	public void isQuizTitlePresent(String quizTitle) throws InvalidQuizTitleEntryException {
		quizDao.isQuizTitlePresent(quizTitle);
	}

	public void isQuestionInQuizPresent(String quizTitle, String questionId) throws NoSuchQuestionExistInQuizException {
		quizDao.isQuestionInQuizPresent(quizTitle, questionId);
	}

}
