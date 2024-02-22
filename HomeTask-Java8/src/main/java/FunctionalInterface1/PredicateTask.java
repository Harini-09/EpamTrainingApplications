package FunctionalInterface1;

import java.util.ArrayList;
import java.util.List;


public class PredicateTask {
	List<Product> products = new ArrayList<>();
	public static void main(String[] args) {
		
		List<Product> products = ProductsDB.getProductsData();
		PredicateClass predicate = new PredicateClass();
		System.out.println("1. Print all the products from the given list of the products if the price is greater than 1000/- :");
		products.stream().filter(predicate::test).forEach(System.out::println);
		
		System.out.println("\n2. Print all the products from the given list of the products if the product is of electronics category :");
		products.stream().filter(t->t.getCategory()=="Electronics").forEach(System.out::println);
		
		System.out.println("\n3. Print all the products from the given list of product if the product price is greater than 100/- which are in electronics category :");
		products.stream().filter(t->t.getPrice()>100 && t.getCategory()=="Electronics").forEach(System.out::println);
		
		System.out.println("\n4. Print all the products from the given list of product if the product price is greater than 100/- or product is in electronics category :");
		products.stream().filter(t->t.getPrice()>100 || t.getCategory()=="Electronics").forEach(System.out::println);
		
		System.out.println("\n5. Print all the products from the given list of product if the product price is less than 100/- and product is in electronics category:");
		products.stream().filter(t->t.getPrice()<100 && t.getCategory()=="Electronics").forEach(System.out::println);
		
	}
	
	

}
