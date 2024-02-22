package service;

import java.util.List;
import java.util.Map;

import database.QuizAssignment;
import model.Question;
import model.QuizQuestion;

public class QuizAllotment {
	private QuizAssignment quizAssignment = new QuizAssignment();
	public List<QuizQuestion> allotQuiz() {
		return quizAssignment.assignQuiz();
	}
	
	public Map<String,Question> getQuestions(){
		return quizAssignment.getQuestions();
	}
}

