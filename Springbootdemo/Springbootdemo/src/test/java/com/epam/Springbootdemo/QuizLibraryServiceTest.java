package com.epam.Springbootdemo;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.springbootdemo.database.DatabaseOperations;
import com.epam.springbootdemo.database.QuizDataBaseOperations;
import com.epam.springbootdemo.modal.Question;
import com.epam.springbootdemo.modal.Quiz;
import com.epam.springbootdemo.service.QuizLibraryService;


@ExtendWith(MockitoExtension.class)
 class QuizLibraryServiceTest {
	@Mock
	QuizDataBaseOperations databaseoperations;
	@Mock
	DatabaseOperations questiondatabaseoperations;

	
	@InjectMocks
	private QuizLibraryService quizLibraryService;
	
	@Test
	void AddQuizIfIdisnotpresent() {
		List<Question>questions=new ArrayList<>();
		Quiz quiz=new Quiz(100,"oops",100,questions);
		Mockito.when(databaseoperations.addData(quiz)).thenReturn(true);
		Object isQuizAdded=quizLibraryService.addQuiz(quiz);
		Mockito.verify(databaseoperations).addData(quiz);
		
		
	}
	@Test
	void donotAddQuizIfIdistpresent() {
		List<Question>questions=new ArrayList<>();
		Quiz quiz=new Quiz(100,"oops",100,questions);
		Mockito.when(databaseoperations.addData(quiz)).thenReturn(false);
		Object isQuizAdded=quizLibraryService.addQuiz(quiz);
		Mockito.verify(databaseoperations).addData(quiz);
	
	}
	@Test
	void deleteQuestionIfIDPresent() {
		Mockito.when(databaseoperations.deleteData(1)).thenReturn(true);
		boolean isQuizdeleted=quizLibraryService.deleteQuiz(1);
		Mockito.verify(databaseoperations).deleteData(1);
		assertEquals(true,isQuizdeleted);


	}
	@Test
	void dontdeleteQuestionIfIDnotPresent() {
		Mockito.when(databaseoperations.deleteData(10)).thenReturn(false);
		boolean isQuizdeleted=quizLibraryService.deleteQuiz(10);
		Mockito.verify(databaseoperations).deleteData(10);
		assertEquals(false,isQuizdeleted);


	} 
	@Test
	void viewQuizIFIDPresent() {
		List<Question>questions=new ArrayList<>();
		Quiz quiz=new Quiz(1,"oops",100,questions);
		Mockito.when(databaseoperations.viewData(1)).thenReturn(quiz);
		Quiz expected=(Quiz)quizLibraryService.view(1);
		Mockito.verify(databaseoperations).viewData(1);
		assertEquals(quiz,expected);
	}
	@Test
	void getAllDataOfQuizes() {
		List<Question>questions=new ArrayList<>();
		List<Quiz>listOfQuizes=new ArrayList<>();
		Quiz quiz=new Quiz(1,"oops",100,questions);
        listOfQuizes.add(quiz);
		Mockito.when(databaseoperations.getAllData()).thenReturn(listOfQuizes);
		List<Quiz> expected=(List<Quiz>) quizLibraryService.getAlldata();
		Mockito.verify(databaseoperations).getAllData();
		assertEquals(listOfQuizes,expected);
	}
	
	@Test
	void addQuestionTothePreviousQuiz() {
		List<Question>questions=new ArrayList<>();
		Question question=new Question(1, "question", Arrays.asList("1","2"), "answer",
				"difficultyLevel", "tag");
		questions.add(question);
		Mockito.when(databaseoperations.addQuestions(1, 1)).thenReturn(question);
//		Mockito.when(databaseoperations.updateData(questions, 1, 3)).thenReturn(true);
		Object expected=quizLibraryService.addQuestion(1,1);
		Mockito.verify(databaseoperations).addQuestions(1,1);
//		Mockito.verify(databaseoperations).updateData(questions, 1, 3);
//		assertEquals(question,expected);
	}
	@Test
	void removeQuestionTothePreviousQuiz() {
		List<Question>questions=new ArrayList<>();
		Question question=new Question(1, "question", Arrays.asList("1","2"), "answer",
				"difficultyLevel", "tag");
		questions.add(question);
		Mockito.when(databaseoperations.removeQuestions(1,1)).thenReturn(question);
//		Mockito.when(databaseoperations.updateData(questions, 1, 3)).thenReturn(true);
		Object expected=quizLibraryService.deleteQuestion(1,1);
		Mockito.verify(databaseoperations).removeQuestions(1,1);
//		Mockito.verify(databaseoperations).updateData(questions, 1, 3);
//		assertEquals(true,expected);
	}
	@Test
	void updatetitleofQuizIfIDPresent() {
		Mockito.when(databaseoperations.updateData("newTitle", 1, 1)).thenReturn(true);
		Object expected=quizLibraryService.updateQuiz(1,"newTitle",1);
		Mockito.verify(databaseoperations).updateData("newTitle", 1, 1);
		assertEquals(true,expected);


	}
	@Test
	void dontupdatetitleofQuizIfIDNotPresent() {
		Mockito.when(databaseoperations.updateData("newTitle", 1, 1)).thenReturn(false);
		Object expected=quizLibraryService.updateQuiz(1,"newTitle",1);
		Mockito.verify(databaseoperations).updateData("newTitle", 1, 1);
		assertEquals(false,expected);


	}
	@Test
	void updateMarksofQuizIfIDPresent() {
		Mockito.when(databaseoperations.updateData(100, 1, 1)).thenReturn(true);
		Object expected=quizLibraryService.updateQuiz(1,100,1);
		Mockito.verify(databaseoperations).updateData(100, 1, 1);
		assertEquals(true,expected);


	}
	@Test
	void dontupdateMarksofQuizIfIDNotPresent() {
		Mockito.when(databaseoperations.updateData(100, 1, 1)).thenReturn(false);
	    Object expected=quizLibraryService.updateQuiz(1,100,1);
		Mockito.verify(databaseoperations).updateData(100, 1, 1);
		assertEquals(false,expected);


	}
	@Test
	void addNewQuestiontoQuizIFIDPresent() {
		List<Question>questions=new ArrayList<>();
		Question question=new Question(1, "question", Arrays.asList("1","2"), "answer",
				"difficultyLevel", "tag");
		Mockito.when(questiondatabaseoperations.viewData(1)).thenReturn(question);
		Question expected=(Question)quizLibraryService.addQuestionToNewQuiz(1);
		Mockito.verify(questiondatabaseoperations).viewData(1);
		assertEquals(question,expected);
	}
	
	
}
