package com.epam.springbootdemo.database;



import com.epam.springbootdemo.modal.User;

public interface UserDAOOperations {
	Object viewData(User object);
	boolean addData(Object object);
}
