package com.epam.dtoconverter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.dtos.QuizDto;
import com.epam.entities.Quiz;

public class QuizDtoConverter {

	@Autowired
	ModelMapper modelMapper;
	
	public Quiz convertDtoToQuiz(QuizDto quizDto) {
		return modelMapper.map(quizDto, Quiz.class);
	}
	
	public QuizDto convertQuizToDto(Quiz quiz) {
		return modelMapper.map(quiz, QuizDto.class);
	}
}
