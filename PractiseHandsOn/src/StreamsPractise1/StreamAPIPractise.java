package StreamsPractise1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class StreamAPIPractise {
	public static void main(String[] args) {
		
		//Stream Interface - Stream,       Primitive Stream Interfaces - IntStream,LongStream,DoubleStream
		//Java SE 8 introduces the Streams API, which lets us express sophisticated data processing queries.
		//stream - a sequence of elements from a source that supports aggregate operations. Streams don’t actually store elements; they are computed on demand.
		//Furthermore, stream operations have two fundamental characteristics that make them very different from collection operations: 1.Pipelining 2.Internal iteration
		//streams - for processing of objects from the collection. It represents a stream of objects from the collection
		//java.util.stream.Stream;   Stream s = collection.stream();  //stream() is defined in every collection object to convert into stream.
		//Collections are about data and streams are about computations
		//lazy(terminal should invoke) and shortcircuiting(limit(n))
		//1.lazy -  intermediate operations do not perform any processing until a terminal operation is invoked on the stream pipeline; they are “lazy.” This is because intermediate operations can usually be “merged” and processed into a single pass by the terminal operation.
		//2.shortcircuiting - This is because limit(2) uses short-circuiting; we need to process only part of the stream, not all of it, to return a result. This is similar to evaluating a large Boolean expression chained with the and operator: as soon as one expression returns false, we can deduce that the whole expression is false without evaluating all of it. Here, the operation limit returns a stream of size 2. 
		//The Stream interface in java.util.stream.Stream defines many operations, which can be grouped in two categories.The following 2 operations: 
		//Intermediate ops -> filter, sorted, and map, which can be connected together to form a pipeline
		//Terminal ops -> collect, which closed the pipeline and returned a result or sometimes empty. 
		//Stream operations that can be connected are called intermediate operations. They can be connected together because their return type is a Stream.
		//Operations that close a stream pipeline are called terminal operations. They produce a result from a pipeline such as a List, an Integer, or even void (any non-Stream type).
		//**intermediate ops can be merged and processed in a single same pass when terminal op is invoked.
		//Stateful operations are skip(), distinct(),limit() and sorted(). Rest all other stream operations are stateless.
		//When an operation requires retaining the information of the elements it has processed so far to process the current element then it is a stateful operation.
		//Example: Distinct operation requires keeping track of all the values it has processed so far, based on this information only it can decide whether the current value is unique or it has been processed before and accordingly either will add the current value to the new stream(which is the output of the distinct operation) or neglect the value and not add it to the new stream.
		//The Optional<T> class (java.util.Optional) is a container class to represent the existence or absence of a value.The Optional class contains several methods to test the existence of an element.Ex:ifPresent(Consumer). It returns Optional.empty if not found.
		//Java SE 8 introduces three primitive specialized stream interfaces to tackle this issue—IntStream, DoubleStream, and LongStream—that respectively specialize the elements of a stream to be int, double, and long.
		//*The most-common methods you will use to convert a stream to a specialized version are mapToInt, mapToDouble, and mapToLong. These methods work exactly like the method map that we saw earlier, but they return a specialized stream instead of a Stream<T>.
		//*We can also create streams from values, an array, or a file. Just use the static methods Stream.of(values) for values, Arrays.stream(array) for an array and Files.lines(Paths.get(“yourFile.txt”), Charset.defaultCharset()) for a file.
		//Stream.iterate(intial value,UnaryOperator<T>) and Stream.generate(Supplier<? extends T> s) — They let us create a stream from a function. However, because elements are calculated on demand, these two operations can produce elements “forever.” This is what we call an infinite stream: a stream that doesn’t have a fixed size, as a stream does when we create it from a fixed collection.
		//The JDK contains many terminal operations (such as average, sum, min, max, and count) that return one value by combining the contents of a stream. These operations are called reduction operations.
		//The JDK provides us with the general-purpose reduction operations reduce and collect.
		//The reduce operation always returns a new value. Suppose that you want to reduce the elements of a stream to a more complex object, such as a collection, If your reduce operation involves adding elements to a collection, then every time your accumulator function processes an element, it creates a new collection that includes the element, which is inefficient. It would be more efficient for you to update an existing collection instead->([initial empty],([],b)->[].add(b)). You can do this with the Stream.collect method.
		//Unlike the reduce method, which always creates a new value when it processes an element, the collect method modifies, or mutates, an existing value.
		//*The 1.collect(supplier,accumulator(BiConsumer),combiner(BiConsumer)) takes 3 arguments.  2.collect(Collectors obj) takes one arg of type Collectors.This class encapsulates the functions used as arguments in the collect operation.
		//1.supplier - a function that creates a new mutable result container, 2.accumulator - a function that must fold an element into a result stream, 3.combiner - a function that accepts two partial result containers and merges them, which must be compatible with the accumulator function.  The combiner function must fold the elements from the second result container into the first result container.
	    //<R> R collect(Supplier<R> supplier,BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner); 
		//*The Collectors class contains many useful reduction operations (static methods which return Collectors class instance), such as accumulating elements into collections and summarizing elements according to various criteria. **These reduction operations return instances of the class Collector, so you can use them as a parameter for the collect operation.
		//Collectors - toList(),groupingBy(),mapping(),reducing(),averagingInt()
		//Optional class - It is a container[a box] that either has something inside it or nothing.
		//Optionals - They are a better way to handle a situation where a method doesn't have a value to return when called. Instead of returning null,It returns an Optional. An Optional is like a box whether it contains a value or doesn't.
		//Mainly used for return types of methods - To explicitly tell the user that the value they are looking for might not exist and they have to account for that possibility and they have to deal with it.
		
		
		//Collectors.
//		List<Long> listLong = new ArrayList<Long>();
//		listLong.add(Long.valueOf(20));
//		List<? extends Number> listNumbers = new ArrayList<>(listLong);
//		System.out.println(listNumbers);
//		listNumbers.add(Integer.valueOf(1));
//		System.out.println(listNumbers);
//		System.out.println("Hello World");
		
		//Intermediate ops :
//I		//1.filter(Predicate): Takes a predicate (java.util.function.Predicate) as an argument and returns a stream including all elements that match the given predicate
//I		//2.peek(),distinct(): Returns a stream with unique elements (according to the implementation of equals for a stream element)
//I		//3.limit(n): Returns a stream that is no longer than the given size n
//I		//4.skip(n): Returns a stream with the first n number of elements discarded  
		//5.map(Stream<T>Function):Takes a function (java.util.function.Function) as an argument to project the elements of a stream into another form. The function is applied to each element, “mapping” it into a new element.To extract information from each element of a stream.
		//6.mapToInt,mapToDouble,mapToLong : Just like map but to convert a stream to a specialized version(primitive).They return a new stream of type IntStream or LongStream or DoubleStream(which is a stream that contains only Double values).
		//7.range(s,e) and rangeClosed(s,e) : static methods of primitive stream interfaces. range is exclusive, whereas rangeClosed is inclusive.
		//8.flatMap(Stream<Stream<T>>Function):Takes input as a stream of stream and it flattens the data to a single stream and it returns.
		//9.(1)sorted() - To get a normal stream sorted in ascending order
		//9.(2)sorted(Comparator.reverseOrder()) - To get a normal stream sorted in descending order
		//9.(3)sorted(Comparator.comparing(F)) - For custom sorting in ascending order
		//9.(4)sorted(Comparator.comparing(F).reversed()) - For custom sorting in descending order
		//9.(5)sorted(Map.Entry.comparingByKeyOrValue()) - For sorting map converted to set actually[map.entrySet()] in ascending order of keys or values as specified.
		//9.(6)sorted(Map.Entry.comparingByKeyOrValue(Comparator.reverseOrder())) - For sorting map in descending order.
		//9.(7)sorted(Map.Entry.comparingByKeyOrValue(Comparator.comparing(F))) - For custom sorting of map where key or values are again custom and we need to specify attribute inside it.
		//9.(8)sorted(Map.Entry.comparingByKeyOrValue(Comparator.comparing(F).reversed())) - For custom sorting of map in descending order.
		
		
		
		//flatMap Example:
		/*
		 * List<List<Integer>> listOfLists = Arrays.asList( Arrays.asList(1, 2, 3),
		 * Arrays.asList(4, 5), Arrays.asList(6, 7, 8) );
		 * 
		 * List<Integer> flattenedList = listOfLists.stream() .flatMap(list ->
		 * list.stream()) .toList();
		 * 
		 * // Prints [1, 2, 3, 4, 5, 6, 7, 8] System.out.println("Flattened list: " +
		 * flattenedList);
		 */
		
		
		List<Integer> l = new ArrayList<>();
		l.add(20);
		l.add(10);
		l.add(30);
		Collections.sort(l,Collections.reverseOrder());
		System.out.println(l);
		List<Integer> l2 = l.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println(l2);

//		NavigableSet<Integer> set = new TreeSet<>();
//		set.add(1);
//		set.add(2);
//		System.out.println(set);
//		//set=set.descendingSet();
//		Iterator<Integer> i = set.descendingIterator();
//		while(i.hasNext())
//			System.out.println(i.next());
		//System.out.println(set);
		
		
		//Collections.sort(list);
		
		
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		list.stream()
			.filter(x->x>3)
			.forEach(System.out::println);
		
		//Terminal ops :
		//1.forEach(Consumer) - An aggregate operation. It takes Consumer as an argument and performs action but returns void.
		//2.anyMatch(P), allMatch(P), and noneMatch(P) - They all take a predicate as an argument and return a boolean as the result.
		//3.findFirst(),findAny() - No arguments. For retrieving arbitrary elements from a stream. They return an Optional object [Optional<Product> obj= ]. They can be used in conjunction with other stream operations such as filter->  .ifPresent(System.out::println)
		//** collect is an operation (** gives mutable container unlike reduce which gives immutable container) that takes as an argument various recipes for accumulating the elements of a stream into a summary result. Here, toList() describes a recipe for converting a Stream into a List.
		//*4.(1)collect(Collectors.toList()) - To combine all elements in a Stream into a List.It takes one parameter of type Collector. This class encapsulates the functions used as arguments in the collect operation that requires three arguments (supplier, accumulator(BiConsumer), and combiner(BiConsumer) functions). This op accumulates the stream elements into a new instance of List. As with most operations in the Collectors class, the toList(static function of Collectors) operator returns an instance of Collector, not a collection.
		//5.reduce(initial value,BinaryOperator<T>(accumulator)) - The final result is stored and print using sysout().It repeatedly applies an operation (for example, adding two numbers) on each element until a result is produced. It’s often called a fold operation in functional programming because you can view this operation as “folding” repeatedly a long piece of paper (your stream) until it forms one little square, which is the result of the fold operation.
		//6.Reduction ops(Applied on primitive streams) that returns one value by combining the contents of a stream -> 1)sum()-returns int, 2)average()-returns OptionalDouble obj, 3)min(c),max(c)-returns OptionalInt and pass arg Comparator - mandatory  5)count()-returns long
		//4.(2)collect(Collectors.groupingBy(Function)) - The groupingBy operation returns a map whose keys are the values that result from applying the lambda expression specified as its parameter (which is called a classification function)  // Collector<T, ?, M> groupingBy(Function<? super T, ? extends K> classifier,Supplier<M> mapFactory,Collector<? super T, A, D> downstream)
		//4.(3)collect(Collectors.groupingBy(Function,Collectors.mapping(Function,Collectors.toList())))
		//4.(4)collect(Collectors.groupingBy(Function,Collectors.reducing(identity(0),mapper Function,operation Function)))
		//4.(5)collect(Collectors.groupingBy(Function,Collectors.averagingInt(Function)))
		//Below 3 static methods return Optional object when we re sending some object as param -
		//7.(1)Optional.ofNullable(obj) - It return obj if it exists orelse Optional.EMPTY gets returned
		//7.(2)Optional.of(obj) - If we are sure that obj is not empty.
		//7.(3)Optional.empty() - To create an empty Optional. (Optional.empty() == Optional.EMPTY) --> Optional<?> EMPTY = new Optional<>(null);
		
		//7.(4)Optionalobj.get() or Optionalobj.orElseThrow(Supplier)- It returns the value inside the Optional. But returns "No such element exception" if no element is present in the obj.
		//7.(5)Optionalobj.isPresent() - It resolves 7.(4) through a check-It returns true if value present or false if no value is present.
	 //*//7.(6)Optionalobj.orElse(default value) - If value present then it returns the value or else it returns the default value.
		//7.(7)Optionalobj.orElseGet(lambda exp)
		
		Optional<Integer> op=Optional.of(10);
		
		
//============================================================================================================		
		
		//1.collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
		//2.collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getAge)));
		//3.collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary)));
		//4.collect(Collectors.minBy(Comparator.comparing(Employee::getAge)));
		//5.collect(Collectors.groupingBy(Employee::getDepartment,Collectors.mapping(Employee::getName,Collectors.toList())));
		//6.DoubleSummaryStatistics s=employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
		//7.Map<Boolean,List<Employee>> emp = employeeList.stream().collect(Collectors.partitioningBy(e->e.getAge()>25));
		
		
//============================================================================================================
		
//		BinaryOperator<Integer> binary = new BinaryOperator<>() {
//			public Integer apply(Integer a,Integer b) {
//				return a+b;
//			}
//		};
//		Integer res=binary.apply(10,21);
//		System.out.println(res);
		
	
		
//		ProductStore products= new ProductStore();
//		List<Product> productlist = products.getProducts();
//		Comparator<Product> c = (p1,p2) -> p2.getPrice()-p1.getPrice();
//		Set<Product> set = new TreeSet<>(c);
//		set.add(productlist.get(0));
//		set.add(productlist.get(1));
//		set.add(productlist.get(10));			
//		System.out.println(set);
		
//		Map<String,List<String>> map=productlist.stream()
//				   .collect(Collectors.groupingBy(t->{
//					   System.out.println("Grouping By - "+t.getCategory()+" -> "+t);
//					   return t.getCategory();
//				   },Collectors.mapping(t->{
//					   System.out.println("\nMapping - "+t);
//					   return t.getName();
//				   }, Collectors.toList())));
//		System.out.println(map);
		
		
		
		
		
//		boolean anymatch=productlist.stream()
//				   .noneMatch(t->t.getCategory()=="abc");
//		System.out.println(anymatch);
				
	
//		System.out.println(productlist.stream()
//					.map(t->{
//						System.out.println("new price = "+t.getPrice());
//						return t.getPrice();
//					})
//					.reduce(0,(a,b)->{
//						System.out.println(a+"?"+b);
//						return Integer.max(a,b);   //whatever it returns that goes to 'a' and 'b' is next element in stream
//					}));
//				Averager averager= productlist.stream()
//				.map(t->{
//					System.out.println("new price = "+t.getPrice());
//					return t.getPrice();
//				})
//				.collect(()->{
//					System.out.println("creating new object");
//				return new Averager();}, (x,y)->{
//					System.out.println("Accumulator operation ");
//					x.accept(y);},(a,b)->{
//						System.out.println("Combiner operation");
//						a.combine(b);});
//				System.out.println(averager.average());
		
		
//		List<String> vowels = List.of("a", "e", "i", "o", "u");
//
//		// sequential stream - nothing to combine
//		StringBuilder result = vowels.parallelStream().collect(()->{
//			System.out.println("New Instance created");
//			return new StringBuilder();
//		}, (x, y) -> {
//			System.out.println("Accumulator operation - "+x.append(y));
//		},
//				(a, b) -> {
//					System.out.println("Combiner operation");
//				a.append(",").append(b);});
//		System.out.println(result.toString());
//		IntStream.range(1, 5)
//				.forEach(System.out::println);
				
					
					
//		List<Product> kidsProducts = new ArrayList<>();
//		for(Product product : productlist) {
//			if(product.getCategory().equals("kids"))
//				kidsProducts.add(product);
//		}
//		Collections.sort(kidsProducts,new Comparator<Product>() {
//			public int compare(Product p1,Product p2) {
//				return p2.getPrice().compareTo(p1.getPrice());
//		}
//		});
//		System.out.println(kidsProducts);
		
		
//		productlist.stream()
//				.filter(t->{
//					System.out.println("Processing filter "+t);
//				return t.getCategory().equals("Kids");
//				
//				})
//				.findAny()
//				.ifPresent(t->System.out.println("Hello"));
			
//				//.sorted(Comparator.comparing(Product::getPrice).reversed())
//				.map(t->{
//					System.out.println("Processing Map "+t);
//					return t.getName();
//			})
//				.limit(2)
//			//.collect(Collectors.groupingBy(Product::getCategory)));
//				.forEach(System.out::println);		
		
		
//		
//		
//						List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8);
//				Integer a =  numbers.stream()
//					   .filter( n-> {
//							   System.out.println("Processing "+n);
//							   if(n%1==0)
//								   return true;
//							   return false;
//					   })
//					   .sorted(Comparator.reverseOrder())
//						.mapToInt(n->{
//							System.out.println("Mapping "+n);
//							return n+1;
//						})
//						.sum();
//				System.out.println(a);
//				.forEach(System.out::println);
//						.map(n -> {
//		                    System.out.println("mapping " + n);
//		                    return n * n;
//		                  });
//						
		
						
	}

//	private static void forEach(Object object) {
//		// TODO Auto-generated method stub
//		
//	}
	

}























