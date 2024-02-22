package service;


import database.UserDAOJpaImpl;
import database.UserDao;
import model.User;


public class UserAuthenticationImpl implements UserAuthentication {

	private UserDao userDao = new UserDAOJpaImpl();

	@Override
	public boolean logIn(User user) {
		return userDao.logIn(user);
	}
	
	@Override
	public boolean signUp(User user) {
		return userDao.signUp(user);
	}
}
