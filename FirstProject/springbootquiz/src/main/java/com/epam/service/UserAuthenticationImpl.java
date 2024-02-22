package com.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.database.UserDao;
import com.epam.entities.User;

@Service
public class UserAuthenticationImpl implements UserAuthentication {

	@Autowired
	private UserDao userDaoImpl;

	@Override
	public boolean logIn(User user) {
		return userDaoImpl.logIn(user);
	}

	@Override
	public boolean signUp(User user) {
		return userDaoImpl.signUp(user);
	}
}
