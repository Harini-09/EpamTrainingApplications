package com.epam.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.entities.Question;
import com.epam.entities.Quiz;

@Repository
public class ValidationImpl implements Validation {

	@Override
	public List<Question> getQuestionsInQuiz(Quiz quiz) {
		return quiz.getQuestionsList();
	}
} 
   