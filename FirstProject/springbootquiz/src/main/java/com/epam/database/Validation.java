package com.epam.database;

import java.util.List;

import com.epam.entities.Question;
import com.epam.entities.Quiz;

public interface Validation {
	public List<Question> getQuestionsInQuiz(Quiz quiz);
}
