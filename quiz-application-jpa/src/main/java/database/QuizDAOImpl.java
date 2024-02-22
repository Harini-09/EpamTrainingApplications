package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import customexceptions.InvalidQuestionIdEntryException;
import customexceptions.InvalidQuizTitleEntryException;
import customexceptions.NoSuchQuestionExistInQuizException;
import model.Question;
import model.Quiz;

public class QuizDAOImpl implements QuizDao {
	private QuizLibrary quizLibrary = new QuizLibrary();
	private Map<Integer,Quiz> quizes = quizLibrary.getQuizTitles();
	private QuestionsLibrary questionLibrary = new QuestionsLibrary();
	private Map<String, Question> questions = questionLibrary.getQuestions();

	public List<Quiz> viewQuiz() {
		List<Quiz> quizList = new ArrayList<>();
		for(Quiz quiz : quizes.values()) {
			quizList.add(quiz);
		}
		return quizList;
	}

	public boolean removeQuiz(int quizId) {
		quizes.remove(quizId);
		return quizes.containsKey(quizId);
	}

	public boolean createQuiz(int quizid, Quiz newQuiz) {
		if (newQuiz == null) {
			return false;
		}
		quizes.put(quizid, newQuiz);
		return quizes.containsKey(quizid);
	}
	
	public boolean addQuestion(int quizId, String questionId) {
		Question question = questions.get(questionId);
		quizes.get(quizId).getQuestions().add(question);
		return quizes.get(quizId).getQuestions().contains(questions.get(questionId));
	}

	public boolean removeQuestion(int quizId, String questionId) {
		Question removedQuestion = null;
		for (Question question : quizes.get(quizId).getQuestions()) {
			if (question.getQuestionId().equals(questionId)) {
				removedQuestion = question;
			}
		}
		quizes.get(quizId).getQuestions().remove(removedQuestion);
		return quizes.get(quizId).getQuestions().contains(removedQuestion);
	}

	public int updateMarks(int quizId,int marks) {
		int newMarks;
		quizes.get(quizId).setTotalMarks(marks);
		newMarks = quizes.get(quizId).getTotalMarks();
		return newMarks;
	}

	public Question addNewQuestion(String questionId) {
		return questions.get(questionId);
	}

	public void isQuestionIdPresent(String questionId) throws InvalidQuestionIdEntryException {
		if (!questions.containsKey(questionId)) {
			throw new InvalidQuestionIdEntryException();
		}
	}

	public void isQuizTitlePresent(int quizId) throws InvalidQuizTitleEntryException {
		if (!quizes.containsKey(quizId)) {
			throw new InvalidQuizTitleEntryException();
		}
	}

	public void isQuestionInQuizPresent(int quizId, String questionId) throws NoSuchQuestionExistInQuizException {
		Quiz quizQuestion = quizes.get(quizId);
		List<Question> quizQuestions = quizQuestion.getQuestions();
		Optional<Question> optional = quizQuestions.stream().filter(t -> t.getQuestionId().equals(questionId))
				.findFirst();
		if (!optional.isPresent()) {
			throw new NoSuchQuestionExistInQuizException();
		}
	}

	

}
