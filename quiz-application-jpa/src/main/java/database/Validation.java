package database;

import java.util.List;

import model.Question;
import model.Quiz;

public interface Validation {
	public Question getQuestion(String questionid);
	public List<Question> getQuestionsInQuiz(Quiz quiz);
}
