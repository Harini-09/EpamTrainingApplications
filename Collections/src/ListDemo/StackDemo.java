package ListDemo;

import java.util.List;
import java.util.Stack;

public class StackDemo {
	public static void main(String[] args) {
		//5 methods [push,get,pop,search,empty]
		
		//It is fully recommended to use Deque to implement stack data structure - push,pop,peek
		
		Stack<String> stack = new Stack<>();
		stack.push("Annie");
		stack.push("Kartie");
		stack.push("Holmes");
		System.out.println(stack);
		
		System.out.println(stack.get(1));
		
		System.out.println(stack.pop());
		
		System.out.println(stack.peek());
		Stack<String> s = new Stack<>();
		s.push("James");
		s.push("Kristen");
		
		System.out.println(stack.equals(s));
		
		stack.addAll(s);
		System.out.println(stack);
		System.out.println(stack.search("Annie"));
		
		System.out.println(stack.empty());
		//print();
	}
	public static void print() {
		System.out.println("Hello");
	}

}
