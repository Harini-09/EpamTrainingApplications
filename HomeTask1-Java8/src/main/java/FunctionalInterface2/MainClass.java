package FunctionalInterface2;

import java.util.ArrayList;
import java.util.List;

public class MainClass {
	public static void main(String[] args) {
		List<Product> products = new ArrayList<>();
		products.add(new Product("Laptop", 1200, "electronics", "prime"));
		products.add(new Product("airpods", 3500, "electronics", "choice"));
		products.add(new Product("chips", 110, "snacks", "prime"));
		products.add(new Product("Washing Machine", 2000, "Electronics", "prime"));
		products.add(new Product("Rice", 3800, "grocerries", "select"));

		ConsumerMethods c = new ConsumerMethods();

		c.printTheProduct(products);
		c.settingPremiumIfConditionSatisfied(products);
		c.suffixingIfConditionSatisfied(products);
		c.printPremiumProductsSuffixed(products);
		c.generateRandomProduct(products);
		c.generateRandomOTP();

	}

}
