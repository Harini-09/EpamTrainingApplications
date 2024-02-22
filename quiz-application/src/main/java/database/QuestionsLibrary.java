package database;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import model.Question;

public class QuestionsLibrary {
	public static Map<String, Question> questions = new TreeMap<>();

	public Map<String, Question> getQuestions() {
		questions.put("Q01", new Question("Collections", "Which collection stores unique values?",
				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Easy", "Java", "c"));
		questions.put("Q02", new Question("Strings", "Which method is used to get the length of a string?",
				Arrays.asList("a.length()", "b.size()", "c.len()", "d.none"), "Easy", "Java", "a"));
		questions.put("Q03", new Question("Oops", "Which of the following is not oops concept in java?",
				Arrays.asList("a.Inheritance", "b.Encapsulation", "c.Polymorphism", "d.Compilation"), "Easy", "Java", "d"));
		questions.put("Q04", new Question("Arrays", "Which method is used to find the length of an array?",
				Arrays.asList("a.len", "b.size", "c.length", "d.none"), "Easy", "Java", "c"));
		questions.put("Q05", new Question("ExceptionHandling", "Which of the following keyword is not an exception handling keyword?",
				Arrays.asList("a.try", "b.catch", "c.except", "d.throw"), "Easy", "Java", "c"));
		return questions; 
	}
}
