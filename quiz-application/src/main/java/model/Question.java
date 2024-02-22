package model;

import java.util.List;
import java.util.Objects;

public class Question {
	private String title;
	private String question;
	private List<String> options;
	private String questionlevel;
	private String topictag;
	private String answer;
	public Question() {
		
	}
	public Question(String title, String question, List<String> options, String questionlevel, String topictag,
			String answer) {
		super();
		this.title = title;
		this.question = question;
		this.options = options;
		this.questionlevel = questionlevel;
		this.topictag = topictag;
		this.answer = answer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<String> getOptions() {
		return options;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
	public String getQuestionlevel() {
		return questionlevel;
	}
	public void setQuestionlevel(String questionlevel) {
		this.questionlevel = questionlevel;
	}
	public String getTopictag() {
		return topictag;
	}
	public void setTopictag(String topictag) {
		this.topictag = topictag;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "Question [title=" + title + ", question=" + question + ", options=" + options + ", questionlevel="
				+ questionlevel + ", topictag=" + topictag + ", answer=" + answer + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(answer, options, question, questionlevel, title, topictag);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		return Objects.equals(answer, other.answer) && Objects.equals(options, other.options)
				&& Objects.equals(question, other.question) && Objects.equals(questionlevel, other.questionlevel)
				&& Objects.equals(title, other.title) && Objects.equals(topictag, other.topictag);
	}
	
	

}
