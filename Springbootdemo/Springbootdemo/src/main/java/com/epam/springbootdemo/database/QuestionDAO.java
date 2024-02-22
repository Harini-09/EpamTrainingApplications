package com.epam.springbootdemo.database;

import java.util.List;


import javax.persistence.EntityManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.springbootdemo.modal.Question;


@Repository
public class QuestionDAO implements DatabaseOperations {


	@Autowired
     private EntityManager entityManager;



	public Object viewData(Object questionID) {
		return entityManager.find(Question.class,questionID);
	}

	public Object addData(Object newQuestion){
		entityManager.getTransaction().begin();
		entityManager.merge(newQuestion);
		entityManager.getTransaction().commit();	
		return newQuestion;
	}

	@SuppressWarnings("unchecked")
	public Object updateData(Object value,int questionID,int choice) {	
			Question question = entityManager.find(Question.class,questionID);
			
			if(choice==1) {	
			question.setQuestion((String)value);
			}
			else if(choice==2) {
			question.setOptions((List<String>)value);
			}
            else if(choice==3) {
				question.setAnswer((String)value);
			}
            else if(choice==4) {
				question.setDifficultyLevel((String)value);
			}
            else if(choice==5) {
            	question.setTag((String)value);
            }	
		return addData(question);
	}
	
	public boolean deleteData(Object questionID) {
		
		Question question = entityManager.find(Question.class, questionID);
		boolean flag = (question != null);
		if(flag) {
		entityManager.getTransaction().begin();
		entityManager.remove(question);
		entityManager.getTransaction().commit();
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question>getAllData() {
		return entityManager.createQuery("from Question").getResultList();

		
	}


}
