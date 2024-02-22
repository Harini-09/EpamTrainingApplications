package com.epam.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.entities.Question;

public interface QuestionRepo extends CrudRepository<Question, Integer> {
	
}
