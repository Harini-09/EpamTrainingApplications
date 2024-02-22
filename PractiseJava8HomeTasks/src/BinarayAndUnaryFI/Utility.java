package BinarayAndUnaryFI;

import java.util.Random;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;

public class Utility {

	IntPredicate intpredicate = new IntPredicate() {
		public boolean test(int num) {
			for (int i = 2; i < num; i++) {
				if (num % i == 0)
					return false;
			}
			return true;
		}
	};

	IntConsumer intconsumer = new IntConsumer() {
		public void accept(int num) {
			System.out.println(num * num);
		}
	};

	IntSupplier intsupplier = new IntSupplier() {
		public int getAsInt() {
			Random random = new Random();
			return random.nextInt(5000);
		}
	};

	public void checkPrime(int n) {
		if (intpredicate.test(n))
			System.out.println("Prime");
		else
			System.out.println("Not Prime");
	}

	public void printSquare(int n) {
		intconsumer.accept(n);
	}

	public void printRandomInteger() {
		System.out.println(intsupplier.getAsInt());
	}

}
