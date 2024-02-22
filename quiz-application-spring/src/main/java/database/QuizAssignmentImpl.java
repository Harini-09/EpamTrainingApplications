package database;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import entities.Quiz;
import singletonobjects.UtilityObjects;

@Component
public class QuizAssignmentImpl implements QuizAssignment{
	private EntityManager entityManager;

	public QuizAssignmentImpl() { 
		this.entityManager = UtilityObjects.getEntityManagerInstance();
	}
	
	@Override
	public Quiz assignQuiz() {
		entityManager.getTransaction().begin();
		Quiz quiz =  entityManager.find(Quiz.class,101);
		entityManager.getTransaction().commit();
		return quiz;
	} 
 
}
