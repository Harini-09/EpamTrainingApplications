package HandsOnJava8;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamAPIDemo {

	public static void main(String[] args) {
		ProductStore productStore = new ProductStore();
		List<Product> productsList = productStore.getProducts();
//stream(),parallelStream()		
//Intermediate - filter,map,max,skip,limit
//Terminal - forEach,collect,findFirst,       orElse,orElseGet,orElseThrow
		
		
		
		
//		//Higher order functions(Taking func as a parameter and returns a func)
//		//consumer predicate supplier function
//		//accept		test   get	   apply
//		//forEach	    filter  		map
//		productsList.stream()
//			//Internally immediately test method is invoked the moment we write filter and this method get will be called 
//			//internally by the test method by passing arg as product
//					.filter(Predicate.not(StreamAPIDemo::getSpecificProducts)) 
//					//not is a static method of Predicate interface
//					.map(product->{
//						Product customAttributesproduct = new Product();
//						customAttributesproduct.setItem(product.getItem());
//						customAttributesproduct.setPrice(product.getPrice());
//						return customAttributesproduct;
//					})
//					.forEach(System.out::println);
//		//forEach(product->System.out.println(product.getItem()+","+product.getPrice());

		
		
		
		
		
		
		//In memory data store - Stream API
//		Instant startTime = Instant.now();
//		System.out.println(productsList.stream()
//					.max(Comparator.comparing(Product::getPrice)));
//		System.out.println(Duration.between(startTime,Instant.now()).toMillis());
//		
//		Instant startTime2 = Instant.now();
//		System.out.println(productsList.stream()
//					.collect(Collectors.maxBy(Comparator.comparing(Product::getPrice)))); 
//		System.out.println(Duration.between(startTime2,Instant.now()).toMillis());
//		
//		
//		//groupingBy - aggregate operation
		System.out.println(productsList.stream()
					.collect(Collectors.groupingBy(Product::getCategory)));//Collectors.maxBy(Comparator.comparing(Product::getPrice)))));
//					
		
		
		
	
//		//sorted
//		productsList.stream()	//sequential stream,   .parallelStream() - parallel stream
//					.sorted(Comparator.comparing(Product::getPrice).reversed())
//					//.forEach(System.out::println);
//					//.collect(Collectors.toList()).get(0));
//					.skip(1) //It will ignore the first highest price(record) and it will get us the second highest price(record)
//					.limit(5)
//					.parallel() //8 cores of cpu used in parallel, sequential-only one core used. parallel() must be used on stream
//					.forEach(System.out::println);
		
		
		
		
//Files.lines(Path.of("D:/Jan-2023/Complext_Data_V1.csv")) <-loc of our csv file in of() //returns a stream
		
		
		
		
		
	}	
	public static boolean getSpecificProducts(Product product) {
		return product.getCategory().equals("Electronics");
	}
	
}
