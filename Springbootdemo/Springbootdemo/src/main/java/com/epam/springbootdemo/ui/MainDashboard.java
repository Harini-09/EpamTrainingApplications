package com.epam.springbootdemo.ui;
import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.epam.springbootdemo.modal.User;
import com.epam.springbootdemo.repository.UtilityObjects;
import com.epam.springbootdemo.service.AuthenticationSystem;

@Component
public class MainDashboard {
private static final Logger LOGGER=LogManager.getLogger(MainDashboard.class);

@Autowired
private AdminDashboard adminDashboard;

@Autowired
private AuthenticationSystem authenticationSystem;

	public void userInterface() {
	
		int loop = 1;

		while (loop != 0) {
			User user = new User();
			LOGGER.info(
					"\n Welcome To Quiz Application.\n 1.AdminLogin.\n 2.UserLogin.\n 3.UserSignUp.\nEnter the number for operation u want to perform");
			LOGGER.info(
					"---------------------------------------------------------------------------------------------------------");
		
			try {
			int option= UtilityObjects.getScanner().nextInt();
			UtilityObjects.getScanner().nextLine();

			LOGGER.info("\nPlease Enter UserName:");
			String userName = UtilityObjects.getScanner().nextLine();

			user.setUserName(userName);
			LOGGER.info("\nPlease Enter Password:");
			String password = UtilityObjects.getScanner().nextLine();
			user.setPassword(password);
			operation(user, option);
			
			 
			}
			catch(InputMismatchException e) {
				
				LOGGER.info("Please Enter Valid Input...");
				UtilityObjects.getScanner().nextLine();
			}
			 
			
			
			
				
			
			
			
			LOGGER.info("\n To exit Enter 0 or to Continue Enter 1:");
			loop = UtilityObjects.getScanner().nextInt();
			
		}
	}

	public  void operation(User user, int option) {

		if(option==1) { 
			user.setUserType("Admin");
			
			if (authenticationSystem.login(user)) {
			
				adminDashboard.adminOperations(user);
			}
			else {
				LOGGER.info("Invalid credentials---------Enter valid username and Password");
			}
		}
		else if(option==2) { 
			user.setUserType("User");
			
			if (authenticationSystem.login(user)) {
			
				adminDashboard.adminOperations(user);
			}
			else {
				LOGGER.info("Invalid credentials---------Enter valid username and Password");
			}
		}

		else if(option==3) {
			user.setUserType("User");
			if(authenticationSystem.signUp(user)) {
				LOGGER.info("Registration Successfully!!!!");
			}else {
				LOGGER.info("Registration UnSuccessfully!!!!");
			}
		
		}
		else {
			  LOGGER.info("Invalid Option!!!!");
		}
  

	}
}
