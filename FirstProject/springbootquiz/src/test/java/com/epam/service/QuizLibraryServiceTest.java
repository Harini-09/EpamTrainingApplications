package com.epam.service;

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

import com.epam.database.QuizDao;

import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.customexceptions.InvalidQuizIdEntryException;
import com.epam.customexceptions.NoSuchQuestionExistInQuizException;
import com.epam.entities.Question;
import com.epam.entities.Quiz;

@ExtendWith(MockitoExtension.class)
class QuizLibraryServiceTest {

	@Mock
	QuizDao quizDao;

	@InjectMocks
	QuizLibraryService crudOperations;

	@Test
	void insertTest() {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		List<Question> questionsList = Arrays.asList(question);
		Quiz quiz = new Quiz("Basic", questionsList, 30);
		quiz.setQuizId(101);
		Mockito.when(quizDao.createQuiz(quiz)).thenReturn(quiz);
		Quiz addedQuiz = crudOperations.insert(quiz);
		Mockito.verify(quizDao).createQuiz(quiz);
		assertEquals(quiz, addedQuiz);
	}

	@Test
	void addQuestionTest() {
		Mockito.when(quizDao.addQuestion(101, 1)).thenReturn(true);
		boolean isQuestionAdded = crudOperations.addQuestion(101, 1);
		Mockito.verify(quizDao).addQuestion(101, 1);
		assertEquals(true, isQuestionAdded);
	}

	@Test
	void removeQuestionTest() {
		Mockito.when(quizDao.removeQuestion(101, 1)).thenReturn(false);
		boolean isQuestionPresent = crudOperations.removeQuestion(101, 1);
		Mockito.verify(quizDao).removeQuestion(101, 1);
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
		Question question1 = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question1.setQuestionId(1);
		List<Question> questionsList = Arrays.asList(question1);
		Quiz quiz = new Quiz("Basic", questionsList, 30);
		quiz.setQuizId(101);
		List<Quiz> quizes = Arrays.asList(quiz);
		Mockito.when(quizDao.viewQuiz()).thenReturn(quizes);
		List<Quiz> quizesList = crudOperations.view();
		Mockito.verify(quizDao).viewQuiz();
		assertEquals(quizes, quizesList);
	}

	@Test
	void addNewQuestionTest() {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		Mockito.when(quizDao.addNewQuestion(1)).thenReturn(question);
		Question resultQuestion = crudOperations.addNewQuestion(1);
		Mockito.verify(quizDao).addNewQuestion(1);
		assertEquals(question, resultQuestion);
	}

	@Test
	void isQuestionIdPresentNegativeTest() throws InvalidQuestionIdEntryException {
		doThrow(InvalidQuestionIdEntryException.class).when(quizDao).isQuestionIdPresent(1);
		assertThrows(InvalidQuestionIdEntryException.class, () -> crudOperations.isQuestionIdPresent(1));
	}

	@Test
	void isQuestionIdPresentPositiveTest() throws InvalidQuestionIdEntryException {
		doNothing().when(quizDao).isQuestionIdPresent(1);
		crudOperations.isQuestionIdPresent(1);
		Mockito.verify(quizDao).isQuestionIdPresent(1);
	}

	@Test
	void isQuizTitlePresentNegativeTest() throws InvalidQuizIdEntryException {
		doThrow(InvalidQuizIdEntryException.class).when(quizDao).isQuizTitlePresent(101);
		assertThrows(InvalidQuizIdEntryException.class, () -> crudOperations.isQuizTitlePresent(101));
	}

	@Test
	void isQuizTitlePresentPositiveTest() throws InvalidQuizIdEntryException {
		doNothing().when(quizDao).isQuizTitlePresent(101);
		crudOperations.isQuizTitlePresent(101);
		Mockito.verify(quizDao).isQuizTitlePresent(101);
	}

	@Test
	void isQuestionInQuizPresentNegativeTest() throws NoSuchQuestionExistInQuizException {
		doThrow(NoSuchQuestionExistInQuizException.class).when(quizDao).isQuestionInQuizPresent(101, 1);
		assertThrows(NoSuchQuestionExistInQuizException.class, () -> crudOperations.isQuestionInQuizPresent(101, 1));
	}

	@Test
	void isQuestionInQuizPresentPositiveTest() throws NoSuchQuestionExistInQuizException {
		doNothing().when(quizDao).isQuestionInQuizPresent(101, 1);
		crudOperations.isQuestionInQuizPresent(101, 1);
		Mockito.verify(quizDao).isQuestionInQuizPresent(101, 1);
	}

}
