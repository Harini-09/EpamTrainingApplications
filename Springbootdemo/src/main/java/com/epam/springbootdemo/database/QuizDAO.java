package com.epam.springbootdemo.database;



import javax.persistence.EntityManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.epam.springbootdemo.modal.Question;
import com.epam.springbootdemo.modal.Quiz;

@Repository
public class QuizDAO implements QuizDataBaseOperations {

	@Autowired
	private EntityManager entityManager;

	public Object viewData(Object quizID) {
		return entityManager.find(Quiz.class, quizID);

	}

	@Override
	public Object addData(Object quiz) {
		Quiz newQuiz = (Quiz) quiz;
		entityManager.getTransaction().begin();
		entityManager.merge(newQuiz);
		entityManager.getTransaction().commit();
		return newQuiz;
	}

	public Object addQuestions(int questionID, int quizID) {
		Quiz quiz = entityManager.find(Quiz.class, quizID);
		Question question=entityManager.find(Question.class, questionID);
		if(!quiz.getQuizQuestions().contains(question)) {
			quiz.getQuizQuestions().add(question);
		}

		return addData(quiz);
	}

	public Object removeQuestions(int questionID, int quizID) {
		Question question = entityManager.find(Question.class, questionID);
		Quiz quiz = entityManager.find(Quiz.class, quizID);
		quiz.getQuizQuestions().removeIf(ques -> ques.getQuestionID() == question.getQuestionID());
		return addData(quiz);
	}

	
	@Override
	public Object updateData(Object value, int quizID, int choice) {

		Quiz quiz = entityManager.find(Quiz.class, quizID);

		if (choice == 1) {
			quiz.setTitle((String) value);
		} else{
			quiz.setTotalMarks((int) value);
		} 

		return addData(quiz);

	}

	@Override
	public boolean deleteData(Object quizID) {
		Quiz quiz = entityManager.find(Quiz.class, quizID);
		boolean flag = (quiz != null);
		if(flag) {
		entityManager.getTransaction().begin();
		entityManager.remove(quiz);
		entityManager.getTransaction().commit();
		}
		return flag;
	}

	@Override
	public Object getAllData() {
		return entityManager.createQuery("from Quiz").getResultList();

	}

}
