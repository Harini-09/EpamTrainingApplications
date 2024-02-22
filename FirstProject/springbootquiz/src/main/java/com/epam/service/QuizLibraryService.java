package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.customexceptions.InvalidQuizIdEntryException;
import com.epam.customexceptions.NoSuchQuestionExistInQuizException;
import com.epam.database.QuizDao;
import com.epam.entities.Question;
import com.epam.entities.Quiz;

@Service
public class QuizLibraryService {

	@Autowired
	private QuizDao quizDaoImpl;

	public Quiz insert(Quiz newQuiz) {
		return quizDaoImpl.createQuiz(newQuiz);
	}

	public boolean addQuestion(int quizId, int questionId) {
		return quizDaoImpl.addQuestion(quizId, questionId);
	}

	public boolean removeQuestion(int quizId, int questionId) {
		return quizDaoImpl.removeQuestion(quizId, questionId);
	}

	public int updateMarks(int quizId, int newmarks) {
		return quizDaoImpl.updateMarks(quizId, newmarks);
	}

	public boolean delete(int quizId) {
		return quizDaoImpl.removeQuiz(quizId);
	}

	public List<Quiz> view() {
		return quizDaoImpl.viewQuiz();
	}

	public Question addNewQuestion(int questionId) {
		return quizDaoImpl.addNewQuestion(questionId);
	}

	public void isQuestionIdPresent(int questionId) throws InvalidQuestionIdEntryException {
		quizDaoImpl.isQuestionIdPresent(questionId);
	}

	public void isQuizTitlePresent(int quizId) throws InvalidQuizIdEntryException {
		quizDaoImpl.isQuizTitlePresent(quizId);
	}

	public void isQuestionInQuizPresent(int quizId, int questionId) throws NoSuchQuestionExistInQuizException {
		quizDaoImpl.isQuestionInQuizPresent(quizId, questionId);
	}

}
