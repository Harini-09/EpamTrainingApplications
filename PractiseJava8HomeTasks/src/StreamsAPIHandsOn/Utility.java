package StreamsAPIHandsOn;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Utility {

	List<Employee> employees = EmployeeDB.getEmployeeDetails();

	public void calcuateMaleAndFemaleCount() {
//		Long count = employees.stream()
//				 .filter(t->t.getGender()=="Female")
//				 .count();
//		System.out.println(count);
//		
		Map<String, Long> map = employees.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		// System.out.println("Female = "+map.get("Female")+"Male = "+map.get("Male"));
		System.out.println(map);
		System.out.println("************************************************");
	}

	public void nameAllDepartments() {
		employees.stream().map(t -> t.getDepartment()).distinct().forEach(System.out::println);
		System.out.println("************************************************");
	}

	public void calculateMaleAndFemaleAverageAges() {
		Map<String, Double> map = employees.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getAge)));
		// System.out.println("Female = "+map.get("Female")+"Male = "+map.get("Male"));
		System.out.println(map);
		System.out.println("************************************************");
	}

	public void getHighestPaidEmployeeDetails() {
//		employees.stream()
//				 .sorted(Comparator.comparing(Employee::getSalary).reversed())
//				 .limit(1)
//				 .forEach(System.out::println);

		Optional<Employee> opt = employees.stream()
				.collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary)));
		System.out.println(opt.orElseThrow());
		System.out.println("************************************************");
	}

	public void getEmployeesJoinedAfter2015() {
		employees.stream().filter(t -> t.getYearOfJoining() > 2015).map(t -> t.getName()).forEach(System.out::println);
		System.out.println("************************************************");
	}

	public void calculateEmployeesInEachDepartment() {
		Map<String, Long> map = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		System.out.println(map);
		System.out.println("************************************************");
	}

	public void calculateEachDepartmentAverageSalary() {
		Map<String, Double> map = employees.stream().collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
		System.out.println(map);
		System.out.println("************************************************");
	}

	public void getDetailsOfYoungestMaleEmplooyeeInProductDevelopment() {
		Optional<Employee> emp = employees.stream()
				.filter(t -> t.getDepartment().equals("Product Development") && t.getGender() == "Male")
				.collect(Collectors.minBy(Comparator.comparing(Employee::getAge)));
		System.out.println(emp.get());
		System.out.println("************************************************");

	}

	public void getSeniorEmployee() {
		Employee emp = employees.stream().min(Comparator.comparing(Employee::getYearOfJoining)).get();
		System.out.println(emp);
		System.out.println("************************************************");
	}

	public void calculateMaleAndFemaleCountInSalesAndMarketing() {
		Map<String, Long> map = employees.stream().filter(t -> t.getDepartment().equals("Sales And Marketing"))
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		System.out.println(map);
		System.out.println("************************************************");
	}

	public void calculateMaleAndFemaleAverageSalary() {
		Map<String, Double> map = employees.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
		System.out.println(map);
		System.out.println("************************************************");

	}

	public void getEmployeesInEachDepartment() {
		Map<String, List<Employee>> map = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
		System.out.println(map);
		System.out.println("************************************************");

	}

	public void calculateTotalOrganizationAverageAndTotalSalary() {
//		Double d = employees.stream()
//				 .mapToDouble(t->t.getSalary())
//				 .average()
//				 .getAsDouble();

		DoubleSummaryStatistics d = employees.stream().map(t -> t.getSalary())
				.collect(Collectors.summarizingDouble(e -> e));
		System.out.println(d.getAverage());
		System.out.println(d.getSum());
		System.out.println("************************************************");

	}

	public void getEmployeesSeperatingYoungerAndOlder() {
		Map<Boolean, List<Employee>> map = employees.stream().collect(Collectors.partitioningBy(t -> t.getAge() > 25));
		System.out.println(map);
		System.out.println(map.get(false));
		System.out.println(map.get(true));
		System.out.println("************************************************");

	}

	public void getDetailsOfOldestEmployee() {
		Employee emp = employees.stream().max(Comparator.comparing(Employee::getAge)).get();
		System.out.println(emp);
		System.out.println("************************************************");

	}

}
