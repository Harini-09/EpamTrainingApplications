package refpoints;

import java.math.BigInteger;
import java.util.Scanner;


//3,4,15,20,53
/* 1. If we try to access Private variables or methods with obj outside the class, we get exception.
 * 2. We can't assign private to outer class and interface, but can assign an inner class private.
 * 3. we can override private methods but can't access from parent, & can be accessible only from the child.
 * 4. this and super cannot be used in static context.
 * 5. Why main() static? If it were a non-static method, JVM creates an object first then call main() method that will lead the problem of extra memory allocation.
 * 6. Class Loading is the process of storing the class-specific information in the memory. JVM executes the static block at “CLASS LOADING TIME”.
 * 7. import static ListDemo.StackDemo.print;  <- To directly access static members without class name and obj. Oresle we need to access classes with package name if not imported.
 * 8. The Enum in Java is a data type which contains a fixed set of constants. They are static and final by default. Can be accessed with the enum name(being static). Enum contains only private constuctors. enum is like an inner class of a class.
 * 9. In method overloading, methods differ by only parameters.
 * 10. To get arbitrary number of string as inputs in a method, we can define -> public void get(String... string){} . Similarly goes with any var-arg -> (int... values)
 * 11. If parent constuctor has parameters, then definitely child constructor need to pass parameters. void get(String... args) and void get(String a) are dif methods.
 * 12. In method overriding, the methods of subclass will be dominating the methods of super class. For below 1, activates parent and for below 2&3 cases, activates child method.
 * 13. If both parent and child have same variable declaration and initialization, then (1) Parent p = new Parent() - activates parent variable, (2) Child c = new Child() - activates child variable, (3) Parent p = new Child() - activates parent variable
 * 14. variables -> has-a relation & extends,implements -> is-a relation 
 * 15. In inheritance, we should have a default parent class constructor for sure.
 * 16. If a final method in parent is private, then we can create the same method in child with public modifier asa parent private method is hidden and child method is considered to be completely different.
 * 17. If we don't specify static for main(), we get run time exception but compiles successfully.
 * 18. We can have a try-finally block also. A try cannot be used without a catch or finally block(otherwise,compile error occurs) and this must be within a method.
 * 19. Unreachable catch block error (at compile time) - when superclass exception defined prior to subclass exception in the order of catch blocks.
 * 20. There can be only one finally clause with a single try block. When System.exit() method is invoked before executing finally block, then finally block will not be executed. System.exit() -> The exit() method of System class terminates the current Java virtual machine running on system. finally block overrides the value returned by try block.
 * 21. Throws keyword is used in case of checked exception only. There are 2 ways to handle exceptions - 1)try-catch block  2)throws keyword
 * 22. Exception doesnot define any methods. The methods of Throwable class are 1)String getMessage() 2)void printStackTrace()
 * 23. *double can store infinity value.
 * 
 */
public class First {
	public static void main(String args[]){
//		for(int i=1;i<args.length;i++){
//		  System.out.print(args[i]+" ");
//		}	
//		System.out.println("Hello");
//		First f= new First();
//		//f.print("");
//		System.out.println(f.print(null));
//		//String str=null;
//		//System.out.println(str);
//	}
		
//		enum Numbers{One,Two,Three}
//		Numbers n = Numbers.One;
//		int a=10;
//		public String print(String str) {
//			return str;
//			a+=1;
//			System.out.println(a);
//			final int A;
//			A=12;
//			int _a;
//			int $b;
//			return 2;
//		}
//		public static float print(String... strings) {
//			//System.out.println(strings[0]);
//			int[] a= {1,2,3};
//			int[] b = a;
//			return 1.1f;
//		}
//		int x=10;
//		int y=15;
//		String str="Hello";
//		System.out.println(str+x+y);
//		int b=4;
//		int c=4;
//		double a = 12/(b-c);
//		System.out.println(a);

//		float a=2.12f;
//		Float f = new Float(2.12f);
//		System.out.println(f);
//		
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter string");
		String s=sc.nextLine();
		//System.out.println(s);
		System.out.println("Enter string");
		sc.next();
		sc.nextLine();
		System.out.println("Enter string");
		String s1=sc.nextLine();
		System.out.println(s+" "+s1);
		
		System.out.println(power("147573952589676412928"));
	}
	public static int power(String A) {
		BigInteger num = new BigInteger(A);
		BigInteger quo = BigInteger.valueOf(2);
		while(num.intValue()>1) {
			if(num.intValue()%2!=0)
				return 0;
			num=num.divide(quo);
		}
	return 1;
	}	
	
	
}
