package com.epam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.entities.Question;
import com.epam.repository.QuestionRepo;

@ExtendWith(MockitoExtension.class)
class QuestionLibraryServiceTest {

	@Mock 
	private QuestionRepo questionRepo;

	@InjectMocks
	private QuestionLibraryService crudOperations;
	
	private Question question;
	
	private Optional<Question> optional;

	@BeforeEach 
	void setUp() {
		question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		optional = Optional.of(question);
	}
	
	@Test
	void insertTest() {
		Mockito.when(questionRepo.save(question)).thenReturn(question);
		Question addedQuestion = crudOperations.insert(question);
		assertEquals(question, addedQuestion);
	}

	@Test
	void modifyTitleTest() {
		Mockito.when(questionRepo.findById(1)).thenReturn(optional);
		Mockito.when(questionRepo.save(question)).thenReturn(question);
		String title = crudOperations.modifyTitle(1, "Collections");
		assertEquals("Collections", title); 
	}
	
	@Test
	void modifyQuestionTest() {
		Mockito.when(questionRepo.findById(1)).thenReturn(optional);
		Mockito.when(questionRepo.save(question)).thenReturn(question);
		String questionDesc = crudOperations.modifyQuestion(1, "Which method is used to get the length of a string?");
		assertEquals("Which method is used to get the length of a string?", questionDesc); 
	}

	@Test
	void modifyOptionsTest() {
		List<String> options = Arrays.asList("a.len()", "b.size()", "c.length()");
		Mockito.when(questionRepo.findById(1)).thenReturn(optional);
		Mockito.when(questionRepo.save(question)).thenReturn(question);
		List<String> modifiedOptions = crudOperations.modifyOptions(1, options);
		assertEquals(options, modifiedOptions); 
	}

	@Test
	void modifyQuestionLevelTest() {
		Mockito.when(questionRepo.findById(1)).thenReturn(optional);
		Mockito.when(questionRepo.save(question)).thenReturn(question);
		String resultQuestionLevel = crudOperations.modifyQuestionLevel(1, "Basic");
		assertEquals("Basic", resultQuestionLevel); 	}

	@Test
	void modifyTopicTagTest() {
		Mockito.when(questionRepo.findById(1)).thenReturn(optional);
		Mockito.when(questionRepo.save(question)).thenReturn(question);
		String topicTag = crudOperations.modifyTopicTag(1, "Java");
		assertEquals("Java", topicTag);
	}

	@Test 
	void modifyAnswerTest() {
		Mockito.when(questionRepo.findById(1)).thenReturn(optional);
		Mockito.when(questionRepo.save(question)).thenReturn(question);
		String answer = crudOperations.modifyAnswer(1, "a");
		assertEquals("a", answer);
	}

	@Test
	void deleteTest() {
		Mockito.when(questionRepo.findById(1)).thenReturn(optional);
		doNothing().when(questionRepo).delete(question);
		boolean isQuestionPresent = crudOperations.delete(1);
		assertEquals(true, isQuestionPresent); 
	}

	@Test
	void viewQuestionsTest() {
		Question question1 = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question1.setQuestionId(1);
		Question question2 = new Question("Strings", "Which method is used to get the length of a string?",
				Arrays.asList("a.length()", "b.size()", "c.len()", "d.none"), "Easy", "Java", "a");
		question2.setQuestionId(2);
		List<Question> questions = Arrays.asList(question1, question2);
		Mockito.when(questionRepo.findAll()).thenReturn(questions);
		List<Question> resultQuestions = crudOperations.viewQuestions();
		assertEquals(questions, resultQuestions);
	}
 
	@Test 
	void isQuestionIdPresentNegativeTest() throws InvalidQuestionIdEntryException {
		Mockito.when(questionRepo.existsById(1)).thenReturn(false);
		assertThrows(InvalidQuestionIdEntryException.class, () -> crudOperations.isQuestionIdPresent(1));
	}  
	
	@Test
	void isQuestionIdPresentPositiveTest() throws InvalidQuestionIdEntryException {
		Mockito.when(questionRepo.existsById(1)).thenReturn(true);
		crudOperations.isQuestionIdPresent(1);
		Mockito.verify(questionRepo).existsById(1);
	}
}
