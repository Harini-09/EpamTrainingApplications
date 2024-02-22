package com.epam.quizapplication;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import customexceptions.InvalidQuestionIdEntryException;
import customexceptions.InvalidQuizTitleEntryException;
import customexceptions.NoSuchQuestionExistInQuizException;
import database.QuestionsLibrary;
import database.QuizLibrary;
import model.QuizQuestion;
import service.CrudOperationsOnQuiz;

class CrudOperationsOnQuizTest {

	CrudOperationsOnQuiz operations;

	@BeforeEach
	void getObjects() {
		operations = new CrudOperationsOnQuiz();
	}

	@Test
	void testIsQuestionIdPresentSuccessCase() throws InvalidQuestionIdEntryException {
		operations.isQuestionIdPresent("Q01");
		assertEquals(true, QuestionsLibrary.questions.containsKey("Q01"));

	}

	@Test
	void testIsQuestionIdPresentFailedCase() {
//		try {
//			operations.isQuestionIdPresent("Q11");
//		} catch (InvalidQuestionIdEntryException e) {
//			assertEquals("Warning!! The given Question id is not present in the library. Please enter a valid question id.",e.getMessage());
//		}
		assertThrows(InvalidQuestionIdEntryException.class,()->operations.isQuestionIdPresent("Q11"));
	}

	@Test
	void testIsQuizTitlePresentSuccessCase() throws InvalidQuizTitleEntryException {
		operations.isQuizTitlePresent("Quiz1");
		assertEquals(true, QuizLibrary.quizTitles.containsKey("Quiz1"));
	}

	@Test
	void testIsQuizTitlePresentFailedCase() {
//		try {
//			operations.isQuizTitlePresent("Quiz5");
//		} catch (InvalidQuizTitleEntryException e) {
//			assertEquals("Warning!! The entered Quiz Title is not present in the library. Please enter a valid Quiz Title.",e.getMessage());
//		}
		assertThrows(InvalidQuizTitleEntryException.class,()->operations.isQuizTitlePresent("Quiz5"));

	}

	@Test
	void testIsQuestionInQuizPresentSuccessCase() throws NoSuchQuestionExistInQuizException {
		operations.isQuestionInQuizPresent("Quiz1", "Q01");
		List<QuizQuestion> quizQuestions = QuizLibrary.quizTitles.get("Quiz1");
		Optional<QuizQuestion> optional = quizQuestions.stream().filter(t -> t.getQuestionid().equals("Q01"))
				.findFirst();
		assertEquals(true, optional.isPresent());
	}

	@Test
	void testIsQuestionInQuizPresentFailedCase() {
//		try {
//			operations.isQuestionInQuizPresent("Quiz1", "Q10");
//		} catch (NoSuchQuestionExistInQuizException e) {
//			assertEquals("Warning!! The entered Question Id is not present in the given Quiz. Please enter a valid Question Id present in the quiz.",e.getMessage());
//		}
		assertThrows(NoSuchQuestionExistInQuizException.class,()->operations.isQuestionInQuizPresent("Quiz1", "Q10"));
	}
	
	@Test
	void addNewQuestionTest() {
		QuizQuestion newQuestion = operations.addNewQuestion("Q01", 4);
		assertEquals(true,newQuestion.getQuestionid().equals("Q01"));
	}

	@Test
	void testInsertQuiz() {
		List<QuizQuestion> quizQuestions = new ArrayList<>();
		quizQuestions.add(new QuizQuestion("Q01", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), 4));
		boolean result = operations.insert("Quiz2", quizQuestions);
		assertEquals(true, result);
	}

	@Test
	void testInsertNull() {
		boolean result = operations.insert("Quiz3", null);
		assertEquals(false, result);
	}

	@Test
	void testDelete() {
		boolean result = operations.delete("Quiz1");
		assertEquals(false, result);
	}

	@Test
	void testView() {
		Set<Map.Entry<String, List<QuizQuestion>>> result = operations.view();
		assertEquals(QuizLibrary.quizTitles.entrySet(), result);
	}

	@Test
	void testViewNull() {
		Set<Map.Entry<String, List<QuizQuestion>>> result = null;
		assertEquals(null, result);
	}

	@Test
	void testAddQuestion() {
		String result = operations.addQuestion("Quiz1", "Q01", 4);
		assertEquals("Q01", result);
	}

	@Test 
	void testRemoveQuestion() {
		boolean result = operations.removeQuestion("Quiz1", "Q05");
		assertEquals(false, result);
	}
 
	@Test
	void testUpdateMarks() {
		int result = operations.updateMarks("Quiz1", "Q01", 5);
		assertEquals(5, result);
	}

}
