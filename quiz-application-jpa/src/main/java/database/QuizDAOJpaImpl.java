package database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import customexceptions.InvalidQuestionIdEntryException;
import customexceptions.InvalidQuizTitleEntryException;
import customexceptions.NoSuchQuestionExistInQuizException;
import model.Question;
import model.Quiz;
import singletonobjects.UtilityObjects;

public class QuizDAOJpaImpl implements QuizDao {

	private EntityManager entityManager;
	private Validation validation; 
	
	public QuizDAOJpaImpl() {
		this.entityManager = UtilityObjects.getEntityMangerInstance();
		this.validation=new ValidationImpl();
	}

	@Override
	public List<Quiz> viewQuiz() {
		TypedQuery<Quiz> query = entityManager.createQuery("from Quiz",Quiz.class);
		return query.getResultList();
	}

	@Override  
	public boolean removeQuiz(int quizId) {
		entityManager.getTransaction().begin();
		Quiz quiz = entityManager.find(Quiz.class, quizId);
		entityManager.remove(quiz);
		entityManager.getTransaction().commit();
		return entityManager.contains(quiz);
	}

	@Override
	public boolean createQuiz(int quizid, Quiz newQuiz) {
		entityManager.getTransaction().begin();
		entityManager.persist(newQuiz);
		entityManager.getTransaction().commit();
		return entityManager.contains(newQuiz);
	}

	@Override
	public boolean addQuestion(int quizId, String questionId) {
		entityManager.getTransaction().begin();
		Quiz quiz = entityManager.find(Quiz.class, quizId);
		Question question = validation.getQuestion(questionId);
		List<Question> questions = validation.getQuestionsInQuiz(quiz);
		boolean isQuestionAdded = questions.add(question);
		entityManager.merge(quiz);
		entityManager.getTransaction().commit();
		return isQuestionAdded;  
	}
 
	@Override
	public boolean removeQuestion(int quizId, String questionId) {
		entityManager.getTransaction().begin();
		Quiz quiz = entityManager.find(Quiz.class, quizId); 
		Question question = validation.getQuestion(questionId);
		List<Question> questions = validation.getQuestionsInQuiz(quiz);
		boolean isQuestionPresent = questions.remove(question);
		entityManager.merge(quiz); 
		entityManager.getTransaction().commit();
		return isQuestionPresent; 
	}

	@Override
	public int updateMarks(int quizId, int marks) {
		entityManager.getTransaction().begin();
		Quiz quiz = entityManager.find(Quiz.class, quizId);
		quiz.setTotalMarks(marks);
		entityManager.merge(quiz);
		entityManager.getTransaction().commit();
		return quiz.getTotalMarks();
	}

	@Override 
	public Question addNewQuestion(String questionId) {
		entityManager.getTransaction().begin();
		Question question = validation.getQuestion(questionId);
		entityManager.getTransaction().commit();
		return question;
	}

	@Override
	public void isQuestionIdPresent(String questionId) throws InvalidQuestionIdEntryException {
		entityManager.getTransaction().begin();
		Question question = validation.getQuestion(questionId);
		try {
			if (!entityManager.contains(question)) {
				throw new InvalidQuestionIdEntryException();
			}
		} finally {
			entityManager.getTransaction().commit();
		}
	}

	@Override
	public void isQuizTitlePresent(int quizId) throws InvalidQuizTitleEntryException {
		entityManager.getTransaction().begin();
		Quiz quiz = entityManager.find(Quiz.class, quizId);
		try {
			if (!entityManager.contains(quiz)) {
				throw new InvalidQuizTitleEntryException(); 
			}
		} finally { 
			entityManager.getTransaction().commit();
		}

	} 

	@Override
	public void isQuestionInQuizPresent(int quizId, String questionId) throws NoSuchQuestionExistInQuizException {
		entityManager.getTransaction().begin();
		Question question = validation.getQuestion(questionId);
		Quiz quiz = entityManager.find(Quiz.class, quizId);
		List<Question> questions = quiz.getQuestions();
		try { 
			if (!questions.contains(question)) {
				throw new NoSuchQuestionExistInQuizException();
			}
		} finally {
			entityManager.getTransaction().commit();
		}
	} 
}
