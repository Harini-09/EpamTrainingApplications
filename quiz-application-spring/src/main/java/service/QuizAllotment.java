package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import database.QuizAssignment;
import entities.Quiz; 

@Component
public class QuizAllotment{
	
	@Autowired
	private QuizAssignment quizAssignment;

	public Quiz allotQuiz() {
		return quizAssignment.assignQuiz();
	}

}
