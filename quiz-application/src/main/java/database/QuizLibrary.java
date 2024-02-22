package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import model.QuizQuestion;
import model.Question;

public class QuizLibrary {
	private QuestionsLibrary questionsLibrary = new QuestionsLibrary();
	public static Map<String, List<QuizQuestion>> quizTitles = new TreeMap<>();
	public Map<String, Question> questions = questionsLibrary.getQuestions();

	public Map<String, List<QuizQuestion>> getQuizTitles() {
		quizTitles.put("Quiz1", createQuiz());
		return quizTitles;
	}
 
	private List<QuizQuestion> createQuiz() {
		List<QuizQuestion> quiz = new ArrayList<>();
		for (String s : questions.keySet()) {
			quiz.add(new QuizQuestion(s, questions.get(s).getTitle(), questions.get(s).getQuestion(),
					questions.get(s).getOptions(), 4));
		}
		return quiz; 
	}

}
