package com.epam.springbootdemo.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.springbootdemo.database.UserDAOOperations;
import com.epam.springbootdemo.modal.User;

@Service
public class AuthenticationSystem {
    
    @Autowired
	private UserDAOOperations databaseDaoOperations;		

	public boolean login(User user) {
		
		Object isValidUser=databaseDaoOperations.viewData(user);
		return isValidUser.equals(user.getUserName());

	}
   
	public boolean signUp(User user) {  
        return databaseDaoOperations.addData(user);
	}
}
