package database;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import entities.Question;
import entities.Quiz;
import singletonobjects.UtilityObjects;

@Component
public class ValidationImpl implements Validation {
	EntityManager entityManager = UtilityObjects.getEntityManagerInstance();

	@Override
	public Question getQuestion(String questionId) {
		@SuppressWarnings("unchecked")
		List<Question> questions = entityManager.createQuery("from Question").getResultList();
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
