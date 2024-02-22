package com.epam.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.entities.Question;
import com.epam.repository.QuestionRepo;

@Service
public class QuestionLibraryService {

	@Autowired 
	QuestionRepo questionRepo;

	private Optional<Question> question;
	
	public List<Question> viewQuestions() {
		return (List<Question>) questionRepo.findAll();
	}

	public Question insert(Question newQuestion) {
		return questionRepo.save(newQuestion);
	}
  
	public boolean delete(int questionId) throws ConstraintViolationException {
		boolean isDeleted = false;
		question = questionRepo.findById(questionId);
		if (question.isPresent()) {
			questionRepo.delete(question.get());
			isDeleted = !(questionRepo.existsById(questionId));
		}
		return isDeleted;
	}
	
	public String modifyTitle(int questionId, String title) {
		question = questionRepo.findById(questionId);
		question.get().setTitle(title);
		questionRepo.save(question.get());
		return question.get().getTitle();
	} 
	 
	public String modifyQuestion(int questionId, String questionDesc) {
		question = questionRepo.findById(questionId);
		question.get().setQuestionDescription(questionDesc);
		questionRepo.save(question.get());
		return question.get().getQuestionDescription();
	}
	
	public List<String> modifyOptions(int questionId, List<String> options) {
		question = questionRepo.findById(questionId);
		question.get().setOptions(options);
		questionRepo.save(question.get());
		return question.get().getOptions();
	}

	public String modifyQuestionLevel(int questionId, String questionLevel) {
		question = questionRepo.findById(questionId);
		question.get().setQuestionlevel(questionLevel);
		questionRepo.save(question.get());
		return question.get().getQuestionlevel();
	}
	
	public String modifyTopicTag(int questionId, String topicTag) {
		question = questionRepo.findById(questionId);
		question.get().setTopictag(topicTag);
		questionRepo.save(question.get());
		return question.get().getTopictag();
	}
	
	public String modifyAnswer(int questionId, String answer) {
		question = questionRepo.findById(questionId);
		question.get().setAnswer(answer);
		questionRepo.save(question.get());
		return question.get().getAnswer();
	}
	
	public void isQuestionIdPresent(int questionId) throws InvalidQuestionIdEntryException {
		if (!questionRepo.existsById(questionId)) {
			throw new InvalidQuestionIdEntryException();
		}
	}

}

