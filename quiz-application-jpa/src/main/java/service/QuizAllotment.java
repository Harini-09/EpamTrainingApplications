package service;

import database.QuizAssignment;
import database.QuizAssignmentImpl;
import model.Quiz; 

public class QuizAllotment{
	QuizAssignment quizAssignment;

	public QuizAllotment() {
		this.quizAssignment = new QuizAssignmentImpl();
	}

	public Quiz allotQuiz() {
		return quizAssignment.assignQuiz();
	}

}
