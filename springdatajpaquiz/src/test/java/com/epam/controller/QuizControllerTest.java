package com.epam.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.customexceptions.InvalidQuizIdEntryException;
import com.epam.customexceptions.NoSuchQuestionExistInQuizException;
import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.service.QuizLibraryService;

@ExtendWith(MockitoExtension.class)
class QuizControllerTest {

	@Mock
	QuizLibraryService quizService;
	
	@InjectMocks
	QuizController quizController;
	
	private MockMvc mockMvc;
	Question question;
	Quiz quiz;
	List<Quiz> quizes;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(quizController).build();
		question=new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		List<Question> quizQuestionsList = Arrays.asList(question);
		quiz = new Quiz("Basic",quizQuestionsList,30);
		quizes = Arrays.asList(quiz);
	}
	
	@Test
	void testCreateQuiz() throws Exception {
		Mockito.when(quizService.insert(quiz)).thenReturn(quiz);
		Mockito.when(quizService.addNewQuestion(1)).thenReturn(question);
		MvcResult mvcResult = mockMvc.perform(post("/addquiz")
							.param("title","Basic").param("quizQuestions","1").param("totalMarks","30"))
							.andExpect(status().isOk())
							.andExpect(view().name("quizcreated"))
							.andExpect(model().attribute("message","Quiz Added Successfully:"))
							.andReturn();
		assertNotNull(mvcResult);				
	}
	
	@Test 
	void addQuestionTestSuccess() throws Exception {
		doNothing().when(quizService).isQuizTitlePresent(1);
		doNothing().when(quizService).isQuestionIdPresent(1);
		Mockito.when(quizService.addQuestion(1, 1)).thenReturn(true);
		MvcResult mvcResult = mockMvc.perform(post("/addquestiontoquiz")
									 .param("quizid","1").param("questionid","1"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("questionaddedtoquiz"))
									 .andExpect(model().attributeExists("questionid"))
									 .andExpect(model().attribute("questionid",1))
									 .andExpect(model().attributeExists("quizid"))
									 .andExpect(model().attribute("quizid",1))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void addQuestionTestFailure1() throws Exception {
		doThrow(InvalidQuizIdEntryException.class).when(quizService).isQuizTitlePresent(1);
		MvcResult mvcResult = mockMvc.perform(post("/addquestiontoquiz")
									 .param("quizid","1").param("questionid","1"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("error"))
									 .andExpect(model().attributeExists("message"))
									 .andExpect(model().attribute("message", new InvalidQuizIdEntryException() .getMessage()))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void addQuestionTestFailure2() throws Exception {
		doThrow(InvalidQuestionIdEntryException.class).when(quizService).isQuestionIdPresent(1);
		MvcResult mvcResult = mockMvc.perform(post("/addquestiontoquiz")
									 .param("quizid","1").param("questionid","1"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("error"))
									 .andExpect(model().attributeExists("message"))
									 .andExpect(model().attribute("message", new InvalidQuestionIdEntryException() .getMessage()))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test 
	void removeQuestionTest() throws Exception {
		doNothing().when(quizService).isQuizTitlePresent(1);
		doNothing().when(quizService).isQuestionInQuizPresent(1, 1);
		Mockito.when(quizService.removeQuestion(1, 1)).thenReturn(true);
		MvcResult mvcResult = mockMvc.perform(post("/removequestionfromquiz")
									 .param("quizid","1").param("questionid","1"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("questionremovedfromquiz"))
									 .andExpect(model().attributeExists("questionid"))
									 .andExpect(model().attribute("questionid",1))
									 .andExpect(model().attributeExists("quizid"))
									 .andExpect(model().attribute("quizid",1))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void removeQuestionTestFailure1() throws Exception {
		doThrow(InvalidQuizIdEntryException.class).when(quizService).isQuizTitlePresent(1);
		MvcResult mvcResult = mockMvc.perform(post("/removequestionfromquiz")
									 .param("quizid","1").param("questionid","1"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("error"))
									 .andExpect(model().attributeExists("message"))
									 .andExpect(model().attribute("message", new InvalidQuizIdEntryException() .getMessage()))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void removeQuestionTestFailure2() throws Exception {
		doThrow(NoSuchQuestionExistInQuizException.class).when(quizService).isQuestionInQuizPresent(1, 1);
		MvcResult mvcResult = mockMvc.perform(post("/removequestionfromquiz")
									 .param("quizid","1").param("questionid","1"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("error"))
									 .andExpect(model().attributeExists("message"))
									 .andExpect(model().attribute("message", new NoSuchQuestionExistInQuizException() .getMessage()))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void modifyMarksTestSuccess() throws Exception {
		doNothing().when(quizService).isQuizTitlePresent(1);
		Mockito.when(quizService.updateMarks(1, 30)).thenReturn(30);
		MvcResult mvcResult = mockMvc.perform(post("/modifymarksinquiz")
				 .param("quizid","1").param("marks","30"))
				 .andExpect(status().isOk())
				 .andExpect(view().name("quizmarksmodified"))
				 .andExpect(model().attributeExists("quizid"))
				 .andExpect(model().attribute("quizid",1))
				 .andReturn();
		assertNotNull(mvcResult); 
	}
	
	@Test
	void modifyMarksTestFailure() throws Exception {
		doThrow(InvalidQuizIdEntryException.class).when(quizService).isQuizTitlePresent(1);
		MvcResult mvcResult = mockMvc.perform(post("/modifymarksinquiz")
									 .param("quizid","1").param("marks","30"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("error"))
									 .andExpect(model().attributeExists("message"))
									 .andExpect(model().attribute("message", new InvalidQuizIdEntryException() .getMessage()))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void deleteQuizSuccess() throws Exception {
		doNothing().when(quizService).isQuizTitlePresent(1);
		Mockito.when(quizService.delete(1)).thenReturn(true);
		MvcResult mvcResult = mockMvc.perform(post("/deletequiz")
									 .param("id", "1"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("quizremoved"))
									 .andExpect(model().attribute("quizid", 1))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void deleteQuizFailure() throws Exception {
		doThrow(InvalidQuizIdEntryException.class).when(quizService).isQuizTitlePresent(1);
		MvcResult mvcResult = mockMvc.perform(post("/deletequiz")
									 .param("id","1"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("error"))
									 .andExpect(model().attributeExists("message"))
									 .andExpect(model().attribute("message", new InvalidQuizIdEntryException() .getMessage()))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void viewQuizTest() throws Exception {
		Mockito.when(quizService.view()).thenReturn(quizes);
		MvcResult mvcResult = mockMvc.perform(get("/displayquiz"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("viewquiz"))
									 .andExpect(model().attribute("quizes",quizService.view()))
									 .andExpect(model().attribute("message","Quiz Library"))
									 .andReturn();	 
		assertNotNull(mvcResult);
	}
	
	@Test
	void modifyquizopstest() throws Exception{
		MvcResult mvcResult = mockMvc.perform(post("/modifyquizops")
				 .param("quizid","1"))
				 .andExpect(status().isOk())
				 .andExpect(view().name("modifyquiz"))
				 .andExpect(model().attributeExists("quizid"))
				 .andExpect(model().attribute("quizid",1))
				 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void addquizquestiontest() throws Exception{
		MvcResult mvcResult = mockMvc.perform(post("/addquizquestion")
				 .param("quizid","1"))
				 .andExpect(status().isOk())
				 .andExpect(view().name("addquestioninquiz"))
				 .andExpect(model().attributeExists("quizid"))
				 .andExpect(model().attribute("quizid",1))
				 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void removequizquestiontest() throws Exception{
		MvcResult mvcResult = mockMvc.perform(post("/removequizquestion")
				 .param("quizid","1"))
				 .andExpect(status().isOk())
				 .andExpect(view().name("deletequestionfromquiz"))
				 .andExpect(model().attributeExists("quizid"))
				 .andExpect(model().attribute("quizid",1))
				 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void modifyquizmarkstest() throws Exception{
		MvcResult mvcResult = mockMvc.perform(post("/modifyquizmarks")
				 .param("quizid","1"))
				 .andExpect(status().isOk())
				 .andExpect(view().name("updatequizmarks"))
				 .andExpect(model().attributeExists("quizid"))
				 .andExpect(model().attribute("quizid",1))
				 .andReturn();
		assertNotNull(mvcResult);
	}
}
