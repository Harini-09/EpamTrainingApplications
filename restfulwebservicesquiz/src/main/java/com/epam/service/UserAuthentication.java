package com.epam.service;

import java.util.List;

import com.epam.dtos.UserDto;
import com.epam.entities.User;

public interface UserAuthentication {
	public boolean logIn(UserDto userDto);
	public UserDto signUp(UserDto userDto);
	public List<User> view();
}
