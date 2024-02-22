package com.epam.Springbootdemo;



import java.util.ArrayList;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.springbootdemo.database.DatabaseOperations;
import com.epam.springbootdemo.modal.Question;
import com.epam.springbootdemo.service.QuestionLibraryService;


@ExtendWith(MockitoExtension.class)
 class QuestionLibraryServiceTest {
	
	@Mock
	DatabaseOperations databaseoperations;
	
	@InjectMocks
    QuestionLibraryService questionLibraryService;
	
	@Test
	void addQuestion() {
		List<String> options=new ArrayList<>();
		options.add("1");
		options.add("2");
		options.add("3");
		options.add("4");
		
		Question question=new Question(30, "question", options, "answer",
				"difficultyLevel", "tag");
		Mockito.when(databaseoperations.addData(question)).thenReturn(true);
		Object expected=questionLibraryService.addQuestion(question);
		Mockito.verify(databaseoperations).addData(question);

		assertEquals(true,expected);
	}
	
	@Test
	void deleteQuestionifIDisnotpresent() {
		Mockito.when(databaseoperations.deleteData(3)).thenReturn(false);
		boolean expected=questionLibraryService.deleteQuestion(3);
		Mockito.verify(databaseoperations).deleteData(3);
		assertEquals(false,expected);

	}
	@Test
	void deleteQuestionifIDpresent() {
		Mockito.when(databaseoperations.deleteData(1)).thenReturn(true);
		boolean expected=questionLibraryService.deleteQuestion(1);
		Mockito.verify(databaseoperations).deleteData(1);
		assertEquals(true,expected);

	}
	@Test
	void ModifyQuestionifIDispresent() {
		Mockito.when(databaseoperations.updateData("java",30,1)).thenReturn(true);
		Object expected=questionLibraryService.modifyQuestion("java",30,1);
		Mockito.verify(databaseoperations).updateData("java",30,1);
		assertEquals(true,expected);

	}
	@Test
	void ModifyQuestionifIDisnotpresent() {
		Mockito.when(databaseoperations.updateData("java",21,1)).thenReturn(false);
		Object expected=questionLibraryService.modifyQuestion("java",21,1);
		Mockito.verify(databaseoperations).updateData("java",21,1);

		assertEquals(false,expected);

	}
	@Test
	void ModifyAnswerifIDispresent() {
		Mockito.when(databaseoperations.updateData("java",30,3)).thenReturn(true);
		Object expected=questionLibraryService.modifyQuestion("java",30,3);
		Mockito.verify(databaseoperations).updateData("java",30,3);

		assertEquals(true,expected);

	}
	@Test
	void ModifyAnswerifIDisnotpresent() {
		Mockito.when(databaseoperations.updateData("java",21,3)).thenReturn(false);
		Object expected=questionLibraryService.modifyQuestion("java",21,3);
		Mockito.verify(databaseoperations).updateData("java",21,3);

		assertEquals(false,expected);

	}
	@Test
	void ModifyDifficultyLevelifIDispresent() {
		Mockito.when(databaseoperations.updateData("easy",30,4)).thenReturn(true);
	 	Object expected=questionLibraryService.modifyQuestion("easy",30,4);
		Mockito.verify(databaseoperations).updateData("easy",30,4);

		assertEquals(true,expected);

	}
	@Test
	void ModifyDifficultyLevelifIDisnotpresent() {
		Mockito.when(databaseoperations.updateData("hard",21,3)).thenReturn(false);
		Object expected=questionLibraryService.modifyQuestion("hard",21,3);
		Mockito.verify(databaseoperations).updateData("hard",21,3);

		assertEquals(false,expected);

	}
	@Test
	void getAll() {
		List<String> options=new ArrayList<>();
		options.add("1");
		options.add("2");
		options.add("3");
		options.add("4");
		
		Question question=new Question(27, "question", options, "answer",
				"difficultyLevel", "tag");
		List<Question>questionList=new ArrayList<>();
		questionList.add(question);
		Mockito.when(databaseoperations.getAllData()).thenReturn(questionList);
		List<Question> expected=(List<Question>)questionLibraryService.getAlldata();
		Mockito.verify(databaseoperations).getAllData();
		assertEquals(questionList,expected);
	} 
	@Test
	void viewDataIfIDisPresent() {
		List<String> options=new ArrayList<>();
		options.add("1");
		options.add("2");
		options.add("3");
		options.add("4");
		
		Question question=new Question(1, "question", options, "answer",
				"difficultyLevel", "tag");
		Mockito.when(databaseoperations.viewData(1)).thenReturn(question);
		Question expected=(Question)questionLibraryService.viewList(1);
		Mockito.verify(databaseoperations).viewData(1);

		assertEquals(question,expected);
	}
	
	
	
}
