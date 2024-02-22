package FunctionalInterface3;

import java.util.ArrayList;
import java.util.List;

import FunctionalInterface1.Product;

public class FunctionTask {

	public static void main(String[] args) {
		List<Product> products = new ArrayList<>();
		products.add(new Product("Laptop", 1200, "electronics", "prime"));
		products.add(new Product("airpods", 500, "electronics", "choice"));
		products.add(new Product("chips", 110, "snacks", "prime"));
		products.add(new Product("Washing Machine", 2000, "electronics", "prime"));
		products.add(new Product("Rice", 800, "grocerries", "select"));

		Integer totalProductsCost = products.stream().mapToInt(t -> t.getPrice()).sum();
		System.out.println("Cost of all products =" + totalProductsCost);

		Integer totalCostPricesGreaterThan1000 = products.stream().filter(t -> t.getPrice() > 1000)
				.mapToInt(t -> t.getPrice()).sum();
		System.out.println("Cost of all products greater than 1000 =" + totalCostPricesGreaterThan1000);

		Integer totalElectronicsProductsPrice = products.stream().filter(t -> t.getCategory().equals("electronics"))
				.mapToInt(t -> t.getPrice()).sum();
		System.out.println("Cost of all Electronic products =" + totalElectronicsProductsPrice);

		Integer totalElectronicProductsPriceGreaterThan1000 = products.stream()
				.filter(t -> (t.getPrice() > 1000) && (t.getCategory().equals("electronics")))
				.mapToInt(t -> t.getPrice()).sum();
		System.out.println("Cost of all Electronic products with prices greater than 1000 ="
				+ totalElectronicProductsPriceGreaterThan1000);

	}

}
