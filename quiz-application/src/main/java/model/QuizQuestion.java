package model;

import java.util.List;
import java.util.Objects;

public class QuizQuestion {
	private String questionid;
	private String questiontitle;
	private String question;
	private List<String> options;
	private int marks;
	public QuizQuestion() {
		
	}
	public QuizQuestion(String questionid, String questiontitle, String question, List<String> options, int marks) {
		super();
		this.questionid = questionid;
		this.questiontitle = questiontitle;
		this.question = question;
		this.options = options;
		this.marks = marks;
	}
	public String getQuestionid() {
		return questionid;
	}
	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}
	public String getQuestiontitle() {
		return questiontitle;
	}
	public void setQuestiontitle(String questiontitle) {
		this.questiontitle = questiontitle;
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
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "QuizQuestion [questionid=" + questionid + ", questiontitle=" + questiontitle + ", question=" + question
				+ ", options=" + options + ", marks=" + marks + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(marks, options, question, questionid, questiontitle);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuizQuestion other = (QuizQuestion) obj;
		return marks == other.marks && Objects.equals(options, other.options)
				&& Objects.equals(question, other.question) && Objects.equals(questionid, other.questionid)
				&& Objects.equals(questiontitle, other.questiontitle);
	}
	
	
}
