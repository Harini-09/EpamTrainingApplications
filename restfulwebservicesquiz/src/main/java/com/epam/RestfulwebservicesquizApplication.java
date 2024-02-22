package com.epam;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.epam.dtoconverter.QuestionDtoConverter;
import com.epam.dtoconverter.QuizDtoConverter;
import com.epam.dtoconverter.UserDtoConverter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class RestfulwebservicesquizApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulwebservicesquizApplication.class, args);
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public QuestionDtoConverter getQuestionDtoConverter() {
		return new QuestionDtoConverter();
	}
	
	@Bean
	public QuizDtoConverter getQuizDtoConverter() {
		return new QuizDtoConverter();
	}
	
	@Bean
	public UserDtoConverter getUserDtoConverter() {
		return new UserDtoConverter();
	}

}
