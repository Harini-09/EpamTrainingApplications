package PredicateFI;

import java.util.List;
import java.util.function.Predicate;

public class Utility {

	List<Product> products = ProductDB.getProducts();
	Predicate<Product> predicate1 = new Predicate<>() {
		public boolean test(Product product) {
			return product.getPrice() > 1000;
		}
	};

	Predicate<Product> predicate2 = new Predicate<>() {
		public boolean test(Product product) {
			return product.getCategory() == "Electronics";
		}
	};
	
	Predicate<Product> predicate3 = new Predicate<>() {
		public boolean test(Product product) {
			return product.getCategory().equals("Electronics")&&product.getPrice()>1000;
		}
	};
	
	Predicate<Product> predicate4 = new Predicate<>() {
		public boolean test(Product product) {
			return product.getCategory().equals("Electronics")||product.getPrice()>1000;
		}
	};
	
	Predicate<Product> predicate5 = new Predicate<>() {
		public boolean test(Product product) {
			return product.getCategory().equals("Electronics")&&product.getPrice()<1000;
		}
	};
	
	public void getProductsIfPriceGreaterThan1000() {
		for (Product p : products) {
			if (predicate1.test(p))
				System.out.println(p);
		}
		System.out.println("******************************************************");

	}

	public void getElectronicsProducts() {
		for (Product product : products) {
			if (predicate2.test(product))
				System.out.println(product);
		}
		System.out.println("******************************************************");
	}
	
	public void getElectronicsProductsGreaterThan1000() {
		for (Product product : products) {
			if (predicate3.test(product))
				System.out.println(product);
		}
		System.out.println("******************************************************");
	}
	
	public void getElectronicsProductsOrPriceGreaterThan1000() {
		for (Product product : products) {
			if (predicate4.test(product))
				System.out.println(product);
		}
		System.out.println("******************************************************");
	}
	
	public void getElectronicsProductsLessThan1000() {
		for (Product product : products) {
			if (predicate5.test(product))
				System.out.println(product);
		}
		System.out.println("******************************************************");
	}
	
	
	
	

}