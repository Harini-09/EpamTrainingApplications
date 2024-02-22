package com.epam.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dtoconverter.UserDtoConverter;
import com.epam.dtos.UserDto;
import com.epam.entities.User;
import com.epam.repository.UserRepo;

@Service
public class UserAuthenticationImpl implements UserAuthentication {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UserDtoConverter userDtoConverter;
	
	private final Logger logger = LogManager.getLogger(UserAuthenticationImpl.class);

	@Override
	public boolean logIn(UserDto userDto) {
		logger.info("Entered into the User Library Service - logIn() method");
		User user = userDtoConverter.convertDtoToUser(userDto);
		boolean isUserPresent = false;
		if (userRepo.findByIdAndPasswordAndType(user.getId(), user.getPassword(), user.getType())
				.isPresent()) {
			isUserPresent = true;
		}
		return isUserPresent;
	}

	@Override
	public UserDto signUp(UserDto userDto) {
		logger.info("Entered into the User Library Service - signUp() method");
		User user = userDtoConverter.convertDtoToUser(userDto);
		return userDtoConverter.convertUserToDto(userRepo.save(user));
	}

	public List<User> view() {
		logger.info("Entered into the User Library Service - view() method");
		return (List<User>) userRepo.findAll();
	}
}
