package com.epam.restcontroller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.epam.dtos.UserDto;
import com.epam.entities.User;
import com.epam.service.UserAuthentication;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserRestController.class)
class UserRestControllerTest {

	@MockBean
	UserAuthentication service;
	
	@Autowired
	private MockMvc mockMvc;
	
	private UserDto userDto;
	
	private User user;
	
	@BeforeEach
	void setUp() {
		user = new User("user","user","user");
		userDto = new UserDto("user","user","user");
	}
	
	@Test
	void getQuestionsTest() throws Exception {
		Mockito.when(service.view()).thenReturn(List.of(user));
		MvcResult mvcResult = mockMvc.perform(get("/users/view"))
							.andExpect(status().isOk())
							.andExpect(jsonPath("$[0].id").value("user"))
							.andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void saveUserTest() throws Exception {
		Mockito.when(service.signUp(userDto)).thenReturn(userDto);
		mockMvc.perform(post("/users/save")
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(userDto)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").value("user"));
	} 
	
	@Test
	void checkUserTestSuccess() throws Exception {
		Mockito.when(service.logIn(userDto)).thenReturn(true);
		mockMvc.perform(post("/users/check")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDto)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("User successfully logged in !!!"));
	}
	
	@Test
	void checkUserTestFailure() throws Exception {
		Mockito.when(service.logIn(userDto)).thenReturn(false);
		mockMvc.perform(post("/users/check")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDto)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("Invalid Credentials!!"));
	}
	
}
