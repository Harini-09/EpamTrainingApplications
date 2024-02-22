package com.epam.springbootdemo.modal;


import java.util.List;
import java.util.Objects;


import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Table;





@Table(name="question")
@Entity
public class Question {
	@Id
	private int questionID;
	private String question;
	@ElementCollection
	private List<String>options;
	private String answer;
	private String difficultyLevel;
	private String tag;

	@Override
	public int hashCode() {
		return Objects.hash(answer, difficultyLevel, options, question, questionID, tag);
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
		return Objects.equals(answer, other.answer) && Objects.equals(difficultyLevel, other.difficultyLevel)
				&& Objects.equals(options, other.options) && Objects.equals(question, other.question)
				&& Objects.equals(questionID, other.questionID) && Objects.equals(tag, other.tag);
	}

	public Question() {
		super();
	}

	public Question(int questionID, String question,  List<String> options, String answer, String difficultyLevel,
			String tag) {
		super();
		this.questionID = questionID;
		this.question = question;
		this.options = options;
		this.answer = answer;
		this.difficultyLevel = difficultyLevel;
		this.tag = tag;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public  List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Question [questionID=" + questionID + ", question=" + question + ", options=" + options + ", answer="
				+ answer + ", difficultyLevel=" + difficultyLevel + ", tag=" + tag + "]";
	}

	
	

}
