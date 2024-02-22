package service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import database.QuizAssignment;
import entities.Question;
import entities.Quiz;

@ExtendWith(MockitoExtension.class)
class QuizAllotmentTest {
	@Mock
	private QuizAssignment quizAssignment;

	@InjectMocks
	private QuizAllotment quizAllotment;
	
	@Test
	void allotQuizTest() {
		List<Question> questionsList = Arrays
				.asList(new Question("Q01", "Collections", "Which collection stores unique values?",
						Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c"));
	 	Quiz quiz = new Quiz(101, "Basic", questionsList, 30);
		Mockito.when(quizAssignment.assignQuiz()).thenReturn(quiz);
		Quiz returnedQuiz = quizAllotment.allotQuiz();
		Mockito.verify(quizAssignment).assignQuiz();
		assertEquals(quiz,returnedQuiz); 
	}
	
}
