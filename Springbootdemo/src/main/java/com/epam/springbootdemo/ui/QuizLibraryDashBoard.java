package com.epam.springbootdemo.ui;

import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.epam.springbootdemo.repository.UtilityObjects;


@Component
public class QuizLibraryDashBoard {
	private static final Logger LOGGER=LogManager.getLogger(QuizLibraryDashBoard.class);

	   @Autowired
	   private QuizLibraryDashBoardUI quizLibraryDashBoardUI;
	  
	   

	   


	public void quizeLibraryDashboard() {

	 
		int loop = 1;

		while (loop != 0) {

			LOGGER.info("########### WelCome To Quiz Library Dashboard ##########\n1.Create Quiz.\n2.View Quizes.\n3.Update Quiz.\n4.Delete quiz\nEnter the option:\n");
			try {
				int option = UtilityObjects.getScanner().nextInt();
				UtilityObjects.getScanner().nextLine();

				if (option == 1) {
					quizLibraryDashBoardUI.createQuiz();
				} else if (option == 2) {
					quizLibraryDashBoardUI.viewQuiz();
				} else if (option == 3) {
					quizLibraryDashBoardUI.updateQuiz();   
				} else if (option == 4) {
					quizLibraryDashBoardUI.deleteQuiz();
				} else {
					LOGGER.info("Invalid option");
				}
			} catch (InputMismatchException e) {
				LOGGER.info("Invalid Input type!!!");
				UtilityObjects.getScanner().nextLine();
			}

			LOGGER.info("to exit press 0 or press 1");
			loop = UtilityObjects.getScanner().nextInt();
			UtilityObjects.getScanner().nextLine();
		}
	}
 
	
}
