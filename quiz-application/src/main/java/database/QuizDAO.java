package database;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import customexceptions.InvalidQuestionIdEntryException;
import customexceptions.InvalidQuizTitleEntryException;
import customexceptions.NoSuchQuestionExistInQuizException;
import model.Question;
import model.QuizQuestion;

public class QuizDAO {

	private QuizLibrary quizLibrary = new QuizLibrary();
	private Map<String, List<QuizQuestion>> quizes = quizLibrary.getQuizTitles();
	private QuestionsLibrary questionLibrary = new QuestionsLibrary();
	private Map<String, Question> questions = questionLibrary.getQuestions();

	public Set<Map.Entry<String, List<QuizQuestion>>> viewQuiz() {
		return quizes.entrySet();
	}

	public boolean removeQuiz(String quizTitle) {
		quizes.remove(quizTitle);
		return quizes.containsKey(quizTitle);
	}

	public boolean createQuiz(String quizTitle, List<QuizQuestion> newQuestions) {
		if (newQuestions == null) {
			return false;
		}
		quizes.put(quizTitle, newQuestions);
		return quizes.containsKey(quizTitle);
	}

	public String addQuestion(String quizTitle, String questionId, int marks) {
		quizes.get(quizTitle).add(new QuizQuestion(questionId, questions.get(questionId).getTitle(),
				questions.get(questionId).getQuestion(), questions.get(questionId).getOptions(), marks));
		int length = quizes.get(quizTitle).size();
		return quizes.get(quizTitle).get(length - 1).getQuestionid();
	}

	public boolean removeQuestion(String quizTitle, String questionid) {
		QuizQuestion removedQuestion = null;
		for (QuizQuestion question : quizes.get(quizTitle)) {
			if (question.getQuestionid().equals(questionid)) {
				removedQuestion = question;
			}
		}
		quizes.get(quizTitle).remove(removedQuestion);
		return quizes.get(quizTitle).contains(removedQuestion);
	}

	public int updateMarks(String quizTitle, String questionId, int marks) {
		int index = 0;
		for (QuizQuestion question : quizes.get(quizTitle)) {
			if (question.getQuestionid().equals(questionId)) {
				question.setMarks(marks);
				index = quizes.get(quizTitle).indexOf(question);
				break;
			}
		}
		return quizes.get(quizTitle).get(index).getMarks();
	}

	public QuizQuestion addNewQuestion(String questionId, int marks) {
		return new QuizQuestion(questionId, questions.get(questionId).getTitle(),
				questions.get(questionId).getQuestion(), questions.get(questionId).getOptions(), marks);
	}

	public void isQuestionIdPresent(String questionId) throws InvalidQuestionIdEntryException {
		if (questions.containsKey(questionId) == false) {
			throw new InvalidQuestionIdEntryException();
		}
	}

	public void isQuizTitlePresent(String quizTitle) throws InvalidQuizTitleEntryException {
		if (quizes.containsKey(quizTitle) == false) {
			throw new InvalidQuizTitleEntryException();
		}
	}

	public void isQuestionInQuizPresent(String quizTitle, String questionId) throws NoSuchQuestionExistInQuizException {
		List<QuizQuestion> quizQuestions = quizes.get(quizTitle);
		Optional<QuizQuestion> optional = quizQuestions.stream().filter(t -> t.getQuestionid().equals(questionId))
				.findFirst();
		if (optional.isPresent() == false) {
			throw new NoSuchQuestionExistInQuizException();
		}
	}
}
