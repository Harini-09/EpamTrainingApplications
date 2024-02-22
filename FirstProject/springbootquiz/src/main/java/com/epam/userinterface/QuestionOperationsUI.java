package com.epam.userinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.customexceptions.InvalidEntryException;
import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.entities.Question;
import com.epam.service.QuestionLibraryService;

@Component
public class QuestionOperationsUI implements CrudOperations {

	@Autowired
	private QuestionLibraryService operations;

	@Autowired
	Scanner scanner;

	private final Logger logger = LogManager.getLogger(QuestionOperationsUI.class);

	@Override
	public void insert() {
		Question newQuestion = new Question();
		logger.warn("Enter the values for the question to insert : ");
		logger.warn("Enter question title : ");
		newQuestion.setTitle(scanner.next());
		scanner.nextLine();
		logger.warn("Enter question : ");
		newQuestion.setQuestionDescription(scanner.nextLine());
		addOptions(newQuestion);
		Question addedQuestion = operations.insert(newQuestion);
		logger.warn("The question is successfully added!!!");
		logger.warn(addedQuestion);
	}

	@Override
	public void modify() {
		logger.warn("Enter the question id to be modified :");
		int questionId = scanner.nextInt();
		try {
			operations.isQuestionIdPresent(questionId);
			int choice = 0;
			do {
				logger.warn(
						"Enter which one to modify : 1.title 2.question 3.options 4.questionlevel 5.topictag 6.answer 7.exit");
				choice = scanner.nextInt();
				performModificationOperation(questionId, choice);
			} while (choice != 7);
		} catch (InvalidQuestionIdEntryException e) {
			logger.warn(e.getMessage());
			modify();
		}
	}

	private void performModificationOperation(int questionId, int choice) {
		try {
			if (choice == 1) {
				modifyTitle(questionId);
			} else if (choice == 2) {
				modifyQuestion(questionId);
			} else if (choice == 3) {
				modifyOptions(questionId);
			} else if (choice == 4) {
				modifyQuestionLevel(questionId);
			} else if (choice == 5) {
				modifyTopicTag(questionId);
			} else if (choice == 6) {
				modifyAnswer(questionId);
			} else if (choice > 7) {
				throw new InvalidEntryException();
			}
		} catch (InvalidEntryException e) {
			logger.warn(e.getMessage());
		}
	}

	@Override
	public void delete() {
		logger.warn("Enter the question id to be deleted :");
		int questionId = scanner.nextInt();
		try {
			operations.isQuestionIdPresent(questionId);
			if (!operations.delete(questionId)) {
				logger.warn("The question is successfully deleted!!!");
			} else {
				logger.warn("The question is not successfully deleted!!!");
			}
		} catch (InvalidQuestionIdEntryException e) {
			logger.warn(e.getMessage());
			delete();
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("This question is used in the quiz library..You cannot delete it!!");
		}
	}

	@Override
	public void view() {
		List<Question> questions = operations.viewQuestions();
		if (questions.isEmpty()) {
			logger.warn("The Questions Library is empty!!");
		} else {
			logger.warn("The Questions in the library are : ");
			logger.warn(questions);
		}
	}

	private Question addOptions(Question newQuestion) {
		logger.warn("Enter the no of options do u like to enter");
		int length = scanner.nextInt();
		List<String> options = new ArrayList<>(length);
		int count = 1;
		while (count <= length) {
			logger.warn("Enter the option {} :", count);
			options.add(scanner.next());
			count += 1;
		}
		newQuestion.setOptions(options);
		addAdditionalFeatures(newQuestion);
		return newQuestion;
	}

	private Question addAdditionalFeatures(Question newQuestion) {
		logger.warn("Enter question level :");
		newQuestion.setQuestionlevel(scanner.next());
		logger.warn("Enter topic tag :");
		newQuestion.setTopictag(scanner.next());
		logger.warn("Enter the answer :");
		newQuestion.setAnswer(scanner.next());
		return newQuestion;
	}

	private void modifyTitle(int questionId) {
		logger.warn("Enter the title :");
		String title = scanner.next();
		if (operations.modifyTitle(questionId, title).equals(title)) {
			logger.warn("The title is successfully updated!!!");
		} else {
			logger.warn("The title is not successfully updated!!!");
		}
	}

	private void modifyQuestion(int questionId) {
		logger.warn("Enter the question :");
		scanner.nextLine();
		String question = scanner.nextLine();
		if (operations.modifyQuestion(questionId, question).equals(question)) {
			logger.warn("The question is successfully updated!!!");
		} else {
			logger.warn("The question is not successfully updated!!!");
		}
	}

	private void modifyOptions(int questionId) {
		int count = 1;
		logger.warn("Enter the no of options do u like to enter");
		int length = scanner.nextInt();
		List<String> options = new ArrayList<>(length);
		while (count <= length) {
			logger.warn("Enter the option {} :", count);
			options.add(scanner.next());
			count++;
		}
		if (operations.modifyOptions(questionId, options).equals(options)) {
			logger.warn("The options are not successfully updated!!!");
		} else {
			logger.warn("The options are successfully updated!!!");
		}
	}

	private void modifyQuestionLevel(int questionId) {
		logger.warn("Enter the question level :");
		String questionLevel = scanner.next();
		if (operations.modifyQuestionLevel(questionId, questionLevel).equals(questionLevel)) {
			logger.warn("The question level is successfully updated!!!");
		} else {
			logger.warn("The question level is not successfully updated!!!");
		}
	}

	private void modifyTopicTag(int questionId) {
		logger.warn("Enter the topic tag :");
		String topicTag = scanner.next();
		if (operations.modifyTopicTag(questionId, topicTag).equals(topicTag)) {
			logger.warn("The topic tag is successfully updated!!!");
		} else {
			logger.warn("The topic tag is not successfully updated!!!");
		}
	}

	private void modifyAnswer(int questionId) {
		logger.warn("Enter the answer :");
		String answer = scanner.next();
		if (operations.modifyAnswer(questionId, answer).equals(answer)) {
			logger.warn("The answer is successfully updated!!!");
		} else {
			logger.warn("The answer is not successfully updated!!!");
		}
	}
}
