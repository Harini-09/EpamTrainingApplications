package userinterfacelayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import customexceptions.InvalidEntryException;
import customexceptions.InvalidQuestionIdEntryException;
import model.Question;
import service.CrudOperationsOnQuestions;

public class QuestionOperationsUI implements CrudOperations {
	private static final Logger LOGGER = LogManager.getLogger(QuizApplication.class);
	private Scanner scanner = new Scanner(System.in);
	private CrudOperationsOnQuestions operations = new CrudOperationsOnQuestions();

	@Override
	public void insert() {
		Question newQuestion = new Question();
		LOGGER.info("Enter the values for the question to insert : ");
		LOGGER.info("Enter question id : ");
		String questionId = scanner.next();
		LOGGER.info("Enter question title : ");
		newQuestion.setTitle(scanner.next());
		scanner.nextLine();
		LOGGER.info("Enter question : ");
		newQuestion.setQuestion(scanner.nextLine());
		newQuestion = addOptions(newQuestion);
		if (operations.insert(questionId, newQuestion)) {
			LOGGER.info("The question is successfully added!!!");
		} else {
			LOGGER.info("The question is not successfully added!!!");
		}
	}

	@Override
	public void modify() {
		LOGGER.info("Enter the question id to be modified :");
		String questionId = scanner.next();
		try {
			operations.isQuestionIdPresent(questionId);
			int choice = 0;
			do {
				LOGGER.info(
						"Enter which one to modify : 1.title 2.question 3.options 4.questionlevel 5.topictag 6.answer 7.exit");
				choice = scanner.nextInt();
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
					} else if (choice == 7) {
						choice = 7;
					} else {
						throw new InvalidEntryException();
					}
				} catch (InvalidEntryException e) {
					LOGGER.info(e.getMessage());
				}
			} while (choice != 7);
		} catch (InvalidQuestionIdEntryException e) {
			LOGGER.info(e.getMessage());
			modify();
		}
	}

	@Override
	public void delete() {
		LOGGER.info("Enter the question id to be deleted :");
		String questionId = scanner.next();
		try {
			operations.isQuestionIdPresent(questionId);
			if (operations.delete(questionId)) {
				LOGGER.info("The question is not successfully deleted!!!");
			} else {
				LOGGER.info("The question is successfully deleted!!!");
			}
		} catch (InvalidQuestionIdEntryException e) {
			LOGGER.info(e.getMessage());
			delete();
		}
	}

	@Override
	public void view() {
		Set<Map.Entry<String, Question>> questionsSet = operations.view();
		if (questionsSet == null) {
			LOGGER.info("The Question Library is empty!!");
		} else {
			questionsSet.stream().forEach(LOGGER::info);
		}
	}

	private Question addOptions(Question newQuestion) {
		LOGGER.info("Enter the no of options do u like to enter");
		int length = scanner.nextInt();
		List<String> options = new ArrayList<>(length);
		int count = 1;
		while (count <= length) {
			LOGGER.info("Enter the option " + count + ": ");
			options.add(scanner.next());
			count += 1;
		}
		newQuestion.setOptions(options);
		addAdditionalFeatures(newQuestion);
		return newQuestion;
	}

	private Question addAdditionalFeatures(Question newQuestion) {
		LOGGER.info("Enter question level :");
		newQuestion.setQuestionlevel(scanner.next());
		LOGGER.info("Enter topic tag :");
		newQuestion.setTopictag(scanner.next());
		LOGGER.info("Enter the answer :");
		newQuestion.setAnswer(scanner.next());
		return newQuestion;
	}

	private void modifyTitle(String questionId) {
		LOGGER.info("Enter the title :");
		String title = scanner.next();
		if (operations.modifyTitle(questionId, title).equals(title)) {
			LOGGER.info("The title is successfully updated!!!");
		} else {
			LOGGER.info("The title is not successfully updated!!!");
		}
	}

	private void modifyQuestion(String questionId) {
		LOGGER.info("Enter the question :");
		scanner.nextLine();
		String question = scanner.nextLine();
		if (operations.modifyQuestion(questionId, question).equals(questionId)) {
			LOGGER.info("The question is successfully updated!!!");
		} else {
			LOGGER.info("The question is not successfully updated!!!");
		}
	}

	private void modifyOptions(String questionId) {
		int count = 1;
		LOGGER.info("Enter the no of options do u like to enter");
		int length = scanner.nextInt();
		List<String> options = new ArrayList<>(length);
		while (count <= length) {
			LOGGER.info("Enter the option " + count + ": ");
			options.add(scanner.next());
			count++;
		}
		if (operations.modifyOptions(questionId, options).equals(options)) {
			LOGGER.info("The options are successfully updated!!!");
		} else {
			LOGGER.info("The options are not successfully updated!!!");
		}
	}

	private void modifyQuestionLevel(String questionId) {
		LOGGER.info("Enter the question level :");
		String questionLevel = scanner.next();
		if (operations.modifyQuestionLevel(questionId, questionLevel).equals(questionLevel)) {
			LOGGER.info("The question level is successfully updated!!!");
		} else {
			LOGGER.info("The question level is not successfully updated!!!");
		}
	}

	private void modifyTopicTag(String questionId) {
		LOGGER.info("Enter the topic tag :");
		String topicTag = scanner.next(); 
		if (operations.modifyTopicTag(questionId, topicTag).equals(topicTag)) {
			LOGGER.info("The topic tag is successfully updated!!!");
		} else {
			LOGGER.info("The topic tag is not successfully updated!!!");
		}
	}

	private void modifyAnswer(String questionId) {
		LOGGER.info("Enter the answer :");
		String answer = scanner.next();
		if (operations.modifyAnswer(questionId, answer).equals(answer)) {
			LOGGER.info("The answer is successfully updated!!!");
		} else {
			LOGGER.info("The answer is not successfully updated!!!");
		}
	}
}
