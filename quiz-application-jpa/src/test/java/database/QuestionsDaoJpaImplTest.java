package database;

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

import customexceptions.InvalidQuestionIdEntryException;
import model.Question;

@ExtendWith(MockitoExtension.class)
class QuestionsDaoJpaImplTest {

	@Mock
	private EntityManager entityManager;

	@Mock
	private EntityTransaction transaction;

	@InjectMocks
	private QuestionsDAOJpaImpl questionDao;

	@Mock
	private TypedQuery<Question> query;

	@Mock
	private Validation validation;

	@Test
	void addQuestionTest() {
		Question question = new Question("Q01", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		doNothing().when(entityManager).persist(question);
		Mockito.when(entityManager.contains(question)).thenReturn(true);
		boolean isAdded = questionDao.addQuestion("Q01", question);

		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();
		assertEquals(true, isAdded);
	}

	@Test
	void viewQuestionsTest() {
		List<Question> questionsList = Arrays.asList(
				new Question("Q01", "Collections", "Which collection stores unique values?",
						Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c"),
				new Question("Q02", "Strings", "Which method is used to get the length of a string?",
						Arrays.asList("a.length()", "b.size()", "c.len()", "d.none"), "Easy", "Java", "a"));
		Mockito.when(entityManager.createQuery("from Question", Question.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(questionsList);

		List<Question> retrievedQuestionsList = questionDao.viewQuestions();

		assertEquals(questionsList, retrievedQuestionsList);
	}

	@Test
	void removeQuestionTest() {
		Question question = new Question("Q01", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");

		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(validation.getQuestion("Q01")).thenReturn(question);
		doNothing().when(entityManager).remove(question);
		Mockito.when(entityManager.contains(question)).thenReturn(true);
		boolean isRemoved = questionDao.removeQuestion("Q01");

		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();
		assertEquals(true, isRemoved);
	}

	@Test
	void updateTitleTest() {
		Question question = new Question("Q01", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");

		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(validation.getQuestion("Q01")).thenReturn(question);
		Mockito.when(entityManager.merge(question)).thenReturn(question);
		String updatedTitle = questionDao.updateTitle("Q01", "Strings");

		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager).merge(question);
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();

		assertEquals("Strings", updatedTitle);
	}

	@Test
	void updateQuestionTest() {
		Question question = new Question("Q01", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");

		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(validation.getQuestion("Q01")).thenReturn(question);
		Mockito.when(entityManager.merge(question)).thenReturn(question);
		String updatedQuestion = questionDao.updateQuestion("Q01",
				"Which method is used to find the length of an array?");

		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager).merge(question);
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();

		assertEquals("Which method is used to find the length of an array?", updatedQuestion);
	}

	@Test
	void updateOptionsTest() {
		Question question = new Question("Q01", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		List<String> options = Arrays.asList("a.true", "b.false");
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(validation.getQuestion("Q01")).thenReturn(question);
		Mockito.when(entityManager.merge(question)).thenReturn(question);
		List<String> updatedOptions = questionDao.updateOptions("Q01", options);

		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager).merge(question);
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();

		assertEquals(options, updatedOptions);
	}

	@Test
	void updateQuestionLevelTest() {
		Question question = new Question("Q01", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(validation.getQuestion("Q01")).thenReturn(question);
		Mockito.when(entityManager.merge(question)).thenReturn(question);
		String updatedQuestionLevel = questionDao.updateQuestionLevel("Q01", "Hard");

		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager).merge(question);
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();

		assertEquals("Hard", updatedQuestionLevel);
	}

	@Test
	void updateTopicTagTest() {
		Question question = new Question("Q01", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(validation.getQuestion("Q01")).thenReturn(question);
		Mockito.when(entityManager.merge(question)).thenReturn(question);
		String updatedTopicTag = questionDao.updateTopicTag("Q01", "Java");

		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager).merge(question);
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();

		assertEquals("Java", updatedTopicTag);
	}

	@Test
	void updateAnswerTest() {
		Question question = new Question("Q01", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.Map", "b.Set", "c.List", "d.Stack"), "Intermediate", "Java", "c");
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(validation.getQuestion("Q01")).thenReturn(question);
		Mockito.when(entityManager.merge(question)).thenReturn(question);
		String updatedAnswer = questionDao.updateAnswer("Q01", "b.Set");

		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager).merge(question);
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();

		assertEquals("b.Set", updatedAnswer);
	}

	@Test
	void isQuestionPresentNegativeTest() throws InvalidQuestionIdEntryException {
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(validation.getQuestion("Q01")).thenReturn(null);
		assertThrows(InvalidQuestionIdEntryException.class, () -> questionDao.isQuestionPresent("Q01"));
	}

	@Test
	void isQuestionPresentPositiveTest() throws InvalidQuestionIdEntryException {
		Question question = new Question("Q01", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(validation.getQuestion("Q01")).thenReturn(question);
		Mockito.when(entityManager.contains(question)).thenReturn(true);
		assertDoesNotThrow(() -> questionDao.isQuestionPresent("Q01"));
	}
}
