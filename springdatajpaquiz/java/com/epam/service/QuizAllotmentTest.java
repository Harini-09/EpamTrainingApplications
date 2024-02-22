package com.epam.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.database.QuizAssignment;

import com.epam.entities.Question;
import com.epam.entities.Quiz;

@ExtendWith(MockitoExtension.class)
class QuizAllotmentTest {
	@Mock
	private QuizAssignment quizAssignment;

	@InjectMocks
	private QuizAllotment quizAllotment;

	@Test
	void allotQuizTest() {
		Question question1 = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question1.setQuestionId(1);
		List<Question> questionsList = Arrays.asList(question1);
		Quiz quiz = new Quiz("Basic", questionsList, 30);
		quiz.setQuizId(101);
		Mockito.when(quizAssignment.assignQuiz(101)).thenReturn(quiz);
		Quiz returnedQuiz = quizAllotment.allotQuiz(101);
		Mockito.verify(quizAssignment).assignQuiz(101);
		assertEquals(quiz, returnedQuiz);
	}

}
