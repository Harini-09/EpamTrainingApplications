package StreamsPractise1;

import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.io.Closeable;
import java.io.FilterOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;


//All FI's -> import java.util.function.  ;
//Method Reference note : If any method inside a map(F) found without input like getAge() for Employee class, By default
//it considers it as a static method in java8 streams.
//wonder** Also a non-static method with no args - Can be referred as a Consumer, with single arg - Can be referred as BiConsumer --> ClassName::method

//yet to be resolved as for java version 1.9 all these 3 are valid somehow  <- check for other versions in detail later
//(doubt)anonymous classes - <> cannot be used in RHS at all  but <Type> can be used in RHS as it is just like LHS
//(doubt)1.Comparator<String> c = new Comparator(){ public int compare(){}};  (crct)    2.Comparator<String> c = new Comparator<>(){public int compare(){}};   (wrong)		3.Comparator<String> c = new Comparator<String>(){ public int compare(){}};  (crct)

//Both Arrays.asList() and List.of() [java9] gives immutable lists (fixed size lists) for which (add,delete,update....) any of these operations cannot be performed.
//1.From any collection - collection.stream()
//2.From numbers - Stream.of(1,3,4,2)
//3.From array - Arrays.stream(arr)
//4.From string - let String s ="Hello", s.chars() - will return IntStream 

//If a derived class defines a static method with the same signature as a static method in the base class, the method in the derived class is hidden by the method in the base class. 
//Base b = new Derived(); b.method1();   <- It will call the method of Base class as Derived class's method1() is hidden by the Base class's method1().
//You cannot override the static method of the interface; you can just access them using the name of the interface. If you try to override a static method of an interface by defining a similar method in the implementing interface, it will be considered as another (static) method of the class.

//To get the current date and time using Java 8 Date and Time API - 
//System.out.println("Current Local Date: " + java.time.LocalDate.now());  -  Used LocalDate API to get the date
//System.out.println("Current Local Time: " + java.time.LocalTime.now());  -  Used LocalTime API to get the time
//System.out.println("Current Local Date and Time: " + java.time.LocalDateTime.now()); - Used LocalDateTime API to get both date and time


//Predicate Function Consumer Supplier   
public class PreDefinedFIs {
//	
//	public static void result(Predicate<Integer> predicate,int[] numbers) {
//		for(Integer i:numbers) {
//		if(predicate.test(i))
//			System.out.println(i);
//		}
//	}
	
//(1)====================================================================================
//	
	public static void main(String[] args) {
//		Predicate<Integer> predicate = new Predicate<>() {
//			public boolean test(Integer num) {
//				return num>0;
//			}
//		};
		
//	System.out.println(predicate.test(10));
		//Predicate Interface - test() [single abstract method - SAM]
//		Predicate<Integer> predicate = i ->  i < 10;
//			
//		System.out.println(predicate.test(12)); //autoboxing takes place internally
//		System.out.println(predicate.test(8));
//		System.out.println(predicate.test(new Integer(10)));

		
		//Predicate joining - 1.and  2.or  3.negate   <-Default methods of Predicate [returns Predicate instance/object]
		//3.isEqual - static method of Predicate [returns Predicate instance]

		//Example - and :
		//default Predicate<T> and(Predicate<? super T> other) {   
		//Objects.requireNonNull(other);
		//return (t) -> test(t) && other.test(t);     //It will send a method back see here return carefully
		//}

//		int[] numbers = {1,2,3,4,5,6,7,8,9,10}; 
//		Predicate<Integer> predicate1 = i -> i%2==0;
//		Predicate<Integer> predicate2 = i -> i>5;
//		result(predicate1.negate(),numbers);
	
//		Predicate predicate = Predicate.isEqual("Hello");   //static method
//		System.out.println(predicate.test("Hello"));
		
//========================================================================================		
	
//		//Function interface - apply()
//		Function<String,Integer> function = new Function<>() {   //generics(input and output) are required and imp
//			public Integer apply(String string) {
//				return string.length();
//			}
//		};
//		
//		System.out.println(function.apply("Hello"));
		
//		Function<String,Integer> function = string -> string.length();
//			
//		System.out.println(function.apply("Hello"));
//		
		//1.andThen - F1 will be applied and on result F2 will be applied,  2.compose-reverse  <-Default methods of Function
		//f1.andThen(f2).apply();
		//3.identity - always returns input  <-static method
		
//=========================================================================================		
		
		//Consumer Interface - accept()
		//andThen  <- default method
		
//==========================================================================================				
		
		//Supplier Interface - get()      <-no default and static methods
		
//		String[] words = {"hello","hi","welcome","good"};
//		Supplier<String> supplier = new Supplier<>() {
//			public String get() {
//				int x = (int) (Math.random() * 4);
//				return words[x];
//			}
//		};
//		System.out.println(supplier.get());
		
//(2)============================================================================================		
		//BiPredicate, BiFunction, BiConsumer <- Takes 2 args (2 functional Interfaces)
		
//		BiPredicate<Integer,Integer> bipredicate = (a,b) ->  a.equals(b);
//		System.out.println(bipredicate.test(12,12));
//		
//		BiFunction<Integer,Integer,Integer> bifunction = (a,b) -> a+b;
//		System.out.println(bifunction.apply(10, 12));
//				
//		BiConsumer<String,String> biconsumer = (s1,s2) -> System.out.println(s1+" "+s2);
//		biconsumer.accept("Hello", "World");

//(3)=============================================================================================
		//Primitive Type Functional Interfaces for - Predicate,Function,Consumer,Supplier
		//1.Functional Interfaces for Predicate - IntPredicate,LongPredicate,DoublePredicate - They take primitive inputs(instead of objects) and return boolean in the test()
		
//		IntPredicate intpredicate = new IntPredicate() {	//No need to mention generics
//			public boolean test(int n) {
//				return n==1;
//			}
//		};
//		System.out.println(intpredicate.test(1));
		
		//2.Functional Interfaces for Function - IntFunction,LongFunction,DoubleFunction - They take primitive inputs(instead of objects) and return any type.

		//3.Functional Interfaces for Consumer - IntConsumer,LongConsumer,DoubleConsumer - They take primitive as input.
		
		//4.***Supplier Functional Interfaces - IntSupplier -> public int getAsInt() ||  LongSupplier -> public long getAsLong(),   - They take primitive types
		//DoubleSupplier -> public double getAsDouble()  ||  BooleanSupplier -> public boolean getAsBoolean()
//		IntSupplier intsupplier = new IntSupplier() {
//			public int getAsInt() {
//				return Integer.MAX_VALUE;
//			}
//		};
//		System.out.println(intsupplier.getAsInt());
		
		
//(4)===============================================================================================
		//Unary Operator || Unary Operator Primitive type FI <- If input and output are of same type || child of Function Interface
		//Binary Operator || Binary Operator Primive type FI <- It is a child of BiFunction with all types same
		
//		UnaryOperator<Integer> unary = new UnaryOperator<>() {
//			public Integer apply(Integer n) {
//				return n*n;
//			}
//		};
//		System.out.println(unary.apply(6));
		
		//UnaryOperator for Primitive- IntUnaryOperator - applyAsInt(),  similarly for Double and Long
		//BinaryOperator for Primitive - IntBinaryOperator - applyAsInt(),............
//		IntBinaryOperator biop = new IntBinaryOperator() {
//			public int applyAsInt(int a,int b) {
//				return a+b;
//			}
//		};
//		System.out.println(biop.applyAsInt(1,2));
		
//==================================================================================================
		
		//Method References (::) -> static,instance,constructor  
		// A functional interface method can be mapped to a user defined method using :: operator
//		Consumer<Integer> consumer = (n) -> {
//			for(int i=0;i<n;i++)
//				System.out.println(i);
//		};
	
		
		
//		Consumer<Integer> consumer = new PreDefinedFIs()::work;
//		
//		consumer.accept(5);
		
		//constructor reference - It returns the Sample supplier i.e the Sample object
	
//		Create c = new Create() {
//			public Sample get(String s) {
//				return new Sample(s);
//			}
//		};
//		Sample s = c.get("Hello");
//		System.out.println(s.get());
//		s.sum(10,20);
		 
		//Create c = s->new Sample(s);
//		Create c = Sample::new;  //c gets the reference of Sample i.e, Sample object
//		Sample s = c.get("Hola");
		
		
//		System.out.println("Hello");   ->    forEach(System.out::println)
//		System - class present in java.lang package.
//      out - a static member variable which returns static reference (object) of PrintStream class.
//		println() - A method present in PrintStream class.
		
		//public final class System {
		//public static final PrintStream out = null;
		//public class PrintStream extends FilterOutputStream implements Appendable, Closeable{
		//public void println(Object x) {prints x on console;}
		
		
//		In map() if a method is having an argument, it will be considered as an instance method or else by default considered as a static method.
		
//=================================================================================================

	
	}	
		
		
	}

	
//	public void work(int n) {
//		for(int i=0;i<n;i++)
//			System.out.println(i);
//	}




	
	interface Create{
		public Sample get(String s);
	}

	 class Sample {
		private String s;
		
		Sample(){
			
		}
		
		Sample(String s){
			this.s=s;
		}
		public String get() {
			return s;
		}
		public void sum(int a,int b) {
		System.out.println(a+b);
		}
		
		
		//Method Reference (::) - Functional Interface methods can be mapped to user defined method by using :: (double colon) operator.
		
		Predicate<Integer> predicate1 = (x)->x%2==0;
		
		Predicate<Integer> predicate2 = new Sample("")::userPredicate;
		
		public boolean userPredicate(int x) {
			return x%2==0;
		}
		
		boolean result = predicate2.test(10);
		
		//--------------------------------------------------
		Create c = (s)->new Sample(s);
		Sample s1 = c.get("hellow");
		
		Create c1 = Sample::new;
		Sample s2 = c1.get("hello2");
		
		//--------------------------------------------------
		
		Supplier<Sample> supplier1 = ()->new Sample();
		Sample sample1 = supplier1.get();
		
		Supplier<Sample> supplier2 = Sample::new;
		Sample sample2 = supplier2.get();
				
	 }
	 
	 
	 class Hello{
		Hello(){
			
		}
	 }
