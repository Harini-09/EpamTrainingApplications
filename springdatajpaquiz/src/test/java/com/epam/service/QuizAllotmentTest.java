package com.epam.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.repository.QuizRepo;

@ExtendWith(MockitoExtension.class)
class QuizAllotmentTest {
	@Mock
	private QuizRepo quizRepo;

	@InjectMocks
	private QuizAllotment quizAllotment;

	@Test
	void allotQuizTest() {
		Question question1 = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question1.setQuestionId(1);
		List<Question> questionsList = Arrays.asList(question1);
		Quiz quiz = new Quiz("Basic", questionsList, 30);
		Optional<Quiz> optional = Optional.of(quiz);
		quiz.setQuizId(101);
		Mockito.when(quizRepo.findById(101)).thenReturn(optional);
		Quiz returnedQuiz = quizAllotment.allotQuiz(101);
		assertEquals(quiz, returnedQuiz);
	} 

}
