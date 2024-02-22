package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import customexceptions.InvalidQuestionIdEntryException;
import customexceptions.InvalidQuizTitleEntryException;
import customexceptions.NoSuchQuestionExistInQuizException;
import database.QuizDao;
import entities.Question;
import entities.Quiz;

@Component
public class CrudOperationsOnQuiz {

	@Autowired
	private QuizDao quizDao;

	public boolean insert(int quizId, Quiz newQuiz) {
		return quizDao.createQuiz(quizId, newQuiz);
	}

	public boolean addQuestion(int quizId,String questionId) {
		return quizDao.addQuestion(quizId, questionId);
	}

	public boolean removeQuestion(int quizId, String questionId) {
		return quizDao.removeQuestion(quizId, questionId);
	}

	public int updateMarks(int quizId,int newmarks) {
		return quizDao.updateMarks(quizId,newmarks);
	}

	public boolean delete(int quizId) {
		return quizDao.removeQuiz(quizId);
	}

	public List<Quiz> view() {
		return quizDao.viewQuiz();
	}

	public Question addNewQuestion(String questionId) {
		return quizDao.addNewQuestion(questionId);
	}

	public void isQuestionIdPresent(String questionId) throws InvalidQuestionIdEntryException {
		quizDao.isQuestionIdPresent(questionId);
	} 

	public void isQuizTitlePresent(int quizId) throws InvalidQuizTitleEntryException {
		quizDao.isQuizTitlePresent(quizId);
	}

	public void isQuestionInQuizPresent(int quizId, String questionId) throws NoSuchQuestionExistInQuizException {
		quizDao.isQuestionInQuizPresent(quizId, questionId);
	}

}
