package ConsumerAndSupplier;

import java.util.Arrays;
import java.util.List;

public class ProductDB {
	public static List<Product> getProducts() {
		Product product1 = new Product("Electronics", "Iphone", 48000, 2);
		Product product2 = new Product("Electronics", "MacBook", 150000, 1);
		Product product3 = new Product("Electronics", "Sony Video Projector", 425000, 4);

		Product product4 = new Product("Women's Fashion", "Fast-Track Watch", 10000, 2);
		Product product5 = new Product("Women's Fashion", "Leather HandBag", 2500, 1);

		Product product6 = new Product("Men's Fashion", "T-Shirt", 5000, 5);
		Product product7 = new Product("Men's Fashion", "Reebok Shoes", 5000, 2);
		Product product8 = new Product("Men's Fashion", "Titan Watch", 4800, 1);

		Product product9 = new Product("Kids", "HeadBands", 200, 4);
		Product product10 = new Product("Kids", "Canvas Cap", 1200, 1);
		Product product11 = new Product("Kids", "Baby SunGlasses", 4200, 4);
		Product product12 = new Product("Kids", "iLearn Baby Toys", 19000, 5);
		Product product13 = new Product("Kids", "Montessori Toys", 8900, 2);

		Product product14 = new Product("Books", "Clean Code Principles", 1100, 1);
		Product product15 = new Product("Books", "Microservices", 2300, 2);
		Product product16 = new Product("Books", "Design Patterns & Principles", 850, 1);
		Product product17 = new Product("Kids", "Indoor Playground Set", 4300000, 2);

		return Arrays.asList(product1, product2, product3, product4, product5, product6, product7, product8, product9,
				product10, product11, product12, product13, product14, product15, product16, product17);

	}

}
