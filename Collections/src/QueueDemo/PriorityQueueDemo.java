package QueueDemo;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PriorityQueueDemo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
//		Queue<Integer> pq = new PriorityQueue<>();
//		pq.offer(70);
//		System.out.println(pq);
//		pq.offer(20);
//		System.out.println(pq);
//		pq.offer(50);
//		System.out.println(pq);
//		pq.add(25);
//		System.out.println(pq);
//		System.out.println(pq.poll());
////		System.out.println(pq.poll());
////		System.out.println(pq.poll());
////		System.out.println(pq.poll());
////		//pq.peek();
////		//pq.element();
////		pq.poll();
//		System.out.println(pq);
//		System.out.println(pq.contains(25));
//		System.out.println(pq.size());
//		Object obj = pq.toArray();
//		System.out.println(obj);
		System.out.println("hello");
		ArrayBlockingQueue<String> animals = new ArrayBlockingQueue<>(2);

	       try {
	           //Add elements to animals
	           animals.put("Dog");
	           animals.put("Cat");
	           System.out.println("ArrayBlockingQueue: " + animals);
	           animals.put("monkey");
	           // Remove an element
	           String element = animals.take();
	           System.out.println("Removed Element: " + element);
	        }
	        catch(Exception e) {
	            System.out.println(e);
	        }
	}

}
