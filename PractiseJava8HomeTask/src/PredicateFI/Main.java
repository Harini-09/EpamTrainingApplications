
import java.util.List;
import java.util.function.Predicate;

public class Main {
	public static void main(String[] args) {
		ProductStore productdb = new ProductStore();
		List<Product> products = productdb.getProducts();
		Predicate<Product> predicate = new Predicate<>() {
			public boolean test(Product p) {
				return p.getPrice() > 1000;
			}
		};

		for (Product p : products)
			if (predicate.test(p))
				System.out.println(p);

	}

}
