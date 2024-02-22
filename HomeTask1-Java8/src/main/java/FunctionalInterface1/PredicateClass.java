package FunctionalInterface1;

import java.util.function.Predicate;

public class PredicateClass implements Predicate<Product> {

	@Override
	public boolean test(Product t) {
		// TODO Auto-generated method stub
		return t.getPrice()>1000;
	}

}
