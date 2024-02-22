package ConsumerAndSupplier;

import java.util.List;
import java.util.function.Consumer;

public class Utility1 {

	List<Product> products = ProductDB.getProducts();

	Consumer<Product> consumer1 = new Consumer<>() {
		public void accept(Product p) {
			if (p.getPrice() > 1000)
				p.setGrade(5);
		}
	};

	Consumer<Product> consumer2 = new Consumer<>() {
		public void accept(Product p) {
			if (p.getPrice() > 3000)
				p.setName(p.getName() + "*");
		}

	};

	public void setGradeIfPriceGreaterThan1000() {
		for (Product product : products) {
			consumer1.accept(product);
		}
		System.out.println(products);
		System.out.println("***********************************************");

//		products.stream()
//		.map(t->{if(t.getPrice()>1000)t.setGrade(5);
//				 return t;
//		})
//		.forEach(System.out::println);
//		System.out.println(products);
//		System.out.println("***********************************************");

	}

	public void setPremiumIfPriceGreaterThan3000() {
		for (Product product : products) {
			consumer2.accept(product);
		}
		System.out.println(products);
		System.out.println("***********************************************");

	}

	public void printPremiumProductsSuffixed() {
		for (Product product : products) {
			if (product.getGrade() == 5 && product.getName().contains("*"))
				System.out.println(product);
		}
		System.out.println("***********************************************");
	}

}
