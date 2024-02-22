package com.epam.quizapplication;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import customexceptions.InvalidQuestionIdEntryException;
import database.QuestionsLibrary;
import model.Question;
import service.CrudOperationsOnQuestions;

class CrudOperationsOnQuestionsTest {

	CrudOperationsOnQuestions operations;
	QuestionsLibrary questions;

	@BeforeEach
	void getObject() {
		operations = new CrudOperationsOnQuestions();
	}

	@Test
	void testisQuestionIdPresentSuccessCase() throws InvalidQuestionIdEntryException {
		operations.isQuestionIdPresent("Q05");
		assertEquals(true,QuestionsLibrary.questions.containsKey("Q05"));
	} 

	@Test
	void testisQuestionIdPresentFailedCase() {
		assertThrows(InvalidQuestionIdEntryException.class,()->operations.isQuestionIdPresent("Q11"));
	}  

	@Test
	void testInsert1() { 
		Question newQuestion = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Easy", "Java", "(c)Set");
		boolean result = operations.insert("Q06", newQuestion);
		assertEquals(true, result);
	}

	@Test
	void testInsert2() {
		boolean result = operations.insert("Q06", null);
		assertEquals(false, result);
	}

	@Test
	void testDelete() {
		boolean result = operations.delete("Q01");
		assertEquals(false, result);
	}

	@Test
	void testModifyTitle() {
		String result = operations.modifyTitle("Q01", "StreamsApi");
		assertEquals("StreamsApi", result);
	}

	@Test
	void testModifyQuestion() {
		String result = operations.modifyQuestion("Q03", "Which collections stores unique values");
		assertEquals("Which collections stores unique values", result);
	}

	@Test
	void testModifyOptions() {
		List<String> options = Arrays.asList("a)list", "b)set", "c)map");
		List<String> result = operations.modifyOptions("Q01", options);
		assertEquals(options, result);
	}

	@Test
	void testModifyQuestionlevel() {
		String result = operations.modifyQuestionLevel("Q03", "Hard");
		assertEquals("Hard", result);
	}

	@Test
	void testModifyTopicTag() {
		String result = operations.modifyTopicTag("Q05", "Strings");
		assertEquals("Strings", result);
	}

	@Test
	void testModifyAnswer() {
		String result = operations.modifyAnswer("Q04", "(d)Stack");
		assertEquals("(d)Stack", result);
	}

	@Test
	void testView() {
		Set<Map.Entry<String, Question>> questionSet = QuestionsLibrary.questions.entrySet();
		Set<Map.Entry<String, Question>> result = operations.view();
		assertEquals(questionSet, result);
	}

}
