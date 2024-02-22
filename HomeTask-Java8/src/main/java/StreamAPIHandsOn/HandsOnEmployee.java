package StreamAPIHandsOn;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class HandsOnEmployee {
	
		public void calcuateMaleAndFemaleCount(List<Employee> employeeList) {
			Map<String,Long> genderValues = employeeList.stream().collect(Collectors.groupingBy(employee ->employee.getGender(),Collectors.counting()));
			System.out.println(genderValues);
			System.out.println("-----------------------------------");
		}
			
		public void nameAllDepartments(List<Employee> employeeList) {
			employeeList.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);
			System.out.println("-----------------------------------");
		}

		public void calculateMaleAndFemaleAverageAges(List<Employee> employeeList) {
			Map<String, Double> avgAgeOfMaleAndFemaleEmployees = employeeList.stream()
					.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));

			System.out.println(avgAgeOfMaleAndFemaleEmployees);
			System.out.println("-----------------------------------");
		}

		public void getHighestPaidEmployeeDetails(List<Employee> employeeList) {
			Optional<Employee> highestPaidEmployeeWrapper = employeeList.stream()
					.collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
			Employee highestPaidEmployee = highestPaidEmployeeWrapper.get();
			System.out.println(highestPaidEmployee);
			System.out.println("-----------------------------------");
		}

		public void getEmployeesJoinedAfter2015(List<Employee> employeeList) {
			employeeList.stream().filter(e -> e.getYearOfJoining() > 2015).map(Employee::getName).forEach(System.out::println);
			System.out.println("-----------------------------------");
		}

		public void calculateEmployeesInEachDepartment(List<Employee> employeeList) {
			Map<String, Long> employeeCountByDepartment = employeeList.stream()
					.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
			System.out.println(employeeCountByDepartment);
			System.out.println("-----------------------------------");
		}

		public void calculateEachDepartmentAverageSalary(List<Employee> employeeList) {
			Map<String, Double> avgSalaryOfDepartments = employeeList.stream().collect(
					Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
			System.out.println(avgSalaryOfDepartments);
			System.out.println("-----------------------------------");
		}

		public void getDetailsOfYoungestMaleEmplooyeeInProductDevelopment(List<Employee> employeeList) {
			Employee youngestMaleEmployeeInProductDevelopment = employeeList.stream()
					.filter(e -> e.getGender() == "Male" && e.getDepartment() == "Product Development")
					.min(Comparator.comparingInt(Employee::getAge)).get();
			System.out.println(youngestMaleEmployeeInProductDevelopment);
			System.out.println("-----------------------------------");
		}

		public void getHighestWorkingExperienceEmployee(List<Employee> employeeList) {
			Employee seniorMostEmployee = employeeList.stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining))
					.findFirst().get();
			System.out.println(seniorMostEmployee);
			System.out.println("-----------------------------------");
		}

		public void calculateMaleAndFemaleCountInSalesAndMarketing(List<Employee> employeeList) {
			Map<String, Long> countMaleFemaleEmployeesInSalesMarketing = employeeList.stream()
					.filter(e -> e.getDepartment() == "Sales And Marketing")
					.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
			System.out.println(countMaleFemaleEmployeesInSalesMarketing);
			System.out.println("-----------------------------------");

		}

		public void calculateMaleAndFemaleAverageSalary(List<Employee> employeeList) {
			Map<String, Double> avgSalaryOfMaleAndFemaleEmployees = employeeList.stream()
					.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
			System.out.println(avgSalaryOfMaleAndFemaleEmployees);
			System.out.println("-----------------------------------");
		}

		public void getEmployeesInEachDepartment(List<Employee> employeeList) {
			Map<String, List<Employee>> employeeListByDepartment = employeeList.stream()
					.collect(Collectors.groupingBy(Employee::getDepartment));
			Set<Entry<String, List<Employee>>> entrySet = employeeListByDepartment.entrySet();
			System.out.println(entrySet);
			System.out.println("-----------------------------------");
		}

		public void calculateTotalOrganizationAverageAndTotalSalary(List<Employee> employeeList) {
			DoubleSummaryStatistics employeeSalaryStatistics = employeeList.stream()
					.collect(Collectors.summarizingDouble(Employee::getSalary));
			System.out.println("Average Salary = " + employeeSalaryStatistics.getAverage());
			System.out.println("Total Salary = " + employeeSalaryStatistics.getSum());
			System.out.println("-----------------------------------");
		}

		public void getEmployeesSeperatingYoungerAndOlder(List<Employee> employeeList) {
			Map<Boolean, List<Employee>> partitionEmployeesByAge = employeeList.stream()
					.collect(Collectors.partitioningBy(e -> e.getAge() > 25));
			System.out.println("Complete Partition Object :: " + partitionEmployeesByAge);
			System.out.println("Employee who are younger than or equal to 25 :: " + partitionEmployeesByAge.get(Boolean.FALSE));
			System.out.println("Employee who are older than 25 :: " + partitionEmployeesByAge.get(Boolean.TRUE));
			System.out.println("-----------------------------------");
		}

		public void getDetailsOfOldestEmployee(List<Employee> employeeList) {
			Employee oldestEmployee = employeeList.stream().max(Comparator.comparingInt(Employee::getAge)).get();
	        System.out.println("Name : "+oldestEmployee.getName());
			System.out.println("Age : "+oldestEmployee.getAge());
			System.out.println("Department : "+oldestEmployee.getDepartment());
			System.out.println("-----------------------------------");
		}

}



