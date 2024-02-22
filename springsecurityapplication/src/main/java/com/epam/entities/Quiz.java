package com.epam.entities;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Table(name = "quiz")
@Entity
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="quiz_id")
	private Integer quizId;

	private String title;
	@Column(name="total_marks")
	private int totalMarks;

	@ManyToMany
	@Column(name="questions_list")
	private List<Question> questionsList;

	public Quiz() {

	}

	public Quiz(String title, List<Question> questionsList, int totalMarks) {
		super();
		this.title = title;
		this.questionsList = questionsList;
		this.totalMarks = totalMarks;
	}

	

	public Integer getQuizId() {
		return quizId;
	}

	public void setQuizId(Integer quizId) {
		this.quizId = quizId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Question> getQuestionsList() {
		return questionsList;
	}

	public void setQuestions(List<Question> questions) {
		this.questionsList = questions;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	@Override
	public String toString() {
		return "Quiz [quizId=" + quizId + ", title=" + title + ", questions=" + questionsList + ", totalMarks="
				+ totalMarks + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(questionsList, quizId, title, totalMarks);
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
		return Objects.equals(questionsList, other.questionsList) && quizId == other.quizId
				&& Objects.equals(title, other.title) && totalMarks == other.totalMarks;
	}

}
