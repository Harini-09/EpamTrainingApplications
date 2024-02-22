package com.epam.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name = "question")
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;
	private String title;
	private String questionDescription;
	@ElementCollection(targetClass = String.class)
	private List<String> options = new ArrayList<>();
	private String questionlevel;
	private String topictag;
	private String answer;

	@ManyToMany(mappedBy = "questionsList", targetEntity = Quiz.class)
	List<Quiz> quizList;

	public Question() {

	}

	public Question(String title, String questionDescription, List<String> options, String questionlevel,
			String topictag, String answer) {
		super();
		this.title = title;
		this.questionDescription = questionDescription;
		this.options = options;
		this.questionlevel = questionlevel;
		this.topictag = topictag;
		this.answer = answer;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuestionDescription() {
		return questionDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
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

	public List<Quiz> getQuizList() {
		return quizList;
	}

	public void setQuizList(List<Quiz> quizList) {
		this.quizList = quizList;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", title=" + title + ", questionDescription="
				+ questionDescription + ", options=" + options + ", questionlevel=" + questionlevel + ", topictag="
				+ topictag + ", answer=" + answer + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(answer, options, questionDescription, questionId, questionlevel, title, topictag);
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
				&& Objects.equals(questionDescription, other.questionDescription) && questionId == other.questionId
				&& Objects.equals(questionlevel, other.questionlevel) && Objects.equals(title, other.title)
				&& Objects.equals(topictag, other.topictag);
	}

}