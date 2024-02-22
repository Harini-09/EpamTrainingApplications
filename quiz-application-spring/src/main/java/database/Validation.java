package database;

import java.util.List;

import entities.Question;
import entities.Quiz;

public interface Validation {
	public Question getQuestion(String questionid);
	public List<Question> getQuestionsInQuiz(Quiz quiz);
}
