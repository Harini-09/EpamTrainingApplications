package com.epam.HomeTask1Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Utility{
	
	public void findSecondBiggestNumber() {
		List<Integer> numberslist = new ArrayList<>();
		numberslist.add(10);
		numberslist.add(20);
		numberslist.add(15);
		numberslist.add(12);
		numberslist.add(25);
		int bigNumber=Integer.MIN_VALUE;
		int secondBigNumber=bigNumber;
		for(Integer value:numberslist)
		{
			if(value>bigNumber)
			{
				secondBigNumber=bigNumber;
				bigNumber=value;
			}
		}
		System.out.println("1. The second biggest number in the given list is : "+secondBigNumber);
		
	}
	
	public void getNumbersInReverseOrder() {
		
			List<Integer> arraylist = new ArrayList<>();
			arraylist.add(50);
			arraylist.add(90);
			arraylist.add(10);
			arraylist.add(30);
			Collections.sort(arraylist,Comparator.reverseOrder());
			System.out.println("\n\n2. The list of numbers in reverse order are :"+arraylist);
	}
//	public static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
//        return Collections.reverseOrder();
//    }
//	 public static <T> Comparator<T> reverseOrder() {
//	        return (Comparator<T>) ReverseComparator.REVERSE_ORDER;
//	    }
	public void treeSetReverseSort() {
			Set<Integer> treeset = new TreeSet<>(Comparator.reverseOrder());
			treeset.add(50);
			treeset.add(10);
			treeset.add(60);
			treeset.add(30);
			treeset.add(40);
		
			System.out.println("\n\n4. The sorted order of the given numbers in reverse order : "+treeset);
	}
	
	public void treeSetNameSort()
	{
		Set<Employee> employeeset = new TreeSet<>(new NameComparator());
		employeeset.add(new Employee(101,"Vidhi"));
		employeeset.add(new Employee(102,"Aneesha"));
		employeeset.add(new Employee(103,"Chitra"));
		employeeset.add(new Employee(104,"Saavi"));
	
		System.out.println("\n\n5. The Employees in sorted order of their names are : "+employeeset);
	}
	
	public void sortMapOfNumbersByValue(TreeMap<Integer,Integer> mapOfNumbers) {
		SortMapOfNumbersByValue comparator1 = new SortMapOfNumbersByValue();
		Set<Map.Entry<Integer, Integer>> entrySetOfNumbers = mapOfNumbers.entrySet();
		List<Map.Entry<Integer, Integer>> listOfEntrySetOfNumbers = new ArrayList<>(entrySetOfNumbers);
		
		Collections.sort(listOfEntrySetOfNumbers,comparator1);
		System.out.println("\n\n6. Map of Numbers in Descending order by Value :" +listOfEntrySetOfNumbers);
	}
	
	public void sortMapOfEmployeeByNames(TreeMap<Integer,String> mapOfEmployeeNames) {
		SortMapOfEmployeesByValue comparator2 = new SortMapOfEmployeesByValue();
		Set<Map.Entry<Integer, String>> entrySetOfEmployees = mapOfEmployeeNames.entrySet();
		List<Map.Entry<Integer, String>> listOfEntrySetOfEmployees = new ArrayList<>(entrySetOfEmployees);
		
		Collections.sort(listOfEntrySetOfEmployees,comparator2);
		System.out.println("\n\n7. Map of employees in Descending order by Name :" +listOfEntrySetOfEmployees);
	}
	


}


