package FunctionalInterface2;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ConsumerMethods {

	Consumer<Product> setPremiumIfConditionSatisfied = product -> {
		if (product.getPrice() > 1000)
			product.setGrade("Premium");
	};

	Consumer<Product> setSuffixIfConditionSatisfied = product -> {
		if (product.getPrice() > 3000)
			product.setName(product.getName() + "*");
	};

	public void printTheProduct(List<Product> productList) {
		String printParameter = "file";
		Consumer<Product> consumer = new Consumer<Product>() {
			@Override
			public void accept(Product product) {
				if (printParameter.equals("file")) {
					Logger logger = Logger.getLogger("Log");
					try {
						FileHandler fileHandler = new FileHandler("task.txt");
						logger.addHandler(fileHandler);
						logger.info(product.toString());
					} catch (Exception exception) {
						System.out.println(exception);
					}
				} else {
					System.out.println(product);
				}
			}
		};

		productList.stream().forEach(consumer);
		System.out.println("--------------------------------------------------");
	}

	public void settingPremiumIfConditionSatisfied(List<Product> productsList) {
		for (Product product : productsList) {
			setPremiumIfConditionSatisfied.accept(product);
		}
		System.out.println(
				"List of products after updating the products with premium with the prices greater than 1000 :");
		productsList.stream().forEach(System.out::println);
		System.out.println("--------------------------------------------------");
	}

	public void suffixingIfConditionSatisfied(List<Product> productsList) {
		for (Product product : productsList)
			setSuffixIfConditionSatisfied.accept(product);
		System.out.println(
				"\nList of products after updating the products to be suffixed with * with the prices greater than 3000 :");
		productsList.stream().forEach(System.out::println);
		System.out.println("--------------------------------------------------");
	}

	public void printPremiumProductsSuffixed(List<Product> productsList) {
		System.out.println("\nList of the premium grade products with name suffiexed with * :");
		for (Product product : productsList) {
			if (product.getGrade() == "Premium" && (product.getName().endsWith("*")))
				System.out.println(product);
		}
		System.out.println("--------------------------------------------------");
	}

	public void generateRandomProduct(List<Product> productsList) {
		int noOfProducts = productsList.size();
		Supplier<Product> supplyRandomProduct = () -> {
			int x = (int) (Math.random() * noOfProducts);
			return productsList.get(x);
		};
		Product randomProduct = supplyRandomProduct.get();
		System.out.println("Random Product : \n" + randomProduct);
		System.out.println("--------------------------------------------------");
	}

	public void generateRandomOTP() {
		Supplier<Integer> supplyRandomOTP = () -> {
			Random random = new Random();
			return random.nextInt(10000);
		};
		System.out.println("Random OTP generated is :" + supplyRandomOTP.get());
		System.out.println("--------------------------------------------------");
	}
}
