package FunctionalInterface1;

import java.util.ArrayList;
import java.util.List;

public class ProductsDB {
	public static List<Product> getProductsData() {
		List<Product> products = new ArrayList<>();
		products.add(new Product("Laptop", 1200, "electronics", "prime"));
		products.add(new Product("airpods", 500, "electronics", "choice"));
		products.add(new Product("chips", 110, "snacks", "prime"));
		products.add(new Product("Washing Machine", 2000, "Electronics", "prime"));
		products.add(new Product("Rice", 800, "grocerries", "select"));
		return products;

	}

}
