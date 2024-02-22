package practiseprograms;

public class Main {
	public static void main(String[] args) {
		FindPrime findPrime = FindPrime.getGlobalInstance();
		if(findPrime.isPrime(10) == 0) {
			System.out.println("Prime");
		} else {
			System.out.println("Not a Prime");
		}

	}
	
	public void withdrawMoney(String accountNumber, double amount) throws InvalidAccountNumberException{
	    if (accountNumber.isEmpty() || accountNumber==null) {
	        throw new InvalidAccountNumberException("Please enter the account number. It cannot be empty!!!");
	    }
	    if(!isValidAccount(accountNumber)) {
	    	throw new InvalidAccountNumberException("Please enter a valid account number!!");
	    }
	    
	}
	
	private boolean isValidAccount(String accountNumber) {
		boolean isValid=false;
		return isValid;
	}
}
