package customexceptions;

@SuppressWarnings("serial")
public class NoSuchQuestionExistInQuizException extends Exception {

	@Override
	public String getMessage() {
		return "Warning!! The entered Question Id is not present in the given Quiz. Please enter a valid Question Id present in the quiz.";
	}
}
