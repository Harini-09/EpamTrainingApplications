package com.epam.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.epam.dtos.UserDto;
import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.service.QuizAllotment;
import com.epam.service.QuizLibraryService;
import com.epam.service.UserAuthenticationImpl;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
	
	@Mock
	UserAuthenticationImpl authentication;
	
	@Mock
	QuizAllotment quizAllotment;
	
	@Mock
	QuizLibraryService quizService;
	
	@InjectMocks
	UserController userController;
	
	private MockMvc mockMvc;
	UserDto userDto;
	
	@BeforeEach
	void setUp() {
		mockMvc= MockMvcBuilders.standaloneSetup(userController).build();
	}
	
	@Test
	void homeTest() {
		assertEquals("home",userController.home());
	}
	
	@Test
	void validateAdmin() throws Exception {
		userDto = new UserDto("sam","sam@112","admin");
		Mockito.when(authentication.logIn(userDto)).thenReturn(true);
		MvcResult mvcResult = mockMvc.perform(
				post("/validateAdmin").param("id","sam").param("password", "sam@112").param("type", "admin"))
				.andExpect(status().isOk()).andExpect(view().name("showoperations")).andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void validateInvalidAdmin() throws Exception {
		userDto = new UserDto("sam","sam@112","admin");
		Mockito.when(authentication.logIn(userDto)).thenReturn(false);
		MvcResult mvcResult = mockMvc.perform(post("/validateAdmin").param("id","sam").param("password", "sam@112").param("type", "admin"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("invaliduser", "Incorrect Credentials!!")).andExpect(view().name("errorinuser"))
				.andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void validateUser() throws Exception{
		userDto = new UserDto("sam","sam@112","user");
		Mockito.when(authentication.logIn(userDto)).thenReturn(true);
		MvcResult mvcResult = mockMvc.perform(
				post("/validateuser").param("id","sam").param("password", "sam@112").param("type", "user"))
				.andExpect(status().isOk()).andExpect(view().name("showquizforuser")).andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void validateInvalidUser() throws Exception {
		userDto = new UserDto("sam","sam@112","user");
		Mockito.when(authentication.logIn(userDto)).thenReturn(false);
		MvcResult mvcResult = mockMvc.perform(post("/validateuser").param("id","sam").param("password", "sam@112").param("type", "admin"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("invaliduser", "Incorrect Credentials!!")).andExpect(view().name("errorinuser"))
				.andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void registeruser() throws Exception{
		userDto = new UserDto("sam","sam@112","user");
		Mockito.when(authentication.signUp(userDto)).thenReturn(userDto);
		MvcResult mvcResult = mockMvc.perform(
				post("/registeruser").param("id","sam").param("password", "sam@112").param("type", "user"))
				.andExpect(status().isOk()).andExpect(view().name("showquizforuser")).andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void viewQuizes() throws Exception {
		Question question=new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		List<Question> quizQuestionsList = Arrays.asList(question);
		Quiz quiz = new Quiz("Basic",quizQuestionsList,30);
		List<Quiz> quizes = Arrays.asList(quiz);
		Mockito.when(quizService.view()).thenReturn(quizes);
		MvcResult mvcResult = mockMvc.perform(get("/viewquizesavailable"))
								.andExpect(status().isOk())
								.andExpect(view().name("availablequizes"))
								.andExpect(model().attribute("message","Quizes Available in Quiz Library:"))
								.andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void takeQuiz() throws Exception {
		Question question=new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		List<Question> quizQuestionsList = Arrays.asList(question);
		Quiz quiz = new Quiz("Basic",quizQuestionsList,30);
		quiz.setQuizId(1);
		Mockito.when(quizAllotment.allotQuiz(1)).thenReturn(quiz);
		MvcResult mvcResult = mockMvc.perform(get("/TakeQuiz").param("quizID", "1"))
									.andExpect(status().isOk())
									.andExpect(view().name("quiz"))
									.andExpect(model().attribute("message","Quizes Available in Quiz Library:"))
									.andReturn();
		
		assertNotNull(mvcResult); 
	}
	
	@Test
	void submitQuiz() throws Exception {
		Question question=new Question("Collections", "Which collection stores unique values?",
		Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		List<Question> quizQuestionsList = Arrays.asList(question);
		Quiz quiz = new Quiz("Basic",quizQuestionsList,30);
		quiz.setQuizId(1);
		Mockito.when(quizAllotment.allotQuiz(1)).thenReturn(quiz);
		MvcResult mvcResult = mockMvc.perform(get("/submitQuiz").param("answers", "[\"a\",\"b\"]").param("quizID","1"))
				.andExpect(status().isOk())
				.andExpect(view().name("userlogout"))
				.andExpect(model().attribute("message","Your Total Score:"+0.0))
				.andReturn();
 
		assertNotNull(mvcResult); 
	}
	
	 
}























