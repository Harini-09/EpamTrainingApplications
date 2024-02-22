package com.epam.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.customexceptions.InvalidQuizIdEntryException;
import com.epam.entities.Quiz;
import com.epam.repository.QuizRepo; 

@Service
public class QuizAllotment{
	
	@Autowired
	QuizRepo quizRepo;
	
	private final Logger logger = LogManager.getLogger(QuizAllotment.class);

	public Quiz allotQuiz(int quizId) throws InvalidQuizIdEntryException {
		logger.info("Entered into the Quiz Allotment service - allotQuiz()");
		return quizRepo.findById(quizId).map(quiz->quiz).orElseThrow(InvalidQuizIdEntryException::new);
	} 

} 
