package database;

import model.User;

public interface UserDao {
	public boolean logIn(User user);
	public boolean signUp(User user);
}
