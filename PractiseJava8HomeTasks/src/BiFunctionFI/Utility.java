package BiFunctionFI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class Utility {
	
	List<Product> products = ProductDB.getProducts();
	BiFunction<String,Integer,Product> bifunction1 = new BiFunction<>() {
		public Product apply(String name,Integer price) {
			Product productnew = new Product();
			productnew.setName(name);
			productnew.setPrice(price);
			return productnew;
		}
	};
	
	BiFunction<Product,Integer,Integer> bifunction2 = new BiFunction<>() {
		public Integer apply(Product product,Integer quantity) {
			return product.getPrice()*quantity;
		}
	};
	
	
	public void createNewProduct() {
		System.out.println("New Product is created : "+bifunction1.apply("Laptop", 30000));
	}
	
	public void calculateCostOfCart() {
		Map<Product,Integer> cart = new HashMap<>();
		cart.put(products.get(0), 2);
		System.out.println(bifunction2.apply(products.get(0),cart.get(products.get(0))));
		
		
	}
	

}
