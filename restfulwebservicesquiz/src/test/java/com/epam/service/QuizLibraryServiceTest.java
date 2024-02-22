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
import com.epam.customexceptions.InvalidQuizIdEntryException;
import com.epam.customexceptions.NoSuchQuestionExistInQuizException;
import com.epam.dtoconverter.QuizDtoConverter;
import com.epam.dtos.QuizDto;
import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.repository.QuestionRepo;
import com.epam.repository.QuizRepo;

@ExtendWith(MockitoExtension.class)
class QuizLibraryServiceTest {

	@Mock 
	QuizRepo quizRepo;
	
	@Mock
	QuestionRepo questionRepo;
	
	@Mock
	Validation validationImpl;
	
	@Mock
	QuizDtoConverter quizDtoConverter;

	@InjectMocks
	QuizLibraryService crudOperations;

	private Question question;
	
	private Quiz quiz;
	
	private QuizDto quizDto;
	
	private Optional<Quiz> quizOptional;
	
	private List<Quiz> quizList ;
	
	private Optional<Question> questionOptional;
	
	@Mock
	private List<Question> questionsList;
	
	@BeforeEach
	void setUp() {
		question = new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		question.setQuestionId(1);
		List<Question> questionsList = Arrays.asList(question);
		quiz = new Quiz("Basic", questionsList, 30);
		quizDto = new QuizDto("Basic", questionsList, 30);
		quizList = Arrays.asList(quiz);
		quiz.setQuizId(101);
		questionOptional = Optional.of(question);
		quizOptional = Optional.of(quiz);
	}
	
	@Test
	void insertTest() {
		Mockito.when(quizDtoConverter.convertDtoToQuiz(quizDto)).thenReturn(quiz);
		Mockito.when(quizRepo.save(quiz)).thenReturn(quiz);
		Mockito.when(quizDtoConverter.convertQuizToDto(quiz)).thenReturn(quizDto);
		QuizDto addedQuiz = crudOperations.insert(quizDto);
		assertEquals(quizDto, addedQuiz);
	}

	@Test
	void addQuestionTest() throws Exception{
		Mockito.when(quizRepo.existsById(101)).thenReturn(true);
		Mockito.when(questionRepo.existsById(1)).thenReturn(true);
		Mockito.when(quizRepo.findById(101)).thenReturn(quizOptional);
		Mockito.when(questionRepo.findById(1)).thenReturn(questionOptional);
		Mockito.when(validationImpl.getQuestionsInQuiz(quiz)).thenReturn(questionsList);
		Mockito.when(questionsList.add(question)).thenReturn(true);
		Mockito.when(quizRepo.save(quiz)).thenReturn(quiz);
		Mockito.when(quizDtoConverter.convertQuizToDto(quiz)).thenReturn(quizDto);
		QuizDto returnedQuiz = crudOperations.addQuestion(101, 1);
		assertEquals(quizDto, returnedQuiz);
	}
	
	@Test
	void addQuestionTestFailure1() throws InvalidQuizIdEntryException {
		Mockito.when(quizRepo.existsById(101)).thenReturn(false);
		assertThrows(InvalidQuizIdEntryException.class, () -> crudOperations.addQuestion(101,1));
	}
	
	@Test
	void addQuestionTestFailure2() throws InvalidQuestionIdEntryException {
		Mockito.when(quizRepo.existsById(101)).thenReturn(true);
		Mockito.when(questionRepo.existsById(1)).thenReturn(false);
		assertThrows(InvalidQuestionIdEntryException.class, () -> crudOperations.addQuestion(101,1));
	}
	
	@Test
	void removeQuestionTest() throws Exception{
		Mockito.when(quizRepo.existsById(101)).thenReturn(true);
		Mockito.when(quizRepo.findById(101)).thenReturn(quizOptional);
		Mockito.when(questionRepo.findById(1)).thenReturn(questionOptional);
		Mockito.when(validationImpl.getQuestionsInQuiz(quiz)).thenReturn(questionsList);
		Mockito.when(questionsList.contains(question)).thenReturn(true);
		Mockito.when(validationImpl.getQuestionsInQuiz(quiz)).thenReturn(questionsList);
		Mockito.when(questionsList.remove(question)).thenReturn(true);
		Mockito.when(quizRepo.save(quiz)).thenReturn(quiz);
		Mockito.when(quizDtoConverter.convertQuizToDto(quiz)).thenReturn(quizDto);
		QuizDto returnedQuiz = crudOperations.removeQuestion(101, 1);
		assertEquals(quizDto, returnedQuiz);
	}
	
	@Test
	void removeQuestionTestFailure1() throws InvalidQuizIdEntryException {
		Mockito.when(quizRepo.existsById(101)).thenReturn(false);
		assertThrows(InvalidQuizIdEntryException.class, () -> crudOperations.removeQuestion(101,1));
	}
	
	@Test
	void removeQuestionTestFailure2() throws NoSuchQuestionExistInQuizException {
		Mockito.when(quizRepo.existsById(101)).thenReturn(true);
		Mockito.when(quizRepo.findById(101)).thenReturn(quizOptional);
		Mockito.when(questionRepo.findById(1)).thenReturn(questionOptional);
		Mockito.when(validationImpl.getQuestionsInQuiz(quiz)).thenReturn(questionsList);
		Mockito.when(questionsList.contains(question)).thenReturn(false);
		assertThrows(NoSuchQuestionExistInQuizException.class, () -> crudOperations.removeQuestion(101,1));
	}

	@Test
	void updateMarksTestSuccess() throws Exception{
		Mockito.when(quizRepo.findById(101)).thenReturn(quizOptional);
		Mockito.when(quizRepo.save(quiz)).thenReturn(quiz);
		Mockito.when(quizDtoConverter.convertQuizToDto(quiz)).thenReturn(quizDto);
		QuizDto returnedQuiz = crudOperations.updateMarks(101, 40);
		assertEquals(quizDto, returnedQuiz);
	}
	
	@Test
	void updateMarksTestFailure() throws InvalidQuizIdEntryException {
		Mockito.when(quizRepo.findById(101)).thenReturn(Optional.empty());
		assertThrows(InvalidQuizIdEntryException.class, () -> crudOperations.updateMarks(101,20));
	}

	@Test
	void deleteTestSuccess() throws Exception{
		Mockito.when(quizRepo.findById(101)).thenReturn(quizOptional);
		doNothing().when(quizRepo).delete(quiz);
		Mockito.when(quizDtoConverter.convertQuizToDto(quiz)).thenReturn(quizDto);
		QuizDto returnedQuiz = crudOperations.delete(101);
		assertEquals(quizDto, returnedQuiz);
	}
	
	@Test
	void deleteTestFailure() throws InvalidQuizIdEntryException {
		Mockito.when(quizRepo.findById(101)).thenReturn(Optional.empty());
		assertThrows(InvalidQuizIdEntryException.class, () -> crudOperations.delete(101));
	}

	@Test 
	void viewTest() {
		Mockito.when(quizRepo.findAll()).thenReturn(quizList);
		List<Quiz> quizesList = crudOperations.view();
		assertEquals(quizList, quizesList);
	}

	@Test
	void addNewQuestionTestSuccess() throws Exception {
		Mockito.when(questionRepo.findById(1)).thenReturn(questionOptional);
		Question resultQuestion = crudOperations.addNewQuestion(1);
		assertEquals(question, resultQuestion);
	}
	
	@Test
	void addNewQuestionTestFailure() throws InvalidQuestionIdEntryException {
		Mockito.when(questionRepo.findById(1)).thenReturn(Optional.empty());
		assertThrows(InvalidQuestionIdEntryException.class, () -> crudOperations.addNewQuestion(1));
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
	void isQuizTitlePresentNegativeTest() throws InvalidQuizIdEntryException {
		Mockito.when(quizRepo.existsById(1)).thenReturn(false);
		assertThrows(InvalidQuizIdEntryException.class, () -> crudOperations.isQuizTitlePresent(1));
	}

	@Test
	void isQuizTitlePresentPositiveTest() throws InvalidQuizIdEntryException {
		Mockito.when(quizRepo.existsById(1)).thenReturn(true);
		crudOperations.isQuizTitlePresent(1);
		Mockito.verify(quizRepo).existsById(1);
	}

	@Test
	void isQuestionInQuizPresentNegativeTest() throws NoSuchQuestionExistInQuizException {
		Mockito.when(quizRepo.findById(1)).thenReturn(quizOptional);
		Mockito.when(questionRepo.findById(1)).thenReturn(questionOptional);
		Mockito.when(validationImpl.getQuestionsInQuiz(quiz)).thenReturn(questionsList);
		Mockito.when(questionsList.contains(question)).thenReturn(false);
		assertThrows(NoSuchQuestionExistInQuizException.class, () -> crudOperations.isQuestionInQuizPresent(1,1));
	}
  
	@Test
	void isQuestionInQuizPresentPositiveTest() throws NoSuchQuestionExistInQuizException {
		Mockito.when(quizRepo.findById(1)).thenReturn(quizOptional);
		Mockito.when(questionRepo.findById(1)).thenReturn(questionOptional);
		Mockito.when(validationImpl.getQuestionsInQuiz(quiz)).thenReturn(questionsList);
		Mockito.when(questionsList.contains(question)).thenReturn(true);
		crudOperations.isQuestionInQuizPresent(1, 1);
		Mockito.verify(quizRepo).findById(1);
	}

}
