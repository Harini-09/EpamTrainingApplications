package QueueDemo;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class LinkedListDemo {
	public static void main(String[] args) {
		//offer,add,poll,remove,peek,element
		//arraylist - resizable arrays,get is easy(through index)
		//linkedlist - double linked list,modification(add,remove) is easy(through change of node connections)
		
		LinkedList<Integer> linkedlist = new LinkedList<>();
		linkedlist.offer(70);
		linkedlist.offer(20);
		linkedlist.offer(50);
		linkedlist.add(15);
		System.out.println(linkedlist.get(1));
		System.out.println(linkedlist.remove());
		System.out.println(linkedlist.poll());
		System.out.println(linkedlist);
		System.out.println(linkedlist.element());
	}

}
