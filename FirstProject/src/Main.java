import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {

//	public static void main(String[] args) {

//		Scanner sc = new Scanner(System.in);
//		boolean a;
//		if(5>5) {
//			a=false;
//		}
//		System.out.println(a);
		
		
//	}
	
	public static void main(String[] args) {
		
		Map<Integer,Employee> employeeMap = new HashMap<>();
		employeeMap.put(1,new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeMap.put(3,new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeMap.put(2,new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

		
		Comparator<Map.Entry<Integer,Integer>> c = (set1,set2)->set2.getValue().compareTo(set1.getValue());
		
		List<Integer> list = Arrays.asList(15,12,30);
		
		list.stream().sorted().forEach(System.out::println);
		System.out.println("=======================1.=============================");
		
		list.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
		System.out.println("=======================2.=============================");
		
		employeeList.stream().sorted(Comparator.comparing(Employee::getId)).forEach(System.out::println);
		System.out.println("=======================3.=============================");
		
		employeeList.stream().sorted(Comparator.comparing(Employee::getId).reversed()).forEach(System.out::println);
		System.out.println("=======================4.=============================");
		
		Map<Integer,Integer> map = new HashMap<>();
		map.put(15, 100);
		map.put(12, 80);
		map.put(30, 130);
		Set<Map.Entry<Integer, Integer>> set = map.entrySet();
		set.stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
		
		System.out.println("=======================5.=============================");
		
		set.stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(System.out::println);
		
		System.out.println("=======================6.=============================");
		
		Set<Map.Entry<Integer, Employee>> empset = employeeMap.entrySet();
		
		empset.stream().sorted(Map.Entry.comparingByValue(Comparator.comparing(Employee::getAge).reversed())).forEach(System.out::println);
		
//		System.out.println(map);
//		NavigableMap<Integer, Integer> n = map.descendingMap();
//		System.out.println(n.entrySet());
//		
		
//		List<Integer> l = List.of(10,20,30);
//		l.add(50);
//		//Collections.reverse(l);
//		System.out.println(l);
		
//		System.out.println(map);
//		Set<Map.Entry<Integer,Integer>> s =map.entrySet();
//		List<Map.Entry<Integer,Integer>> list = new ArrayList<>(s);
//		Collections.sort(list,c);
//		System.out.println(list);
		
//		System.out.println(map);
//		NavigableMap<Integer, Integer> n = map.descendingMap();
//		System.out.println(n.entrySet());
		
		
		
		
		
		
		
		
		
		
//		
//        Map<String,Integer> map = new HashMap<>();
//       String str = "vikas epam vikas Vikas epam and to as vikas";
//       String[] arr = str.split(" ");
//       for(String s : arr){
//           if(map.containsKey(s)) {
//           map.put(s,(map.get(s))+1);
//       }
//           else {
//        	   map.put(s, 1);
//           }
//    }
//       System.out.println(map);
//       String result = map.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
//       System.out.println(result);
	}
}
