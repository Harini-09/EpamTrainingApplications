package com.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.database.QuizAssignment;
import com.epam.entities.Quiz; 

@Service
public class QuizAllotment{
	
	@Autowired
	private QuizAssignment quizAssignmentImpl;

	public Quiz allotQuiz(int quizId) {
		return quizAssignmentImpl.assignQuiz(quizId);
	}

}
