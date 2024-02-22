package customexceptions;

@SuppressWarnings("serial")
public class InvalidQuestionIdEntryException extends Exception {

	public String getMessage() {
		return "Warning!! The given Question id is not present in the library. Please enter a valid question id.";
	}
}
