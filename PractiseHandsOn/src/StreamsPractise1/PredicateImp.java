package StreamsPractise1;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;

//public class PredicateImp{
//	public static void main(String[] args) {
//		PredicateImp pr = new PredicateImp();
//		System.out.println(pr.p.test(10));
//	}
//	Predicate<Integer> p = new Predicate<>() {
//		public boolean test(Integer n) {
//			return n>0;
//		}
//	};
//}



//public class PredicateImp implements Predicate<Integer>{
//	public static void main(String[] args) {
//		PredicateImp p = new PredicateImp();
//		System.out.println(p.test(-1));
//	}
//	@Override
//	public boolean test(Integer n) {
//		return n>0;
//	}
//	
//}



//public class PredicateImp{
//	public static void main(String[] args) {
//		Predicate<String> p = new Predicate<>() {
//			public boolean test(String str) {
//			return str.length()>1;
//			}
//		};
//		p.test("abc");
//	}


	
public class PredicateImp{
	public static void main(String[] args) {
		BiPredicate<String,Integer> p = (str,num) -> str.length()==num;
			
		System.out.println(p.test("hello",5));
		
		BinaryOperator<String> function = new BinaryOperator<String>() {
			@Override
			public String apply(String str1,String str2) {
				return str1+str2;
			}
		};
		System.out.println(function.apply("Harini","Shradha"));
		
		
		IntBinaryOperator i = new IntBinaryOperator() {
			@Override
			public int applyAsInt(int a,int b) {
				return a+b;
			}
		};
		System.out.println(i.applyAsInt(12, 14));
	}
	
	
	
}