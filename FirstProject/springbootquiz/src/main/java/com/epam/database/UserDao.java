package com.epam.database;

import com.epam.entities.User;

public interface UserDao {
	public boolean logIn(User user);
	public boolean signUp(User user);
}
