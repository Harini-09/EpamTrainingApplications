package database;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import model.Question;

public class QuestionsLibrary {
	private static Map<String, Question> questions = new TreeMap<>();

	public Map<String, Question> getQuestions() {
		Question question = new Question("Q01", "Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
		questions.put(question.getQuestionId(), question);
		question = new Question("Q02", "Strings", "Which method is used to get the length of a string?",
				Arrays.asList("a.length()", "b.size()", "c.len()", "d.none"), "Easy", "Java", "a");
		questions.put(question.getQuestionId(), question);
		question = new Question("Q03", "Oops", "Which of the following is not oops concept in java?",
				Arrays.asList("a.Inheritance", "b.Encapsulation", "c.Polymorphism", "d.Compilation"), "Easy", "Java",
				"d");
		questions.put(question.getQuestionId(), question);
		question = new Question("Q04", "Arrays", "Which method is used to find the length of an array?",
				Arrays.asList("a.len", "b.size", "c.length", "d.lengthof"), "Easy", "Java", "c");
		questions.put(question.getQuestionId(), question);
		question = new Question("Q05", "ExceptionHandling",
				"Which of the following keyword is not an exception handling keyword?",
				Arrays.asList("a.try", "b.catch", "c.except", "d.throw"), "Easy", "Java", "c");
		questions.put(question.getQuestionId(), question);
		return questions;
	}
}
