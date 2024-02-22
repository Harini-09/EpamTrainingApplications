package com.epam.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.entities.Question;

public interface QuestionRepo extends CrudRepository<Question, Integer> {  //@param <T> the domain type the repository manages, and, @param <ID> the type of the id of the entity the repository manages

}
