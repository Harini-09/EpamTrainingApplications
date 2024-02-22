package BinarayUnaryOperatorTask;

import java.util.Random;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;

public class IntPredicateClass {

	public static void main(String[] args) {
		// Write an IntPredicate to verify if the given number is a primenumber
		checkPrime(21);
		// Write an IntConsumer to print square of the given number
		printSquareOfNumber(12);
		// Write a IntSupplier to give random integer below 5000.
		generateRandomInt();

	}

	public static void checkPrime(int number) {
		IntPredicate intPredicate = (x) -> {
			int value = 2;
			while (value < x) {
				if (x % value == 0)
					return false;
				value += 1;
			}
			return true;
		};

		System.out.println(intPredicate.test(number));

	}

	public static void printSquareOfNumber(int number) {
		IntConsumer intConsumer = (x) -> {
			System.out.println(x * x);
		};
		intConsumer.accept(number);
	}

	public static void generateRandomInt() {
		IntSupplier intSupplier = () -> {
			Random random = new Random();
			return random.nextInt(5000);
		};
		System.out.println(intSupplier.getAsInt());
	}
}
