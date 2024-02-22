package StreamAPIHandsOn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Function;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HandsOnEmployee {
//			1.	How many male and female employees are there in the organization?
//			2.	Print the name of all departments in the organization?
//			3.	What is the average age of male and female employees?
//			4.	Get the details of highest paid employee in the organization?
//			5.	Get the names of all employees who have joined after 2015?
//			6.	Count the number of employees in each department?
//			7.	What is the average salary of each department?
//			8.	Get the details of youngest male employee in the product development department?
//			9.	Who has the most working experience in the organization?
//			10.	How many male and female employees are there in the sales and marketing team?
//			11.	What is the average salary of male and female employees?
//			12.	List down the names of all employees in each department?
//			13.	What is the average salary and total salary of the whole organization?
//			14.	Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
//			15.	Who is the oldest employee in the organization? What is his age and which department he belongs to?

	
	
	public void calcuateMaleAndFemaleCount(List<Employee> employeeList) {
		
		Map<String,Long> map = employeeList.stream()
					.collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
		System.out.println(map);
	}

	public void nameAllDepartments(List<Employee> employeeList) {
		List<String> s = employeeList.stream()
					.map(e->e.getDepartment())
					.distinct()
					.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(s);
	}
	
	public void calculateMaleAndFemaleAverageAges(List<Employee> employeeList) {
		Map<String,Double> map2 = employeeList.stream()
					.collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getAge)));
	System.out.println(map2);
	}
	
	public void getHighestPaidEmployeeDetails(List<Employee> employeeList) {
		Optional<Employee> r=employeeList.stream()
					.max(Comparator.comparing(Employee::getSalary));
					
	}

	
	
	
	
//	public void calcuateMaleAndFemaleCount(List<Employee> employeeList) {
//		System.out.println("=================================1===================================");
//		Map<String,Long> map =employeeList.stream()
//					.collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
//		System.out.println(map);
//		System.out.println("=================================2===================================");
//	}
//	
//	public void nameAllDepartments(List<Employee> employeeList) {
//		employeeList.stream()
//					.map(Employee::getDepartment)
//					.distinct()
//					.forEach(System.out::println);
//		System.out.println("=================================3===================================");
//	}
//	
//	public void calculateMaleAndFemaleAverageAges(List<Employee> employeeList) {
//		 Map<String,Double> map2 = employeeList.stream()
//					.collect(Collectors.groupingBy(Employee::getGender,HashMap::new,Collectors.averagingDouble(Employee::getAge)));
//		 System.out.println(map2);
//		 System.out.println("=================================4===================================");
//	}
//	
//	public void getHighestPaidEmployeeDetails(List<Employee> employeeList) {
////		Comparator<Employee> comparator = new Comparator<Employee>() {
////			public int compare(Employee e1,Employee e2) {
////				if(e1.getSalary()>e2.getSalary())
////					return 1;
////				else if(e1.getSalary()<e2.getSalary())
////					return -1;
////				return 0;
////			}
////		};
////		employeeList.stream()
////					.max(comparator)
////					.ifPresent(System.out::print);
////		System.out.println("=================================5===================================");
//		
////		employeeList.stream()
////					.sorted(Comparator.comparing(Employee::getSalary).reversed())
////					.findFirst()
////					.ifPresent(System.out::println);
//		employeeList.stream()
//					.collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary)))
//					.ifPresent(System.out::println);
//		System.out.println("=================================5===================================");
//	}
//
//	public void getEmployeesJoinedAfter2015(List<Employee> employeeList) {
//		Set<Employee> set = employeeList.stream()
//					.filter(employee->employee.getYearOfJoining()>2015)
//					.collect(Collectors.toCollection(HashSet::new));
//		Iterator<Employee> i = set.iterator();
//		while(i.hasNext()) {
//			System.out.println(i.next());
//		}
//		System.out.println("=================================6===================================");
//	}
//
//	public void calculateEmployeesInEachDepartment(List<Employee> employeeList) {
//		Map<String,Long> map3 = employeeList.stream()
//					.collect(Collectors.groupingBy(Employee::getDepartment,HashMap::new,Collectors.counting()));
//		System.out.println(map3);
//		System.out.println("=================================7===================================");
//	}
//
//	public void calculateEachDepartmentAverageSalary(List<Employee> employeeList) {
//		Map<String,Double> map4 = employeeList.stream()
//					.collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));
//		System.out.println(map4);
//		System.out.println("=================================8===================================");
//	}
//
//	public void getDetailsOfYoungestMaleEmplooyeeInProductDevelopment(List<Employee> employeeList) {
//		employeeList.stream()
//					.filter(employee->employee.getGender().equals("Male")&&employee.getDepartment().equals("Product Development"))
//					.min(Comparator.comparing(Employee::getAge))
//					.ifPresent(System.out::println);
//		System.out.println("=================================9===================================");
//	}
//
//	public void getHighestWorkingExperienceEmployee(List<Employee> employeeList) {
//		employeeList.stream()
//					.min(Comparator.comparing(Employee::getYearOfJoining))
//					.ifPresent(System.out::println);
//		System.out.println("=================================10===================================");
//					
//	}
//
//	public void calculateMaleAndFemaleCountInSalesAndMarketing(List<Employee> employeeList) {
//		Map<String,Long> map5 = employeeList.stream()
//					.filter(employee->employee.getDepartment().equals("Sales And Marketing"))
//					.collect(Collectors.groupingBy(Employee::getGender,HashMap::new,Collectors.counting()));
//		System.out.println(map5);
//		System.out.println("=================================11===================================");
//	}
//
//	public void calculateMaleAndFemaleAverageSalary(List<Employee> employeeList) {
//		Map<String,Double> map6 = employeeList.stream()
//					.collect(Collectors.groupingBy(Employee::getGender,HashMap::new,Collectors.averagingDouble(Employee::getSalary)));
//		System.out.println(map6);
//		System.out.println("=================================12===================================");
//	}
//
//	public void getEmployeesInEachDepartment(List<Employee> employeeList) {
//		Map<String,List<String>> map7 = employeeList.stream()
//					.collect(Collectors.groupingBy(Employee::getDepartment,Collectors.mapping(Employee::getName, Collectors.toList())));
//		System.out.println(map7);
//		System.out.println("=================================13===================================");
//	}
//
//	public void calculateTotalOrganizationAverageAndTotalSalary(List<Employee> employeeList) {
//	DoubleSummaryStatistics result =employeeList.stream()
//					.collect(Collectors.summarizingDouble(Employee::getSalary));
//		System.out.println("Total Salary = "+result.getSum()+"\nAverage Salary = "+result.getAverage());
//		System.out.println("=================================14===================================");
//	}
//
//	public void getEmployeesSeperatingYoungerAndOlder(List<Employee> employeeList) {
//			Map<Boolean,List<String>> map8 = employeeList.stream()
//						.collect(Collectors.partitioningBy(employee->employee.getAge()<=25,Collectors.mapping(Employee::getName,Collectors.toList())));
//			System.out.println("Younger = "+map8.get(Boolean.TRUE));
//			System.out.println("Older = "+map8.get(Boolean.FALSE));
//			System.out.println("=================================15===================================");
//	}
//
//	public void getDetailsOfOldestEmployee(List<Employee> employeeList) {
//		employeeList.stream()
//					.max(Comparator.comparing(Employee::getAge))
//					.ifPresent(System.out::println);
//	}

	
	
	

	

	
	
	
//	public void calcuateMaleAndFemaleCount(List<Employee> employeeList) {
//		Map<String,Long> map = employeeList.stream()
//					.collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
//		System.out.println("1. "+map);
//		System.out.println("---------------------------------");
//	}
//	
//	
//	public void nameAllDepartments(List<Employee> employeeList) {
//		employeeList.stream()
//					.map(e->e.getDepartment())
//					.distinct()
//					.forEach(System.out::println);
//		System.out.println("---------------------------------");
//	}
//	
//	public void calculateMaleAndFemaleAverageAges(List<Employee> employeeList) {
//		Map<String,Double> map = employeeList.stream()
//					.collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getAge)));
//		System.out.println("3. "+map);
//		System.out.println("---------------------------------");
//	}
//	
//	public void getHighestPaidEmployeeDetails(List<Employee> employeeList) {
//		Employee emp = employeeList.stream()
//					.sorted(Comparator.comparing(Employee::getSalary).reversed())
//					.findFirst().get();
//		System.out.println(emp);
//		
//		Optional<Employee> e = employeeList.stream()
//					.collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary)));
//		System.out.println(e.get());
//		System.out.println("---------------------------------");
//					
//	}
//	
//	public void getEmployeesJoinedAfter2015(List<Employee> employeeList) {
//		employeeList.stream()
//					.filter(e->e.getYearOfJoining()>2015)
//					.map(e->e.getName())
//					.forEach(System.out::println);
//		System.out.println("---------------------------------");
//	}
//	
//	public void calculateEmployeesInEachDepartment(List<Employee> employeeList) {
//		Map<String,Long> map = employeeList.stream()
//					.collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
//		System.out.println(map);
//		System.out.println("---------------------------------");
//	}
//	
//	public void calculateEachDepartmentAverageSalary(List<Employee> employeeList) {
//		Map<String,Double> map = employeeList.stream()
//					.collect(Collectors.groupingBy(e->e.getDepartment(),Collectors.averagingDouble(Employee::getSalary)));
//	System.out.println(map);
//	System.out.println("---------------------------------");
//	}
//	
//	public void getDetailsOfYoungestMaleEmplooyeeInProductDevelopment(List<Employee> employeeList) {
//		employeeList.stream()
//					.filter(e->e.getGender().equalsIgnoreCase("Male"))
//					.sorted(Comparator.comparing(Employee::getAge))
//					.limit(1)
//					.forEach(System.out::println);
//		
//		Optional<Employee> emp = employeeList.stream()
//					.filter(e->e.getGender().equalsIgnoreCase("male"))
//					.collect(Collectors.minBy(Comparator.comparing(Employee::getAge)));
//		System.out.println(emp.get());
//		System.out.println("---------------------------------");
//	}
//	
//	public void getHighestWorkingExperienceEmployee(List<Employee> employeeList) {
//		Optional<Employee> e = employeeList.stream()
//					.collect(Collectors.minBy(Comparator.comparing(Employee::getYearOfJoining)));
//		System.out.println(e.get());
//		System.out.println("---------------------------------");
//	}
//	
//	public void calculateMaleAndFemaleCountInSalesAndMarketing(List<Employee> employeeList) {
//		Map<String,Long> map = employeeList.stream()
//					.filter(e->e.getDepartment().equalsIgnoreCase("Sales And Marketing"))
//					.collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
//		System.out.println(map);
//		System.out.println("---------------------------------");
//	}
//	
//	public void calculateMaleAndFemaleAverageSalary(List<Employee> employeeList) {
//		Map<String,Double> e = employeeList.stream()
//					.collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getSalary)));
//		System.out.println(e);
//		System.out.println("---------------------------------");
//	}
//	
//	public void getEmployeesInEachDepartment(List<Employee> employeeList) {
//		Map<String,List<String>> map = employeeList.stream()
//					.collect(Collectors.groupingBy(Employee::getDepartment,Collectors.mapping(Employee::getName,Collectors.toList())));
//	System.out.println(map);
//	System.out.println("-----------------------------------");
//	}
//	
//	public void calculateTotalOrganizationAverageAndTotalSalary(List<Employee> employeeList) {
//		DoubleSummaryStatistics s=employeeList.stream()
//					.collect(Collectors.summarizingDouble(Employee::getSalary));
//		System.out.println(s.getAverage());
//		System.out.println(s.getSum());
//	}
//		
//	
//	public void getEmployeesSeperatingYoungerAndOlder(List<Employee> employeeList) {
//		Map<Boolean,List<Employee>> emp = employeeList.stream()
//					.collect(Collectors.partitioningBy(e->e.getAge()>25));
//		System.out.println(emp.get(Boolean.FALSE));
//		System.out.println(emp.get(Boolean.TRUE));
//	}	

	
	//===============================================================================================================

	
//		public void calcuateMaleAndFemaleCount(List<Employee> employeeList) {
//			Map<String,Long> genderValues = employeeList.stream().collect(Collectors.groupingBy(employee ->employee.getGender(),Collectors.counting()));
//			System.out.println(genderValues);
//			System.out.println("-----------------------------------");
//		}
//			
//		public void nameAllDepartments(List<Employee> employeeList) {
//			employeeList.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);
//			System.out.println("-----------------------------------");
//		}
//
//		public void calculateMaleAndFemaleAverageAges(List<Employee> employeeList) {
//			Map<String, Double> avgAgeOfMaleAndFemaleEmployees = employeeList.stream()
//					.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
//
//			System.out.println(avgAgeOfMaleAndFemaleEmployees);
//			System.out.println("-----------------------------------");
//		}
//
//		public void getHighestPaidEmployeeDetails(List<Employee> employeeList) {
//			Optional<Employee> highestPaidEmployeeWrapper = employeeList.stream()
//					.collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
//			Employee highestPaidEmployee = highestPaidEmployeeWrapper.get();
//			System.out.println(highestPaidEmployee);
//			System.out.println("-----------------------------------");
//		}
//
//		public void getEmployeesJoinedAfter2015(List<Employee> employeeList) {
//			employeeList.stream().filter(e -> e.getYearOfJoining() > 2015).map(Employee::getName).forEach(System.out::println);
//			System.out.println("-----------------------------------");
//		}
//
//		public void calculateEmployeesInEachDepartment(List<Employee> employeeList) {
//			Map<String, Long> employeeCountByDepartment = employeeList.stream()
//					.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
//			System.out.println(employeeCountByDepartment);
//			System.out.println("-----------------------------------");
//		}
//
//		public void calculateEachDepartmentAverageSalary(List<Employee> employeeList) {
//			Map<String, Double> avgSalaryOfDepartments = employeeList.stream().collect(
//					Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
//			System.out.println(avgSalaryOfDepartments);
//			System.out.println("-----------------------------------");
//		}
//
//		public void getDetailsOfYoungestMaleEmplooyeeInProductDevelopment(List<Employee> employeeList) {
//			Employee youngestMaleEmployeeInProductDevelopment = employeeList.stream()
//					.filter(e -> e.getGender() == "Male" && e.getDepartment() == "Product Development")
//					.min(Comparator.comparingInt(Employee::getAge)).get();
//			System.out.println(youngestMaleEmployeeInProductDevelopment);
//			System.out.println("-----------------------------------");
//		}
//
//		public void getHighestWorkingExperienceEmployee(List<Employee> employeeList) {
//			Employee seniorMostEmployee = employeeList.stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining))
//					.findFirst().get();
//			System.out.println(seniorMostEmployee);
//			System.out.println("-----------------------------------");
//		}
//
//		public void calculateMaleAndFemaleCountInSalesAndMarketing(List<Employee> employeeList) {
//			Map<String, Long> countMaleFemaleEmployeesInSalesMarketing = employeeList.stream()
//					.filter(e -> e.getDepartment() == "Sales And Marketing")
//					.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
//			System.out.println(countMaleFemaleEmployeesInSalesMarketing);
//			System.out.println("-----------------------------------");
//
//		}
//
//		public void calculateMaleAndFemaleAverageSalary(List<Employee> employeeList) {
//			Map<String, Double> avgSalaryOfMaleAndFemaleEmployees = employeeList.stream()
//					.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
//			System.out.println(avgSalaryOfMaleAndFemaleEmployees);
//			System.out.println("-----------------------------------");
//		}
//
//		public void getEmployeesInEachDepartment(List<Employee> employeeList) {
//			Map<String, List<Employee>> employeeListByDepartment = employeeList.stream()
//					.collect(Collectors.groupingBy(Employee::getDepartment));
//			Set<Entry<String, List<Employee>>> entrySet = employeeListByDepartment.entrySet();
//			System.out.println(entrySet);
//			System.out.println("-----------------------------------");
//		}
//
//		public void calculateTotalOrganizationAverageAndTotalSalary(List<Employee> employeeList) {
//			DoubleSummaryStatistics employeeSalaryStatistics = employeeList.stream()
//					.collect(Collectors.summarizingDouble(Employee::getSalary));
//			System.out.println("Average Salary = " + employeeSalaryStatistics.getAverage());
//			System.out.println("Total Salary = " + employeeSalaryStatistics.getSum());
//			System.out.println("-----------------------------------");
//		}
//
//		public void getEmployeesSeperatingYoungerAndOlder(List<Employee> employeeList) {
//			Map<Boolean, List<Employee>> partitionEmployeesByAge = employeeList.stream()
//					.collect(Collectors.partitioningBy(e -> e.getAge() > 25));
//			System.out.println("Complete Partition Object :: " + partitionEmployeesByAge);
//			System.out.println("Employee who are younger than or equal to 25 :: " + partitionEmployeesByAge.get(Boolean.FALSE));
//			System.out.println("Employee who are older than 25 :: " + partitionEmployeesByAge.get(Boolean.TRUE));
//			System.out.println("-----------------------------------");
//		}

//		public void getDetailsOfOldestEmployee(List<Employee> employeeList) {
//			Employee oldestEmployee = employeeList.stream().max(Comparator.comparingInt(Employee::getAge)).get();
//	        System.out.println("Name : "+oldestEmployee.getName());
//			System.out.println("Age : "+oldestEmployee.getAge());
//			System.out.println("Department : "+oldestEmployee.getDepartment());
//			System.out.println("-----------------------------------");
//		}

	
	
	
	

	
	
	
	
	
	
}

























