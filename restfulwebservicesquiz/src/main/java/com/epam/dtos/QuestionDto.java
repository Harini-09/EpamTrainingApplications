package com.epam.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.epam.entities.Quiz;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuestionDto {
	@Size(min=3,max=20,message="Title Length should be between 3 and 20")
	private String title;
	@Size(min=8,max=50,message="Question Length should be between 8 and 30")
	private String questionDescription;
	@Size(min=2,max=6,message="The no of options should be between 2 and 6")
	private List<String> options = new ArrayList<>();
	@NotBlank(message="Question Level shouldn't be blank")
	private String questionlevel;
	@NotBlank(message="Topic Tag shouldn't be blank")
	private String topictag;
	@Size(min=1,max=1,message="The answer should be a single character option")
	private String answer;
	private List<Quiz> quizList;

	public QuestionDto() {

	}

	public QuestionDto(String title, String questionDescription, List<String> options, String questionlevel,
			String topictag, String answer) {
		super();
		this.title = title;
		this.questionDescription = questionDescription;
		this.options = options;
		this.questionlevel = questionlevel;
		this.topictag = topictag;
		this.answer = answer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(answer, options, questionDescription, questionlevel, title, topictag);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionDto other = (QuestionDto) obj;
		return Objects.equals(answer, other.answer) && Objects.equals(options, other.options)
				&& Objects.equals(questionDescription, other.questionDescription)
				&& Objects.equals(questionlevel, other.questionlevel) && Objects.equals(title, other.title)
				&& Objects.equals(topictag, other.topictag);
	}
}
