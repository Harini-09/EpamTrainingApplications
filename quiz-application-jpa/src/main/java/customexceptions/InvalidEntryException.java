package customexceptions;

@SuppressWarnings("serial")
public class InvalidEntryException extends Exception {

	@Override
	public String getMessage() {
		return "Hello! You entered an INVALID option. Please enter a VALID option.";
	}

}
