package QueueDemo;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

class ComparatorClass implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		if(o1>o2)
			return -1;
		else if(o1<o2)
			return 1;
		return 0;
	}
	
}
public class ArrayDequeDemo {

	//add,offer, remove,poll, element,peek
	
	public static void main(String[] args) {
		Queue<Integer> numbers = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1>o2)
					return -1;
				else if(o1<o2)
					return 1;
				return 0;
			}
			
		});
		numbers.offer(20);
		numbers.offer(100);
		numbers.offer(30);
		numbers.offer(150);
		
		Iterator<Integer> iterator = numbers.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
//		for(Integer number : numbers) {
//			System.out.println(number);
//		}
	}

}
