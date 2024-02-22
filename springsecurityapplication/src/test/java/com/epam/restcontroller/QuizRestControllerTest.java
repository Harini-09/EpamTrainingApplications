package com.epam.restcontroller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.epam.dtos.QuizDto;
import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.service.QuizLibraryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(QuizRestController.class)
class QuizRestControllerTest {
	
	@MockBean
	QuizLibraryService service;
	
	@Autowired
	private MockMvc mockMvc;
	
	private QuizDto quizDto;
	
	private Quiz quiz;
	
	private Question question;

	@BeforeEach
	public void setUp() {
		question=new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		List<Question> quizQuestionsList = Arrays.asList(question);
		quiz = new Quiz("Basic",quizQuestionsList,30);
		quizDto = new QuizDto("Basic",quizQuestionsList,30);
	}
	
	@Test
	void getQuizesTest() throws Exception {
		Mockito.when(service.view()).thenReturn(List.of(quiz));
		MvcResult mvcResult = mockMvc.perform(get("/quizes/view"))
							.andExpect(status().isOk())
							.andExpect(jsonPath("$[0].title").value("Basic"))
							.andReturn();
		assertNotNull(mvcResult);
	}  
	
	@Test
	void saveQuizTest() throws Exception {
		Mockito.when(service.insert(quizDto)).thenReturn(quizDto);
		mockMvc.perform(post("/quizes/save")
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(quizDto)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.title").value("Basic"));
	}
	
	@Test
	void deleteQuestionTest() throws Exception {
		Mockito.when(service.delete(1)).thenReturn(quizDto);
		mockMvc.perform(delete("/quizes/delete/1"))
				.andExpect(status().isNoContent());
	}
	
	@Test
	void addQuizQuestionTest() throws Exception {
		Mockito.when(service.addQuestion(1, 1)).thenReturn(quizDto);
		mockMvc.perform(put("/quizes/questionaddition/1?quizid=1"))
				.andExpect(status().isOk());
	}
	
	@Test
	void deleteQuizQuestionTest() throws Exception {
		Mockito.when(service.removeQuestion(1, 1)).thenReturn(quizDto);
		mockMvc.perform(put("/quizes/questiondeletion/1?quizid=1"))
				.andExpect(status().isOk());
	}
	
	@Test
	void updateTotalMarksTest() throws Exception {
		Mockito.when(service.updateMarks(1, 50)).thenReturn(quizDto);
		mockMvc.perform(put("/quizes/marksupdation/1?marks=50"))
				.andExpect(status().isOk());
	}
	
	
}
