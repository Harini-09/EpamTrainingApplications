package CollectionsHomeTask2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solutions {

	List<Employee> employeeList = new ArrayList<Employee>(); //Like a variable created inside a class which is accessible to all of its methods within the class.
															//Memory is allocated for the variables at the time of obj creation for that particular class.

	public void addEmployeeDetails() {

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
	}

	public void calcuateMaleAndFemaleCount() {
		int females = 0, males = 0;
		for (Employee employee : employeeList) {
			if (employee.getGender() == "Female")
				females++;
			else
				males++;
		}
		System.out.println("1. No of male employees = " + males + ", No of female employees = " + females + "\n");
	}

	public void nameAllDepartments() {
		System.out.println("2. The names of all the departments in the organizations are :");
		for (Employee employee : employeeList) {
			System.out.print(employee.getDepartment() + ", ");
		}
		System.out.println("\n");
	}

	public void calculateMaleAndFemaleAverageAges() {
		int avgAgeFemale = 0, avgAgeMale = 0, females = 0, males = 0;
		for (Employee employee : employeeList) {
			if (employee.getGender() == "Female") {
				avgAgeFemale += employee.getAge();
				females += 1;
			} else {
				avgAgeMale += employee.getAge();
				males += 1;
			}
		}
		avgAgeFemale = avgAgeFemale / females;
		avgAgeMale = avgAgeMale / males;
		System.out.println("3. Average age of male employees = " + avgAgeMale + ", Average age of female employees = "
				+ avgAgeFemale + "\n");
	}

	public void getHighestPaidEmployeeDetails() {
		System.out.println("4. The details of the highest paid employee in the organization :");
		int highestPay = Integer.MIN_VALUE;
		int highPaidEmployeeDetails = 0;
		for (Employee employee : employeeList) {
			if (employee.getSalary() > highestPay)
				highPaidEmployeeDetails = employeeList.indexOf(employee);
		}
		System.out.println(employeeList.get(highPaidEmployeeDetails) + "\n");
	}

	public void getEmployeesJoinedAfter2015() {
		System.out.println("5. The names of all employees who have joined after 2015 :");
		for (Employee employee : employeeList) {
			if (employee.getYearOfJoining() > 2015)
				System.out.print(employee.getName() + ", ");
		}
		System.out.println("\n");
	}

	public void calculateEmployeesInEachDepartment() {
		System.out.println("6. The no of employees in each department :");
		Map<String, Integer> map = new HashMap<>();
		for (Employee employee : employeeList) {
			if (map.containsKey(employee.department))
				map.put(employee.getDepartment(), map.get(employee.getDepartment()) + 1);
			else
				map.put(employee.getDepartment(), 1);
		}
		System.out.println(map + "\n");
	}

	public void calculateEachDepartmentAverageSalary() {
		System.out.println("7. The average salary of each department :");
		Map<String, Double> map = new HashMap<>();
		for (Employee employee : employeeList) {
			if (map.containsKey(employee.getSalary()))
				map.put(employee.department, (map.get(employee.department) + (employee.salary)) / 2);
			else
				map.put(employee.department, employee.salary);
		}

		System.out.println(map);
	}

	public void getDetailsOfYoungestMaleEmplooyeeInProductDevelopment() {
		int minAge = Integer.MAX_VALUE;
		int youngestEmp = 0;
		for (Employee employee : employeeList) {
			if (employee.department == "Product Development" && employee.age < minAge) {
				minAge = employee.getAge();
				youngestEmp = employeeList.indexOf(employee);
			}
		}
		System.out.println("\n8. The youngest male employee in the product development departement is : "
				+ employeeList.get(youngestEmp).getName());
	}

	public void getHighestWorkingExperienceEmployee() {
		int mini = Integer.MAX_VALUE;
		int empl = 0;
		for (Employee employee : employeeList) {
			if (employee.getYearOfJoining() < mini) {
				mini = employee.getYearOfJoining();
				empl = employeeList.indexOf(employee);
			}
		}
		System.out.println("\n9. The employee with the most working experience in the organization is : "
				+ employeeList.get(empl).name);
	}

	public void calculateMaleAndFemaleCountInSalesAndMarketing() {
		int noOfFemales = 0;
		int noOfMales = 0;
		for (Employee employee : employeeList) {
			if (employee.department == "Sales And Marketing") {
				if (employee.gender == "Female")
					noOfMales++;
				else
					noOfFemales++;
			}
		}
		System.out.println("\n10. The no of male and female employees in the sales and the marketing are : Males="
				+ noOfMales + ", Females=" + noOfFemales);
	}

	public void calculateMaleAndFemaleAverageSalary() {
		int femalesSal = 0, malesSal = 0, noOfFemales = 0, noOfMales = 0;
		for (Employee employee : employeeList) {
			if (employee.gender == "Female") {
				femalesSal += employee.getSalary();
				noOfFemales += 1;
			} else {
				malesSal += employee.salary;
				noOfMales += 1;
			}
		}
		System.out.println("\n11. The average salary of male and female employees is : Male=" + malesSal / noOfMales
				+ ", Female=" + femalesSal / noOfFemales);
	}

	public void getEmployeesInEachDepartment() {
		System.out.println("\n12. The names of all employees in each department: ");
		Set<String> empset = new HashSet<>();
		for (Employee employee : employeeList)
			empset.add(employee.department);
		for (String department : empset) {
			System.out.print(department + " = {");
			for (Employee employee : employeeList) {
				if (department == employee.department)
					System.out.print(employee.name + ", ");
			}
			System.out.print("}\n");
		}
	}

	public void calculateTotalOrganizationAverageAndTotalSalary() {
		int sumOfSal = 0, noOfEmployees = 0;
		for (Employee employee : employeeList) {
			sumOfSal += employee.getSalary();
			noOfEmployees += 1;
		}
		System.out.println("\n13. The Average salary = " + sumOfSal / noOfEmployees + ", Total salary = " + sumOfSal);
	}

	public void getEmployeesSeperatingYoungerAndOlder() {
		List<String> youngEmployeesList = new ArrayList<>();
		List<String> oldEmployeesList = new ArrayList<>();
		for (Employee employee : employeeList) {
			if (employee.getAge() <= 25)
				youngEmployeesList.add(employee.getName());
			else
				oldEmployeesList.add(employee.name);
		}
		System.out.println("\n14. The employees who are younger or equal to 25 years = " + youngEmployeesList);
		System.out.println("    The employee who are older than 25 years = " + oldEmployeesList);
	}

	public void getDetailsOfOldestEmployee() {
		int maxAge = Integer.MIN_VALUE, oldEmp = 0;
		for (Employee employee : employeeList) {
			if (employee.getAge() > maxAge) {
				maxAge = employee.getAge();
				oldEmp = employeeList.indexOf(employee);
			}
		}
		System.out.println("\n15. The oldest employee in the organization is : Name = "
				+ employeeList.get(oldEmp).getName() + ", Age = " + employeeList.get(oldEmp).getAge()
				+ ", Departement = " + employeeList.get(oldEmp).getDepartment());
	}
}
