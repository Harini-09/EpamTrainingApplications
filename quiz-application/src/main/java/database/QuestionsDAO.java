package database;

import java.util.List;
import java.util.Map;
import java.util.Set;

import customexceptions.InvalidQuestionIdEntryException;
import model.Question;

public class QuestionsDAO {

	private QuestionsLibrary questionLibrary = new QuestionsLibrary();
	private Map<String, Question> questions = questionLibrary.getQuestions();
	private Set<Map.Entry<String, Question>> questionsSet = questions.entrySet();

	public boolean addQuestion(String questionId, Question newQuestion) {
		if (newQuestion == null) {
			return false;
		}
		questions.put(questionId, newQuestion);
		return questions.containsKey(questionId);
	}

	public Set<Map.Entry<String, Question>> viewQuestions() {
		return questionsSet;
	}

	public boolean removeQuestion(String questionId) {
		questions.remove(questionId);
		return questions.containsKey(questionId);
	}

	public String updateTitle(String questionId, String title) {
		questionsSet.stream().filter(t -> t.getKey().equals(questionId)).forEach(t -> t.getValue().setTitle(title));
		return questions.get(questionId).getTitle();
	}

	public String updateQuestion(String questionId, String question) {
		questionsSet.stream().filter(t -> t.getKey().equals(questionId))
				.forEach(t -> t.getValue().setQuestion(question));
		return questions.get(questionId).getQuestion();
	}

	public List<String> updateOptions(String questionId, List<String> options) {
		questionsSet.stream().filter(t -> t.getKey().equals(questionId)).forEach(t -> t.getValue().setOptions(options));
		return questions.get(questionId).getOptions();
	}

	public String updateQuestionLevel(String questionId, String level) {
		questionsSet.stream().filter(t -> t.getKey().equals(questionId))
				.forEach(t -> t.getValue().setQuestionlevel(level));
		return questions.get(questionId).getQuestionlevel();
	}

	public String updateTopicTag(String questionId, String topicTag) {
		questionsSet.stream().filter(t -> t.getKey().equals(questionId))
				.forEach(t -> t.getValue().setTopictag(topicTag));
		return questions.get(questionId).getTopictag();
	}

	public String updateAnswer(String questionId, String answer) {
		questionsSet.stream().filter(t -> t.getKey().equals(questionId)).forEach(t -> t.getValue().setAnswer(answer));
		return questions.get(questionId).getAnswer();
	}

	public void isQuestionPresent(String questionId) throws InvalidQuestionIdEntryException {
		if (questions.containsKey(questionId) == false) {
			throw new InvalidQuestionIdEntryException();
		}
	}

}
