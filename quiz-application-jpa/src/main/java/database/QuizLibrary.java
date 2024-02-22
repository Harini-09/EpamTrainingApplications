package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import model.Question;
import model.Quiz;

public class QuizLibrary {
	private QuestionsLibrary questionsLibrary = new QuestionsLibrary();
	private static Map<Integer, Quiz> quizTitles = new TreeMap<>();
	private Map<String, Question> questions = questionsLibrary.getQuestions();

	public Map<Integer, Quiz> getQuizTitles() {
		quizTitles.put(101, new Quiz(101, "Quiz1", createQuizQuestions(), 40));
		return quizTitles;
	}

	private List<Question> createQuizQuestions() {
		List<Question> quizQuestions = new ArrayList<>();
		for (Map.Entry<String, Question> entry : questions.entrySet()) {
			String qid = entry.getKey();
			quizQuestions.add(questions.get(qid));
		}
		return quizQuestions;
	}
}
