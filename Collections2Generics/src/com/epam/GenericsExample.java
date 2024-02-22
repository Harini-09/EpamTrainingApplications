package com.epam;

import java.util.ArrayList;
import java.util.List;



public class GenericsExample {
	public static void main(String arg[]) {
		Printer<Cat> printer = new Printer<>(new Cat());
		printer.print();
		
//		Printer<Double> doubleprinter = new Printer<>(13.3);
//		doubleprinter.print();
		
		ArrayList<Cat> cats=new ArrayList<>();
		cats.add(new Cat());
		
		Cat mycat=cats.get(0);
		
		shout("Hello",20);
		shout(12,"WE");
		
		List<Cat> catList = new ArrayList<>();
		catList.add(new Cat());
		
	
		
	}
	public static <T,V> T shout(T thingToShout,V otherthing) {
		System.out.println(thingToShout+"!!!!");
		System.out.println(otherthing+"!!!");
		return thingToShout;
	}
	
	public static void printList(List<? extends Animal> mylist)
	{
		System.out.println();
	}
	
	
}
