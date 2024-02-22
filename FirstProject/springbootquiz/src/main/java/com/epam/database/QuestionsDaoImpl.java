package com.epam.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.entities.Question;

@Repository
public class QuestionsDaoImpl implements QuestionsDao {

	@Autowired
	EntityManager entityManager;

	@Override
	public Question addQuestion(Question newQuestion) {
		entityManager.getTransaction().begin();
		entityManager.persist(newQuestion);
		entityManager.getTransaction().commit();
		return newQuestion;
	}

	@Override
	public List<Question> viewQuestions() {
		TypedQuery<Question> query = entityManager.createQuery("from Question", Question.class);
		return query.getResultList();
	}

	@Override
	public boolean removeQuestion(int questionId) throws ConstraintViolationException {
		entityManager.getTransaction().begin();
		Question question = entityManager.find(Question.class, questionId);
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
	public String updateTitle(int questionId, String title) {
		entityManager.getTransaction().begin();
		Question question = entityManager.find(Question.class, questionId);
		question.setTitle(title);
		Question returnedQuestion = entityManager.merge(question);
		entityManager.getTransaction().commit();
		return returnedQuestion.getTitle();
	}

	@Override
	public String updateQuestionDescription(int questionId, String newQuestion) {
		entityManager.getTransaction().begin();
		Question question = entityManager.find(Question.class, questionId);
		question.setQuestionDescription(newQuestion);
		Question returnedQuestion = entityManager.merge(question);
		entityManager.getTransaction().commit();
		return returnedQuestion.getQuestionDescription();
	}

	@Override
	public List<String> updateOptions(int questionId, List<String> options) {
		entityManager.getTransaction().begin();
		Question question = entityManager.find(Question.class, questionId);
		question.setOptions(options);
		Question returnedQuestion = entityManager.merge(question);
		entityManager.getTransaction().commit();
		return returnedQuestion.getOptions();
	}

	@Override
	public String updateQuestionLevel(int questionId, String level) {
		entityManager.getTransaction().begin();
		Question question = entityManager.find(Question.class, questionId);
		question.setQuestionlevel(level);
		Question returnedQuestion = entityManager.merge(question);
		entityManager.getTransaction().commit();
		return returnedQuestion.getQuestionlevel();
	}

	@Override
	public String updateTopicTag(int questionId, String topicTag) {
		entityManager.getTransaction().begin();
		Question question = entityManager.find(Question.class, questionId);
		question.setTopictag(topicTag);
		Question returnedQuestion = entityManager.merge(question);
		entityManager.getTransaction().commit();
		return returnedQuestion.getTopictag();
	}

	@Override
	public String updateAnswer(int questionId, String answer) {
		entityManager.getTransaction().begin();
		Question question = entityManager.find(Question.class, questionId);
		question.setAnswer(answer);
		Question returnedQuestion = entityManager.merge(question);
		entityManager.getTransaction().commit();
		return returnedQuestion.getAnswer();
	}

	@Override
	public void isQuestionPresent(int questionId) throws InvalidQuestionIdEntryException {
		entityManager.getTransaction().begin();
		Question question = entityManager.find(Question.class, questionId);
		try {
			if (!entityManager.contains(question)) {
				throw new InvalidQuestionIdEntryException();
			}
		} finally {
			entityManager.getTransaction().commit();
		}
	}
}
