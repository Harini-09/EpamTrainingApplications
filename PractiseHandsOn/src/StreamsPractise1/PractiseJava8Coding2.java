package StreamsPractise1;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PractiseJava8Coding2 {

	public static void main(String[] args) {
		
		//1.Given a list of integers, find out all the even numbers that exist in the list using Stream functions?
		Stream.of(1,4,2,3,6,3)
			  .filter(n->n%2==0)
			  .forEach(System.out::println);
		System.out.println("------------------------------------------------------------------------");
		
		//2. Given a list of integers, find out all the numbers starting with 1 using Stream functions?
		Queue<String> q = Stream.of(1,3,14,76,2,12)
			  .map(n->n+"")
			  .filter(s->s.startsWith("1"))
			  .collect(Collectors.toCollection(PriorityQueue::new));
		System.out.println(q);
		System.out.println("------------------------------------------------------------------------");
		
		//3. How to find duplicate elements in a given integers list in java using Stream functions?
		Set<Integer> set = new HashSet<>();
		Stream.of(3,2,3,6,8,2,6,9,1)
			  .filter(n->!set.add(n))
			  .forEach(System.out::println);
		System.out.println("------------------------------------------------------------------------");
		
		//6. Given a list of integers, find the maximum value element present in it using Stream functions?
		Stream.of(2,11,4,2,5,6)
			  .max(Integer::compare)
			  .ifPresent(System.out::println);
		System.out.println("------------------------------------------------------------------------");
		
		
		//7. Given a String, find the first non-repeated character in it using Stream functions?
		String input = "Java articles are Awesome";
		Character c1 = input.chars()
			 .mapToObj(c->Character.toLowerCase(Character.valueOf((char) c)))
			 .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap<Character, Long>::new,Collectors.counting()))
			 .entrySet()
			 .stream()
			 .filter(e->e.getValue()==1L)
			 .findFirst()
			 .get()
			 .getKey();
		System.out.println(c1);
		System.out.println("------------------------------------------------------------------------");
		
		int in = 10;
		short sh=(short)in;
		
		//8. Given a String, find the first repeated character in it using Stream functions?
		String input2 = "Java articles are Awesome";
		Character result2 = input2.chars()
			  .mapToObj(c->Character.valueOf((char)c))
			  .map(Character::toLowerCase)
			  .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
			  .entrySet()
			  .stream()
			  .filter(es->es.getValue()>1)
			  .findFirst()
			  .get()
			  .getKey();
		System.out.println(result2);	
		System.out.println("------------------------------------------------------------------------");

		
		//11. Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
		int[] intArr = {2,1,5,6,7};
		Set<Integer> se = new HashSet<>();
		Boolean b = Arrays.stream(intArr)
			  .anyMatch(ele->se.add(ele)==false);
		System.out.println(b);	  
		System.out.println("------------------------------------------------------------------------");

		
		//19. How to find only duplicate elements with its count from the String ArrayList in Java8?
		List<String> list = List.of("AA","BB","CC","BB","CC","DD","BB");
		Map<String,Long> ma = list.stream()
			  .filter(s->Collections.frequency(list,s)>1)
			  .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		System.out.println(ma);	  
		System.out.println("------------------------------------------------------------------------");

		
		//22. Write a program to print the count of each character in a String?
		String s ="string data to count each character";
		Map<String, Long> map = Arrays.stream(s.split(""))
                .map(String::toLowerCase)
                .collect(Collectors
                .groupingBy(str -> str, 
                  LinkedHashMap::new, Collectors.counting()));
		System.out.println(map);
		System.out.println("------------------------------------------------------------------------");
		
		
		List<List<Character>> listOfLists = Arrays.asList(
		        Arrays.asList('a', 'b', 'c'),
		        Arrays.asList('d', 'e', 'f'),
		        Arrays.asList('g', 'h', 'i')
		);

		List<Character> flattenedList = listOfLists.stream()
		        .flatMap(stream->stream.stream())
		        .collect(Collectors.toList());

		System.out.println(flattenedList); // Output: [a, b, c, d, e, f, g, h, i]
	}

}
