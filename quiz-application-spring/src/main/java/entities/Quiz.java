package entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name = "quiz")
@Entity
public class Quiz {
	@Id
	private Integer quizId;
	
	private String title;
	private int totalMarks;
	
	@ManyToMany
	private List<Question> questionsList;

	public Quiz() {

	}
 
	public Quiz(int quizId, String title, List<Question> questions, int totalMarks) {
		super();
		this.quizId = quizId;
		this.title = title;
		this.questionsList = questions;
		this.totalMarks = totalMarks; 
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Question> getQuestions() {
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
		return "Quiz [quizId=" + quizId + ", title=" + title + ", questions=" + questionsList + ", totalMarks=" + totalMarks
				+ "]";
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
