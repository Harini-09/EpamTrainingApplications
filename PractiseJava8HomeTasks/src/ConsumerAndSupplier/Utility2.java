package ConsumerAndSupplier;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Utility2 {
	private int randomno;
	List<Product> products = ProductDB.getProducts();
	Random random = new Random();

	Supplier<Product> supplier1 = new Supplier<>() {
		public Product get() {
			return products.get(randomno);
		}
	};

	Supplier<Integer> supplier2 = new Supplier<>() {
		public Integer get() {
			return random.nextInt(999, 10000);
		}
	};

	public void printRandomProduct() {
		// Random random = new Random();
		randomno = random.nextInt(products.size());
		System.out.println(supplier1.get());
		System.out.println("***********************************************");
	}

	public void getRandomOTP() {
		System.out.println(supplier2.get());
		System.out.println("***********************************************");
	}

}
