package practiseprograms;

public class FindPrime {
	private static FindPrime findPrime;

	public static FindPrime getGlobalInstance() {
		if (findPrime == null) {
			return new FindPrime();
		}
		return findPrime;
	}

	public int isPrime(int number) {
		int count = 0, i = 2;
		while (i < number) {
			if (number % i == 0) {
				count++;
				break;
			}
			i++;
		}
		return count;
	}

}
