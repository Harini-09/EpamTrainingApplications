package com.epam.springbootdemo.modal;



import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Id;

import javax.persistence.ManyToMany;

import javax.persistence.Table;
@Table(name="quiz")
@Entity
public class Quiz{
	@Id		
	private int quizID;
	private String title;
	private int totalMarks;
    @ManyToMany(targetEntity = Question.class,fetch =FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Question> quizQuestions;
	public int getQuizID() {
		return quizID;
	}

	public void setQuizID(int quizID) {
		this.quizID = quizID;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public List<Question> getQuizQuestions() {
		return quizQuestions;
	}

	public void setQuizQuestions(List<Question> quizQuestions) {
		this.quizQuestions = quizQuestions;
	}

	public Quiz() {
		super();

	}

	public Quiz(int quizID,String title, int totalMarks, List<Question> quizQuestions) {
		super();
		this.quizID=quizID;
		this.title = title;
		this.totalMarks = totalMarks;
		this.quizQuestions = quizQuestions;
	}

	@Override
	public String toString() {
		return "Quiz [quizID=" + quizID + ", title=" + title + ", totalMarks=" + totalMarks + ", quizQuestions="
				+ quizQuestions + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(quizID, quizQuestions, title, totalMarks);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quiz other = (Quiz) obj;
		return quizID == other.quizID && Objects.equals(quizQuestions, other.quizQuestions)
				&& Objects.equals(title, other.title) && totalMarks == other.totalMarks;
	}

	
}
