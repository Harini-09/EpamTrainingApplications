package com.epam.controller;

import java.util.ArrayList;
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
import com.epam.customexceptions.ProcessFailedException;
import com.epam.dtos.QuestionDto;
import com.epam.entities.Question;
import com.epam.service.QuestionLibraryService;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
@ExtendWith(MockitoExtension.class)
class QuestionControllerTest {
	
	@Mock
	QuestionLibraryService questionService;
	
	@InjectMocks
	QuestionController questionController;
	
	private MockMvc mockMvc;
	
	private QuestionDto questionDto;
	
	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
		questionDto = new QuestionDto("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
	}
	
	@Test
	void testCreate() throws Exception {
		Mockito.when(questionService.insert(any())).thenReturn(questionDto);
		MvcResult mvcResult = mockMvc.perform(post("/createquestion").param("title","Collections").param("questionDescription","Which collection stores unique values?").param("options", "Arrays.asList(\"a.List\", \"b.Map\", \"c.Set\", \"d.Stack\")").param("questionlevel", "Intermediate").param("topictag","Java").param("answer","c"))
									 .andExpect(view().name("success"))
									 .andExpect(model().attributeExists("message"))
									 .andExpect(model().attribute("message","Question created successfully"))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test  
	void testModifyTitleSuccess() throws Exception {
		doNothing().when(questionService).isQuestionIdPresent(1);
		Mockito.when(questionService.modifyTitle(eq(1), any())).thenReturn(questionDto);
		MvcResult mvcResult = mockMvc.perform(post("/modifytitleforquestion")
									 .param("id","1").param("title","Collections"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("questiontitlemodified"))
									 .andExpect(model().attributeExists("questionid"))
									 .andExpect(model().attribute("questionid",1))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	 
	@Test
	void testModifyTitleFailure() throws Exception {
		doThrow(InvalidQuestionIdEntryException.class).when(questionService).isQuestionIdPresent(1);
		MvcResult mvcResult = mockMvc.perform(post("/modifytitleforquestion")
									 .param("id","1").param("title","Collections"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("errorinquestions"))
									 .andExpect(model().attributeExists("errormessage"))
									 .andExpect(model().attribute("errormessage",new InvalidQuestionIdEntryException().getMessage()))
									 .andReturn();
		assertNotNull(mvcResult);
	} 
	
	
	
	@Test 
	void testModifyQuestionSuccess() throws Exception {
		doNothing().when(questionService).isQuestionIdPresent(1);
		Mockito.when(questionService.modifyQuestion(eq(1), any())).thenReturn(questionDto);
		MvcResult mvcResult = mockMvc.perform(post("/modifyquestiondescriptionforquestion")
									 .param("id","1").param("question","What is java?"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("questiondescriptionmodified"))
									 .andExpect(model().attributeExists("questionid"))
									 .andExpect(model().attribute("questionid",1))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test 
	void testModifyQuestionFailure() throws Exception {
		doThrow(InvalidQuestionIdEntryException.class).when(questionService).isQuestionIdPresent(1);
		MvcResult mvcResult = mockMvc.perform(post("/modifyquestiondescriptionforquestion")
									 .param("id","1").param("question","What is java?"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("errorinquestions"))
									 .andExpect(model().attributeExists("errormessage"))
									 .andExpect(model().attribute("errormessage",new InvalidQuestionIdEntryException().getMessage()))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test 
	void testModifyOptionsSuccess() throws Exception {
		doNothing().when(questionService).isQuestionIdPresent(1);
		Mockito.when(questionService.modifyOptions(eq(1), any())).thenReturn(questionDto);
		MvcResult mvcResult = mockMvc.perform(post("/modifyquestionoptions")
									 .param("id","1").param("options","a.List, b.Map, c.Set, d.Stack"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("questionoptionsmodified"))
									 .andExpect(model().attributeExists("questionid"))
									 .andExpect(model().attribute("questionid",1))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	 
	@Test 
	void testModifyOptionsFailure() throws Exception {
		doThrow(InvalidQuestionIdEntryException.class).when(questionService).isQuestionIdPresent(1);
		MvcResult mvcResult = mockMvc.perform(post("/modifyquestionoptions")
									 .param("id","1").param("options","a.List, b.Map, c.Set, d.Stack"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("errorinquestions"))
									 .andExpect(model().attributeExists("errormessage"))
									 .andExpect(model().attribute("errormessage",new InvalidQuestionIdEntryException().getMessage()))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test 
	void testModifyQuestionLevelSuccess() throws Exception {
		doNothing().when(questionService).isQuestionIdPresent(1);
		Mockito.when(questionService.modifyQuestionLevel(eq(1), any())).thenReturn(questionDto);
		MvcResult mvcResult = mockMvc.perform(post("/modifyquestionlevel")
									 .param("id","1").param("questionlevel","Hard"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("questionlevelmodified"))
									 .andExpect(model().attributeExists("questionid"))
									 .andExpect(model().attribute("questionid",1))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test 
	void testModifyQuestionLevelFailure() throws Exception {
		doThrow(InvalidQuestionIdEntryException.class).when(questionService).isQuestionIdPresent(1);
		MvcResult mvcResult = mockMvc.perform(post("/modifyquestionlevel")
									 .param("id","1").param("questionlevel","Hard"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("errorinquestions"))
									 .andExpect(model().attributeExists("errormessage"))
									 .andExpect(model().attribute("errormessage",new InvalidQuestionIdEntryException().getMessage()))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	 
	@Test 
	void testModifyTopicTagSuccess() throws Exception {
		doNothing().when(questionService).isQuestionIdPresent(1);
		Mockito.when(questionService.modifyTopicTag(eq(1), any())).thenReturn(questionDto);
		MvcResult mvcResult = mockMvc.perform(post("/modifytopictag")
									 .param("id","1").param("topictags","java"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("questiontopictagmodified"))
									 .andExpect(model().attributeExists("questionid"))
									 .andExpect(model().attribute("questionid",1))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test 
	void testModifyTopicTagFailure() throws Exception {
		doThrow(InvalidQuestionIdEntryException.class).when(questionService).isQuestionIdPresent(1);
		MvcResult mvcResult = mockMvc.perform(post("/modifytopictag")
									 .param("id","1").param("topictags","java"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("errorinquestions"))
									 .andExpect(model().attributeExists("errormessage"))
									 .andExpect(model().attribute("errormessage",new InvalidQuestionIdEntryException().getMessage()))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test 
	void testModifyAnswerSuccess() throws Exception {
		doNothing().when(questionService).isQuestionIdPresent(1);
		Mockito.when(questionService.modifyAnswer(eq(1), any())).thenReturn(questionDto);
		MvcResult mvcResult = mockMvc.perform(post("/modifyanswer")
									 .param("id","1").param("answer","a"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("questionanswermodified"))
									 .andExpect(model().attributeExists("questionid"))
									 .andExpect(model().attribute("questionid",1))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test 
	void  testModifyAnswerFailure() throws Exception {
		doThrow(InvalidQuestionIdEntryException.class).when(questionService).isQuestionIdPresent(1);
		MvcResult mvcResult = mockMvc.perform(post("/modifyanswer")
				 					 .param("id","1").param("answer","a"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("errorinquestions"))
									 .andExpect(model().attributeExists("errormessage"))
									 .andExpect(model().attribute("errormessage",new InvalidQuestionIdEntryException().getMessage()))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	 
	@Test
	void testRemoveQuestionSuccess() throws Exception{
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		doNothing().when(questionService).isQuestionIdPresent(1);
		Mockito.when(questionService.delete(1)).thenReturn(question);
		MvcResult mvcResult = mockMvc.perform(post("/removequestion")
							 		 .param("id", "1"))
				.andExpect(status().isOk())
				 .andExpect(view().name("questionremoved"))
				 .andExpect(model().attributeExists("questionid"))
				 .andExpect(model().attribute("questionid",1))
				 .andReturn();
assertNotNull(mvcResult);
	}
	
	@Test 
	void  testRemoveQuestionFailure1() throws Exception {
		doThrow(InvalidQuestionIdEntryException.class).when(questionService).isQuestionIdPresent(1);
		MvcResult mvcResult = mockMvc.perform(post("/removequestion")
				 					 .param("id","1"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("errorinquestions"))
									 .andExpect(model().attributeExists("errormessage"))
									 .andExpect(model().attribute("errormessage",new InvalidQuestionIdEntryException().getMessage()))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	 
	@Test  
	void  testRemoveQuestionFailure2() throws Exception {
		doNothing().when(questionService).isQuestionIdPresent(1);
		doThrow(new ProcessFailedException("This question is used in the quiz library..You cannot delete it!!")).when(questionService).delete(1);
		MvcResult mvcResult = mockMvc.perform(post("/removequestion")
				 					 .param("id","1"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("errorinquestions"))
									 .andExpect(model().attributeExists("errormessage"))
									 .andExpect(model().attribute("errormessage","This question is used in the quiz library..You cannot delete it!!"))
									 .andReturn();
		assertNotNull(mvcResult);
	}
	
	 
	@Test
	void displayTest() throws Exception {
		Question question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		List<Question> questionlist=new ArrayList<>();
		questionlist.add(question);
		Mockito.when(questionService.viewQuestions()).thenReturn(questionlist);
		MvcResult mvcResult = mockMvc.perform(get("/displayQuestions"))
									 .andExpect(status().isOk())
									 .andExpect(view().name("viewQuestions"))
									 .andExpect(model().attributeExists("questions"))
									 .andExpect(model().attribute("questions",questionService.viewQuestions()))
									 .andExpect(model().attribute("message", "Question Library"))
									 .andReturn();
					assertNotNull(mvcResult);
	}
	
	@Test
	void modifyopstest() throws Exception{
		MvcResult mvcResult = mockMvc.perform(post("/modifyops")
				 .param("id","1"))
				 .andExpect(status().isOk())
				 .andExpect(view().name("modifyquestion"))
				 .andExpect(model().attributeExists("questionid"))
				 .andExpect(model().attribute("questionid",1))
				 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void modifytitletest() throws Exception{
		MvcResult mvcResult = mockMvc.perform(post("/modifytitleid")
				 .param("id","1"))
				 .andExpect(status().isOk())
				 .andExpect(view().name("modifytitle"))
				 .andExpect(model().attributeExists("questionid"))
				 .andExpect(model().attribute("questionid",1))
				 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void modifyquedesctest() throws Exception{
		MvcResult mvcResult = mockMvc.perform(post("/modifyquestiondescriptionid")
				 .param("id","1"))
				 .andExpect(status().isOk())
				 .andExpect(view().name("modifyquestiondescription"))
				 .andExpect(model().attributeExists("questionid"))
				 .andExpect(model().attribute("questionid",1))
				 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void modifyoptionstest() throws Exception{
		MvcResult mvcResult = mockMvc.perform(post("/modifyoptionsid")
				 .param("id","1"))
				 .andExpect(status().isOk())
				 .andExpect(view().name("modifyoptions"))
				 .andExpect(model().attributeExists("questionid"))
				 .andExpect(model().attribute("questionid",1))
				 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void modifyleveltest() throws Exception{
		MvcResult mvcResult = mockMvc.perform(post("/modifylevelid")
				 .param("id","1"))
				 .andExpect(status().isOk())
				 .andExpect(view().name("modifyquestionlevel"))
				 .andExpect(model().attributeExists("questionid"))
				 .andExpect(model().attribute("questionid",1))
				 .andReturn();
		assertNotNull(mvcResult);
	}	
	
	@Test
	void modifytopictagtest() throws Exception{
		MvcResult mvcResult = mockMvc.perform(post("/modifytopictagid")
				 .param("id","1"))
				 .andExpect(status().isOk())
				 .andExpect(view().name("modifytopictag"))
				 .andExpect(model().attributeExists("questionid"))
				 .andExpect(model().attribute("questionid",1))
				 .andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void modifyanswertest() throws Exception{
		MvcResult mvcResult = mockMvc.perform(post("/modifyanswerid")
				 .param("id","1"))
				 .andExpect(status().isOk())
				 .andExpect(view().name("modifyanswer"))
				 .andExpect(model().attributeExists("questionid"))
				 .andExpect(model().attribute("questionid",1))
				 .andReturn();
		assertNotNull(mvcResult);
	}	
}
