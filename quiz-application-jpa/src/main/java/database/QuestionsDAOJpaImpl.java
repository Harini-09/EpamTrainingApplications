package database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.exception.ConstraintViolationException;

import customexceptions.InvalidQuestionIdEntryException;
import model.Question;
import singletonobjects.UtilityObjects;

public class QuestionsDAOJpaImpl implements QuestionsDao {
	private EntityManager entityManager;
	private Validation validation;

	public QuestionsDAOJpaImpl() {
		this.entityManager = UtilityObjects.getEntityMangerInstance();
		this.validation = new ValidationImpl();
	}

	@Override
	public boolean addQuestion(String questionId, Question newQuestion) {
		entityManager.getTransaction().begin();
		entityManager.persist(newQuestion);
		entityManager.getTransaction().commit();
		return entityManager.contains(newQuestion);
	}

	@Override
	public List<Question> viewQuestions() {
		TypedQuery<Question> query = entityManager.createQuery("from Question", Question.class);
		return query.getResultList();
	}

	@Override
	public boolean removeQuestion(String questionId) throws ConstraintViolationException {
		entityManager.getTransaction().begin();
		Question question = validation.getQuestion(questionId);
		try {
			entityManager.remove(question);
			entityManager.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
		return entityManager.contains(question);
	}

	@Override
	public String updateTitle(String questionId, String title) {
		entityManager.getTransaction().begin();
		Question question = validation.getQuestion(questionId);
		question.setTitle(title);
		entityManager.merge(question);
		entityManager.getTransaction().commit();
		return question.getTitle();
	}

	@Override
	public String updateQuestion(String questionId, String newQuestion) {
		entityManager.getTransaction().begin();
		Question question = validation.getQuestion(questionId);
		question.setQuestion(newQuestion);
		question = entityManager.merge(question);
		entityManager.getTransaction().commit();
		return question.getQuestion();
	}

	@Override
	public List<String> updateOptions(String questionId, List<String> options) {
		entityManager.getTransaction().begin();
		Question question = validation.getQuestion(questionId);
		question.setOptions(options);
		entityManager.merge(question);
		entityManager.getTransaction().commit();
		return question.getOptions();
	}

	@Override
	public String updateQuestionLevel(String questionId, String level) {
		entityManager.getTransaction().begin();
		Question question = validation.getQuestion(questionId);
		question.setQuestionlevel(level);
		entityManager.merge(question);
		entityManager.getTransaction().commit();
		return question.getQuestionlevel();
	}

	@Override
	public String updateTopicTag(String questionId, String topicTag) {
		entityManager.getTransaction().begin();
		Question question = validation.getQuestion(questionId);
		question.setTopictag(topicTag);
		Question returnedQuestion = entityManager.merge(question);
		entityManager.getTransaction().commit();
		return returnedQuestion.getTopictag();
	}

	@Override
	public String updateAnswer(String questionId, String answer) {
		entityManager.getTransaction().begin();
		Question question = validation.getQuestion(questionId);
		question.setAnswer(answer);
		entityManager.merge(question);
		entityManager.getTransaction().commit();
		return question.getAnswer();
	}

	@Override
	public void isQuestionPresent(String questionId) throws InvalidQuestionIdEntryException {
		entityManager.getTransaction().begin();
		Question question = validation.getQuestion(questionId);
		//question=entityManager.find(Question.class, questionId);
		try {
			if (!entityManager.contains(question)) {
				throw new InvalidQuestionIdEntryException();
			}
		} finally {
			entityManager.getTransaction().commit();
		}
	}
}
