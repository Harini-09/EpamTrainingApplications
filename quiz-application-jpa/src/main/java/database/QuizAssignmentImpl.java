package database;

import javax.persistence.EntityManager;

import model.Quiz;
import singletonobjects.UtilityObjects;

public class QuizAssignmentImpl implements QuizAssignment{
	private EntityManager entityManager;

	public QuizAssignmentImpl() { 
		this.entityManager = UtilityObjects.getEntityMangerInstance();
	}
	
	@Override
	public Quiz assignQuiz() {
		entityManager.getTransaction().begin();
		Quiz quiz =  entityManager.find(Quiz.class,101);
		entityManager.getTransaction().commit();
		return quiz;
	} 
 
}
