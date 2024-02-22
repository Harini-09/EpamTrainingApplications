package com.epam.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.entities.Question;
import com.epam.entities.Quiz;

@ExtendWith(MockitoExtension.class)
class QuizAssignmentTest {
	@Mock
	private EntityManager entityManager;

	@Mock
	private EntityTransaction transaction;

	@InjectMocks
	private QuizAssignmentImpl quizAssignment;

	@Test
	void assignQuizTest() {
		Question question  = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		List<Question> questionsList = Arrays.asList(question);
		Quiz quiz = new Quiz("Basic", questionsList, 30);
		quiz.setQuizId(101);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Quiz.class, 101)).thenReturn(quiz);
		Quiz returnedQuiz = quizAssignment.assignQuiz(101);
		assertEquals(quiz, returnedQuiz);
	}

}
