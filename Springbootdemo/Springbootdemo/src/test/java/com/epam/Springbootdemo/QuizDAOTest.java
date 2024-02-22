package com.epam.Springbootdemo;


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.springbootdemo.customexception.InvalidEntryException;
import com.epam.springbootdemo.database.QuizDAO;
import com.epam.springbootdemo.modal.Question;
import com.epam.springbootdemo.modal.Quiz;

@ExtendWith(MockitoExtension.class)
 class QuizDAOTest {
	@Mock
	EntityManager entityManager;
	@Mock
	TypedQuery<Quiz> query;
	@Mock
	EntityTransaction transaction;
	
	@InjectMocks
	QuizDAO quizDAO;
	@Test
	void viewDataIFIDPresent() {
		Question question=new Question(1, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		Quiz quiz=new Quiz(1,"question",100, Arrays.asList(question));
			
		Mockito.when(entityManager.find(Quiz.class,1)).thenReturn(quiz);
		Quiz expectedquiz=(Quiz) quizDAO.viewData(1);
		Mockito.verify(entityManager).find(Quiz.class,1);
		assertEquals(expectedquiz, quiz);
		
		 
	}
	@Test
	void viewAllQuestionPresent() {
		List<Quiz>listOfQuizes=new ArrayList<>();
		Question question=new Question(1, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		Quiz quiz=new Quiz(1,"question",100, Arrays.asList(question));
		listOfQuizes.add(quiz);
		Mockito.when(entityManager.createQuery("from Quiz")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(listOfQuizes);
		List<Quiz>expected=(List<Quiz>) quizDAO.getAllData();
		Mockito.verify(entityManager).createQuery("from Quiz");
		Mockito.verify(query).getResultList();
		assertEquals(expected, listOfQuizes);
		
		
	}
	@Test
	void addQuestioninAlreadyCreatedQuiz() {
	
		Question question2=new Question(2, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		Question question3=new Question(3, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		List<Question>questionList=new ArrayList<>();
		questionList.add(question3);
		Quiz quiz=new Quiz(1,"question",100,questionList);
		Mockito.when(entityManager.find(Question.class, 2)).thenReturn(question2);
		Mockito.when(entityManager.find(Quiz.class, 1)).thenReturn(quiz);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
        
		Object expected=quizDAO.addQuestions(2, 1);
		Mockito.verify(entityManager,times(2)).getTransaction();
		Mockito.verify(entityManager).merge(quiz);
		

		assertEquals(expected, quiz);

	}
	@Test
	void dontaddQuestioninAlreadyCreatedQuiz() {
	
		Question question2=new Question(2, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		

		Quiz quiz=new Quiz(1,"question",100, List.of(question2));
		Mockito.when(entityManager.find(Question.class, 2)).thenReturn(question2);
		Mockito.when(entityManager.find(Quiz.class, 1)).thenReturn(quiz);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);


		Object expected=quizDAO.addQuestions(2, 1);
		Mockito.verify(entityManager,times(2)).getTransaction();
		Mockito.verify(entityManager).merge(quiz);
		

		assertEquals(expected, quiz);

	}
	@Test
	void removeQuestioninAlreadyCreatedQuiz() {
	
		Question question2=new Question(2, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		List<Question>listOfQuestions=new ArrayList<>();
    	listOfQuestions.add(question2);
		Quiz quiz=new Quiz(1,"question",100, listOfQuestions);
		Mockito.when(entityManager.find(Question.class, 2)).thenReturn(question2);
		Mockito.when(entityManager.find(Quiz.class, 1)).thenReturn(quiz);

		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);

		Object expectedList=quizDAO.removeQuestions(2,1);
	
		assertEquals(quiz,expectedList);

	}
	@Test
	void dontremoveQuestioninAlreadyCreatedQuiz() {
	
		Question question2=new Question(2, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		Question question1=new Question(1, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		List<Question>listOfQuestions=new ArrayList<>();
        listOfQuestions.add(question1);

		Quiz quiz=new Quiz(1,"question",100, listOfQuestions);
		Mockito.when(entityManager.find(Question.class, 2)).thenReturn(question2);
		Mockito.when(entityManager.find(Quiz.class, 1)).thenReturn(quiz);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Object expectedList=quizDAO.removeQuestions(2,1);
		Mockito.verify(entityManager,times(2)).getTransaction();
		Mockito.verify(entityManager).merge(quiz);
		assertEquals(quiz,expectedList);

	}
	@Test
	void AddQuizIFIDnotPresent() {
		Question question2=new Question(2, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		List<Question>listOfQuestions=new ArrayList<>();
		listOfQuestions.add(question2);
		Quiz quiz=new Quiz(1,"question",100, listOfQuestions);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.merge(quiz)).thenReturn(quiz);
	
		Object expected=quizDAO.addData(quiz);
		Mockito.verify(entityManager,times(2)).getTransaction();
		Mockito.verify(entityManager).merge(quiz);
	

		assertEquals(quiz,expected);
 

		

	}
//	@Test
//	void dontAddQuizIFIDnPresent() {
//		Question question2=new Question(2, "question", Arrays.asList("21"), "answer",
//				"difficultyLevel", "tag");
//		List<Question>listOfQuestions=new ArrayList<>();
//		listOfQuestions.add(question2);
//		Quiz quiz=new Quiz(1,"question",100, listOfQuestions);
//		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
//		Mockito.doThrow(InvalidEntryException.class).when(entityManager).merge(quiz);
//		quizDAO.addData(quiz);
//		Mockito.verify(entityManager,times(2)).getTransaction();
//		Mockito.verify(entityManager).merge(quiz);	
//
//	}
//	@Test
//	void throwExceptionwhileaddingQuizIFIDnotPresent() {
//		Question question2=new Question(2, "question", Arrays.asList("21"), "answer",
//				"difficultyLevel", "tag");
//		List<Question>listOfQuestions=new ArrayList<>();
//		listOfQuestions.add(question2);
//		Quiz quiz=new Quiz(1,"question",100, listOfQuestions);
//		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
//		Mockito.doThrow(InvalidEntryException.class).when(entityManager).merge(quiz);
//		quizDAO.addData(quiz);
//		Mockito.verify(entityManager,times(2)).getTransaction();
//		Mockito.verify(entityManager).merge(quiz);	
//
//	}
	@Test
	void deleteQuizIFIDPresent() {
		Question question2=new Question(2, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		List<Question>listOfQuestions=new ArrayList<>();
		listOfQuestions.add(question2);
		Quiz quiz=new Quiz(1,"question",100, listOfQuestions);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Quiz.class,quiz)).thenReturn(quiz);
		Mockito.doNothing().when(entityManager).remove(quiz);
	
		boolean expected=quizDAO.deleteData(quiz);
		Mockito.verify(entityManager,times(2)).getTransaction();
		Mockito.verify(entityManager).remove(quiz);


		assertEquals(true,expected);
 

		

	}
	@Test
	void dontdeleteQuizIFIDnotPresent() {
		Question question2=new Question(2, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		List<Question>listOfQuestions=new ArrayList<>();
		listOfQuestions.add(question2);
		Quiz quiz=new Quiz(1,"question",100, listOfQuestions);
	
		Mockito.when(entityManager.find(Quiz.class,quiz)).thenReturn(null);
	
		boolean expected=quizDAO.deleteData(quiz);
		assertEquals(false,expected);
		
	}
//	@Test
//	void dontdeleteQuestionIFQuestionisNull() {
//		Question question2=new Question(2, "question", Arrays.asList("21"), "answer",
//				"difficultyLevel", "tag");
//		List<Question>listOfQuestions=new ArrayList<>();
//		listOfQuestions.add(question2);
//		Quiz quiz=new Quiz(1,"question",100, listOfQuestions);
//		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
//
//		Mockito.when(entityManager.find(Quiz.class,quiz)).thenReturn(quiz);
//		Mockito.doThrow(InvalidEntryException.class).when(entityManager).remove(quiz);
//
//	
//		boolean expected=quizDAO.deleteData(quiz);
//	
//		
//	
//
//		
// 
//
//		
//
//	}
	
	@Test
	void updateTitleIFIDisPresent() {
		Question question2=new Question(2, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		List<Question>listOfQuestions=new ArrayList<>();
		listOfQuestions.add(question2);
		Quiz quiz=new Quiz(1,"question",100, listOfQuestions);
		Mockito.when(entityManager.find(Quiz.class,1)).thenReturn(quiz);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
        Object expected=quizDAO.updateData("newtitle", 1, 1);
        Mockito.verify(entityManager,times(2)).getTransaction();
		Mockito.verify(entityManager).merge(quiz);
		assertEquals(quiz,expected);
	}
	
	@Test
	void updatemarksIFIDisPresent() {
		Question question2=new Question(2, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		List<Question>listOfQuestions=new ArrayList<>();
		listOfQuestions.add(question2);
		Quiz quiz=new Quiz(1,"question",100, listOfQuestions);
		Mockito.when(entityManager.find(Quiz.class,1)).thenReturn(quiz);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
        Object expected=quizDAO.updateData(200, 1, 2);
        Mockito.verify(entityManager,times(2)).getTransaction();
		Mockito.verify(entityManager).merge(quiz);
		assertEquals(quiz,expected);
	}
//	@Test
//	void updateListofQuestionsIFIDisPresent() {
//		Question question2=new Question(2, "question", Arrays.asList("21"), "answer",
//				"difficultyLevel", "tag");
//		Question question1=new Question(1, "question", Arrays.asList("21"), "answer",
//				"difficultyLevel", "tag");
//		List<Question>listOfQuestions=new ArrayList<>();
//		List<Question>newListOfQuestions=new ArrayList<>();
//         newListOfQuestions.add(question1);
//		listOfQuestions.add(question2);
//		Quiz quiz=new Quiz(1,"question",100, listOfQuestions);
//		Mockito.when(entityManager.find(Quiz.class,1)).thenReturn(quiz);
//		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
//        boolean expected=quizDAO.updateData(newListOfQuestions, 1, 3);
//        Mockito.verify(entityManager,times(2)).getTransaction();
//		Mockito.verify(entityManager).merge(quiz);
//		assertEquals(true,expected);
//	}
	
//	@Test
//	void updateListofQuestionIFIDisPresent() {
//		Question question2=new Question(2, "question", Arrays.asList("21"), "answer",
//				"difficultyLevel", "tag");
//
//		List<Question>listOfQuestions=new ArrayList<>();
//		List<Question>newListOfQuestions=new ArrayList<>();
//		listOfQuestions.add(question2);
//		 newListOfQuestions.add(question2);
//		Quiz quiz=new Quiz(1,"question",100, listOfQuestions);
////		Mockito.when(quiz.getQuizQuestions()).thenReturn(listOfQuestions);
////		Mockito.when(listOfQuestions.contains(question2)).thenReturn(false);
//		
//		Mockito.when(entityManager.find(Quiz.class,1)).thenReturn(quiz);
//		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
//        boolean expected=quizDAO.updateData(newListOfQuestions,1,4);
//        Mockito.verify(entityManager,times(2)).getTransaction();
//        assertEquals(true,expected);
//	}
//	@Test
//	void dontupdateListofQuestionIFIDnotPresent() {
//		Question question2=new Question(2, "question", Arrays.asList("21"), "answer",
//				"difficultyLevel", "tag");
//		Question question1=new Question(1, "question", Arrays.asList("21"), "answer",
//				"difficultyLevel", "tag");
//		List<Question>listOfQuestions=new ArrayList<>();
//		List<Question>newListOfQuestions=new ArrayList<>();
//		listOfQuestions.add(question1);
//		newListOfQuestions.add(question2);
//		Quiz quiz=new Quiz(1,"question",100, listOfQuestions);
////	Mockito.when(quiz.getQuizQuestions()).thenReturn(listOfQuestions);
////	Mockito.when(listOfQuestions.contains(question2)).thenReturn(false);
//		
//		Mockito.when(entityManager.find(Quiz.class,1)).thenReturn(quiz);
//		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
//        boolean expected=quizDAO.updateData(newListOfQuestions,1,4);
//        Mockito.verify(entityManager,times(2)).getTransaction();
//        assertEquals(true,expected);
//	}
//	@Test
//	void updateListofQuestionsIFQuestionIDisPresent() {
//		Question question2=new Question(2, "question", Arrays.asList("21"), "answer",
//				"difficultyLevel", "tag");
//		Question question1=new Question(1, "question", Arrays.asList("21"), "answer",
//				"difficultyLevel", "tag");
//		List<Question>listOfQuestions=new ArrayList<>();
//		List<Question>newListOfQuestions=new ArrayList<>();
//         newListOfQuestions.add(question1);
//		listOfQuestions.add(question2);
//		Quiz quiz=new Quiz(1,"question",100, listOfQuestions);
//		Mockito.when(entityManager.find(Quiz.class,1)).thenReturn(quiz);
//		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
//        boolean expected=quizDAO.updateData(newListOfQuestions, 1, 3);
//        Mockito.verify(entityManager,times(2)).getTransaction();
//		Mockito.verify(entityManager).merge(quiz);
//		assertEquals(true,expected);
//	}
//	@Test
//	void dontupdateListofQuestionsIFIDisPresent() {
//	
//		Question question1=new Question(1, "question", Arrays.asList("21"), "answer",
//				"difficultyLevel", "tag");
//		List<Question>listOfQuestions=new ArrayList<>();
//		List<Question>newListOfQuestions=new ArrayList<>();
//         newListOfQuestions.add(question1);
//		listOfQuestions.add(question1);
//		Quiz quiz=new Quiz(1,"question",100, listOfQuestions);
//		Mockito.when(entityManager.find(Quiz.class,1)).thenReturn(quiz);
//		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
//        boolean expected=quizDAO.updateData(newListOfQuestions, 1, 3);
//        Mockito.verify(entityManager,times(2)).getTransaction();
//		Mockito.verify(entityManager).merge(quiz);
//		assertEquals(true,expected);
//	}
}
