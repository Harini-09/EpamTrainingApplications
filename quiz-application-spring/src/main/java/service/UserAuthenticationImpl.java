package service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import database.UserDao;
import entities.User;

@Component
public class UserAuthenticationImpl implements UserAuthentication {

	@Autowired
	private UserDao userDao;

	@Override
	public boolean logIn(User user) {
		return userDao.logIn(user);
	}
	
	@Override
	public boolean signUp(User user) {
		return userDao.signUp(user);
	}
}
