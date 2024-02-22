package service;

import java.util.List;

import customexceptions.InvalidQuestionIdEntryException;
import customexceptions.InvalidQuizTitleEntryException;
import customexceptions.NoSuchQuestionExistInQuizException;
import database.QuizDAOJpaImpl;
import database.QuizDao;
import model.Question;
import model.Quiz;

public class CrudOperationsOnQuiz {

	private QuizDao quizDao;
	
	public CrudOperationsOnQuiz(){
		this.quizDao=new QuizDAOJpaImpl();
	}

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
