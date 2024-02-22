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
import database.QuestionsDao;
import entities.Question;

@ExtendWith(MockitoExtension.class)
class CrudOperationsOnQuestionsTest {

	@Mock
	private QuestionsDao questionsDao;

	@InjectMocks
	private CrudOperationsOnQuestions crudOperations;

	@Test
	void insertTest() {
		Question question = new Question("Q01", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		Mockito.when(questionsDao.addQuestion("Q01", question)).thenReturn(true);
		boolean isQuestionAdded = crudOperations.insert("Q01", question);
		Mockito.verify(questionsDao).addQuestion("Q01", question);
		assertEquals(true, isQuestionAdded);
	}

	@Test
	void modifyTitleTest() {
		Mockito.when(questionsDao.updateTitle("Q01", "Collections")).thenReturn("Collections");
		String title = crudOperations.modifyTitle("Q01", "Collections");
		Mockito.verify(questionsDao).updateTitle("Q01", "Collections");
		assertEquals("Collections", title);
	}

	@Test
	void modifyQuestionTest() {
		Mockito.when(questionsDao.updateQuestion("Q01", "Which method is used to get the length of a string?"))
				.thenReturn("Which method is used to get the length of a string?");
		String question = crudOperations.modifyQuestion("Q01", "Which method is used to get the length of a string?");
		Mockito.verify(questionsDao).updateQuestion("Q01", "Which method is used to get the length of a string?");
		assertEquals("Which method is used to get the length of a string?", question);
	}

	@Test
	void modifyOptionsTest() {
		List<String> options = Arrays.asList("a.len()", "b.size()", "c.length()");
		Mockito.when(questionsDao.updateOptions("Q01", options)).thenReturn(options);
		List<String> resultOptions = crudOperations.modifyOptions("Q01", options);
		Mockito.verify(questionsDao).updateOptions("Q01", options);
		assertEquals(options, resultOptions);
	}

	@Test
	void modifyQuestionLevelTest() {
		Mockito.when(questionsDao.updateQuestionLevel("Q01", "Basic")).thenReturn("Basic");
		String resultQuestionLevel = crudOperations.modifyQuestionLevel("Q01", "Basic");
		Mockito.verify(questionsDao).updateQuestionLevel("Q01", "Basic");
		assertEquals("Basic", resultQuestionLevel);
	}

	@Test
	void modifyTopicTagTest() {
		Mockito.when(questionsDao.updateTopicTag("Q01", "Java")).thenReturn("Java");
		String topicTag = crudOperations.modifyTopicTag("Q01", "Java");
		Mockito.verify(questionsDao).updateTopicTag("Q01", "Java");
		assertEquals("Java", topicTag);
	}

	@Test
	void modifyAnswerTest() {
		Mockito.when(questionsDao.updateAnswer("Q01", "a")).thenReturn("a");
		String answer = crudOperations.modifyAnswer("Q01", "a");
		Mockito.verify(questionsDao).updateAnswer("Q01", "a");
		assertEquals("a", answer);
	}

	@Test
	void deleteTest() {
		Mockito.when(questionsDao.removeQuestion("Q01")).thenReturn(false);
		boolean isQuestionPresent = crudOperations.delete("Q01");
		Mockito.verify(questionsDao).removeQuestion("Q01");
		assertEquals(false, isQuestionPresent);
	}

	@Test
	void viewQuestionsTest() {
		List<Question> questions = Arrays.asList(
				(new Question("Q01", "Collections", "Which collection stores unique values?",
						Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c")),
				(new Question("Q02", "Strings", "Which method is used to get the length of a string?",
						Arrays.asList("a.length()", "b.size()", "c.len()", "d.none"), "Easy", "Java", "a")));
		Mockito.when(questionsDao.viewQuestions()).thenReturn(questions);
		List<Question> resultQuestions = crudOperations.viewQuestions();
		Mockito.verify(questionsDao).viewQuestions();
		assertEquals(questions, resultQuestions);
	}

	@Test
	void isQuestionIdPresentNegativeTest() throws InvalidQuestionIdEntryException {
		doThrow(InvalidQuestionIdEntryException.class).when(questionsDao).isQuestionPresent("Q01");
		assertThrows(InvalidQuestionIdEntryException.class, () -> crudOperations.isQuestionIdPresent("Q01"));
	}

	@Test
	void isQuestionIdPresentPositiveTest() throws InvalidQuestionIdEntryException {
		doNothing().when(questionsDao).isQuestionPresent("Q01");
		crudOperations.isQuestionIdPresent("Q01");
		Mockito.verify(questionsDao).isQuestionPresent("Q01");
	}
}
