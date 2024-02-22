package com.epam.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.database.QuestionsDao;
import com.epam.entities.Question;

@Service
public class QuestionLibraryService {

	@Autowired
	private QuestionsDao questionsDaoImpl;

	public Question insert(Question newQuestion) {
		return questionsDaoImpl.addQuestion(newQuestion);
	}

	public String modifyTitle(int questionId, String title) {
		return questionsDaoImpl.updateTitle(questionId, title);
	}

	public String modifyQuestion(int questionId, String question) {
		return questionsDaoImpl.updateQuestionDescription(questionId, question);
	}

	public List<String> modifyOptions(int questionId, List<String> options) {
		return questionsDaoImpl.updateOptions(questionId, options);
	}

	public String modifyQuestionLevel(int questionId, String questionLevel) {
		return questionsDaoImpl.updateQuestionLevel(questionId, questionLevel);
	}

	public String modifyTopicTag(int questionId, String topicTag) {
		return questionsDaoImpl.updateTopicTag(questionId, topicTag);
	}

	public String modifyAnswer(int questionId, String answer) {
		return questionsDaoImpl.updateAnswer(questionId, answer);
	}

	public boolean delete(int questionId) throws ConstraintViolationException {
		return questionsDaoImpl.removeQuestion(questionId);
	}

	public List<Question> viewQuestions() {
		return questionsDaoImpl.viewQuestions();
	}

	public void isQuestionIdPresent(int questionId) throws InvalidQuestionIdEntryException {
		questionsDaoImpl.isQuestionPresent(questionId);
	}

}
