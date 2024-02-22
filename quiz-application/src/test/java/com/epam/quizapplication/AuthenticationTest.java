package com.epam.quizapplication;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import service.UserAuthentication;

class AuthenticationTest {
	
	UserAuthentication authentication;
	@BeforeEach
	void getObject() {
		authentication=new UserAuthentication() ;
	}
	
	@Test
	void testExistingAdminLogin() {
		boolean result = authentication.logIn("miketandon", "mike#112","admin");
		assertEquals(true,result);
	}
	
	@Test
	void testNotExistingAdminLogin() {
		boolean result = authentication.logIn("kale", "kale#123","admin");
		assertEquals(false,result); 
	}
	
	@Test
	void testExistingUserLogin() {
		boolean result = authentication.logIn("Terarus", "tery#112","user");
		assertEquals(true,result);
	}
	
	@Test
	void testNotExistingUserLogin() {
		boolean result = authentication.logIn("johnwilliams", "john@112","user");
		assertEquals(false,result);
	}
	
	@Test
	void testAdminSignUp() {
		boolean result = authentication.signUp("miketandon", "mike#112");
		assertEquals(true,result);
	}
	
	@Test
	void testEmptyAdminSignUp() {
		boolean result = authentication.signUp("", "");
		assertEquals(false,result);
	}
	
	@Test
	void testAdminSignUpWithUserIdEmpty() {
		boolean result = authentication.signUp("", "mike#112");
		assertEquals(false,result);
	}
	
	@Test
	void testAdminSignUpWithPasswordEmpty() {
		boolean result = authentication.signUp("", "mike#112");
		assertEquals(false,result);
	}

}
