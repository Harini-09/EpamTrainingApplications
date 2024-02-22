package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import customexceptions.InvalidQuestionIdEntryException;
import customexceptions.InvalidQuizTitleEntryException;
import customexceptions.NoSuchQuestionExistInQuizException;
import database.QuizDao;
import entities.Question;
import entities.Quiz;

@ExtendWith(MockitoExtension.class)
class CrudOperationsOnQuizTest {

	@Mock
	QuizDao quizDao;

	@InjectMocks
	CrudOperationsOnQuiz crudOperations; 

	@Test
	void insertTest() {
		Question question = new Question("Q01", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		List<Question> questionsList = Arrays.asList(question);
		Quiz quiz = new Quiz(101, "Basic", questionsList, 30);
		Mockito.when(quizDao.createQuiz(101, quiz)).thenReturn(true);
		boolean isQuizCreated = crudOperations.insert(101, quiz);
		Mockito.verify(quizDao).createQuiz(101, quiz);
		assertEquals(true, isQuizCreated);
	}

	@Test
	void addQuestionTest() {
		Mockito.when(quizDao.addQuestion(101, "Q01")).thenReturn(true);
		boolean isQuestionAdded = crudOperations.addQuestion(101, "Q01");
		Mockito.verify(quizDao).addQuestion(101, "Q01");
		assertEquals(true, isQuestionAdded);
	}

	@Test
	void removeQuestionTest() {
		Mockito.when(quizDao.removeQuestion(101, "Q01")).thenReturn(false);
		boolean isQuestionPresent = crudOperations.removeQuestion(101, "Q01");
		Mockito.verify(quizDao).removeQuestion(101, "Q01");
		assertEquals(false, isQuestionPresent);
	} 

	@Test
	void updateMarksTest() {
		Mockito.when(quizDao.updateMarks(101, 40)).thenReturn(40);
		int resultMarks = crudOperations.updateMarks(101, 40);
		Mockito.verify(quizDao).updateMarks(101, 40);
		assertEquals(40, resultMarks);
	}

	@Test
	void deleteTest() {
		Mockito.when(quizDao.removeQuiz(101)).thenReturn(false);
		boolean isQuizPresent = crudOperations.delete(101);
		Mockito.verify(quizDao).removeQuiz(101);
		assertEquals(false, isQuizPresent);
	}

	@Test
	void viewTest() {
		List<Question> questionsList = Arrays
				.asList(new Question("Q01", "Collections", "Which collection stores unique values?",
						Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c"));
		Quiz quiz = new Quiz(101, "Basic", questionsList, 30);
		List<Quiz> quizes = Arrays.asList(quiz);
		Mockito.when(quizDao.viewQuiz()).thenReturn(quizes);
		List<Quiz> quizesList = crudOperations.view();
		Mockito.verify(quizDao).viewQuiz();
		assertEquals(quizes, quizesList);
	}

	@Test
	void addNewQuestionTest() {
		Question question = new Question("Q01", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		Mockito.when(quizDao.addNewQuestion("Q01")).thenReturn(question);
		Question resultQuestion = crudOperations.addNewQuestion("Q01");
		Mockito.verify(quizDao).addNewQuestion("Q01");
		assertEquals(question, resultQuestion);
	}

	@Test
	void isQuestionIdPresentNegativeTest() throws InvalidQuestionIdEntryException {
		doThrow(InvalidQuestionIdEntryException.class).when(quizDao).isQuestionIdPresent("Q01");
		assertThrows(InvalidQuestionIdEntryException.class, () -> crudOperations.isQuestionIdPresent("Q01"));
	}

	@Test
	void isQuestionIdPresentPositiveTest() throws InvalidQuestionIdEntryException {
		doNothing().when(quizDao).isQuestionIdPresent("Q01");
		crudOperations.isQuestionIdPresent("Q01");
		Mockito.verify(quizDao).isQuestionIdPresent("Q01");
	}

	@Test
	void isQuizTitlePresentNegativeTest() throws InvalidQuizTitleEntryException {
		doThrow(InvalidQuizTitleEntryException.class).when(quizDao).isQuizTitlePresent(101);
		assertThrows(InvalidQuizTitleEntryException.class, () -> crudOperations.isQuizTitlePresent(101));
	}

	@Test
	void isQuizTitlePresentPositiveTest() throws InvalidQuizTitleEntryException {
		doNothing().when(quizDao).isQuizTitlePresent(101);
		crudOperations.isQuizTitlePresent(101);
		Mockito.verify(quizDao).isQuizTitlePresent(101);
	}

	@Test
	void isQuestionInQuizPresentNegativeTest() throws NoSuchQuestionExistInQuizException {
		doThrow(NoSuchQuestionExistInQuizException.class).when(quizDao).isQuestionInQuizPresent(101, "Q01");
		assertThrows(NoSuchQuestionExistInQuizException.class,
				() -> crudOperations.isQuestionInQuizPresent(101, "Q01"));
	}

	@Test 
	void isQuestionInQuizPresentPositiveTest() throws NoSuchQuestionExistInQuizException {
		doNothing().when(quizDao).isQuestionInQuizPresent(101, "Q01");
		crudOperations.isQuestionInQuizPresent(101, "Q01");
		Mockito.verify(quizDao).isQuestionInQuizPresent(101, "Q01");
	}

}
