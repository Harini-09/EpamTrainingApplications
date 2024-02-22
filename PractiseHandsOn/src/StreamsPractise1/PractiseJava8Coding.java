package StreamsPractise1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PractiseJava8Coding {
public static void main(String[] args) throws Exception {
//	int a =10;
//	int b=20;
//	IntBinaryOperator op = (x,y)->x+y;
//	System.out.println(op.applyAsInt(a, b));
	
/*	//1.Given a list of integers, find out all the even numbers that exist in the list using Stream functions?
	int[] list1 = {1,2,3,4,5,6};
	Arrays.stream(list1)     //gives primitive stream i.e, IntStream is returned here.
		  .filter(num->num%2==0)
		  .forEach(System.out::println);   */
	
/*	//2.Given a list of integers, find out all the numbers starting with 1 using Stream functions?
	Stream.of(13,32,19,14,53,65)
		  .map(n->n+" ")
		  .filter(s->s.startsWith("1"))
		  .forEach(System.out::println);    */

/*	//3. How to find duplicate elements in a given integers list in java using Stream functions?
	List<Integer> list3 = Arrays.asList(1,4,2,3,2,1,4,6);
	Set<Integer> set = new HashSet<>();
	list3.stream()
		 .filter(n->!set.add(n))
		 .forEach(System.out::println);     */
	
/*	//4.Given the list of integers, find the first element of the list using Stream functions?
	Stream.of(1,3,5,2,3)
		  .findFirst()
		  .ifPresent(System.out::println);   */	
	
/*	//5.Given a list of integers, find the total number of elements present in the list using Stream functions?
	Long count = List.of(1,4,3,23,43)
		.stream()
		.count();
	System.out.println(count);				*/
	
/*	//6. Given a list of integers, find the maximum value element present in it using Stream functions?
	Stream.iterate(5,n->n+1)
		  .limit(5)
		  .max(Integer::compareTo)
		  .ifPresent(System.out::println);	*/
	
	//7. Given a String, find the first non-repeated character in it using Stream functions? - see line 128
//some additional concepts:
/*	String s = "HelloH";
	Integer[] arr = {1,2,3,4};
	Vector<Integer> l = Arrays.stream(arr).collect(Collectors.toCollection(Stack::new));
	Object[] arr2 = l.toArray();
	System.out.println(Arrays.toString(arr2));
	
	String[] strings = {"abc","adsa","sdat"};
	String result = Arrays.stream(strings)
		  .collect(Collectors.joining(","));
	System.out.println(result);
	
	IntBinaryOperator bi = (a,b)->a-b;
	System.out.println(bi.applyAsInt(10, 4));
	
	Optional<Integer> r = Arrays.stream(arr)
			.collect(Collectors.maxBy(Integer::compare));
	System.out.println(r.orElse(0));
	

	Map<Integer, List<Integer>> map = Arrays.stream(arr)
		  .collect(Collectors.groupingBy(n->n,HashMap::new,Collectors.mapping(value->value+5,Collectors.toList())));
	System.out.println(map);
	
	Map<Boolean,List<Integer>> map4=Arrays.stream(arr)
		  .collect(Collectors.partitioningBy(n->n%2==0));
	System.out.println(map4);
	
	List<Integer> res = Arrays.stream(arr)
		  .collect(Collectors.mapping(Function.identity(), Collectors.toList()));
	System.out.println(res);
	
	Integer resu=Arrays.stream(arr)
		  .collect(Collectors.reducing(0,a->a+10,(a,b)->a+b));
	System.out.println(resu);

	DoubleSummaryStatistics st = Arrays.stream(arr)
		  .collect(Collectors.summarizingDouble(n->n));
	System.out.println(st.getAverage());
	
//	s.chars()
//	 .map(c->Character.toLowerCase(Character.valueOf((char)c)))
//	 .collect(Collectors.groupingBy())
//	 .
//	
//	Predicate p =  Predicate.isEqual("a");
//	Function f = Function.identity();
//	Function f2 = n->n;
//	System.out.println(f.apply(1));
//	System.out.println(f.apply(1));
//	System.out.println(p.test("a"));  */
	
/*	//7. Given a String, find the first non-repeated character in it using Stream functions?
	
	String str = "Heellvl";
	str.chars()
	   .mapToObj(s->Character.toLowerCase((char)s))
	   .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()));
	   .entrySet()
	   .stream()
	   .filter(entry->entry.getValue()==1)
	   .map(e->e.getKey())
	   .findFirst()
	   .ifPresent(System.out::println);		*/
	
/*	//8. Given a String, find the first repeated character in it using Stream functions?
	String str = "Heellooo";
	str.chars()
	   .mapToObj(c->Character.toLowerCase((char)c))
	   .collect(Collectors.groupingBy(Function.identity(),HashMap::new,Collectors.counting()))
	   .entrySet()
	   .stream()
	   .filter(entry->entry.getValue()>1)
	   .map(entry->entry.getKey())
	   .findFirst()
	   .ifPresent(System.out::println);     */
	
/*	//9. Given a list of integers, sort all the values present in it using Stream functions?
	List<Integer> l =Arrays.asList(9,3,5,2,6);
	l.stream()
	 .sorted()
	 .forEach(System.out::println);			*/
	 
	//10. Given a list of integers, sort all the values present in it in descending order using Stream functions?
/*	List<Integer> l =Arrays.asList(9,3,5,2,6);
	l.stream()
	 .sorted(Comparator.reverseOrder())
	 .forEach(System.out::println);			*/
			
/*	 //11. Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
	int[] arr = {4,3,6,2};  
	Set<Integer> set = Arrays.stream(arr)
			.mapToObj(n->n)
		  .collect(Collectors.toCollection(HashSet::new));
	if(set.size()==arr.length)
	System.out.println("false");
	else
		System.out.println("True");         */
	
/*	//14. Java 8 program to perform cube on list elements and filter numbers greater than 50.
	List.of(3,2,5,4).stream()
					.map(n->Math.pow(n, 3))
					.filter(n->n>50)
					.forEach(System.out::println);		*/
	
/*	//19. How to find only duplicate elements with its count from the String ArrayList in Java8?
	List<String> l = List.of("AA","BB","CC","AA","BB");
		Map<String,Long> map =	l.stream()
			 .filter(s->Collections.frequency(l, s)==2)
			 .distinct()
			 .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		System.out.println(map);						*/
	 
//	int[] a = {92,6,2,4,7,4,8,8};
//	Optional<Integer> o = Arrays.stream(a)
//		  .mapToObj(n->n)
//		  .collect(Collectors.maxBy(Comparator.naturalOrder()));
//	System.out.println(o.get());
//	
//	l.stream()
//	 .max(String::compareTo)
//	 .ifPresent(System.out::println);
//	 
//	Stream.iterate(5,n->n+1)
//	  .limit(5)
//	  .max(Integer::compare)
//	  .ifPresent(System.out::println);
//	
//	
//	Integer sum = Stream.iterate(1,n->n+1)
//	  .limit(2)
//	  .mapToInt(n->(Integer)n)
//	  .sum();
//	System.out.println(sum);
//	List<Integer> li = new ArrayList<>();
//	li.add(7);
//	li.add(9);
//	List<Integer> list1 = List.of(1,3,4);
//	List<Integer> list2 = Arrays.asList(2,5,8);
//	List<Integer> list3 = new ArrayList<>(li);
//	Stream.of(list1,list2,list3)
//		  .flatMap(lis->lis.stream())
//		  .forEach(System.out::print);
	
	int[] arr = {1,2,4};
	System.out.println(Arrays.toString(arr));
	List<Integer> list = new ArrayList<>();
	Queue<Integer> queue = new PriorityQueue<>();
	Map<Integer,String> map = new HashMap<>();
	map.put(1,"a");
	map.put(2,"b");
	map.put(3,"c");
	map.put(1, "d");
	System.out.println(map);   //Map<Integer,String>
	System.out.println(map.keySet());  //Set<Integer>
	System.out.println(map.values());  //Set<String>
	System.out.println(map.entrySet());  //Set<Map.Entry<Integer,String>>
	Set<Map.Entry<Integer,String>> entry = map.entrySet();
	for(Map.Entry<Integer,String> e : entry) {
		System.out.println(e.getKey()+","+e.getValue());
		e.setValue(e.getValue()+"e");
		System.out.println(e.getKey()+","+e.getValue());
	}
	
	Product product1 = new Product("Electronics", "Iphone", 48000, 2);
	Product product2 =new Product("Men's Fashion", "Titan Watch", 4800, 1);
	Product product3 = new Product("Electronics", "MacBook", 150000, 1);
	Product product4 = new Product("Kids", "Canvas Cap", 1200, 1);
	Product product5 = new Product("Electronics", "Sony Video Projector", 425000, 4);

	Comparator<Product> comparator =(p1,p2) -> p1.getPrice().compareTo(p2.getPrice());
		
	
	
	TreeMap<Integer,Product> map2 = new TreeMap<>();
	map2.put(1,product1);
	map2.put(4,product2);
	map2.put(2,product3);
	map2.put(5,product4);
	map2.put(3,product5);
//	System.out.println(map2);
//	Map<String,Optional<Product>> res = List.of(product1,product2,product3,product4,product5)
//		.stream()
//		.collect(Collectors.groupingBy(Product::getCategory,Collectors.mapping(Function.identity(),Collectors.minBy(comparator))));
//	System.out.println(res);
//
//	Comparator<Map.Entry<Integer,String>> cmp = new Comparator<Map.Entry<Integer,String>>() {
//		public int compare(Map.Entry<Integer,String> e1,Map.Entry<Integer,String> e2) {
//			if(e1.getValue().compareTo(e2.getValue())>1)
//				return 1;
//			else if(e1.getValue().compareTo(e2.getValue())<1)
//				return -1;
//			return 0;
//		}
//	};
//	
//	
//	TreeMap<Integer,String> map3 = new TreeMap<>();
//	map3.put(1,"d");
//	map3.put(2,"z");
//	map3.put(3,"c");
//	map3.put(4,"b");
	
	Set<Map.Entry<Integer,Product>> set = map2.entrySet();
	List<Map.Entry<Integer,Product>> newlist = new ArrayList<>(set);
//	Collections.sort(newlist,cmp.reversed());
//	System.out.println(newlist);
	System.out.println("====================================================================");
	set.stream()
	   .sorted(Map.Entry.comparingByValue(Comparator.comparing(Product::getGrade).reversed()))
	   .forEach(System.out::println);
	
	List<Integer> list1 = new ArrayList<>();
	list1.add(3);
	list1.add(4);
	list1.add(5);
	
//	R <R>collect(Supplier<R>,BiConsumer<R,? super T>,BiConsumer<R,R>)
	
	List<Integer> result5 = list1.stream()
		 .collect(ArrayList::new,(l,n)->l.add(n),(a,b)->a.addAll(b));
	System.out.println(result5);

	int[] res = list1.stream()
		 .collect(()->new int[1],(i,a)->i[0]+=a.intValue(),(a,b)->a[0]+=b[0]);
	System.out.println(res[0]);
	
	Integer sum=0;
	for(Integer i : list1) {
		sum+=i;
	}

	System.out.println(sum);
	@SuppressWarnings({ "removal", "deprecation" })
	Integer sum2 = list1.stream()
	.collect(() -> new Integer(0), (i,a) -> i += a.intValue(), (a1, a2) -> a1 += a2);
	System.out.println(sum2);
	int[] re = list1.stream()
		 .collect(()->new int[1],(add,a)->add[0]+=a.intValue(),(a,b)->a[0]+=b[0]);
	System.out.println(re[0]);
	
	
	   List<Integer> numbers = Arrays.asList(5, 10, 10, 20);
	   
	   System.out.println("hahaha");
	   numbers.stream()
	   		  .filter(Predicate.isEqual(Integer.valueOf(10)))
	   		  .forEach(System.out::println);
	  
	   numbers.stream()
	   		  .collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
       
       SumContainer sum10 = numbers.stream()
               .collect(SumContainer::new,SumContainer::accumulate,SumContainer::combine);
   Integer ans =     numbers.stream()
       		  .collect(Collectors.summingInt(Integer::intValue));
   System.out.println("ans= "+ans);
       
       System.out.println("Sum: " + sum10.getValue());
       
	  Integer resu = numbers.stream()
	   		  .collect(()->new Integer(0),(i,a)->i+=a.intValue(),(a,b)->a+=b);
	   System.out.println(resu);
	   
	   System.out.println("===============================================================");
	   
	    Product p1 = new Product("Electronics", "Iphone", 48000, 2);
		Product p2 =new Product("Men's Fashion", "Titan Watch", 4800, 1);
		Product p3 = new Product("Electronics", "MacBook", 150000, 1);
		Product p4 = new Product("Kids", "Canvas Cap", 1200, 1);
		Product p5 = new Product("Electronics", "Sony Video Projector", 425000, 4);
	   
	   Map<Integer,Product> productsMap = new TreeMap<>(Collections.reverseOrder());
	   productsMap.put(3, p1);
	   productsMap.put(2, p2);
	   productsMap.put(4, p3);
	   productsMap.put(5, p4);
	   productsMap.put(1, p5);
	   
//	   Comparator<Map.Entry<Integer, Product>> com = new Comparator<Map.Entry<Integer, Product>>(){
//		   public int compare(Map.Entry<Integer, Product> p1,Map.Entry<Integer, Product> p2) {
//			   return p1.getValue().getName().compareTo(p2.getValue().getName());
//		   }
//	   };
//	   
	  Set<Map.Entry<Integer, Product>> productsSet = productsMap.entrySet();
	  List<Map.Entry<Integer, Product>> productsList = new ArrayList<>(productsSet);
//	  Collections.sort(productsList,com.reversed());
//	   
//	   System.out.println(productsList);
	   
	  productsList.stream() 
	  			  .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
	  			  .forEach(System.out::println);
	   
		
//		Comparator<Product> com = new Comparator<Product>() {
//			public int compare(Product p1,Product p2) {
//				return p1.getCategory().compareTo(p2.getCategory());
//			}
//		};
//		
//		Map<Product,Integer> productsMap = new TreeMap<>(com.reversed());
//		   productsMap.put(p1,2);
//		   productsMap.put(p2,5);
//		   productsMap.put(p3,4);
//		   productsMap.put(p4,3);
//		   productsMap.put(p5,10);
	   
//	   System.out.println(productsMap);
	  
	  
	   
	   
	}
}


















class SumContainer{
	private int value;
	SumContainer(){
		this.value=0;
	}
	public int getValue() {
		return this.value;
	}
	public void accumulate(int a) {
		this.value+=a;
	}
	public void combine(SumContainer container) {
		this.value+=container.value;
	}
}
