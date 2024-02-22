package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import database.UserDao;
import entities.User;

@ExtendWith(MockitoExtension.class)
class UserAuthenticationImplTest {

	@Mock
	private UserDao userDao;

	@InjectMocks
	private UserAuthenticationImpl userAuthentication;

	@Test
	void logInTest() {
		User user = new User("admin", "admin", "admin");

		Mockito.when(userDao.logIn(user)).thenReturn(true);

		boolean isValidUser = userAuthentication.logIn(user);

		Mockito.verify(userDao).logIn(user);

		assertEquals(true, isValidUser);
	}

	@Test
	void signUpTest() {
		User user = new User("admin", "admin", "admin");

		Mockito.when(userDao.signUp(user)).thenReturn(true);

		boolean isUserSigned = userAuthentication.signUp(user);

		Mockito.verify(userDao).signUp(user);

		assertEquals(true, isUserSigned);
	}

}
