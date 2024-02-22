package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.customexceptions.ProcessFailedException;
import com.epam.dtoconverter.QuestionDtoConverter;
import com.epam.dtos.QuestionDto;
import com.epam.entities.Question;
import com.epam.repository.QuestionRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionLibraryService {

	@Autowired
	QuestionRepo questionRepo;
	
	@Autowired
	QuestionDtoConverter questionDtoConverter;

	public List<Question> viewQuestions() {
		log.info("Entered into the Question Library Service - viewQuestions() method");
		return (List<Question>) questionRepo.findAll();
	}

	public QuestionDto insert(QuestionDto newQuestionDto) {
		log.info("Entered into the Question Library Service - insert() method");
		Question newQuestion = questionDtoConverter.convertDtoToQuestion(newQuestionDto);
		return questionDtoConverter.convertQuestionToDto(questionRepo.save(newQuestion));
	} 

	public Question delete(int questionId) throws InvalidQuestionIdEntryException, ProcessFailedException {
		log.info("Entered into the Question Library Service - delete() method");
		try {
			return questionRepo.findById(questionId).map(question -> {
				questionRepo.delete(question);
				return question;
			}).orElseThrow(InvalidQuestionIdEntryException::new);
		} catch (DataIntegrityViolationException e) {
			throw new ProcessFailedException("This question is used in the quiz library..You cannot delete it!!");
		}
	}

	public QuestionDto modifyTitle(int questionId, String title) throws InvalidQuestionIdEntryException {
		log.info("Entered into the Question Library Service - modifyTitle() method");
		return questionRepo.findById(questionId).map(question -> {
			question.setTitle(title);
			questionRepo.save(question);
			return questionDtoConverter.convertQuestionToDto(question);
		}).orElseThrow(InvalidQuestionIdEntryException::new);
	}

	public QuestionDto modifyQuestion(int questionId, String questionDesc) throws InvalidQuestionIdEntryException {
		log.info("Entered into the Question Library Service - modifyQuestion() method");
		return questionRepo.findById(questionId).map(question -> {
			question.setQuestionDescription(questionDesc);
			questionRepo.save(question);
			return questionDtoConverter.convertQuestionToDto(question);
		}).orElseThrow(InvalidQuestionIdEntryException::new);
	}

	public QuestionDto modifyOptions(int questionId, List<String> options) throws InvalidQuestionIdEntryException {
		log.info("Entered into the Question Library Service - modifyOptions() method");
		return questionRepo.findById(questionId).map(question -> {
			question.setOptions(options);
			questionRepo.save(question);
			return questionDtoConverter.convertQuestionToDto(question);
		}).orElseThrow(InvalidQuestionIdEntryException::new);
	}

	public QuestionDto modifyQuestionLevel(int questionId, String questionLevel) throws InvalidQuestionIdEntryException {
		log.info("Entered into the Question Library Service - modifyQuestionLevel() method");
		return questionRepo.findById(questionId).map(question -> {
			question.setQuestionlevel(questionLevel);
			questionRepo.save(question);
			return questionDtoConverter.convertQuestionToDto(question);
		}).orElseThrow(InvalidQuestionIdEntryException::new);
	}

	public QuestionDto modifyTopicTag(int questionId, String topicTag) throws InvalidQuestionIdEntryException {
		log.info("Entered into the Question Library Service - modifyTopicTag() method");
		return questionRepo.findById(questionId).map(question -> {
			question.setTopictag(topicTag);
			questionRepo.save(question);
			return questionDtoConverter.convertQuestionToDto(question);
		}).orElseThrow(InvalidQuestionIdEntryException::new);
	}

	public QuestionDto modifyAnswer(int questionId, String answer) throws InvalidQuestionIdEntryException {
		log.info("Entered into the Question Library Service - modifyAnswer() method");
		return questionRepo.findById(questionId).map(question -> {
			question.setAnswer(answer);
			questionRepo.save(question);
			return questionDtoConverter.convertQuestionToDto(question);
		}).orElseThrow(InvalidQuestionIdEntryException::new);
	}

	public void isQuestionIdPresent(int questionId) throws InvalidQuestionIdEntryException {
		log.info("Entered into the Question Library Service - isQuestionIdPresent() method");
		if(!questionRepo.existsById(questionId)) {
			throw new InvalidQuestionIdEntryException();
		}
	}

	public QuestionDto getQuestionById(int questionId) throws InvalidQuestionIdEntryException {
		log.info("Entered into the Question Library Service - getQuestionById() method");
		return questionRepo.findById(questionId).map(question -> questionDtoConverter.convertQuestionToDto(question)).orElseThrow(InvalidQuestionIdEntryException::new);
	}

}
