package BiFunctionTask;

import java.util.function.BiFunction;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Task {

	public static void main(String[] args) {

		BiFunction<String, Integer, Product> creatingObject = (String name, Integer price) -> {

			Product item = new Product();
			item.setName(name);
			item.setPrice(price);
			return item;

		};
		System.out.print(creatingObject.apply("Laptop Stand", 580));

		ProductStore productStore = new ProductStore();
		List<Product> listOfProducts = productStore.getProducts();
		Map<Product, Integer> mapOfProducts = new HashMap<>();
		mapOfProducts.put(listOfProducts.get(0), 2);
		mapOfProducts.put(listOfProducts.get(1), 2);
		mapOfProducts.put(listOfProducts.get(2), 3);
		mapOfProducts.put(listOfProducts.get(3), 1);

		// This is for one product
		BiFunction<Product, Integer, Integer> costOfProduct = (Product productDetails, Integer quantity) -> {
			return productDetails.getPrice() * quantity;
		};

		System.out.println("\nTotal cost of a Selected product : " + costOfProduct.apply(listOfProducts.get(0), 3));

		Integer totalCost = 0;
		for (Product product : mapOfProducts.keySet()) {
			totalCost += costOfProduct.apply(product, mapOfProducts.get(product));
		}

		System.out.println("Total Cost of Products in cart : " + totalCost);
	}

}
