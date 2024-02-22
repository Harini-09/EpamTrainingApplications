package com.epam.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.epam.entities.User;

public interface UserRepo extends CrudRepository<User, Integer> {
	Optional<User> findByIdAndPasswordAndType(String id,String password,String type);
	
}
