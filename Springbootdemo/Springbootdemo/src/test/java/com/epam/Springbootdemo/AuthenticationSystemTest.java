package com.epam.Springbootdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.List;

import javax.persistence.EntityManager;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;





import com.epam.springbootdemo.database.UserDAO;
import com.epam.springbootdemo.modal.User;
import com.epam.springbootdemo.service.AuthenticationSystem;


@ExtendWith(MockitoExtension.class)
class AuthenticationSystemTest {
@Mock
UserDAO userdao;
@Mock
EntityManager entityManager;

@InjectMocks
AuthenticationSystem authenticationSystem;

  @Test
  void loginonlyIFValidUser() {
	  User user=new User(2,"Admin","Admin","Admin","Admin@gmail.com");
	  Mockito.when(userdao.viewData(user)).thenReturn(List.of(user));
	  boolean expected=authenticationSystem.login(user);
	  assertEquals(false,expected);
  }
	@Test
	  void dontloginonlyIFInValidUser() {
		
		  User user1=new User(1,"Admin","Admin","Admin","Admin@gmail.com");
//		  User user2=new User(2,"Admin","Admin","Admin","Admin@gmail.com");
	
		  Mockito.when(userdao.viewData(user1)).thenReturn(List.of());
		  boolean expected=authenticationSystem.login(user1);
		  assertEquals(false,expected);

	  }
	@Test
	  void dontSignUpforalreadypsignedUpUser() {
		
		   User user=new User(1,"User","User","User","User@gmail.com");
		   Mockito.when(userdao.addData(user)).thenReturn(false);
		   boolean expected=authenticationSystem.signUp(user);
		   assertFalse(expected);
	  }
	
	@Test
	  void SignUpfornewUser() {
		 User user=new User(1,"User","User","User","User@gmail.com");
		   Mockito.when(userdao.addData(user)).thenReturn(true);
		   boolean expected=authenticationSystem.signUp(user);
		   assertTrue(expected);
	  }
}
