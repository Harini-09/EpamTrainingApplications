package service;

import model.User;

public interface UserAuthentication {
	public boolean logIn(User user);
	public boolean signUp(User user);
}
