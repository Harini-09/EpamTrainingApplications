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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.springbootdemo.customexception.InvalidEntryException;
import com.epam.springbootdemo.database.QuestionDAO;
import com.epam.springbootdemo.modal.Question;



@ExtendWith(MockitoExtension.class)

 class QuestionDAOTest {

	@Mock
	EntityManager entityManager;
	@Mock
	TypedQuery<Question> query;
	@Mock
	EntityTransaction transaction;
	
	@InjectMocks
	QuestionDAO questionDAO;
	
	@Test
	void viewDataIFIDPresent() {
		Question question=new Question(1, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		Mockito.when(entityManager.find(Question.class,1)).thenReturn(question);
		Question expectedquestion=(Question) questionDAO.viewData(1);
		Mockito.verify(entityManager).find(Question.class,1);
		assertEquals(expectedquestion, question);
		
		
	}
	@Test
	void viewAllQuestionPresent() {
		List<Question>listOfQuestion=new ArrayList<>();
		Question question=new Question(1, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		listOfQuestion.add(question);
		Mockito.when(entityManager.createQuery("from Question")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(listOfQuestion);
		List<Question>expectedquestion=(List<Question>) questionDAO.getAllData();
		Mockito.verify(entityManager).createQuery("from Question");
		Mockito.verify(query).getResultList();
		assertEquals(expectedquestion, listOfQuestion);
		
		
	}
	@Test 
	void AddQuestionIFIDnotPresent() {
		Question newquestion=new Question(1, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.merge(newquestion)).thenReturn(newquestion);
	
		Object expected=questionDAO.addData(newquestion);
		Mockito.verify(entityManager,times(2)).getTransaction();
		Mockito.verify(entityManager).merge(newquestion);
	

		assertEquals(newquestion,expected); 

		

	}

	@Test
	void deleteQuestionIFIDPresent() {
		Question newquestion=new Question(1, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.find(Question.class,newquestion)).thenReturn(newquestion);
		Mockito.doNothing().when(entityManager).remove(newquestion);
	
		boolean expected=questionDAO.deleteData(newquestion);
		Mockito.verify(entityManager,times(2)).getTransaction();
		Mockito.verify(entityManager).remove(newquestion);


		assertEquals(true,expected); 

		

	}
	@Test
	void dontdeleteQuestionIFIDnotPresent() {
		Question newquestion=new Question(1, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		Mockito.when(entityManager.find(Question.class,newquestion)).thenReturn(null);
	
		boolean expected=questionDAO.deleteData(newquestion);
	
	


		assertEquals(false,expected); 

		

	}


	@Test
	void updateQuestionIFIDisPresent() {
		Question newquestion=new Question(1, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		Mockito.when(entityManager.find(Question.class,1)).thenReturn(newquestion);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
        Object expected=questionDAO.updateData("newquestion", 1, 1);
        Mockito.verify(entityManager,times(2)).getTransaction();
		Mockito.verify(entityManager).merge(newquestion);
		assertEquals(newquestion,expected);	}
	@Test
	void updateAnsIFIDisPresent() {
//		Question newquestion=new Question(1, "question", Arrays.asList("21"), "answer",
//				"difficultyLevel", "tag");
//		Mockito.when(entityManager.find(Question.class,1)).thenReturn(newquestion);
//		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
//		Object expected=questionDAO.updateData("newans", 1, 3);
//        Mockito.verify(entityManager,times(2)).getTransaction();
//		Mockito.verify(entityManager).merge(newquestion);
//		assertEquals(newquestion,expected);	
		update(1);
		
		}
	
	@ParameterizedTest
	@ValueSource(ints= {1,3,4,5})
	void update(int choice) {
		Question newquestion=new Question(1, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		Mockito.when(entityManager.find(Question.class,1)).thenReturn(newquestion);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Object expected=questionDAO.updateData("updatedValue", 1, choice);
        Mockito.verify(entityManager,times(2)).getTransaction();
		Mockito.verify(entityManager).merge(newquestion);
		assertEquals(newquestion,expected);	}
	@Test
	void updateDifficultyLevelIFIDisPresent() {
//		Question newquestion=new Question(1, "question", Arrays.asList("21"), "answer",
//				"difficultyLevel", "tag");
//		Mockito.when(entityManager.find(Question.class,1)).thenReturn(newquestion);
//		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
//		Object expected=questionDAO.updateData("hard", 1, 4);
//        Mockito.verify(entityManager,times(2)).getTransaction();
//		Mockito.verify(entityManager).merge(newquestion);
//		assertEquals(newquestion,expected);
		update(4);
		
		}
	@Test
	void updateQuestionTagIFIDisPresent() {
//		Question newquestion=new Question(1, "question", Arrays.asList("21"), "answer",
//				"difficultyLevel", "tag");
//		Mockito.when(entityManager.find(Question.class,1)).thenReturn(newquestion);
//		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
//		Object expected=questionDAO.updateData("java", 1, 5);
//        Mockito.verify(entityManager,times(2)).getTransaction();
//		Mockito.verify(entityManager).merge(newquestion);
//		assertEquals(newquestion,expected);
		update(5);
		}
	@Test
	void updateOptionsTagIFIDisPresent() {
		List<String>options=Arrays.asList("True","False");
		Question newquestion=new Question(1, "question", Arrays.asList("21"), "answer",
				"difficultyLevel", "tag");
		Mockito.when(entityManager.find(Question.class,1)).thenReturn(newquestion);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Object expected=questionDAO.updateData(options, 1, 2);
        Mockito.verify(entityManager,times(2)).getTransaction();
		Mockito.verify(entityManager).merge(newquestion);
		assertEquals(newquestion,expected);
//		update(2);
	}
	
}
