package com.epam.database;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.entities.Quiz;

@Repository
public class QuizAssignmentImpl implements QuizAssignment{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Quiz assignQuiz(int quizId) {
		entityManager.getTransaction().begin();
		Quiz quiz =  entityManager.find(Quiz.class,quizId);
		entityManager.getTransaction().commit();
		return quiz;
	} 
 
}
