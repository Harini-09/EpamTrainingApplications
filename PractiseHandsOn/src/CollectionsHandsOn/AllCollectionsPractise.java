package CollectionsHandsOn;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class AllCollectionsPractise {

	public static void main(String[] args) {
//		Set<Integer> hashset = new HashSet<>();
//		hashset.add(100);
//		hashset.add(200);
//		Integer[] a = new Integer[2];
//		a[0]=10;
//		a[1]=20;
//		ArrayList<Integer> arraylist = new ArrayList<>();
//		arraylist.add(10);
//		arraylist.add(20);
//		arraylist.add(30);
//		arraylist.set(1, 40);
//		ArrayList<Integer> arraylist3=(ArrayList<Integer>) arraylist.clone();
//		List<Integer> arraylist2 = Arrays.asList(100,200);
//		arraylist.addAll(arraylist2);
//		//ArrayList<Integer> arraylist3=(ArrayList<Integer>)  arraylist.clone();
//		//ArrayList<Integer> cloneNumber = (ArrayList<Integer>)arraylist.clone();
//		System.out.println(arraylist3);
//		//arraylist.removeAll(arraylist2);
//		Iterator iterator=arraylist.iterator(); //iterator obj also holds some collection.All elements go into iterator and get stored.
//		while(iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}
//		System.out.println(arraylist.contains(10));
//		System.out.println(arraylist.size());
//		arraylist.sort(Comparator.reverseOrder());
////		System.out.println(arraylist);
//		//System.out.println(arraylist.get(1));
////		arraylist.add(0,50);
//		System.out.println(arraylist);
//		
//		Stack<Integer> stack = new Stack<>();
//		stack.push(10);
//		stack.push(20);
//		System.out.println(stack.peek());
//		System.out.println(stack.empty());
//		System.out.println(stack.search(10));
//		
//		LinkedList<Integer> list = new LinkedList<>();
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		ListIterator<Integer> l = list.listIterator();
//		while(l.hasNext()) {
//			System.out.println(l.next());
//		}
//		System.out.println();
//		while(l.hasPrevious()) {
//			System.out.println(l.previous());
//		}
//		Iterator iterator = list.descendingIterator();
//		while(iterator.hasNext())
//			System.out.println(iterator.next());
//	
//		BlockingQueue<Integer> bq=new ArrayBlockingQueue<>(3);
//		try {
//			bq.take();
//		bq.put(10);
//		bq.offer(20);
//		bq.offer(30);
//		//bq.put(10);
//		System.out.println(bq);
//		}
//		catch(Exception e) {
//            System.out.println(e);
//        }
		
//		Map<String,Integer> map = new HashMap<>();
//		map.put("Ajit", 80);
//		map.put("Vineeth", 92);
//		map.put("Nithin", 80);
//		map.put("Luhan", 80);
//
//		for(Map.Entry<String,Integer> e : map.entrySet()) {		//like PrintStream class inside System, we have Entry class inside Map
//			System.out.println(e);
//		}
		Product p = new Product("Electronics", "Iphone", 48000, 2);
		
		System.out.println(p.hashCode());
		
		
		
		
	}

}
