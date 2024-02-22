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
import customexceptions.InvalidQuizTitleEntryException;
import customexceptions.NoSuchQuestionExistInQuizException;
import model.Question;
import model.Quiz;

@ExtendWith(MockitoExtension.class)
class QuizDaoJpaImplTest {

	@Mock
	private EntityManager entityManager;

	@Mock
	private EntityTransaction transaction;

	@InjectMocks
	private QuizDAOJpaImpl quizDao;

	@Mock
	private TypedQuery<Quiz> query;

	@Mock
	private Validation validation;
	
	@Mock
	private List<Question> quizQuestions;

	@Test
	void viewQuizTest() {
		List<Question> questionsList = Arrays
				.asList(new Question("Q01", "Collections", "Which collection stores unique values?",
		 				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c"));
	 	Quiz quiz = new Quiz(101, "Basic", questionsList, 30);
		List<Quiz> quizes = Arrays.asList(quiz);
		Mockito.when(entityManager.createQuery("from Quiz", Quiz.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(quizes);

		List<Quiz> retrievedQuizList = quizDao.viewQuiz();

		assertEquals(quizes, retrievedQuizList);

	}

	@Test
	void createQuizTest() {
		List<Question> questionsList = Arrays
				.asList(new Question("Q01", "Collections", "Which collection stores unique values?",
						Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c"));
		Quiz quiz = new Quiz(101, "Basic", questionsList, 30);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		doNothing().when(entityManager).persist(quiz);
		Mockito.when(entityManager.contains(quiz)).thenReturn(true);
		boolean isAdded = quizDao.createQuiz(101, quiz);

		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();
		assertEquals(true, isAdded);
	}

	@Test
	void removeQuizTest() {
		List<Question> questionsList = Arrays
				.asList(new Question("Q01", "Collections", "Which collection stores unique values?",
						Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c"));
		Quiz quiz = new Quiz(101, "Basic", questionsList, 30);
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
		List<Question> questionsList = Arrays
				.asList(new Question("Q01", "Collections", "Which collection stores unique values?",
						Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c"));
		Quiz quiz = new Quiz(101, "Basic", questionsList, 30);
		Question newQuestion = new Question("Q02", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");

		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Quiz.class, 101)).thenReturn(quiz);
		Mockito.when(validation.getQuestion("Q02")).thenReturn(newQuestion);
		Mockito.when(validation.getQuestionsInQuiz(quiz)).thenReturn(quizQuestions);
		Mockito.when(quizQuestions.add(newQuestion)).thenReturn(true);
		Mockito.when(entityManager.merge(quiz)).thenReturn(quiz);
		boolean isAdded = quizDao.addQuestion(101, "Q02");
		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();
		assertEquals(true, isAdded);
	}

	@Test
	void removeQuestionTest() {
		List<Question> questionsList = Arrays
				.asList(new Question("Q01", "Collections", "Which collection stores unique values?",
						Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c"));
		Quiz quiz = new Quiz(101, "Basic", questionsList, 30);
		Question question = new Question("Q01", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");

		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Quiz.class, 101)).thenReturn(quiz);
		Mockito.when(validation.getQuestion("Q01")).thenReturn(question);
		Mockito.when(validation.getQuestionsInQuiz(quiz)).thenReturn(quizQuestions);
		Mockito.when(quizQuestions.remove(question)).thenReturn(true);
		
		boolean isRemoved = quizDao.removeQuestion(101, "Q01");
		Mockito.verify(entityManager, times(2)).getTransaction();
		Mockito.verify(entityManager.getTransaction()).begin();
		Mockito.verify(entityManager.getTransaction()).commit();
		assertEquals(true, isRemoved); 
	}

	@Test
	void updateMarksTest() {
		List<Question> questions = Arrays
				.asList(new Question("Q01", "Collections", "Which collection stores unique values?",
						Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c"));
		Quiz quiz = new Quiz(101, "Basic", questions, 30);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Quiz.class, 101)).thenReturn(quiz);
		Mockito.when(entityManager.merge(quiz)).thenReturn(quiz);
		int updatedMarks = quizDao.updateMarks(101, 40);
		assertEquals(40, updatedMarks);

	}

	@Test
	void addNewQuestionTest() {
		Question newQuestion = new Question("Q02", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(validation.getQuestion("Q01")).thenReturn(newQuestion);
		Question returnedQuestion = quizDao.addNewQuestion("Q01");
		assertEquals(newQuestion, returnedQuestion);
	}

	@Test
	void isQuestionPresentNegativeTest() throws InvalidQuestionIdEntryException {
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(validation.getQuestion("Q02")).thenReturn(null);
		assertThrows(InvalidQuestionIdEntryException.class, () -> quizDao.isQuestionIdPresent("Q02"));
	}
	 
	@Test
	void isQuestionPresentPositiveTest() throws InvalidQuestionIdEntryException {
		Question question = new Question("Q01", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(validation.getQuestion("Q01")).thenReturn(question);
		Mockito.when(entityManager.contains(question)).thenReturn(true);
		assertDoesNotThrow(() -> quizDao.isQuestionIdPresent("Q01")); 
	}
	

	@Test
	void isQuizTitlePresentNegativeTest() throws InvalidQuizTitleEntryException {
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Quiz.class, 101)).thenReturn(null);
		Mockito.when(entityManager.contains(null)).thenReturn(false);
		assertThrows(InvalidQuizTitleEntryException.class, () -> quizDao.isQuizTitlePresent(101));
	}
	
	@Test
	void isQuizTitlePresentPositiveTest() throws InvalidQuizTitleEntryException {
		Question question = new Question("Q01", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		List<Question> questions = Arrays.asList(question);
		Quiz quiz = new Quiz(101, "Basic", questions, 30);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Quiz.class, 101)).thenReturn(quiz);
		Mockito.when(entityManager.contains(quiz)).thenReturn(true);
		assertDoesNotThrow(() -> quizDao.isQuizTitlePresent(101)); 
	}

	@Test
	void isQuestionInQuizPresentNegativeTest() throws NoSuchQuestionExistInQuizException {
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Question question = new Question("Q01", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		Question question2 = new Question("Q02", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		List<Question> questions = Arrays.asList(question);
		Quiz quiz = new Quiz(101, "Basic", questions, 30);
		Mockito.when(validation.getQuestion("Q02")).thenReturn(question2);
		Mockito.when(entityManager.find(Quiz.class, 101)).thenReturn(quiz);
		assertThrows(NoSuchQuestionExistInQuizException.class, () -> quizDao.isQuestionInQuizPresent(101, "Q02"));

	}
	
	@Test
	void isQuestionInQuizPresentPositiveTest() throws NoSuchQuestionExistInQuizException {
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Question question = new Question("Q01", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		List<Question> questions = Arrays.asList(question);
		Quiz quiz = new Quiz(101, "Basic", questions, 30);
		Mockito.when(validation.getQuestion("Q01")).thenReturn(question);
		Mockito.when(entityManager.find(Quiz.class, 101)).thenReturn(quiz);
		assertDoesNotThrow(() -> quizDao.isQuestionInQuizPresent(101,"Q01")); 

	}
 
}
