package com.epam.dtos;

import java.util.List;
import java.util.Objects;

import com.epam.entities.Question;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuizDto {
	@Size(min=3,max=10,message="Title Length should be between 3 and 20")
	private String title;
	@Size(min=1,max=30,message="The minimum no of questions alloted for the quiz should be one")
	private List<Question> questionsList;
	@Positive(message="The Total Marks alloted should be a positive number greater than zero")
	private int totalMarks;

	@Override
	public int hashCode() {
		return Objects.hash(questionsList, title, totalMarks);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuizDto other = (QuizDto) obj;
		return Objects.equals(questionsList, other.questionsList) 
				&& Objects.equals(title, other.title) && totalMarks == other.totalMarks;
	}

}
