package com.epam.springbootdemo.ui;



import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.springbootdemo.modal.Question;
import com.epam.springbootdemo.modal.Quiz;
import com.epam.springbootdemo.modal.User;
import com.epam.springbootdemo.repository.UtilityObjects;
import com.epam.springbootdemo.service.QuizLibraryService;

public class UserDashboard {
	private static final Logger LOGGER=LogManager.getLogger(UserDashboard.class);
  @Autowired
  QuizLibraryService quizLibraryService;
  
  public void UserOperations(User user) {
	 LOGGER.info("Welcome {} To Users DashBoard!!!",user.getUserName());
	 LOGGER.info("1.Show Quizes Availables");
	 LOGGER.info("2.Take Quiz");
	 int option=UtilityObjects.getScanner().nextInt();
     UtilityObjects.getScanner().nextLine();

	 if(option==1) {
		 List<Quiz>quizes=(List<Quiz>) quizLibraryService.getAlldata();
		 quizes.stream().forEach(quiz->LOGGER.info("quizID: {}  Quiz title: {}",quiz.getQuizID(),quiz.getTitle()));
	 }
	 else if(option==2) {
		 LOGGER.info("Enter the ID OF the Quiz U Want to Take:");
		 int quizID=UtilityObjects.getScanner().nextInt();
		 Quiz quiz=(Quiz) quizLibraryService.view(quizID);
		 int score=0;
		 LOGGER.info("Quiz ID: {}",quiz.getQuizID());
		 LOGGER.info("Quiz Title: {}",quiz.getTitle());
		 LOGGER.info("Quiz TotalMarks: {}",quiz.getTotalMarks());
		 LOGGER.info("Quiz TotalQuestions: {}",quiz.getQuizQuestions().size());
		 LOGGER.info("Quiz Questions:");
		 for(Question question:quiz.getQuizQuestions()) {
			 LOGGER.info("Question ID: {}",question.getQuestionID());
			 LOGGER.info("Difficulty Level: {}",question.getDifficultyLevel());
			 LOGGER.info("Topic Tags: {}",question.getTag());
			 LOGGER.info("Question: {}",question.getQuestion());
			 LOGGER.info("Options: {}",question.getOptions());
			 LOGGER.info("Enter the Option for u r Answer:");
			 String answer=UtilityObjects.getScanner().nextLine();
		    
		     if(answer.equals(question.getAnswer())) {
		    	 score+=(quiz.getTotalMarks()/quiz.getQuizQuestions().size());
		     }
			 LOGGER.info("<----------------------------------------------------------------->");



		 }
		 LOGGER.info("Your Final Score:{}",score);



	 }
	 else {
		 
	 }

  }
}
