package service;

import java.util.List;

import database.UserLibrary;
import model.User;

public class UserAuthentication {

	private UserLibrary usersData = new UserLibrary();
	private List<User> users = usersData.getUsers();

	public boolean logIn(String id, String password, String user) {
		return users.stream().anyMatch(users -> users.getId().equals(id) && users.getPassword().equals(password)
				&& users.getType().equals(user));
	}

	public boolean signUp(String id, String password) {
		if (id.isEmpty() || password.isEmpty()) {
			return false;
		}
		User user = new User(id, password, "user");
		users.add(user);
		return UserLibrary.users.contains(user);
	}
}
