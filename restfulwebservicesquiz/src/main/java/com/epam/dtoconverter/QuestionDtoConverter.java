package com.epam.dtoconverter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.dtos.QuestionDto;
import com.epam.entities.Question;

public class QuestionDtoConverter {

	@Autowired
	ModelMapper modelMapper;
	
	public Question convertDtoToQuestion(QuestionDto questionDto) {
		return modelMapper.map(questionDto, Question.class);
	}
	
	public QuestionDto convertQuestionToDto(Question question) {
		return modelMapper.map(question, QuestionDto.class);
	}
}
