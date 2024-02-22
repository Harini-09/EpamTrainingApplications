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
import com.epam.entities.Question;

@ExtendWith(MockitoExtension.class)
class QuestionsDaoJpaImplTest {

	@Mock
	private EntityManager entityManager;

	@Mock
	private EntityTransaction transaction;

	@InjectMocks
	private QuestionsDaoImpl questionDao;

	@Mock
	private TypedQuery<Question> query;

	@Test
	void addQuestionTest() {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		doNothing().when(entityManager).persist(question);
		Question addedQuestion = questionDao.addQuestion(question);

		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();
		assertEquals(question, addedQuestion);
	}

	@Test
	void viewQuestionsTest() {
		Question question1 = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question1.setQuestionId(1);
		Question question2 = new Question("Strings", "Which method is used to get the length of a string?",
				Arrays.asList("a.length()", "b.size()", "c.len()", "d.none"), "Easy", "Java", "a");
		question2.setQuestionId(2);
		List<Question> questionsList = Arrays.asList(question1, question2);
		Mockito.when(entityManager.createQuery("from Question", Question.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(questionsList);

		List<Question> retrievedQuestionsList = questionDao.viewQuestions();

		assertEquals(questionsList, retrievedQuestionsList);
	}

	@Test
	void removeQuestionTest() {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Question.class, 1)).thenReturn(question);
		doNothing().when(entityManager).remove(question);
		Mockito.when(entityManager.contains(question)).thenReturn(true);
		boolean isRemoved = questionDao.removeQuestion(1);

		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();
		assertEquals(true, isRemoved);
	}

	@Test
	void updateTitleTest() {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Question.class, 1)).thenReturn(question);
		Mockito.when(entityManager.merge(question)).thenReturn(question);
		String updatedTitle = questionDao.updateTitle(1, "Strings");

		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager).merge(question);
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();

		assertEquals("Strings", updatedTitle);
	}

	@Test
	void updateQuestionTest() {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Question.class, 1)).thenReturn(question);
		Mockito.when(entityManager.merge(question)).thenReturn(question);
		String updatedQuestion = questionDao.updateQuestionDescription(1, "Which method is used to find the length of an array?");

		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager).merge(question);
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();

		assertEquals("Which method is used to find the length of an array?", updatedQuestion);
	}

	@Test
	void updateOptionsTest() {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		List<String> options = Arrays.asList("a.true", "b.false");
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Question.class, 1)).thenReturn(question);
		Mockito.when(entityManager.merge(question)).thenReturn(question);
		List<String> updatedOptions = questionDao.updateOptions(1, options);

		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager).merge(question);
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();

		assertEquals(options, updatedOptions);
	}

	@Test
	void updateQuestionLevelTest() {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Question.class, 1)).thenReturn(question);
		Mockito.when(entityManager.merge(question)).thenReturn(question);
		String updatedQuestionLevel = questionDao.updateQuestionLevel(1, "Hard");

		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager).merge(question);
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();

		assertEquals("Hard", updatedQuestionLevel);
	}

	@Test
	void updateTopicTagTest() {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Question.class, 1)).thenReturn(question);
		Mockito.when(entityManager.merge(question)).thenReturn(question);
		String updatedTopicTag = questionDao.updateTopicTag(1, "Java");

		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager).merge(question);
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();

		assertEquals("Java", updatedTopicTag);
	}

	@Test
	void updateAnswerTest() {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.Map", "b.Set", "c.List", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Question.class, 1)).thenReturn(question);
		Mockito.when(entityManager.merge(question)).thenReturn(question);
		String updatedAnswer = questionDao.updateAnswer(1, "b.Set");

		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager).merge(question);
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();

		assertEquals("b.Set", updatedAnswer);
	}

	@Test
	void isQuestionPresentNegativeTest() throws InvalidQuestionIdEntryException {
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Question.class, 1)).thenReturn(null);
		assertThrows(InvalidQuestionIdEntryException.class, () -> questionDao.isQuestionPresent(1));
	}

	@Test
	void isQuestionPresentPositiveTest() throws InvalidQuestionIdEntryException {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Question.class, 1)).thenReturn(question);
		Mockito.when(entityManager.contains(question)).thenReturn(true);
		assertDoesNotThrow(() -> questionDao.isQuestionPresent(1));
	}
}
