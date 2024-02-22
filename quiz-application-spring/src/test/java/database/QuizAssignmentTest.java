package database;

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

import entities.Question;
import entities.Quiz;

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
		List<Question> questionsList = Arrays
				.asList(new Question("Q01", "Collections", "Which collection stores unique values?",
						Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c"));
		Quiz quiz = new Quiz(101, "Basic", questionsList, 30);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Quiz.class, 101)).thenReturn(quiz);
		Quiz returnedQuiz = quizAssignment.assignQuiz();
		assertEquals(quiz, returnedQuiz);
	}

}
