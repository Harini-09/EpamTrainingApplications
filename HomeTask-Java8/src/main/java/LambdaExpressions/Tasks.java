package LambdaExpressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.OptionalInt;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Tasks {

	public void checkPalindrome() {
		PalindromeInterface check = (s) -> {
			StringBuilder sb = new StringBuilder(s);
			sb.reverse();
			String rev = sb.toString();
			if (s.equals(rev))
				return true;
			else
				return false;
		};

		System.out.println(check.checkPalindrome("abccba"));
		System.out.println("--------------------------------------------------");
	}

	public void findSecondBiggestNumber() {
		BiggestNumberInterface findNumber = () -> {
			List<Integer> numberslist = Arrays.asList(10, 30, 15, 20, 18);
			OptionalInt secondBigObject = numberslist.stream().skip(0).mapToInt(t -> t).reduce(Integer::max);
			return secondBigObject;
		};
		System.out.println(findNumber.findSecondBiggestNumber());
		System.out.println("--------------------------------------------------");
	}

	public void checkIfStringsAreRotations() {
		StringsRotationsInterface checkIfRotations = (str1, str2) -> (str1.length() == str2.length())
				&& ((str1 + str1).indexOf(str2) != -1);
		System.out.println(checkIfRotations.checkForRotation("abcd", "cdab"));
		System.out.println("--------------------------------------------------");
	}

	public void printNumbersUsingThread() {
		Thread thread = new Thread(() -> {
			int counter = 1;
			while (counter < 10) {
				System.out.println(counter + " ");
				counter += 1;
			}
		});
		System.out.println("New Thread:");
		thread.start();
	}

	public void sortInReverseOrder(List<Integer> numbers) {
		numbers.stream().sorted(Comparator.reverseOrder()).forEach(System.out::print);
		System.out.println("\n--------------------------------------------------");
	}

	public void sortEmployeesInAlphabeticalNameOrder(List<Employee> employee) {
		// Collections.sort(employee,(e1,e2)->e2.getName().compareTo(e1.getName()));
		List<Employee> sortedEmployees = employee.stream().sorted(Comparator.comparing(t -> t.getName()))
				.collect(Collectors.toList());
		System.out.println(sortedEmployees);
		System.out.println("--------------------------------------------------");
	}

	public void sortNumbersUsingTreeSetInReverseOrder() {
		ReverseSortInterface reversesort = (list) -> {
			TreeSet<Object> treeset = new TreeSet<>();
			treeset.addAll(list);
			TreeSet<Object> res = (TreeSet<Object>) treeset.descendingSet();
			System.out.println(res);
		};
		List<Object> list = Arrays.asList(1, 2, 3, 4, 5);
		reversesort.getReverseOrder(list);
		System.out.println("--------------------------------------------------");
	}

	public void sortEmployeesUsingTreeSet(List<Employee> employee) {
		TreeSet<Employee> treeset = new TreeSet<>((e1, e2) -> e2.getName().compareTo(e1.getName()));
		treeset.addAll(employee);
		System.out.println(treeset);
		System.out.println("--------------------------------------------------");
	}

	public void sortValuesUsingTreeMapInReverseOrder() {
		Map<String, Integer> studentmarks = new TreeMap<>();
		studentmarks.put("Anvi", 82);
		studentmarks.put("Vineela", 74);
		studentmarks.put("Rita", 98);
		studentmarks.put("Tina", 62);
		List<Entry<String, Integer>> studentslist = new ArrayList<>(studentmarks.entrySet());
		studentslist.stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEach(System.out::println);
		System.out.println("--------------------------------------------------");
	}

	public void sortEmployeesUsingTreeMap() {
		Map<Employee, Integer> employeegrade = new TreeMap<>((e1, e2) -> e2.getName().compareTo(e1.getName()));
		employeegrade.put(new Employee(101, "Anvi"), 82);
		employeegrade.put(new Employee(102, "Vineela"), 74);
		employeegrade.put(new Employee(103, "Rita"), 98);
		employeegrade.put(new Employee(104, "Tina"), 62);
		System.out.println(employeegrade);
		System.out.println("--------------------------------------------------");
	}

	public void sortEmployeesUsingComparator(List<Employee> employeelist) {
		Collections.sort(employeelist, (e1, e2) -> e2.getName().compareTo(e1.getName()));
		System.out.println(employeelist);
		System.out.println("--------------------------------------------------");
	}

}
