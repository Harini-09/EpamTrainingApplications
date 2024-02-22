package com.epam.springbootdemo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.springbootdemo.database.DatabaseOperations;
import com.epam.springbootdemo.database.QuizDataBaseOperations;
import com.epam.springbootdemo.modal.Question;
import com.epam.springbootdemo.modal.Quiz;

@Service
public class QuizLibraryService {

	@Autowired
	private QuizDataBaseOperations quizDAO;
	@Autowired
	private DatabaseOperations questionDAO;

	public Object addQuestion(int questionID, int quizID) {
		return quizDAO.addQuestions(questionID, quizID);

	}

	public Object deleteQuestion(int questionID, int quizID) {
		return quizDAO.removeQuestions(questionID, quizID);
	}

	public Question addQuestionToNewQuiz(int questionID) {
		return (Question) questionDAO.viewData(questionID);
	}

	public Object addQuiz(Quiz quiz) {
		return quizDAO.addData(quiz);
	}

	public boolean deleteQuiz(int quizID) {
		return quizDAO.deleteData(quizID);
	}

	public Object updateQuiz(int quizID, Object value, int choice) {
		return quizDAO.updateData(value, quizID, choice);
	}

	public Object view(int quizID) {
		return quizDAO.viewData(quizID);
	}

	public Object getAlldata() {
		return quizDAO.getAllData();
	}

}