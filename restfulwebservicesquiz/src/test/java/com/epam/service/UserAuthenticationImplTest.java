package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.dtoconverter.UserDtoConverter;
import com.epam.entities.User;
import com.epam.dtos.UserDto;
import com.epam.repository.UserRepo;

@ExtendWith(MockitoExtension.class)
class UserAuthenticationImplTest {

	@Mock
	private UserRepo userRepo;

	@Mock
	private UserDtoConverter userDtoConverter;

	@InjectMocks
	private UserAuthenticationImpl userAuthentication;
	
	private Optional<User> optional;

	private UserDto userDto;

	private User user;

	@BeforeEach
	void setUp() {
		userDto = new UserDto("admin", "admin", "admin");
		user = new User("admin", "admin", "admin");
	}

	@Test
	void LogInTestSuccess() {
		Mockito.when(userDtoConverter.convertDtoToUser(userDto)).thenReturn(user);
		optional = Optional.of(user);
		Mockito.when(userRepo.findByIdAndPasswordAndType("admin", "admin", "admin")).thenReturn(optional);
		boolean isValidUser = userAuthentication.logIn(userDto);
		assertEquals(true, isValidUser);
	}

	@Test
	void logInTestFailure() {
		Mockito.when(userDtoConverter.convertDtoToUser(userDto)).thenReturn(user);
		optional = Optional.empty();
		Mockito.when(userRepo.findByIdAndPasswordAndType("admin", "admin", "admin")).thenReturn(optional);
		boolean isValidUser = userAuthentication.logIn(userDto);
		assertEquals(false, isValidUser);
	}

	@Test
	void signUpTest() {
		Mockito.when(userDtoConverter.convertDtoToUser(userDto)).thenReturn(user);
		Mockito.when(userRepo.save(user)).thenReturn(user);
		Mockito.when(userDtoConverter.convertUserToDto(user)).thenReturn(userDto);
		UserDto returnedUser = userAuthentication.signUp(userDto);
		assertEquals(userDto, returnedUser);
	}

	@Test
	void viewTest() {
		List<User> users = new ArrayList<>();
		users.add(user);
		Iterable<User> iterableUsers = users;
		Mockito.when(userRepo.findAll()).thenReturn(iterableUsers);
		List<User> returnedList = userAuthentication.view();
		assertEquals(users, returnedList);
	}

}
