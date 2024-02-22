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

import com.epam.database.QuestionsDao;

import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.entities.Question;

@ExtendWith(MockitoExtension.class)
class QuestionLibraryServiceTest {

	@Mock
	private QuestionsDao questionsDao;

	@InjectMocks
	private QuestionLibraryService crudOperations;

	@Test
	void insertTest() {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		Mockito.when(questionsDao.addQuestion(question)).thenReturn(question);
		Question addedQuestion = crudOperations.insert(question);
		Mockito.verify(questionsDao).addQuestion(question);
		assertEquals(question, addedQuestion);
	}

	@Test
	void modifyTitleTest() {
		Mockito.when(questionsDao.updateTitle(1, "Collections")).thenReturn("Collections");
		String title = crudOperations.modifyTitle(1, "Collections");
		Mockito.verify(questionsDao).updateTitle(1, "Collections");
		assertEquals("Collections", title);
	}

	@Test
	void modifyQuestionTest() {
		Mockito.when(questionsDao.updateQuestionDescription(1, "Which method is used to get the length of a string?"))
				.thenReturn("Which method is used to get the length of a string?");
		String question = crudOperations.modifyQuestion(1, "Which method is used to get the length of a string?");
		Mockito.verify(questionsDao).updateQuestionDescription(1, "Which method is used to get the length of a string?");
		assertEquals("Which method is used to get the length of a string?", question);
	}

	@Test
	void modifyOptionsTest() {
		List<String> options = Arrays.asList("a.len()", "b.size()", "c.length()");
		Mockito.when(questionsDao.updateOptions(1, options)).thenReturn(options);
		List<String> resultOptions = crudOperations.modifyOptions(1, options);
		Mockito.verify(questionsDao).updateOptions(1, options);
		assertEquals(options, resultOptions);
	}

	@Test
	void modifyQuestionLevelTest() {
		Mockito.when(questionsDao.updateQuestionLevel(1, "Basic")).thenReturn("Basic");
		String resultQuestionLevel = crudOperations.modifyQuestionLevel(1, "Basic");
		Mockito.verify(questionsDao).updateQuestionLevel(1, "Basic");
		assertEquals("Basic", resultQuestionLevel);
	}

	@Test
	void modifyTopicTagTest() {
		Mockito.when(questionsDao.updateTopicTag(1, "Java")).thenReturn("Java");
		String topicTag = crudOperations.modifyTopicTag(1, "Java");
		Mockito.verify(questionsDao).updateTopicTag(1, "Java");
		assertEquals("Java", topicTag);
	}

	@Test
	void modifyAnswerTest() {
		Mockito.when(questionsDao.updateAnswer(1, "a")).thenReturn("a");
		String answer = crudOperations.modifyAnswer(1, "a");
		Mockito.verify(questionsDao).updateAnswer(1, "a");
		assertEquals("a", answer);
	}

	@Test
	void deleteTest() {
		Mockito.when(questionsDao.removeQuestion(1)).thenReturn(false);
		boolean isQuestionPresent = crudOperations.delete(1);
		Mockito.verify(questionsDao).removeQuestion(1);
		assertEquals(false, isQuestionPresent);
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
		Mockito.when(questionsDao.viewQuestions()).thenReturn(questions);
		List<Question> resultQuestions = crudOperations.viewQuestions();
		Mockito.verify(questionsDao).viewQuestions();
		assertEquals(questions, resultQuestions);
	}

	@Test 
	void isQuestionIdPresentNegativeTest() throws InvalidQuestionIdEntryException {
		doThrow(InvalidQuestionIdEntryException.class).when(questionsDao).isQuestionPresent(1);
		assertThrows(InvalidQuestionIdEntryException.class, () -> crudOperations.isQuestionIdPresent(1));
	} 

	@Test
	void isQuestionIdPresentPositiveTest() throws InvalidQuestionIdEntryException {
		doNothing().when(questionsDao).isQuestionPresent(1);
		crudOperations.isQuestionIdPresent(1);
		Mockito.verify(questionsDao).isQuestionPresent(1);
	}
}
