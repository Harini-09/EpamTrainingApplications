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

import com.epam.dtos.QuestionDto;
import com.epam.entities.Question;
import com.epam.service.QuestionLibraryService;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(QuestionRestController.class)
class QuestionRestControllerTest {
	
	@MockBean
	QuestionLibraryService service;
	
	@Autowired
	private MockMvc mockMvc;
	
	private QuestionDto questionDto;
	
	private Question question;
	
	@BeforeEach
	public void setUp() {
		question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		questionDto = new QuestionDto("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
	}
	
	@Test
	void getQuestionsTest() throws Exception {
		Mockito.when(service.viewQuestions()).thenReturn(List.of(question));
		MvcResult mvcResult = mockMvc.perform(get("/questions/view"))
							.andExpect(status().isOk())
							.andExpect(jsonPath("$[0].title").value("Collections"))
							.andReturn();
		assertNotNull(mvcResult);
				
	}  
	
	@Test
	void getTheQuestionByIdTest() throws Exception {
		Mockito.when(service.getQuestionById(1)).thenReturn(questionDto);
		MvcResult mvcResult = mockMvc.perform(get("/questions/question/{question_id}",1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("Collections"))
				.andReturn();
		assertNotNull(mvcResult);
				
	}
	
	@Test
	void saveQuestionTest() throws Exception {
		Mockito.when(service.insert(questionDto)).thenReturn(questionDto);
		mockMvc.perform(post("/questions/save")
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(questionDto)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.title").value("Collections"));
	}
	
	@Test
	void deleteQuestionTest() throws Exception {
		Mockito.when(service.delete(1)).thenReturn(question);
		mockMvc.perform(delete("/questions/delete/1"))
				.andExpect(status().isNoContent());
	}
	
	@Test
	void updateQuestionTitleTest() throws Exception {
		Mockito.when(service.modifyTitle(1, "Java")).thenReturn(questionDto);
		mockMvc.perform(put("/questions/title/1?title=\"Java\""))
				.andExpect(status().isOk());
	}
	
	@Test
	void updateQuestionDescriptionTest() throws Exception {
		Mockito.when(service.modifyQuestion(1, "What is java?")).thenReturn(questionDto);
		mockMvc.perform(put("/questions/questiondesc/1?question=\"What is java?\""))
				.andExpect(status().isOk());
	}
	
	@Test
	void updateQuestionLevelTest() throws Exception {
		Mockito.when(service.modifyQuestionLevel(1, "Hard")).thenReturn(questionDto);
		mockMvc.perform(put("/questions/level/1?questionlevel=\"Hard\""))
				.andExpect(status().isOk());
	}
	
	@Test
	void updateOptionsTest() throws Exception {
		List<String> options = Arrays.asList("a.true","b.false");
		Mockito.when(service.modifyOptions(1, options)).thenReturn(questionDto);
		mockMvc.perform(put("/questions/options/1?options=\"[a.true,b.fale]\""))
				.andExpect(status().isOk());
	}
	
	@Test
	void updateTopicTagTest() throws Exception {
		Mockito.when(service.modifyTopicTag(1, "Java")).thenReturn(questionDto);
		mockMvc.perform(put("/questions/topictag/1?topictag=\"Java\""))
				.andExpect(status().isOk());
	}
	
	@Test
	void updateAnswerTest() throws Exception {
		Mockito.when(service.modifyAnswer(1, "a")).thenReturn(questionDto);
		mockMvc.perform(put("/questions/answer/1?answer=\"a\""))
				.andExpect(status().isOk());
	}

}
