package service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import customexceptions.InvalidQuestionIdEntryException;
import database.QuestionsDao;
import entities.Question;

@Service
public class CrudOperationsOnQuestions {

	@Autowired
	private QuestionsDao questionDao;

	public boolean insert(String questionId, Question newQuestion) {
		return questionDao.addQuestion(questionId, newQuestion);
	}

	public String modifyTitle(String questionId, String title) {
		return questionDao.updateTitle(questionId, title);
	}

	public String modifyQuestion(String questionId, String question) {
		return questionDao.updateQuestion(questionId, question);
	}

	public List<String> modifyOptions(String questionId, List<String> options) {
		return questionDao.updateOptions(questionId, options);
	}

	public String modifyQuestionLevel(String questionId, String questionLevel) {
		return questionDao.updateQuestionLevel(questionId, questionLevel);
	}

	public String modifyTopicTag(String questionId, String topicTag) {
		return questionDao.updateTopicTag(questionId, topicTag);
	}

	public String modifyAnswer(String questionId, String answer) {
		return questionDao.updateAnswer(questionId, answer);
	}

	public boolean delete(String questionId) throws ConstraintViolationException {
		return questionDao.removeQuestion(questionId);
	}

	public List<Question> viewQuestions() {
		return questionDao.viewQuestions();
	}

	public void isQuestionIdPresent(String questionId) throws InvalidQuestionIdEntryException {
		questionDao.isQuestionPresent(questionId);
	}

}
