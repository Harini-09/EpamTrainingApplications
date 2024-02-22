package com.epam.quizapplication;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import database.QuestionsLibrary;
import database.QuizLibrary;
import model.Question;
import model.QuizQuestion;
import service.QuizAllotment;

class QuizAllotmentTest {
	
	static QuizAllotment quizAllotment;
	QuizLibrary quizLibrary = new QuizLibrary();
	Map<String,List<QuizQuestion>> quizes = quizLibrary.getQuizTitles();
	
	@BeforeAll
	static void getObject() {
		quizAllotment = new QuizAllotment();
	}
	
	@Test
	void testAllotQuiz() {
		List<QuizQuestion> quiz = quizAllotment.allotQuiz();
		assertEquals(quizes.get("Quiz1"),quiz);
	}
	
	@Test
	void testGetQuestions() {
		Map<String,Question> questions = quizAllotment.getQuestions();
		assertEquals(QuestionsLibrary.questions,questions);
	}
	
	

}
