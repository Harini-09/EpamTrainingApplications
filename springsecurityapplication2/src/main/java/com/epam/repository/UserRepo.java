package com.epam.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.epam.entities.Users;

public interface UserRepo extends CrudRepository<Users, Long> {
	Optional<Users> findByUsername(String email);
}
