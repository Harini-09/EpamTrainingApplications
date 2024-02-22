package database;

import java.util.List;

import customexceptions.InvalidQuestionIdEntryException;
import model.Question;

public interface QuestionsDao {
	public boolean addQuestion(String questionId, Question newQuestion);
	public List<Question> viewQuestions();
	public boolean removeQuestion(String questionId);
	public String updateTitle(String questionId, String title);
	public String updateQuestion(String questionId, String question);
	public List<String> updateOptions(String questionId, List<String> options);
	public String updateQuestionLevel(String questionId, String level);
	public String updateTopicTag(String questionId, String topicTag);
	public String updateAnswer(String questionId, String answer);
	public void isQuestionPresent(String questionId) throws InvalidQuestionIdEntryException;
	
}
