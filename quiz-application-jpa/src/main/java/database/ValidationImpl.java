package database;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import model.Question;
import model.Quiz;
import singletonobjects.UtilityObjects;

public class ValidationImpl implements Validation {
	EntityManager entityManager = UtilityObjects.getEntityMangerInstance();

	@Override
	public Question getQuestion(String questionId) {
		entityManager.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Question> questions = entityManager.createQuery("from Question").getResultList();  //gives an instance of query
		entityManager.getTransaction().commit();
		Optional<Question> optional = questions.stream().filter(q -> q.getQuestionId().equals(questionId)).findFirst();
		if (optional.isPresent())
			return optional.get();
		return null;
	}

	@Override
	public List<Question> getQuestionsInQuiz(Quiz quiz) {
		return quiz.getQuestions();
	}
}
