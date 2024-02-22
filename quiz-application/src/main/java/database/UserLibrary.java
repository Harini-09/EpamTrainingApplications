package database;

import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserLibrary {
	public static List<User> users = new ArrayList<>();

	public List<User> getUsers() {
		users.add(new User("johnwilliams", "john@124", "admin"));
		users.add(new User("miketandon", "mike#112", "admin"));
		users.add(new User("a", "b", "admin"));
		users.add(new User("Mickey", "mic@124", "user"));
		users.add(new User("Terarus", "tery#112", "user"));
		users.add(new User("x", "y", "user"));
		return users;
	}
}
