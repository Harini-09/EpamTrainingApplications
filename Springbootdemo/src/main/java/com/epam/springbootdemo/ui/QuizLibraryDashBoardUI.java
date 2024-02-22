package com.epam.springbootdemo.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.springbootdemo.modal.Question;
import com.epam.springbootdemo.modal.Quiz;
import com.epam.springbootdemo.repository.UtilityObjects;
import com.epam.springbootdemo.service.QuestionLibraryService;
import com.epam.springbootdemo.service.QuizLibraryService;

@Component
public class QuizLibraryDashBoardUI {
	private static final Logger LOGGER=LogManager.getLogger(QuizLibraryDashBoardUI.class); 

	@Autowired
	private QuizLibraryService quizLibraryService;

	@Autowired
	private QuestionLibraryService questionLibraryService;

	public void createQuiz() {

		List<Question> listOfQuestions = new ArrayList<>();

		Quiz quiz = new Quiz();
		LOGGER.info("Enter The ID For the Quiz:");
		int quizID = UtilityObjects.getScanner().nextInt();
		UtilityObjects.getScanner().nextLine();
		LOGGER.info("Enter The Title For the Quiz:");
		String quizTitle = UtilityObjects.getScanner().nextLine();
		Optional<Object> checkifQuizNull = Optional.ofNullable(quizLibraryService.view(quizID));
		if (checkifQuizNull.isPresent()) {
			LOGGER.info("Please user another ID ...Quiz with the same ID already Exists!");
		}

		else {
			quiz.setQuizID(quizID);
			quiz.setTitle(quizTitle);

			LOGGER.info("Enter The Total Marks For the Quiz:");
			quiz.setTotalMarks(UtilityObjects.getScanner().nextInt());
			UtilityObjects.getScanner().nextLine();
			int loop = 1;

			while (loop != 0) {
				LOGGER.info("Enter the ID of The Questions U want to add");
				int questionID = UtilityObjects.getScanner().nextInt();

				Optional<Object> checkifQuestionNull = Optional.ofNullable(questionLibraryService.viewList(questionID));

				if (checkifQuestionNull.isEmpty()) {
					LOGGER.info("ID Not exists.........Enter Valid ID");
				} else {

					listOfQuestions.add(quizLibraryService.addQuestionToNewQuiz(questionID));

					LOGGER.info("Question Added Successfully!!!");
					LOGGER.info("To add More question press 1 or to exit press 0");
					loop = UtilityObjects.getScanner().nextInt();
					UtilityObjects.getScanner().nextLine();
				}

			}
			quiz.setQuizQuestions(listOfQuestions);
			Optional<Object> checkifQuizAdded = Optional.ofNullable(quizLibraryService.addQuiz(quiz));

			if (checkifQuizAdded.isPresent()) {

				LOGGER.info("Quiz Created successfully");
			} else {
		 		LOGGER.info("Error");

			}

		}
	}

	public final Object viewQuiz() {

		LOGGER.info("Available Quizes:");
		LOGGER.info(quizLibraryService.getAlldata());
		return quizLibraryService.getAlldata();
	}

	public final void updateQuiz() {

		@SuppressWarnings("unchecked")
		List<Quiz> quiz = (List<Quiz>) viewQuiz();
		if (!quiz.isEmpty()) {
			int loop = 1;
			while (loop != 0) {
				LOGGER.info("Enter the ID of quiz u want to Update:");
				int quizID = UtilityObjects.getScanner().nextInt();
				UtilityObjects.getScanner().nextLine();
				Optional<Object> checkifQuizNull = Optional.ofNullable(quizLibraryService.view(quizID));

				if (checkifQuizNull.isEmpty()) {
					LOGGER.info("Please Enter Valid Quiz ID..!!");
				} else {

					LOGGER.info("Enter the field of u want to update\n1.title\n2.marks\n3.questions\n");
					int choice = UtilityObjects.getScanner().nextInt();
					UtilityObjects.getScanner().nextLine();

					if (choice == 1) {
						LOGGER.info("Enter new title:");
						String updatedtitle = UtilityObjects.getScanner().nextLine();
						quizLibraryService.updateQuiz(quizID, updatedtitle, choice);

					} else if (choice == 2) {
						LOGGER.info("Enter new marks:");
						int updatedmarks = UtilityObjects.getScanner().nextInt();
						quizLibraryService.updateQuiz(quizID, updatedmarks, choice);

					} else if (choice == 3) {
						updateQuestion(quizID);
					} else {
						LOGGER.info("Invalid Input!!!!!111");

					}


				}
				LOGGER.info("To update More fields press 1 or to exit press 0");
				loop = UtilityObjects.getScanner().nextInt();
				UtilityObjects.getScanner().nextLine();

			}
		} else {
			LOGGER.info("No Quizes Available for Updation!!!!");

		}
	}

	public final void deleteQuiz() {

		@SuppressWarnings("unchecked")
		List<Quiz> quiz = (List<Quiz>) viewQuiz();

		if (!quiz.isEmpty()) {
			LOGGER.info("Enter the ID of the Quiz u want to delete");
			int quizID = UtilityObjects.getScanner().nextInt();
			Optional<Object> checkifQuizNull = Optional.ofNullable(quizLibraryService.view(quizID));

			if (checkifQuizNull.isPresent()) {
				quizLibraryService.deleteQuiz(quizID);
				LOGGER.info("Quiz Deleted Successfully");
			} else {
			    LOGGER.info("Please Enter Valid Quiz ID...!!");
			}
		} else {
			    LOGGER.info("No Quizes Available for deletion!!!!");

		}
	}

	public final void updateQuestion(int quizID) {

		LOGGER.info("Choose the operation u want to perform:\n1.add question\n2.remove question");
		int option = UtilityObjects.getScanner().nextInt();
		UtilityObjects.getScanner().nextLine();
		if (option == 1)

			addQuestion(quizID);
		else if (option == 2) {
			
			removeQuestion(quizID);
		} else
			LOGGER.info("Invalid input!!!");

	}

	private final void removeQuestion(int quizID) {

		int loop = 1;
		

		while (loop != 0) {
			LOGGER.info("Enter the ID of The Questions U want to remove");
			int questionID = UtilityObjects.getScanner().nextInt();
			Optional<Object> checkifQuizNull = Optional.ofNullable(questionLibraryService.viewList(questionID));

			if (checkifQuizNull.isEmpty()) {
				LOGGER.info("ID doesnt exists....Enter Valid ID");
			} else {
				Optional<Object> checkifQuestionDeleted = Optional.ofNullable(quizLibraryService.deleteQuestion(questionID, quizID));

				if (checkifQuestionDeleted.isPresent()) {
					LOGGER.info("Question Deleted Successfully!!!!");
					LOGGER.info(quizLibraryService.view(quizID));

				} else {
					LOGGER.info("Invalid QuestionID....Please Enter Valid QuestionID");
				}

				LOGGER.info("To Remove More question press 1 or to exit press 0");
				loop = UtilityObjects.getScanner().nextInt();
				UtilityObjects.getScanner().nextLine();
			}
		}

	}

	private final void addQuestion(int quizID) {

		int loop = 1;
		while (loop != 0) {

			LOGGER.info("Enter the ID of The Questions U want to add");
			int questionID = UtilityObjects.getScanner().nextInt();
			Optional<Object> checkifQuestionNull = Optional.ofNullable(questionLibraryService.viewList(questionID));

			if (checkifQuestionNull.isEmpty()) {
				LOGGER.info("ID doesnt exists....Enter Valid ID");
			} else {
				Optional<Object> checkifQuestionAdded = Optional.ofNullable(quizLibraryService.addQuestion(questionID, quizID));
				if (checkifQuestionAdded.isPresent()) {
					LOGGER.info("After Updating Quiz: ");
					LOGGER.info(quizLibraryService.view(quizID));
				} else {
					LOGGER.info("Please Enter Valid Quiz Name....Title is not updated");
				}

				LOGGER.info("To add More question press 1 or to exit press 0");
				loop = UtilityObjects.getScanner().nextInt();
				UtilityObjects.getScanner().nextLine();
			}
		}
	}

	public final void updateTitle(int quizID, int choice) {

		LOGGER.info("Enter the new Title for u r quiz:");
		String updatedTitle = UtilityObjects.getScanner().nextLine();
		LOGGER.info("After Updating Quiz:");
		Optional<Object> checkifTitleUpdated = Optional.ofNullable(quizLibraryService.updateQuiz(quizID, updatedTitle, choice));
		if (checkifTitleUpdated.isPresent()) {
			@SuppressWarnings("unchecked")
			List<Quiz> quizes = (List<Quiz>) quizLibraryService.getAlldata();
			quizes.stream().filter(quiz -> quiz.getQuizID() == (quizID)).forEach(LOGGER::info);
			LOGGER.info("Title updated Successfully!!");
		} else {
			LOGGER.info("Title is not updated Successfully!!");

		}

	}

	public final void updateMarks(int quizID, int choice) {

		LOGGER.info("Enter the new Marks for u r quiz:");
		int updatedMarks = UtilityObjects.getScanner().nextInt();
		LOGGER.info("After Updating Quiz:");
		Optional<Object> checkifMarksUpdated = Optional.ofNullable(quizLibraryService.updateQuiz(quizID, updatedMarks, choice));

	
		if (checkifMarksUpdated.isPresent()) {
			@SuppressWarnings("unchecked")
			List<Quiz> quizes = (List<Quiz>) quizLibraryService.getAlldata();
			quizes.stream().filter(quiz -> quiz.getQuizID() == (quizID)).forEach(LOGGER::info);
			LOGGER.info("Title updated Successfully!!");
		} else {
			LOGGER.info("Title is not updated..Enter Valid Title!!");

		}

	}
}
