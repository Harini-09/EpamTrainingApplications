package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.entities.User;
import com.epam.repository.UserRepo;

@ExtendWith(MockitoExtension.class)
class UserAuthenticationImplTest {

	@Mock
	private UserRepo userRepo;

	@InjectMocks
	private UserAuthenticationImpl userAuthentication;

	private Optional<User> optional;
	
	@Test
	void logInTestSuccess() {
		User user = new User("admin", "admin", "admin");
		optional = Optional.of(user);

		Mockito.when(userRepo.findByIdAndPasswordAndType("admin", "admin","admin")).thenReturn(optional);

		boolean isValidUser = userAuthentication.logIn(user);


		assertEquals(true, isValidUser); 
	}

	@Test
	void signUpTest() {
		User user = new User("admin", "admin", "admin");

		Mockito.when(userRepo.save(user)).thenReturn(user);
 
		boolean isUserSigned = userAuthentication.signUp(user);


		assertEquals(true, isUserSigned);
	}

} 
