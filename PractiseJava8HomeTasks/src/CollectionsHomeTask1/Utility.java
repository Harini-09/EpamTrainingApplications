package CollectionsHomeTask1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Utility {
	List<Integer> numbers = Arrays.asList(60, 10, 50, 20, 40, 30);
	List<Employee> employees = EmployeesDB.getEmployeeDetails();

	public void findSecondBiggestNumber() {

	}

	public void sortInReverseOrder() {
		Collections.sort(numbers, Comparator.reverseOrder());
		System.out.println(numbers);
	}

	public void sortEmployeeNamesInAlphabeticalOrder() {
		Collections.sort(employees, new Comparator<Employee>() {
			public int compare(Employee e1, Employee e2) {
				return e1.getName().compareTo(e2.getName());
			}
		});
		for (Employee e : employees)
			System.out.println(e);
	}

	public void sortNumbersInReverseOrderWithTreeSet() {
		TreeSet<Integer> set = new TreeSet<>(numbers);
		NavigableSet<Integer> desSet = set.descendingSet(); // descendingSet method is available in TreeSet class and
															// returns a Navigable set.
		Iterator<Integer> i = desSet.iterator();
		while (i.hasNext())
			System.out.println(i.next());
	}

	public void sortEmployeesInAlphabeticalOrderWithTreeSet() {
		TreeSet<Employee> treeset = new TreeSet<>(new Comparator<Employee>() {
			public int compare(Employee e1, Employee e2) {
				return e1.getName().compareTo(e2.getName());
			}
		});
		treeset.addAll(employees);
		Iterator<Employee> i = treeset.iterator();
		while (i.hasNext())
			System.out.println(i.next());
		System.out.println("************************************************************");
	}

	public void sortInReverseOrderWithTreeMap() {

	}

	public void sortEmployeeNamesInDescendingOrderWithTreeMap() {
		TreeMap<Integer, Employee> treemap = new TreeMap<>();
		for (Employee e1 : employees)
			treemap.put(e1.getId(), e1);
		Set<Map.Entry<Integer, Employee>> empset = treemap.entrySet();
		List<Map.Entry<Integer, Employee>> arraylist = new ArrayList<>(empset);
		Collections.sort(arraylist, new Comparator<Map.Entry<Integer, Employee>>() {

			@Override
			public int compare(Entry<Integer, Employee> o1, Entry<Integer, Employee> o2) {
				// TODO Auto-generated method stub
				return o2.getValue().getName().compareTo(o1.getValue().getName());
			}

		});
		for (Map.Entry<Integer, Employee> employee : arraylist)
			System.out.println(employee.getValue());

		System.out.println("************************************************************");

//		TreeMap<Integer,Employee> treemap = new TreeMap<>();
//		for(Employee e1:employees)
//			treemap.put(e1.getId(),e1);
//		Set<Map.Entry<Integer, Employee>> empset = treemap.entrySet();

//		empset.stream()	
//			  .sorted(Map.Entry.comparingByValue(Comparator.comparing(e->e.getName())))
//			  .forEach(System.out::println);
	}

	public void sortEmployeeNamesInDescendingOrder() {
		Collections.sort(employees, new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				// TODO Auto-generated method stub
				return o2.getName().compareTo(o1.getName());
			}

		});
		for (Employee e : employees)
			System.out.println(e);
	}

}
