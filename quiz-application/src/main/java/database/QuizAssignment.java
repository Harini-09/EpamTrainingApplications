package database;

import java.util.List;
import java.util.Map;

import model.Question;
import model.QuizQuestion;

public class QuizAssignment {
	private QuizLibrary quizLibrary = new QuizLibrary();
	Map<String, List<QuizQuestion>> quizes = quizLibrary.getQuizTitles();
	private QuestionsLibrary questionsLibrary = new QuestionsLibrary();
	Map<String, Question> questions = questionsLibrary.getQuestions();

	public List<QuizQuestion> assignQuiz() {
		return quizes.get("Quiz1");
	}

	public Map<String, Question> getQuestions() {
		return questions;
	}
}
