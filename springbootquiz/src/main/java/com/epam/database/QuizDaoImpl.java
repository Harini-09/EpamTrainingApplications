package com.epam.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.customexceptions.InvalidQuizIdEntryException;
import com.epam.customexceptions.NoSuchQuestionExistInQuizException;
import com.epam.entities.Question;
import com.epam.entities.Quiz;

@Repository
public class QuizDaoImpl implements QuizDao {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private Validation validation;

	@Override
	public List<Quiz> viewQuiz() {
		TypedQuery<Quiz> query = entityManager.createQuery("from Quiz", Quiz.class);
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
	public Quiz createQuiz(Quiz newQuiz) {
		entityManager.getTransaction().begin();
		entityManager.persist(newQuiz);
		entityManager.getTransaction().commit();
		return newQuiz;
	}

	@Override
	public boolean addQuestion(int quizId, int questionId) {
		entityManager.getTransaction().begin();
		Quiz quiz = entityManager.find(Quiz.class, quizId);
		Question question = entityManager.find(Question.class, questionId);
		List<Question> questions = validation.getQuestionsInQuiz(quiz);
		boolean isQuestionAdded = questions.add(question);
		entityManager.merge(quiz);
		entityManager.getTransaction().commit();
		return isQuestionAdded;
	}

	@Override
	public boolean removeQuestion(int quizId, int questionId) {
		entityManager.getTransaction().begin();
		Quiz quiz = entityManager.find(Quiz.class, quizId);
		Question question = entityManager.find(Question.class, questionId);
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
	public Question addNewQuestion(int questionId) {
		entityManager.getTransaction().begin();
		Question question = entityManager.find(Question.class, questionId);
		entityManager.getTransaction().commit();
		return question;
	}

	@Override
	public void isQuestionIdPresent(int questionId) throws InvalidQuestionIdEntryException {
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

	@Override
	public void isQuizTitlePresent(int quizId) throws InvalidQuizIdEntryException {
		entityManager.getTransaction().begin();
		Quiz quiz = entityManager.find(Quiz.class, quizId);
		try {
			if (!entityManager.contains(quiz)) {
				throw new InvalidQuizIdEntryException();
			}
		} finally {
			entityManager.getTransaction().commit();
		}

	}

	@Override
	public void isQuestionInQuizPresent(int quizId, int questionId) throws NoSuchQuestionExistInQuizException {
		entityManager.getTransaction().begin();
		Question question = entityManager.find(Question.class, questionId);
		Quiz quiz = entityManager.find(Quiz.class, quizId);
		List<Question> questions = quiz.getQuestionsList();
		try {
			if (!questions.contains(question)) {
				throw new NoSuchQuestionExistInQuizException();
			}
		} finally {
			entityManager.getTransaction().commit();
		}
	}
}
