package com.epam.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.entities.Quiz;

public interface QuizRepo extends CrudRepository<Quiz,Integer>{

}
