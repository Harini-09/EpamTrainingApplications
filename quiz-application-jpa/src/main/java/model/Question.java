package model;

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
	private int sno;
	private String questionId;
	private String title;
	private String questionDescription;
	@ElementCollection(targetClass = String.class)
	private List<String> options = new ArrayList<>(); 
	private String questionlevel;
	private String topictag;
	private String answer;

	@ManyToMany(mappedBy = "questionsList",targetEntity=Quiz.class) 
	List<Quiz> quizList;

	public Question() {

	}

	public List<Quiz> getQuiz() {
		return quizList;
	}

	public void setQuiz(List<Quiz> quiz) {
		this.quizList = quiz;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public Question(String questionId, String title, String question, List<String> options, String questionlevel,
			String topictag, String answer) {
		super();
		this.questionId = questionId;
		this.title = title;
		this.questionDescription = question;
		this.options = options;
		this.questionlevel = questionlevel;
		this.topictag = topictag;
		this.answer = answer;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuestion() {
		return questionDescription;
	}

	public void setQuestion(String question) {
		this.questionDescription = question;
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
		return "Question [questionId=" + questionId + ", title=" + title + ", question=" + questionDescription + ", options="
				+ options + ", questionlevel=" + questionlevel + ", topictag=" + topictag + ", answer=" + answer + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(answer, options, questionDescription, questionId, questionlevel, sno, title, topictag);
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
				&& Objects.equals(questionDescription, other.questionDescription) && Objects.equals(questionId, other.questionId)
				&& Objects.equals(questionlevel, other.questionlevel) && sno == other.sno
				&& Objects.equals(title, other.title) && Objects.equals(topictag, other.topictag);
	}

}