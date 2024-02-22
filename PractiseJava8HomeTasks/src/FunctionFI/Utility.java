package FunctionFI;

import java.util.List;
import java.util.function.Function;

public class Utility {
	
	List<Product> products = ProductDB.getProducts();
	
	Function<Product,Integer> function1 = new Function<>() {
		public Integer apply(Product product) {
			return product.getPrice();
		}
	};
	
	Function<Product,Integer> function2 = new Function<>() {
		public Integer apply(Product product) {
			if(product.getPrice()>1000)
				return product.getPrice();
			else
				return 0;
		}
	};
	
	Function<Product,Integer> function3 = new Function<>() {
		public Integer apply(Product product) {
			if(product.getCategory().equals("Electronics"))
				return product.getPrice();
			else
				return 0;
		}
	};
	
	Function<Product,Integer> function4 = new Function<>() {
		public Integer apply(Product product) {
			if(product.getPrice()>1000 && product.getCategory().equals("Electronics") )
				return product.getPrice();
			else
				return 0;
		}
	};
	
	
	public void calculateCostOfProducts() {
		int sum=0;
		for(Product product : products) {
			sum=sum+(int)(function1.apply(product));
		}
		System.out.println(sum);
	}
	
	public void calculateCostOfProductsGreaterThan1000() {
		int sum=0;
		for(Product product : products) {
			sum=sum+(int)(function2.apply(product));
		}
		System.out.println(sum);
	}
	
	public void calculateCostOfElectronicProducts() {
		int sum=0;
		for(Product product : products) {
			sum=sum+(int)(function3.apply(product));
		}
		System.out.println(sum);
	}
	
	public void calculateCostOfElectronicProductsGreaterThan1000() {
		int sum=0;
		for(Product product : products) {
			sum=sum+(int)(function4.apply(product));
		}
		System.out.println(sum);
	}
	
	

}
