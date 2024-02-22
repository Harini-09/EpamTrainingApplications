package com.epam.database;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.customexceptions.InvalidQuizIdEntryException;
import com.epam.customexceptions.NoSuchQuestionExistInQuizException;
import com.epam.entities.Question;
import com.epam.entities.Quiz;

@ExtendWith(MockitoExtension.class)
class QuizDaoJpaImplTest {

	@Mock
	private EntityManager entityManager;

	@Mock
	private EntityTransaction transaction;

	@InjectMocks
	private QuizDaoImpl quizDao;

	@Mock
	private TypedQuery<Quiz> query;

	@Mock
	private Validation validation;

	@Mock
	private List<Question> quizQuestions;

	@Test
	void viewQuizTest() {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		List<Question> questionsList = Arrays.asList(question);
		Quiz quiz = new Quiz("Basic", questionsList, 30);
		quiz.setQuizId(101);
		List<Quiz> quizes = Arrays.asList(quiz);
		Mockito.when(entityManager.createQuery("from Quiz", Quiz.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(quizes);

		List<Quiz> retrievedQuizList = quizDao.viewQuiz();

		assertEquals(quizes, retrievedQuizList);

	}

	@Test
	void createQuizTest() {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		List<Question> questionsList = Arrays.asList(question);
		Quiz quiz = new Quiz("Basic", questionsList, 30);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		doNothing().when(entityManager).persist(quiz);
		Quiz addedQuiz = quizDao.createQuiz(quiz);

		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();
		assertEquals(quiz, addedQuiz);
	}

	@Test
	void removeQuizTest() {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		List<Question> questionsList = Arrays.asList(question);
		Quiz quiz = new Quiz("Basic", questionsList, 30);
		quiz.setQuizId(101);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Quiz.class, 101)).thenReturn(quiz);
		doNothing().when(entityManager).remove(quiz);
		Mockito.when(entityManager.contains(quiz)).thenReturn(true);
		boolean isRemoved = quizDao.removeQuiz(101);

		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();
		assertEquals(true, isRemoved);
	}

	@Test
	void addQuestionTest() {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		List<Question> questionsList = Arrays.asList(question);
		Quiz quiz = new Quiz("Basic", questionsList, 30);
		Question newQuestion = new Question("Java", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		newQuestion.setQuestionId(2);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Quiz.class, 101)).thenReturn(quiz);
		Mockito.when(entityManager.find(Question.class, 2)).thenReturn(newQuestion);
		Mockito.when(validation.getQuestionsInQuiz(quiz)).thenReturn(quizQuestions);
		Mockito.when(quizQuestions.add(newQuestion)).thenReturn(true);
		Mockito.when(entityManager.merge(quiz)).thenReturn(quiz);
		boolean isAdded = quizDao.addQuestion(101, 2);
		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();
		assertEquals(true, isAdded);
	}

	@Test
	void removeQuestionTest() {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		List<Question> questionsList = Arrays.asList(question);
		Quiz quiz = new Quiz("Basic", questionsList, 30);
		quiz.setQuizId(101);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Quiz.class, 101)).thenReturn(quiz);
		Mockito.when(entityManager.find(Question.class, 1)).thenReturn(question);
		Mockito.when(validation.getQuestionsInQuiz(quiz)).thenReturn(quizQuestions);
		Mockito.when(quizQuestions.remove(question)).thenReturn(true);

		boolean isRemoved = quizDao.removeQuestion(101,1);
		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();
		assertEquals(true, isRemoved);
	}

	@Test
	void updateMarksTest() {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		List<Question> questions = Arrays.asList(question);
		Quiz quiz = new Quiz("Basic", questions, 30);
		quiz.setQuizId(101);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Quiz.class, 101)).thenReturn(quiz);
		Mockito.when(entityManager.merge(quiz)).thenReturn(quiz);
		int updatedMarks = quizDao.updateMarks(101, 40);
		assertEquals(40, updatedMarks);

	}

	@Test
	void addNewQuestionTest() {
		Question newQuestion = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		newQuestion.setQuestionId(1);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Question.class, 1)).thenReturn(newQuestion);
		Question returnedQuestion = quizDao.addNewQuestion(1);
		assertEquals(newQuestion, returnedQuestion);
	}

	@Test
	void isQuestionPresentNegativeTest() throws InvalidQuestionIdEntryException {
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Question.class, 1)).thenReturn(null);
		assertThrows(InvalidQuestionIdEntryException.class, () -> quizDao.isQuestionIdPresent(1));
	}

	@Test
	void isQuestionPresentPositiveTest() throws InvalidQuestionIdEntryException {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Question.class, 1)).thenReturn(question);
		Mockito.when(entityManager.contains(question)).thenReturn(true);
		assertDoesNotThrow(() -> quizDao.isQuestionIdPresent(1));
	}

	@Test
	void isQuizTitlePresentNegativeTest() throws InvalidQuizIdEntryException {
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Quiz.class, 101)).thenReturn(null);
		Mockito.when(entityManager.contains(null)).thenReturn(false);
		assertThrows(InvalidQuizIdEntryException.class, () -> quizDao.isQuizTitlePresent(101));
	}

	@Test
	void isQuizTitlePresentPositiveTest() throws InvalidQuizIdEntryException {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		List<Question> questions = Arrays.asList(question);
		Quiz quiz = new Quiz("Basic", questions, 30);
		quiz.setQuizId(101);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Quiz.class, 101)).thenReturn(quiz);
		Mockito.when(entityManager.contains(quiz)).thenReturn(true);
		assertDoesNotThrow(() -> quizDao.isQuizTitlePresent(101));
	}

	@Test
	void isQuestionInQuizPresentNegativeTest() throws NoSuchQuestionExistInQuizException {
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		Question question2 = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(2);
		List<Question> questions = Arrays.asList(question);
		Quiz quiz = new Quiz("Basic", questions, 30);
		quiz.setQuizId(101);
		Mockito.when(entityManager.find(Question.class, 2)).thenReturn(question2);
		Mockito.when(entityManager.find(Quiz.class, 101)).thenReturn(quiz);
		assertThrows(NoSuchQuestionExistInQuizException.class, () -> quizDao.isQuestionInQuizPresent(101, 2));

	}

	@Test
	void isQuestionInQuizPresentPositiveTest() throws NoSuchQuestionExistInQuizException {
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		List<Question> questions = Arrays.asList(question);
		Quiz quiz = new Quiz("Basic", questions, 30);
		quiz.setQuizId(101);
		Mockito.when(entityManager.find(Question.class, 1)).thenReturn(question);
		Mockito.when(entityManager.find(Quiz.class, 101)).thenReturn(quiz);
		assertDoesNotThrow(() -> quizDao.isQuestionInQuizPresent(101, 1));

	}

}
