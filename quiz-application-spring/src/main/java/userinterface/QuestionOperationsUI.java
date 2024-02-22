package userinterface;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import customexceptions.InvalidEntryException;
import customexceptions.InvalidQuestionIdEntryException;
import entities.Question;
import service.CrudOperationsOnQuestions;
import singletonobjects.UtilityObjects;

@Component
@Primary
public class QuestionOperationsUI implements CrudOperations {

	@Autowired
	private CrudOperationsOnQuestions operations;

	@Override
	public void insert() {
		Question newQuestion = new Question();
		UtilityObjects.getLoggerInstance().warn("Enter the values for the question to insert : ");
		UtilityObjects.getLoggerInstance().warn("Enter question id : ");
		String questionId = UtilityObjects.getScannerInstance().next();
		newQuestion.setQuestionId(questionId);
		UtilityObjects.getLoggerInstance().warn("Enter question title : ");
		newQuestion.setTitle(UtilityObjects.getScannerInstance().next());
		UtilityObjects.getScannerInstance().nextLine();
		UtilityObjects.getLoggerInstance().warn("Enter question : ");
		newQuestion.setQuestion(UtilityObjects.getScannerInstance().nextLine());
		addOptions(newQuestion);
		if (operations.insert(questionId, newQuestion)) {
			UtilityObjects.getLoggerInstance().warn("The question is successfully added!!!");
		} else {
			UtilityObjects.getLoggerInstance().warn("The question is not successfully added!!!");
		}
	}

	@Override
	public void modify() {
		UtilityObjects.getLoggerInstance().warn("Enter the question id to be modified :");
		String questionId = UtilityObjects.getScannerInstance().next();
		try {
			operations.isQuestionIdPresent(questionId);
			int choice = 0;
			do {
				UtilityObjects.getLoggerInstance().warn(
						"Enter which one to modify : 1.title 2.question 3.options 4.questionlevel 5.topictag 6.answer 7.exit");
				choice = UtilityObjects.getScannerInstance().nextInt();
				performModificationOperation(questionId, choice);
			} while (choice != 7);
		} catch (InvalidQuestionIdEntryException e) {
			UtilityObjects.getLoggerInstance().warn(e.getMessage());
			modify();
		}
	}

	private void performModificationOperation(String questionId, int choice) {
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
			UtilityObjects.getLoggerInstance().warn(e.getMessage());
		}
	}

	@Override
	public void delete() {
		UtilityObjects.getLoggerInstance().warn("Enter the question id to be deleted :");
		String questionId = UtilityObjects.getScannerInstance().next();
		try {
			operations.isQuestionIdPresent(questionId);
			if (!operations.delete(questionId)) {
				UtilityObjects.getLoggerInstance().warn("The question is successfully deleted!!!");
			} else {
				UtilityObjects.getLoggerInstance().warn("The question is not successfully deleted!!!");
			}
		} catch (InvalidQuestionIdEntryException e) {
			UtilityObjects.getLoggerInstance().warn(e.getMessage());
			delete();
		} catch (Exception e) {
			UtilityObjects.getLoggerInstance().error(e.getMessage());
			UtilityObjects.getLoggerInstance()
					.error("This question is used in the quiz library..You cannot delete it!!");
		}
	}

	@Override
	public void view() {
		List<Question> questions = operations.viewQuestions();
		if (questions.isEmpty()) {
			UtilityObjects.getLoggerInstance().warn("The Questions Library is empty!!");
		} else {
			UtilityObjects.getLoggerInstance().warn("The Questions in the library are : ");
			UtilityObjects.getLoggerInstance().warn(questions);
		}
	}

	private Question addOptions(Question newQuestion) {
		UtilityObjects.getLoggerInstance().warn("Enter the no of options do u like to enter");
		int length = UtilityObjects.getScannerInstance().nextInt();
		List<String> options = new ArrayList<>(length);
		int count = 1;
		while (count <= length) {
			UtilityObjects.getLoggerInstance().warn("Enter the option {} :",count);
			options.add(UtilityObjects.getScannerInstance().next());
			count += 1;
		}
		newQuestion.setOptions(options);
		addAdditionalFeatures(newQuestion);
		return newQuestion;
	}

	private Question addAdditionalFeatures(Question newQuestion) {
		UtilityObjects.getLoggerInstance().warn("Enter question level :");
		newQuestion.setQuestionlevel(UtilityObjects.getScannerInstance().next());
		UtilityObjects.getLoggerInstance().warn("Enter topic tag :");
		newQuestion.setTopictag(UtilityObjects.getScannerInstance().next());
		UtilityObjects.getLoggerInstance().warn("Enter the answer :");
		newQuestion.setAnswer(UtilityObjects.getScannerInstance().next());
		return newQuestion;
	}

	private void modifyTitle(String questionId) {
		UtilityObjects.getLoggerInstance().warn("Enter the title :");
		String title = UtilityObjects.getScannerInstance().next();
		if (operations.modifyTitle(questionId, title).equals(title)) {
			UtilityObjects.getLoggerInstance().warn("The title is successfully updated!!!");
		} else {
			UtilityObjects.getLoggerInstance().warn("The title is not successfully updated!!!");
		}
	}

	private void modifyQuestion(String questionId) {
		UtilityObjects.getLoggerInstance().warn("Enter the question :");
		UtilityObjects.getScannerInstance().nextLine();
		String question = UtilityObjects.getScannerInstance().nextLine();
		if (operations.modifyQuestion(questionId, question).equals(question)) {
			UtilityObjects.getLoggerInstance().warn("The question is successfully updated!!!");
		} else {
			UtilityObjects.getLoggerInstance().warn("The question is not successfully updated!!!");
		}
	}

	private void modifyOptions(String questionId) {
		int count = 1;
		UtilityObjects.getLoggerInstance().warn("Enter the no of options do u like to enter");
		int length = UtilityObjects.getScannerInstance().nextInt();
		List<String> options = new ArrayList<>(length);
		while (count <= length) {
			UtilityObjects.getLoggerInstance().warn("Enter the option {} :",count);
			options.add(UtilityObjects.getScannerInstance().next());
			count++;
		}
		if (operations.modifyOptions(questionId, options).equals(options)) {
			UtilityObjects.getLoggerInstance().warn("The options are not successfully updated!!!");
		} else {
			UtilityObjects.getLoggerInstance().warn("The options are successfully updated!!!");
		}
	}

	private void modifyQuestionLevel(String questionId) {
		UtilityObjects.getLoggerInstance().warn("Enter the question level :");
		String questionLevel = UtilityObjects.getScannerInstance().next();
		if (operations.modifyQuestionLevel(questionId, questionLevel).equals(questionLevel)) {
			UtilityObjects.getLoggerInstance().warn("The question level is successfully updated!!!");
		} else {
			UtilityObjects.getLoggerInstance().warn("The question level is not successfully updated!!!");
		}
	}

	private void modifyTopicTag(String questionId) {
		UtilityObjects.getLoggerInstance().warn("Enter the topic tag :");
		String topicTag = UtilityObjects.getScannerInstance().next();
		if (operations.modifyTopicTag(questionId, topicTag).equals(topicTag)) {
			UtilityObjects.getLoggerInstance().warn("The topic tag is successfully updated!!!");
		} else {
			UtilityObjects.getLoggerInstance().warn("The topic tag is not successfully updated!!!");
		}
	}
 
	private void modifyAnswer(String questionId) {
		UtilityObjects.getLoggerInstance().warn("Enter the answer :");
		String answer = UtilityObjects.getScannerInstance().next();
		if (operations.modifyAnswer(questionId, answer).equals(answer)) {
			UtilityObjects.getLoggerInstance().warn("The answer is successfully updated!!!");
		} else {
			UtilityObjects.getLoggerInstance().warn("The answer is not successfully updated!!!");
		}
	}
}
