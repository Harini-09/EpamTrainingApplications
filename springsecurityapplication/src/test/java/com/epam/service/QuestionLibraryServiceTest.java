package com.epam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.dtoconverter.QuestionDtoConverter;
import com.epam.dtos.QuestionDto;
import com.epam.entities.Question;
import com.epam.repository.QuestionRepo;

@ExtendWith(MockitoExtension.class)
class QuestionLibraryServiceTest {

	@Mock 
	private QuestionRepo questionRepo;
	
	@Mock
	QuestionDtoConverter questionDtoConverter;

	@InjectMocks
	private QuestionLibraryService crudOperations;
	
	private Question question;
	
	private QuestionDto questionDto;
	
	private Optional<Question> optional;

	@BeforeEach 
	void setUp() {
		question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		questionDto = new QuestionDto("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		optional = Optional.of(question);
	}
	
	@Test
	void insertTest() {
		Mockito.when(questionDtoConverter.convertDtoToQuestion(questionDto)).thenReturn(question);
		Mockito.when(questionRepo.save(question)).thenReturn(question);
		Mockito.when(questionDtoConverter.convertQuestionToDto(question)).thenReturn(questionDto);
		QuestionDto addedQuestion = crudOperations.insert(questionDto);
		assertEquals(questionDto, addedQuestion);
	}

	@Test
	void modifyTitleTestSuccess() throws Exception{
		Mockito.when(questionRepo.findById(1)).thenReturn(optional);
		Mockito.when(questionRepo.save(question)).thenReturn(question);
		Mockito.when(questionDtoConverter.convertQuestionToDto(question)).thenReturn(questionDto);
		QuestionDto returnedQuestionDto = crudOperations.modifyTitle(1, "Collections");
		assertEquals(questionDto, returnedQuestionDto); 
	}
	
	@Test 
	void modifyTitleTestFailure() throws InvalidQuestionIdEntryException {
		Mockito.when(questionRepo.findById(1)).thenReturn(Optional.empty());
		assertThrows(InvalidQuestionIdEntryException.class, () -> crudOperations.modifyTitle(1, "Collections"));
	}
	
	@Test
	void modifyQuestionTestSuccess() throws Exception {
		Mockito.when(questionRepo.findById(1)).thenReturn(optional);
		Mockito.when(questionRepo.save(question)).thenReturn(question);
		Mockito.when(questionDtoConverter.convertQuestionToDto(question)).thenReturn(questionDto);
		QuestionDto returnedQuestionDto = crudOperations.modifyQuestion(1, "Which method is used to get the length of a string?");
		assertEquals(questionDto, returnedQuestionDto); 
	}
	
	@Test 
	void modifyQuestionTestFailure() throws InvalidQuestionIdEntryException {
		Mockito.when(questionRepo.findById(1)).thenReturn(Optional.empty());
		assertThrows(InvalidQuestionIdEntryException.class, () -> crudOperations.modifyQuestion(1, "Which method is used to get the length of a string?"));
	}

	@Test
	void modifyOptionsTestSuccess() throws Exception {
		List<String> options = Arrays.asList("a.len()", "b.size()", "c.length()");
		Mockito.when(questionRepo.findById(1)).thenReturn(optional);
		Mockito.when(questionRepo.save(question)).thenReturn(question);
		Mockito.when(questionDtoConverter.convertQuestionToDto(question)).thenReturn(questionDto);
		QuestionDto returnedQuestionDto = crudOperations.modifyOptions(1, options);
		assertEquals(questionDto, returnedQuestionDto); 
	}
	
	@Test 
	void modifyOptionsTestFailure() throws InvalidQuestionIdEntryException {
		Mockito.when(questionRepo.findById(1)).thenReturn(Optional.empty());
		assertThrows(InvalidQuestionIdEntryException.class, () -> crudOperations.modifyOptions(1, Arrays.asList("a.len()", "b.size()", "c.length()")));
	}

	@Test
	void modifyQuestionLevelTestSuccess() throws Exception {
		Mockito.when(questionRepo.findById(1)).thenReturn(optional);
		Mockito.when(questionRepo.save(question)).thenReturn(question);
		Mockito.when(questionDtoConverter.convertQuestionToDto(question)).thenReturn(questionDto);
		QuestionDto returnedQuestionDto = crudOperations.modifyQuestionLevel(1, "Basic");
		assertEquals(questionDto, returnedQuestionDto); 
	}
	
	@Test 
	void modifyQuestionLevelTestFailure() throws InvalidQuestionIdEntryException {
		Mockito.when(questionRepo.findById(1)).thenReturn(Optional.empty());
		assertThrows(InvalidQuestionIdEntryException.class, () -> crudOperations.modifyQuestionLevel(1, "Basic"));
	}

	@Test
	void modifyTopicTagTestSuccess() throws Exception {
		Mockito.when(questionRepo.findById(1)).thenReturn(optional);
		Mockito.when(questionRepo.save(question)).thenReturn(question);
		Mockito.when(questionDtoConverter.convertQuestionToDto(question)).thenReturn(questionDto);
		QuestionDto returnedQuestionDto = crudOperations.modifyTopicTag(1, "Java");
		assertEquals(questionDto, returnedQuestionDto); 
	}
	
	@Test 
	void modifyTopicTagTestFailure() throws InvalidQuestionIdEntryException {
		Mockito.when(questionRepo.findById(1)).thenReturn(Optional.empty());
		assertThrows(InvalidQuestionIdEntryException.class, () -> crudOperations.modifyTopicTag(1, "Java"));
	}

	@Test 
	void modifyAnswerTestSuccess() throws Exception {
		Mockito.when(questionRepo.findById(1)).thenReturn(optional);
		Mockito.when(questionRepo.save(question)).thenReturn(question);
		Mockito.when(questionDtoConverter.convertQuestionToDto(question)).thenReturn(questionDto);
		QuestionDto returnedQuestionDto = crudOperations.modifyAnswer(1, "a");
		assertEquals(questionDto, returnedQuestionDto); 
	}
	
	@Test 
	void modifyAnswerTestFailure() throws InvalidQuestionIdEntryException {
		Mockito.when(questionRepo.findById(1)).thenReturn(Optional.empty());
		assertThrows(InvalidQuestionIdEntryException.class, () -> crudOperations.modifyAnswer(1, "a"));
	}

	@Test
	void deleteTestSuccess() throws Exception {
		Mockito.when(questionRepo.findById(1)).thenReturn(optional);
		doNothing().when(questionRepo).delete(question);
		Question returnedQuestion = crudOperations.delete(1);
		assertEquals(question, returnedQuestion); 
	}
	
	@Test 
	void deleteTestFailure1() throws InvalidQuestionIdEntryException {
		Mockito.when(questionRepo.findById(1)).thenReturn(Optional.empty());
		assertThrows(InvalidQuestionIdEntryException.class, () -> crudOperations.delete(1));
	}
	
	@Test
	void viewQuestionsTest() {
		Question question1 = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question1.setQuestionId(1);
		Question question2 = new Question("Strings", "Which method is used to get the length of a string?",
				Arrays.asList("a.length()", "b.size()", "c.len()", "d.none"), "Easy", "Java", "a");
		question2.setQuestionId(2);
		List<Question> questions = Arrays.asList(question1, question2);
		Mockito.when(questionRepo.findAll()).thenReturn(questions);
		List<Question> resultQuestions = crudOperations.viewQuestions();
		assertEquals(questions, resultQuestions);
	}
 
	@Test 
	void isQuestionIdPresentNegativeTest() throws InvalidQuestionIdEntryException {
		Mockito.when(questionRepo.existsById(1)).thenReturn(false);
		assertThrows(InvalidQuestionIdEntryException.class, () -> crudOperations.isQuestionIdPresent(1));
	}
	
	@Test
	void isQuestionIdPresentPositiveTest() throws InvalidQuestionIdEntryException {
		Mockito.when(questionRepo.existsById(1)).thenReturn(true);
		crudOperations.isQuestionIdPresent(1);
		Mockito.verify(questionRepo).existsById(1);
	}
	
	@Test
	void getQuestionByIdPositiveTest() throws Exception {
		Mockito.when(questionRepo.findById(1)).thenReturn(optional);
		Mockito.when(questionDtoConverter.convertQuestionToDto(question)).thenReturn(questionDto);
		QuestionDto returnedQuestion = crudOperations.getQuestionById(1);
		assertEquals(questionDto,returnedQuestion);
	}
	
	@Test 
	void getQuestionByIdNegativeTest() throws InvalidQuestionIdEntryException {
		Mockito.when(questionRepo.findById(1)).thenReturn(Optional.empty());
		assertThrows(InvalidQuestionIdEntryException.class, () -> crudOperations.getQuestionById(1));
	}
	
	
}
