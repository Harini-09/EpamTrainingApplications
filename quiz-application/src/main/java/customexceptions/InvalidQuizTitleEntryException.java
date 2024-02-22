package customexceptions;

@SuppressWarnings("serial")
public class InvalidQuizTitleEntryException extends Exception {

	public String getMessage() {
		return "Warning!! The entered Quiz Title is not present in the library. Please enter a valid Quiz Title.";
	}
}
