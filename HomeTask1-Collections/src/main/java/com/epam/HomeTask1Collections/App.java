package com.epam.HomeTask1Collections;
import java.util.*;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Utility utility = new Utility();
		List<Employee> employeelist = new ArrayList<>();
		employeelist.add(new Employee(101,"Vidhi"));
		employeelist.add(new Employee(102,"Aneesha"));
		employeelist.add(new Employee(103,"Chitra"));
		employeelist.add(new Employee(104,"Saavi"));
		
		//1. Find the 2nd biggest number in the given list of numbers
		utility.findSecondBiggestNumber();
		
		
		//2. Use Comparator interface to sort given list of numbers in reverse order
		utility.getNumbersInReverseOrder();
		
		
		//3. Use Comparator interface to sort given list of Employees in the alphabetical order of their name
		Comparator<Employee> c = new Comparator<Employee>()
		{
			public int compare(Employee emp1, Employee emp2)
			{
				return emp1.getName().compareTo(emp2.getName());
			}
		};
		Collections.sort(employeelist,c);
		System.out.println("\n\n3. The sorted list of Employees in the alphabetical order of their names is:");
		for(Employee emp : employeelist)
		{
			System.out.println(emp);
		}
		
		
		//4. Create a Tree Set that sorts the given set of numbers in reverse order
		utility.treeSetReverseSort();
		
		
		//5. Create a Tree Set that sorts the given set of  Employees in the alphabetical order of their names
		utility.treeSetNameSort();
		
		
		//6. Create a Tree Map that sorts the given set of values in descending order
		TreeMap<Integer,Integer> mapOfNumbers = new TreeMap<>();
		mapOfNumbers.put(1,101);
		mapOfNumbers.put(2,412);
		mapOfNumbers.put(3,201);
		mapOfNumbers.put(4,152);
		mapOfNumbers.put(5,621);
			
		utility.sortMapOfNumbersByValue(mapOfNumbers);
		
			
		//7. Create a TreeMap that sorts the given set of employees in descending order of their name
		TreeMap<Integer,String> mapOfEmployeeNames = new TreeMap<>();
		mapOfEmployeeNames.put(101,"Kriti");
		mapOfEmployeeNames.put(102,"Hasina");
		mapOfEmployeeNames.put(103,"Vidhya");
		mapOfEmployeeNames.put(104,"Adhya");
		mapOfEmployeeNames.put(105,"Nisha");
			
		utility.sortMapOfEmployeeByNames(mapOfEmployeeNames);
		
		
		//8. Use Collections.Sort to sort the given set of employees in descending order of their name
		Comparator<Employee> c2 = new Comparator<Employee>()
		{
			public int compare(Employee emp1, Employee emp2)
			{
				return emp1.getName().compareTo(emp2.getName());
			}
		};
		Collections.sort(employeelist,c2.reversed());
		System.out.println("\n\n8. The sorted list of employees in the descending order of their names is :");
		for(Employee emp : employeelist)
		{				
			System.out.println(emp);
		}
		
	}
		
}


