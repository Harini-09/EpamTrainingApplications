package com.epam.database;

import java.util.List;

import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.entities.Question;

public interface QuestionsDao {
	public Question addQuestion(Question newQuestion);
	public List<Question> viewQuestions();
	public boolean removeQuestion(int questionId);
	public String updateTitle(int questionId, String title);
	public String updateQuestionDescription(int questionId, String question);
	public List<String> updateOptions(int questionId, List<String> options);
	public String updateQuestionLevel(int questionId, String level);
	public String updateTopicTag(int questionId, String topicTag);
	public String updateAnswer(int questionId, String answer);
	public void isQuestionPresent(int questionId) throws InvalidQuestionIdEntryException;
	
}
