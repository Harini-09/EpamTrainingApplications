package com.epam.springbootdemo.ui;
import java.util.ArrayList;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.epam.springbootdemo.modal.Question;
import com.epam.springbootdemo.repository.UtilityObjects;
import com.epam.springbootdemo.service.QuestionLibraryService;

@Component
public class QuestionLibraryDashboard {
private static final Logger LOGGER=LogManager.getLogger(QuestionLibraryDashboard.class);

   @Autowired
  private QuestionLibraryService questionLibraryService;
  


	public void functionalities(int option) {

		if (option == 1) {
			addQuestion(); 
		} else if (option == 2) {
			modifyQuestion();
		} else if (option == 3) {
			deleteQuestion();
		} else if (option == 4) {
			viewQuestions(questionLibraryService.getAlldata());
		} else if (option == 5) {
			viewSpecificQuestion();
		} else {
			LOGGER.info("Invalid Option!!!");
		}

	}

	private void viewSpecificQuestion() {

		LOGGER.info("Enter the questionID");
		int questionID = UtilityObjects.getScanner().nextInt();
	

		if (questionLibraryService.viewList(questionID) == null) {
			LOGGER.info("Question with given ID Doesnt Exists...Please Enter valid Question ID");
		} else {
			LOGGER.info(questionLibraryService.viewList(questionID));
		}
		UtilityObjects.getScanner().nextLine();

	}

	private void viewQuestions(Object questionsdata) {
		@SuppressWarnings("unchecked")
		List<Question> questionsList = (List<Question>) questionsdata;
		LOGGER.info(
				"<-----------------------------------QuestionsLibrary----------------------------------------------------->");
		questionsList.stream().forEach(question -> {
			LOGGER.info(question.getQuestionID());
			LOGGER.info(question.getQuestion());
			LOGGER.info(question.getDifficultyLevel());
			LOGGER.info(question.getOptions());
			LOGGER.info(question.getTag());
			LOGGER.info(question.getAnswer());
			LOGGER.info("<------------------------------------------------------------------------>");

		});

	}

	public void questionLibraryDashboard() {
		int loop = 1;
		while (loop != 0) {
			LOGGER.info("1.Creating\n2.Modifying\n3.Removing\n4.Viewing Questions\n5.View Question By QuestionID");
			try {
				int option = UtilityObjects.getScanner().nextInt();
				functionalities(option);
			} catch (InputMismatchException e) {
				LOGGER.info("\nInvalid Input Type!!!:");
				UtilityObjects.getScanner().nextLine();
			}
			LOGGER.info("\n To exit Enter 0 or to Continue Enter 1:");
			loop = UtilityObjects.getScanner().nextInt();
		}

	}

	private void addQuestion() {
		Question newQuestion = new Question();
		LOGGER.info("Enter The question ID");
		int questionID = UtilityObjects.getScanner().nextInt();
		UtilityObjects.getScanner().nextLine();
		Optional<Object>checkIfQuestionnull = Optional.ofNullable(questionLibraryService.viewList(questionID));
		if (checkIfQuestionnull.isPresent()) {

			LOGGER.info("Invalid Question ID.... ID Already present ..Please Enter Valid Question ID");

		} else {
			newQuestion.setQuestionID(questionID);
			LOGGER.info("Enter The question");
			newQuestion.setQuestion(UtilityObjects.getScanner().nextLine());
			LOGGER.info("Enter No of the Options:");
			int noOfOptions = UtilityObjects.getScanner().nextInt();
			UtilityObjects.getScanner().nextLine();
			List<String> listOfOptions = new ArrayList<>();
			if (noOfOptions < 2) {
				LOGGER.info("Please Enter More than 2 options");
			} else {
				for (int i = 0; i < noOfOptions; i++) {
					LOGGER.info("Enter the value for Option{} :" ,(i + 1));
					String option = UtilityObjects.getScanner().nextLine();
					listOfOptions.add(option);
				}
				newQuestion.setOptions(listOfOptions);
				LOGGER.info("Enter the answer:");
				newQuestion.setAnswer(UtilityObjects.getScanner().nextLine());
				LOGGER.info("Enter the difficulty level");
				newQuestion.setDifficultyLevel(UtilityObjects.getScanner().nextLine());
				LOGGER.info("Enter the topic tag:");
				newQuestion.setTag(UtilityObjects.getScanner().nextLine());
				Optional<Object>test=Optional.ofNullable(questionLibraryService.addQuestion(newQuestion));
				if (test.isPresent()) {

					LOGGER.info("Question Added Successfully!!!");
				} else {
					LOGGER.info("QuestionID already exists!! ");
				}
			}
		}

	}

	private void modifyQuestion() {
		
		int loop = 1;

		Optional<Object> checkIfQuestionIsNull = Optional.ofNullable(questionLibraryService.getAlldata());

		if (checkIfQuestionIsNull.isPresent()) {
			while (loop != 0) {
				LOGGER.info("Enter the QuestionID:");
				int questionID = UtilityObjects.getScanner().nextInt();

				LOGGER.info(
						"Enter Number for field u Want to update\n1.question\n2.Options\n3.answer\n4.difficulty level\n5.the topic tag\n");
				int choice = UtilityObjects.getScanner().nextInt();
				UtilityObjects.getScanner().nextLine();
				modification(questionID,choice);
				LOGGER.info("To Update more fields Enter 1 or to exits press 0");
				loop = UtilityObjects.getScanner().nextInt();
				UtilityObjects.getScanner().nextLine();
			}
		} else {
			LOGGER.info(" Questions are not currently Available in Questions Library for updations!!!");

		}

	}

	private void modification(int questionID, int choice) {
		try {
		
			Optional<Object> checkIfQuestionIsNull = Optional.ofNullable(questionLibraryService.viewList(questionID));

			if (!checkIfQuestionIsNull.isPresent()) {
				LOGGER.info("Invalid Question ID....Please Enter Valid Question ID");

			} else {
				LOGGER.info(questionLibraryService.viewList(questionID));

				if (choice == 1) {
					LOGGER.info("Enter the question");
					updateValue(questionID, choice);
				} else if (choice == 2) {
					updateOptions(questionID, choice);

				} else if (choice == 3) {
					LOGGER.info("Enter the updated answer");
					updateValue(questionID, choice);

				} else if (choice == 4) {
					LOGGER.info("Enter the difficulty level");
					updateValue( questionID, choice);
				} else if (choice == 5) {

					LOGGER.info("Enter the topic tag:");
					updateValue(questionID, choice);
				} else {

					LOGGER.info("Invalid Option");

				}
			}
		} catch (InputMismatchException e) {  
			LOGGER.info("Invalid Input Type!!!");
			UtilityObjects.getScanner().nextLine();
		}
		
	}

	private void updateOptions( int questionID, int choice) {
		
		LOGGER.info("Enter the Options:");
		LOGGER.info("Enter No of the Options:");
		int noOfOptions = UtilityObjects.getScanner().nextInt();
		UtilityObjects.getScanner().nextLine();
		if (noOfOptions < 2) {
			LOGGER.info("Please Enter More than 2 options");
		} else { 
			List<String> listOfOptions = new ArrayList<>(); 
			for (int i = 0; i < noOfOptions; i++) {
				LOGGER.info("Enter the value for Option{} :",(i + 1));
				String option = UtilityObjects.getScanner().nextLine();
				listOfOptions.add(option);
			}
			Optional<Object>test=Optional.ofNullable(questionLibraryService.modifyQuestion(listOfOptions, questionID, choice));

			if (test.isPresent()) {
				LOGGER.info("Question Updation Successful!!!");
			} else {
			   LOGGER.info("Please Enter Valid QuestionID");
			}

		}

	}
	private void updateValue( int questionID, int choice) {
		

		String updatedValue = UtilityObjects.getScanner().nextLine();
		Optional<Object>test=Optional.ofNullable(questionLibraryService.modifyQuestion(updatedValue, questionID, choice));

		if (test.isPresent()) {
			LOGGER.info("Question Updated Successfully!!!");
		} else {
			LOGGER.info("Please Enter Valid QuestionID");
		}
		    LOGGER.info("Question Updated Successfully!!!");

	}

	private void deleteQuestion() {
  
		Optional<Object> checkIfQuestionIsNull = Optional.ofNullable(questionLibraryService.getAlldata());
		if (checkIfQuestionIsNull.isPresent()) {
			
			LOGGER.info("Enter The question ID");
			int questionID = UtilityObjects.getScanner().nextInt();
			boolean isQuestionDeleted = questionLibraryService.deleteQuestion(questionID);
			if (isQuestionDeleted) {
				LOGGER.info("Questions Removed Successfully!!");
			} else {
				LOGGER.info("Invalid Question ID....Please Enter Valid Question ID");
			}
		}
		else {
			LOGGER.info(" Questions are not currently Available in Questions Library for deletion!!!");

		}
	}
}
