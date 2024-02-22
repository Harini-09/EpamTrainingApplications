package com.epam.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.entities.Quiz;
import com.epam.repository.QuizRepo; 

@Service
public class QuizAllotment{
	
	@Autowired
	QuizRepo quizRepo;

	public Quiz allotQuiz(int quizId) {
		Optional<Quiz> quiz = quizRepo.findById(quizId);
		if(quiz.isPresent()) {
		return quiz.get();
		}
		return null;
	} 

}
