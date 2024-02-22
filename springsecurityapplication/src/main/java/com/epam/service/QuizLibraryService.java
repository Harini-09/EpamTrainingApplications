package com.epam.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.customexceptions.InvalidQuizIdEntryException;
import com.epam.customexceptions.NoSuchQuestionExistInQuizException;
import com.epam.dtoconverter.QuizDtoConverter;
import com.epam.dtos.QuizDto;
import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.repository.QuestionRepo;
import com.epam.repository.QuizRepo;

@Service
public class QuizLibraryService {

	@Autowired
	QuizRepo quizRepo;

	@Autowired
	QuestionRepo questionRepo;

	@Autowired
	private Validation validation;

	@Autowired
	QuizDtoConverter quizDtoConverter;
	
	private final Logger logger = LogManager.getLogger(QuizLibraryService.class);

	private Optional<Quiz> quiz;

	private Optional<Question> question;

	public QuizDto insert(QuizDto newQuizDto) {
		logger.info("Entered into the Quiz Library Service - insert() method");
		Quiz newQuiz = quizDtoConverter.convertDtoToQuiz(newQuizDto);
		return quizDtoConverter.convertQuizToDto(quizRepo.save(newQuiz));
	}

	public QuizDto addQuestion(int quizId, int questionId)
			throws InvalidQuestionIdEntryException, InvalidQuizIdEntryException {
		logger.info("Entered into the Quiz Library Service - addQuestion() method");
		try {
			isQuizTitlePresent(quizId);
			isQuestionIdPresent(questionId);
			quiz = quizRepo.findById(quizId);
			question = questionRepo.findById(questionId);
			List<Question> questions = validation.getQuestionsInQuiz(quiz.get());
			questions.add(question.get());
			quizRepo.save(quiz.get());
			return quizDtoConverter.convertQuizToDto(quiz.get());
		} catch (InvalidQuizIdEntryException e) {
			throw new InvalidQuizIdEntryException();
		} catch (InvalidQuestionIdEntryException e) {
			throw new InvalidQuestionIdEntryException();
		}

	}

	public QuizDto removeQuestion(int quizId, int questionId)
			throws InvalidQuizIdEntryException, NoSuchQuestionExistInQuizException {
		logger.info("Entered into the Quiz Library Service - removeQuestion() method");
		try {  
			isQuizTitlePresent(quizId);
		isQuestionInQuizPresent(quizId, questionId);
		quiz = quizRepo.findById(quizId);
		question = questionRepo.findById(questionId);
		List<Question> questions = validation.getQuestionsInQuiz(quiz.get());
		questions.remove(question.get());
		quizRepo.save(quiz.get());
		return quizDtoConverter.convertQuizToDto(quiz.get());
		}catch (InvalidQuizIdEntryException e) {
			throw new InvalidQuizIdEntryException();
		}catch(NoSuchQuestionExistInQuizException e) {
			throw new NoSuchQuestionExistInQuizException();
		}
	}

	public QuizDto updateMarks(int quizId, int newmarks) throws InvalidQuizIdEntryException {
		logger.info("Entered into the Quiz Library Service - updateMarks() method");
		return quizRepo.findById(quizId).map(thisquiz -> {
			thisquiz.setTotalMarks(newmarks);
			quizRepo.save(thisquiz);
			return quizDtoConverter.convertQuizToDto(thisquiz);
		}).orElseThrow(InvalidQuizIdEntryException::new);
	}

	public QuizDto delete(int quizId) throws InvalidQuizIdEntryException {
		logger.info("Entered into the Quiz Library Service - delete() method");
		return quizRepo.findById(quizId).map(newquiz -> {
			quizRepo.delete(newquiz);
			return quizDtoConverter.convertQuizToDto(newquiz);
		}).orElseThrow(InvalidQuizIdEntryException::new);
	}

	public List<Quiz> view() {
		logger.info("Entered into the Quiz Library Service - view() method");
		return (List<Quiz>) quizRepo.findAll();
	}
 
	public Question addNewQuestion(int questionId) throws InvalidQuestionIdEntryException {
		logger.info("Entered into the Quiz Library Service - addNewQuestion() method");
		return questionRepo.findById(questionId).orElseThrow(InvalidQuestionIdEntryException::new);
	}

	public void isQuestionIdPresent(int questionId) throws InvalidQuestionIdEntryException {
		logger.info("Entered into the Quiz Library Service - isQuestionIdPresent() method");
		if(!questionRepo.existsById(questionId)) {
			throw new InvalidQuestionIdEntryException();
		}
	}

	public void isQuizTitlePresent(int quizId) throws InvalidQuizIdEntryException {
		logger.info("Entered into the Quiz Library Service - isQuizTitlePresent() method");
		if(!quizRepo.existsById(quizId)) {
			throw new InvalidQuizIdEntryException();
		}
	}

	public void isQuestionInQuizPresent(int quizId, int questionId) throws NoSuchQuestionExistInQuizException {
		logger.info("Entered into the Quiz Library Service - isQuestionInQuizPresent() method");
		quiz = quizRepo.findById(quizId);
		question = questionRepo.findById(questionId);
		List<Question> questions = validation.getQuestionsInQuiz(quiz.get());
		if (!questions.contains(question.get())) {
			throw new NoSuchQuestionExistInQuizException();
		}
	}

}
