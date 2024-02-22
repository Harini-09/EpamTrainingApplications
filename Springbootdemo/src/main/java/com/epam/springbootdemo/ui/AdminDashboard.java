package com.epam.springbootdemo.ui;


import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.epam.springbootdemo.modal.User;
import com.epam.springbootdemo.repository.UtilityObjects;

@Component
public class AdminDashboard {
	private static final Logger LOGGER=LogManager.getLogger(AdminDashboard.class);
	@Autowired
	private QuizLibraryDashBoard quizLibraryDashBoard;
    @Autowired
	private QuestionLibraryDashboard questionLibraryDashBoard;
 

	public void adminOperations(User user) {
     

	 int loop=1;
	 
	 while(loop!=0) {
	
     LOGGER.info("Hello!{},Welcome to Quiz Applications!!!\nMenu\n1.Quiz Library\n2.Questions Library\nEnter the value\n",user.getUserName());
	 try {
	 int option=UtilityObjects.getScanner().nextInt();
	 UtilityObjects.getScanner().nextLine();
	 if(option==1) {
		 quizLibraryDashBoard.quizeLibraryDashboard();
	 }
	 else if(option==2) {
		 
		 questionLibraryDashBoard.questionLibraryDashboard();
	 } 
	 else {
		 LOGGER.info("Invalid Input!!!!");
		 
	 }
	 
	 }
	 catch(InputMismatchException e) {
		
	  LOGGER.info("Invalid Input type!!!");
	  UtilityObjects.getScanner().nextLine();
	   
	  
	 }
	
	   LOGGER.info("To exit press 0 or to continue press 1");
	   loop=UtilityObjects.getScanner().nextInt();
	   UtilityObjects.getScanner().nextLine();
	 
	
	 }
	 
	 
 }
}
