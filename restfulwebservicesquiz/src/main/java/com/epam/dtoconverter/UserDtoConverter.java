package com.epam.dtoconverter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.dtos.UserDto;
import com.epam.entities.User;

public class UserDtoConverter {
	@Autowired
	ModelMapper modelMapper;
	
	public User convertDtoToUser(UserDto userDto) {
		return modelMapper.map(userDto, User.class);
	}
	
	public UserDto convertUserToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}
}
